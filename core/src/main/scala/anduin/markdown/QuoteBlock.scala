package anduin.markdown

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class QuoteBlock(
  content: String
) {
  def apply(): VdomElement = {
    QuoteBlock.component(this)
  }
}

object QuoteBlock {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[QuoteBlock, _]) {
    def render(props: QuoteBlock): VdomElement = {
      <.blockquote(
        Style.padding.hor24.padding.ver12.backgroundColor.warning1,
        Style.border.left.borderWidth.px4.borderColor.warning4,
        ^.dangerouslySetInnerHtml := props.content
      )
    }
  }

  private val component = ScalaComponent
    .builder[QuoteBlock](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
