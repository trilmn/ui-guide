// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.component

import japgolly.scalajs.react.extra.router.RouterCtl

import anduin.guide.Main
import anduin.style.Style
import NavElements.{link, li, ul}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Nav(
  ctl: RouterCtl[Main.Page],
  page: Main.Page
) {
  def apply(): VdomElement = Nav.component(this)
}

object Nav {

  type Props = Nav

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
          link("Components", Main.WIP()),
          ul(
            li(
              link("Button", Main.Button()),
              ul(
                li(link("Button Group", Main.WIP())),
                li(link("Button vs Link", Main.WIP()))
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
