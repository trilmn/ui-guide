package anduin.guide.component

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

final case class CodeBlock(
    content: String,
    language: String = "scala",
) {
  def apply(): VdomElement = {
    CodeBlock.component(this)
  }
}

object CodeBlock {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[CodeBlock, _]) {
    def render(props: CodeBlock): VdomElement = {
      val htmlContent = props.content
        .replace("/*>*/", "<span style=\"opacity: 0.3\">")
        .replace("/*<*/", "</span>")
      val content: TagMod = if (props.language == "scala") {
        ^.dangerouslySetInnerHtml := htmlContent
      } else htmlContent
      val cls = ^.cls := s"language-${props.language}"
      <.div(
        Style.backgroundColor.gray1.padding.ver12.padding.hor8,
        Style.overflow.auto,
        <.pre(
          Style.fontFamily.mono,
          ^.cls := "line-numbers",
          <.code(Style.display.block, cls, content)
        )
      )
    }
  }

  private val component = ScalaComponent
    .builder[CodeBlock](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
