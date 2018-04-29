package anduin.guide.page

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style
import anduin.guide.component.{Example => OrgExample}

final case class ExampleRich(
  content: (String, VdomElement)
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
        OrgExample(props.content)()
      )
    }
  }

  private val component = ScalaComponent
    .builder[ExampleRich](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
