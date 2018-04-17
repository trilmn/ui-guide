package anduin.mcro

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

import japgolly.scalajs.react.vdom.VdomElement

object Foo {
  type T = (String, VdomElement)

  def hello(element: VdomElement): T = macro helloImpl

  def helloImpl(c: blackbox.Context)(element: c.Expr[VdomElement]): c.Expr[T] = {
    import c.universe._

    val start = element.tree.pos.start
    val end = element.tree.pos.end
    val text = String valueOf element.tree.pos.source.content.slice(start, end)

    c.Expr[T](q"""($text, $element)""")
  }
}
