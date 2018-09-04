// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide

import anduin.guide.component.NavElements
import anduin.guide.component.NavElements.{li, link, ul}
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Pages._
// scalastyle:on underscore.import

final case class LayoutNav(
  ctl: Router.Ctl,
  page: Page
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
        li(link("Welcome", Welcome)),
        li(
          link("Style", Pages.Style()), // Name collision
          ul(
            li(
              link("Layout"),
              ul(
                li(link("Space", Space())),
                li(link("Flexbox", Flexbox()))
              )
            ),
            li(link("Color", Color())),
            li(link("Logo", Logo())),
            li(
              link("Typography", Typography()),
              ul(li(link("Fixed line height", FixedLineHeight())))
            )
          )
        ),
        li(
          link("Component", Component()),
          ul(
            li(
              link("Button", Button()),
              ul(
                li(link("ButtonStyle", ButtonStyle())),
                li(link("ButtonLink", ButtonLink()))
              )
            ),
            li(
              link("Container", WIP()),
              ul(
                li(link("Card", Card())),
                li(link("Collapse", Collapse())),
                li(link("Tab", Tab())),
                li(link("Table", Table())),
                li(link("Well", Well()))
              )
            ),
            li(
              link("Icon", Icon()),
              ul(
                li(link("Illus", Illus())),
                li(link("Illus Folder", IllusFolder()))
              )
            ),
            li(
              link("Input", WIP()),
              ul(
                li(link("Checkbox", Checkbox())),
                li(
                  link("Dropdown", WIP()),
                  ul(
                    li(link("Custom", WIP())),
                    li(link("Native", WIP()))
                  )
                ),
                li(link("Radio", WIP())),
                li(link("RadioBox", WIP())),
                li(link("Text", WIP()))
              )
            ),
            li(
              link("Portal", WIP()),
              ul(
                li(link("Popover", WIP())),
                li(link("Menu", WIP())),
                li(link("Modal", Modal()))
              )
            ),
            li(
              link("Text", WIP()),
              ul(
                li(link("Tag", WIP())),
                li(link("DateTime", WIP()))
              )
            )
          )
        ),
        li(
          link("Layout", WIP())
        ),
        li(
          link("Copy", WIP())
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
