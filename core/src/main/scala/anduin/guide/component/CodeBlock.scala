package anduin.guide.component

import scala.util.matching.Regex

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class CodeBlock(
    content: String
) {
  def apply(): VdomElement = {
    CodeBlock.component(this)
  }
}

object CodeBlock {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[CodeBlock, _]) {
    def render(props: CodeBlock): VdomElement = {
      val content = props.content
        .replace("/*>*/", "<span style=\"opacity: 0.3\">")
        .replace("/*<*/", "</span>")
      val mod = ^.dangerouslySetInnerHtml := content
      <.div(
        Style.backgroundColor.gray1.padding.ver12.padding.hor8,
        Style.overflow.auto,
        <.pre(
          Style.fontFamily.mono,
          ^.cls := "line-numbers",
          <.code(Style.display.block, ^.cls := "language-scala", mod)
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
