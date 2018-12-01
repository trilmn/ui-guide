package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Header(
  title: String,
  description: String = "",
  obj: Option[Object] = None
) {
  def apply(): VdomElement = Header.component(this)
}

object Header {

  private type Props = Header

  private def render(props: Props): VdomElement = {
    <.div(
      Style.border.bottom.borderWidth.px2.borderColor.gray2,
      <.h1(
        Style.lineHeight.px40.margin.bottom32,
        ^.fontSize := "40px",
        props.title
      ),
      props.obj.whenDefined(obj => {
        val name = obj.getClass.getName.replace("$", "")
        <.div(Style.margin.bottom32, CodeBlock(name)())
      }),
      TagMod.when(props.description.nonEmpty) {
        <.p(Style.margin.bottom32, props.description)
      }
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
