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
    .builder[RouterCtl[Guide.Page]]("Menu")
    .render_P { ctl =>
      def link(name: String, target: Guide.Page = Guide.WIP()) =
        if (target == Guide.WIP()) <.p(Style.color.gray6, name)
        else ctl.link(target)(Style.color.inherit, name)

      <.div(
        ^.padding := "64px 32px",
        Style.lineHeight.px40,
        ul(
          <.li(
            link("Style", Guide.Style()),
            ul(
              <.li(
                link("Layout"),
                ul(<.li(link("Space", Guide.StyleLayoutSpace())), <.li(link("Flexbox", Guide.StyleLayoutFlexbox())))
              ),
              <.li(link("Color", Guide.StyleColor())),
              <.li(link("Typography", Guide.StyleTypography()))
            )
          ),
          <.li(
            link("Components"),
            ul(<.li(
                 link("Button", Guide.Button()),
                 ul(<.li(link("Button Group")), <.li(link("Button vs Link", Guide.ButtonVsLink())))
               ),
               <.li(
                 link("Icon")
               ))
          )
        )
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def render(c: RouterCtl[Guide.Page], r: Resolution[Guide.Page]): VdomElement =
    <.div(
      Style.flexbox.flex.fontSize.px16.lineHeight.px24,
      <.div(^.width := "256px", navMenu(c)),
      <.div(^.width := "640px", Style.backgroundColor.white, ^.padding := "64px", r.render())
    )
}
