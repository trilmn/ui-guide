// source: https://github.com/japgolly/scalajs-react/blob/master/gh-pages-macros/src/main/scala/ghpages/GhPagesMacros.scala

package anduin.mcro

import java.util.regex.Pattern

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

import japgolly.scalajs.react.internal.MacroUtils

object SourceMacro {
  def exampleSource: String = macro SourceMacroImpls.exampleSource
}

object SourceMacroImpls {
  private val trimRight = "\\s+$".r

  private def blankLine(s: String) = s.trim.isEmpty

  @annotation.tailrec
  private def trimLeftAll(ls: Stream[String]): Stream[String] =
    if (ls.nonEmpty && ls.forall(_.headOption forall Character.isWhitespace))
      trimLeftAll(ls.map(s => if (s.isEmpty) s else s.tail))
    else
      ls

  private val exampleStart = "EXAMPLE:START"
  private val exampleEnd = "EXAMPLE:END"
}

class SourceMacroImpls(val c: Context) extends MacroUtils {
  import c.universe._

  import SourceMacroImpls._

  def splitOnce(marker: String)(s: String): (String, String) = {
    val r = s"""\n[ \t]*//[ \t]*${Pattern quote marker}[ \t]*""".r
    val x = r.split(s)
    if (x.length < 2)
      fail(s"Marker not found: // $marker")
    else if (x.length > 2)
      fail(s"Duplicate marker found: // $marker")
    (x(0), x(1))

  }

  def betweenMarkers(s: String, a: String, b: String): String = {
    val tmp = splitOnce(a)(s)._2
    splitOnce(b)(tmp)._1
  }

  def exampleSource: c.Expr[String] = {

    val fileContent = String valueOf c.enclosingPosition.source.content
    val egContent = betweenMarkers(fileContent, exampleStart, exampleEnd)

    val lines =
      egContent
        .split('\n')
        .toStream
        .map(trimRight.replaceFirstIn(_, ""))
        .dropWhile(blankLine)
        .reverse
        .dropWhile(blankLine)
        .reverse

    var output = trimLeftAll(lines) mkString "\n"
    output = fileContent

    c.Expr[String](Literal(Constant(output)))
  }
}
