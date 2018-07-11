package anduin.guide.page

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

final case class ExampleSimple(
  label: String = "",
  isBgGray: Boolean = false
) {
  def apply(children: VdomNode*): VdomElement = {
    ExampleSimple.component(this)(children: _*)
  }
}

object ExampleSimple {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[ExampleSimple, _]) {
    def render(props: ExampleSimple, children: PropsChildren): VdomElement = {
      val styles = Style.backgroundColor.gray1.padding.all4.fontSize.px13
      val example = <.div(
        Style.padding.all16,
        if (props.isBgGray) Style.backgroundColor.gray2
        else Style.backgroundColor.white,
        children
      )
      val label = TagMod.when(!props.label.isEmpty) {
        <.figcaption(Style.padding.hor16.padding.top4, Markdown(props.label)())
      }
      <.div(Style.padding.ver12, <.figure(styles, example, label))
    }
  }

  private val component = ScalaComponent
    .builder[ExampleSimple](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
