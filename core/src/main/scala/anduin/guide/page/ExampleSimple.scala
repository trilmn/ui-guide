package anduin.guide.page

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class ExampleSimple(
  label: String = ""
) {
  def apply(children: VdomNode*): VdomElement = {
    ExampleSimple.component(this)(children: _*)
  }
}

object ExampleSimple {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[ExampleSimple, _]) {
    def render(props: ExampleSimple, children: PropsChildren): VdomElement = {
      val styles = Style.backgroundColor.gray1.padding.all4.fontSize.px14
      val example = <.div(Style.backgroundColor.white.padding.all16, children)
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
