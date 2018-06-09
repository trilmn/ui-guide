// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide

import japgolly.scalajs.react.extra.router.RouterCtl

import anduin.guide.component.NavElements
import anduin.guide.component.NavElements.{li, link, ul}
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class LayoutNav(
  ctl: RouterCtl[Main.Page],
  page: Main.Page
) {
  def apply(): VdomElement = LayoutNav.component(this)
}

object LayoutNav {

  type Props = LayoutNav

  private def render(props: Props) = {
    implicit val implicitProps: NavElements.Props = NavElements.Props(props.ctl, props.page)
    <.div(
      ^.padding := "64px 0",
      Style.lineHeight.px40,
      ul(
        li(link("Welcome", Main.Welcome)),
        li(
          link("Style", Main.Style()),
          ul(
            li(
              link("Layout"),
              ul(
                li(link("Space", Main.Space())),
                li(link("Flexbox", Main.Flexbox()))
              )
            ),
            li(link("Color", Main.Color())),
            li(
              link("Typography", Main.Typography()),
              ul(li(link("Fixed line height", Main.FixedLineHeight())))
            )
          )
        ),
        li(
          link("Component", Main.WIP()),
          ul(
            li(
              link("Button", Main.Button()),
              ul(
                li(link("ButtonStyle", Main.ButtonStyle())),
                li(link("ButtonLink", Main.ButtonLink()))
              )
            ),
            li(
              link("Icon", Main.Icon())
            ),
            li(
              link("Portal", Main.WIP()),
              ul(
                li(link("Tooltip", Main.Tooltip())),
                li(link("Popover", Main.WIP())),
                li(link("Menu", Main.WIP())),
                li(link("Modal", Main.WIP()))
              )
            ),
            li(
              link("Container", Main.WIP()),
              ul(
                li(link("Table", Main.WIP()))
              )
            )
          )
        )
      )
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
