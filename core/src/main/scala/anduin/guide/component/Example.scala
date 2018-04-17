package anduin.guide.component

import anduin.component.util.ComponentUtils
import anduin.style.Style
import anduin.mcro.SourceMacro

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Example() {
  def apply(children: VdomNode*): VdomElement = {
    Example.component(this)(children: _*)
  }
}

object Example {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[Example, _]) {
    def render(children: PropsChildren): VdomElement = {
      val source = SourceMacro.exampleSource
      println(source)

      // EXAMPLE:START
      val node = <.div("Hello", children)
      // EXAMPLE:END
      node
    }
  }

  private val component = ScalaComponent
  .builder[Example](ComponentName)
  .stateless
  .renderBackendWithChildren[Backend]
  .build
}

