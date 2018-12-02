package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class QuoteBlock(
  content: String
) {
  def apply(): VdomElement = QuoteBlock.component(this)
}

object QuoteBlock {

  private type Props = QuoteBlock

  private def getColors(key: String) = key match {
    case "primary" => Style.backgroundColor.primary1.borderColor.primary4
    case "success" => Style.backgroundColor.success1.borderColor.success4
    case "warning" => Style.backgroundColor.warning1.borderColor.warning4
    case "danger"  => Style.backgroundColor.danger1.borderColor.danger4
    case _         => Style.backgroundColor.gray1.borderColor.gray4
  }

  private def render(props: QuoteBlock): VdomElement = {
    val parts = props.content.split("::")
    val (styles: TagMod, content) = if (parts.isDefinedAt(1)) {
      // well
      val key = parts(1)
      (getColors(key), props.content.replace(s"::$key::", ""))
    } else {
      // block quote
      (Style.fontStyle.italic.borderColor.gray4, props.content)
    }
    <.blockquote(
      styles,
      Style.padding.hor24.padding.ver16.border.left.borderWidth.px4,
      ^.dangerouslySetInnerHtml := content
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
