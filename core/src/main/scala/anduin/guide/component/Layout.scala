package anduin.guide.component

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main._
import anduin.style.Style

object Layout {
  private val navMenu = ScalaComponent
    .builder[RouterCtl[Page]]("Menu")
    .render_P { ctl =>
      def nav(name: String, target: Page) = <.li(ctl.link(target)(name))
      <.ul(Style.listing.list.padding.all32,
           nav("Home", Welcome),
           nav("Button", Button))
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def render(c: RouterCtl[Page], r: Resolution[Page]): VdomElement =
    <.div(
      Style.flexbox.flex,
      <.div(^.width := "256px", navMenu(c)),
      <.div(^.width := "768px",
            Style.padding.all32.backgroundColor.white,
            r.render())
    )
}
