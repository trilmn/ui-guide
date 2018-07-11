package anduin.guide.component

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

final case class Example(
  content: (String, VdomElement),
  isBgGray: Boolean = false
) {
  def apply(): VdomElement = {
    Example.component(this)
  }
}

object Example {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[Example, _]) {
    def render(props: Example): VdomElement = {
      val (source, element) = props.content
      <.div(
        Style.backgroundColor.gray1.padding.all4,
        <.div(
          Style.padding.all16,
          // Sometimes we need a gray background
          if (props.isBgGray) Style.backgroundColor.gray2
          else Style.backgroundColor.white,
          // Ensure the example is shown in correct font size
          // and line height (since these values in Guide is
          // bigger than in the actual app
          Style.fontSize.px13,
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
