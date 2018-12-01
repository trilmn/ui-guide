package anduin.mcro

import japgolly.scalajs.react.vdom.VdomElement

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object Source {

  // @TODO: find out how to convert this to case class
  type AntType = (String, VdomElement)

  // source: https://github.com/japgolly/scalajs-react/blob/master/
  //         gh-pages-macros/src/main/scala/ghpages/GhPagesMacros.scala
  @annotation.tailrec
  private def trimLeftAll(listStr: Array[String]): Array[String] =
    if (listStr.nonEmpty && listStr.forall(_.headOption forall Character.isWhitespace))
      trimLeftAll(listStr.map(str => if (str.isEmpty) str else str.tail))
    else
      listStr

  private val antEndRegex: String = " +}"

  // - below is a magic number to get the previous line into account
  // - the reason it is magical is because the macro result is different
  //   when the root node needs "{" or not
  // - also we can't use pos.point or pos.column here because they refer to
  //   the position of the root computation, not the range we want
  private val pad = 20

  def antImpl(c: blackbox.Context)(element: c.Expr[VdomElement]): c.Expr[AntType] = {

    import c.universe.Quasiquote

    // get original source
    val pos = element.tree.pos
    val inputSourceArr = pos.source.content.slice(pos.start - pad, pos.end)
    val inputSource = String valueOf inputSourceArr

    // format the source (trim indentation)
    var lines = inputSource.split("\n")
    if (lines.head.contains("annotate")) { lines = lines.drop(1) }
    if (lines.last.matches(antEndRegex)) { lines = lines.dropRight(1) }
    val outputSource = trimLeftAll(lines).mkString("\n")

    c.Expr[AntType](q"""($outputSource, $element)""")
  }

  def annotate(element: VdomElement): AntType = macro antImpl

  // ===
  // ===
  // ===

  private val headingRx = "(#+) (.*)".r

  def tocImpl(c: blackbox.Context): c.Expr[List[(Int, String)]] = {
    import c.universe.Quasiquote

    val source = String valueOf c.enclosingPosition.source.content

    val matches = headingRx.findAllIn(source).matchData.toList

    val result: List[(Int, String)] = matches.map(m => {
      val level = m.subgroups.head.length
      val text = m.subgroups.last
      (level, text)
    })

    c.Expr[List[(Int, String)]](q"""$result""")
  }

  def getTocHeadings: List[(Int, String)] = macro tocImpl
}
