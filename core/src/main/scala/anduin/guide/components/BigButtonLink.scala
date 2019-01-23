package anduin.guide.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.style.Style

final case class BigButtonLink(
  ctl: Pages.Ctl,
  page: Pages.Page,
  label: String
) {
  def apply(): VdomElement = BigButtonLink.component(this)
}

object BigButtonLink {

  private type Props = BigButtonLink

  private def render(props: Props): VdomElement = {
    <.div(
      Style.fontFamily.sans.padding.ver12,
      Button(
        tpe = Button.Tpe.Link(props.ctl.urlFor(props.page).value),
        style = Button.Style.Full(color = Button.Color.Blue, height = Button.Height.Fix40)
      )(
        <.span(Style.margin.right12, props.label),
        Icon(name = Icon.Glyph.ArrowRight)()
      )
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
