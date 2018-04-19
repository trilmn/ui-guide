package anduin.guide.component

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

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
      val (source, element) = props.content
      <.div(
        Style.backgroundColor.gray1.padding.all4,
        <.div(Style.backgroundColor.white.padding.all16, element),
        <.div(CodeBlock(content = source)())
      )
    }
  }

  private val component = ScalaComponent
    .builder[Example](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
