package anduin.guide.components

import anduin.guide.components.{Example => OrgExample}
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class ExampleRich(
  content: (String, VdomElement),
  isBgGray: Boolean = false
) {
  def apply(): VdomElement = {
    ExampleRich.component(this)
  }
}

object ExampleRich {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[ExampleRich, _]) {
    def render(props: ExampleRich): VdomElement = {
      <.figure(
        Style.padding.ver12,
        // original one
        OrgExample(props.content, props.isBgGray)()
      )
    }
  }

  private val component = ScalaComponent
    .builder[ExampleRich](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
