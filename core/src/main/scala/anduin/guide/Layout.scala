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
          <.li(link("Welcome", Main.Welcome)),
          <.li(
            link("Style", Main.Style()),
            ul(
              <.li(
                link("Layout"),
                ul(
                  <.li(link("Space", Main.Space())),
                  <.li(link("Flexbox", Main.Flexbox()))
                )
              ),
              <.li(
                link("Color", Main.Color())
              ),
              <.li(
                link("Typography", Main.Typography()),
                ul(
                  <.li(link("Why fixed line height", Main.FixedLineHeight()))
                )
              )
            )
          ),
          <.li(
            link("Components", Main.WIP()),
            ul(
              <.li(
                link("Button", Main.Button()),
                ul(
                  <.li(link("Button Group", Main.WIP())),
                  <.li(link("Button vs Link", Main.WIP()))
                )
              ),
              <.li(
                link("Icon", Main.Icon())
              ),
              <.li(
                link("Portal", Main.WIP()),
                ul(
                  <.li(link("Tooltip", Main.Tooltip())),
                  <.li(link("Popover", Main.WIP())),
                  <.li(link("Menu", Main.WIP())),
                  <.li(link("Modal", Main.WIP()))
                )
              ),
              <.li(
                link("Container", Main.WIP()),
                ul(
                  <.li(link("Table", Main.WIP()))
                )
              )
            )
          )
        )
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def render(c: RouterCtl[Main.Page], r: Resolution[Main.Page]): VdomElement =
    <.div(
      Style.fontSize.px16.lineHeight.px24,
      ^.paddingLeft := "288px",
      <.div(
        ^.width := "288px",
        Style.position.fixed.coordinate.left0.coordinate.top0,
        Style.height.pc100.overflow.auto,
        navMenu(c)
      ),
      <.div(
        ^.width := "768px",
        Style.backgroundColor.white,
        ^.padding := "64px 96px",
        r.render()
      )
    )
}
