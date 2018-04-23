package anduin.guide

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style
import anduin.example.{Example => OrgExample}

final case class Example(
  content: (String, VdomElement)
) {
  def apply(): VdomElement = {
    Example.component(this)
  }
}

object Example {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[Example, _]) {
    def render(props: Example): VdomElement = {
      <.div(
        Style.padding.ver12,
        // original one
        OrgExample(props.content)()
      )
    }
  }

  private val component = ScalaComponent
    .builder[Example](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
