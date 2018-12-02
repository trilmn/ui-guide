package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class ExampleSimple(
  label: String = "",
  isBgGray: Boolean = false
) {
  def apply(children: VdomNode*): VdomElement = {
    ExampleSimple.component(this)(children: _*)
  }
}

object ExampleSimple {

  private type Props = ExampleSimple

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val styles = TagMod(
      Style.backgroundColor.gray1.padding.all4,
      Style.fontSize.px13.lineHeight.px20
    )
    val example = <.div(
      Style.padding.all16,
      if (props.isBgGray) Style.backgroundColor.gray2
      else Style.backgroundColor.white,
      children
    )
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
