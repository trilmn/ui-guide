package anduin.guide

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

object Layout {

  private def ul(content: TagMod*) =
    <.ul(Style.listing.list.padding.left16, content.toTagMod)

  private val navMenu = ScalaComponent
    .builder[RouterCtl[Main.Page]]("Menu")
    .render_P { ctl =>
      def link(name: String, target: Main.Page = Main.WIP()) =
        if (target == Main.WIP()) <.p(Style.color.gray6, name)
        else ctl.link(target)(Style.color.inherit, name)

      <.div(
        ^.padding := "64px 32px",
        Style.lineHeight.px40,
        ul(
          <.li(
            link("Style", Main.Style()),
            ul(
              <.li(
                link("Layout"),
                ul(<.li(link("Space", Main.Space())), <.li(link("Flexbox", Main.Flexbox())))
              ),
              <.li(link("Color", Main.Color())),
              <.li(
                link("Typography", Main.Typography()),
                ul(<.li(link("Why fixed line height", Main.FixedLineHeight())))
              )
            )
          ),
          <.li(
            link("Components", Main.Component()),
            ul(<.li(
                 link("Button", Main.Button()),
                 ul(
                   <.li(link("Button Group", Main.ButtonGroup())),
                   <.li(link("Button vs Link", Main.ButtonVsLink())))
               ),
               <.li(link("Icon", Main.Icon())))
          )
        )
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def render(c: RouterCtl[Main.Page], r: Resolution[Main.Page]): VdomElement =
    <.div(
      Style.flexbox.flex.fontSize.px16.lineHeight.px24,
      <.div(^.width := "320px", navMenu(c)),
      <.div(^.width := "640px", Style.backgroundColor.white, ^.padding := "64px", r.render())
    )
}
