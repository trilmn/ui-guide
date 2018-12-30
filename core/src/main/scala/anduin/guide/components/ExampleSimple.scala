package anduin.guide.components

import anduin.guide.components.ExampleSimple.BgColor
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class ExampleSimple(
  label: String = "",
  bgColor: ExampleSimple.BgColor = BgColor.White
) {
  def apply(children: VdomNode*): VdomElement = {
    ExampleSimple.component(this)(children: _*)
  }
}

object ExampleSimple {

  private type Props = ExampleSimple

  sealed abstract class BgColor(val inner: TagMod, val outer: TagMod)
  object BgColor {
    object White extends BgColor(Style.backgroundColor.white, Style.backgroundColor.gray1)
    object Gray2 extends BgColor(Style.backgroundColor.gray2, Style.backgroundColor.gray1)
    object Gray8 extends BgColor(Style.backgroundColor.gray8, Style.backgroundColor.gray9)
  }

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val styles = TagMod(
      props.bgColor.outer,
      Style.padding.all4,
      Style.fontSize.px13.lineHeight.px20.fontFamily.sans
    )
    val example = <.div(Style.padding.all16, props.bgColor.inner, children)
    val label = TagMod.when(!props.label.isEmpty) {
      <.figcaption(Style.padding.hor16.padding.top4, Markdown(props.label)())
    }
    <.div(Style.padding.ver16, <.figure(styles, example, label))
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
