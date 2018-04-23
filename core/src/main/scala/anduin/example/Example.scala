package anduin.example

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.markdown.CodeBlock
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
        <.div(
          Style.backgroundColor.white.padding.all16,
          // Ensure the example is shown in correct font size
          // and line height (since these values in Guide is
          // bigger than in the actual app
          Style.fontSize.px14,
          // @TODO: Make this a valid Style.lineHeight
          ^.lineHeight := "20px",
          element
        ),
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
