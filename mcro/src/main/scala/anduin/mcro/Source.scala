package anduin.mcro

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

import japgolly.scalajs.react.vdom.VdomElement

object Source {

  // @TODO: find out how to convert this to case class
  type AntType = (String, VdomElement)

  // source: https://github.com/japgolly/scalajs-react/blob/master/
  //         gh-pages-macros/src/main/scala/ghpages/GhPagesMacros.scala
  @annotation.tailrec
  private def trimLeftAll(listStr: Array[String]): Array[String] =
    if (listStr.nonEmpty && listStr.forall(
          _.headOption forall Character.isWhitespace))
      trimLeftAll(listStr.map(str => if (str.isEmpty) str else str.tail))
    else
      listStr

  def antImpl(c: blackbox.Context)(
      element: c.Expr[VdomElement]): c.Expr[AntType] = {

    import c.universe.Quasiquote

    // get original source
    val pos = element.tree.pos
    // don't use pos.start here since we want to capture the whole line
    val start = pos.point - pos.column
    val raw = String valueOf pos.source.content.slice(start, pos.end)

    // format the source (trim indentation)
    val lines = raw.split('\n')
    val source = trimLeftAll(lines) mkString "\n"

    c.Expr[AntType](q"""($source, $element)""")
  }

  def annotate(element: VdomElement): AntType = macro antImpl
}
