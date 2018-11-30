package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class CodeBlock(
  content: String,
  language: String = "scala",
) {
  def apply(): VdomElement = CodeBlock.component(this)
}

object CodeBlock {

  private def trimFirstCollapse(content: String): String = {
    val cond = content.startsWith("/*>*/\n")
    if (cond) content.replaceFirst("\n", "") else content
  }

  private case class Backend(scope: BackendScope[CodeBlock, _]) {
    def render(props: CodeBlock): VdomElement = {
      val htmlContent = trimFirstCollapse(props.content)
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
    .builder[CodeBlock](this.getClass.getSimpleName)
    .stateless
    .renderBackend[Backend]
    .build
}
