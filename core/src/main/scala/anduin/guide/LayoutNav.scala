// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide

import anduin.guide.component.NavElements
import anduin.guide.component.NavElements.{li, Title, ul}
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

  private type Props = LayoutNav

  private def render(props: Props) = {

    implicit val implicitProps: NavElements.Props =
      NavElements.Props(props.ctl, props.page)

    <.div(
      TagMod(^.paddingTop := "64px", ^.paddingBottom := "64px"),
      Style.lineHeight.px40,
      ul(
        li(
          Title("Welcome", Some(Welcome))
        ),
        li(
          Title("Brand", isExpanded = Some(_.isInstanceOf[BrandT])),
          ul(
            li(Title("Logo", Some(Logo())))
          )
        ),
        li(
          Title("Style", Some(Pages.Style()), Some(_.isInstanceOf[StyleT])),
          ul(
            li(
              Title("Layout", isExpanded = Some(_.isInstanceOf[LayoutT])),
              ul(
                li(Title("Space", Some(Space()))),
                li(Title("Flexbox", Some(Flexbox())))
              )
            ),
            li(
              Title("Color", Some(Color()))
            ),
            li(
              Title("Typography", Some(Typography()), Some(_.isInstanceOf[TypographyT])),
              ul(
                li(Title("Fixed line height", Some(FixedLineHeight())))
              )
            )
          )
        ),
        li(
          Title("Component", Some(Component()), Some(_.isInstanceOf[ComponentT])),
          ul(
            li(
              Title("Button", Some(Button()), Some(_.isInstanceOf[ButtonT])),
              ul(
                li(Title("ButtonStyle", Some(ButtonStyle()))),
                li(Title("ButtonLink", Some(ButtonLink())))
              )
            ),
            li(Title("Card", Some(Card()))),
            li(Title("Dropdown", Some(Dropdown()))),
            li(Title("Field", Some(Field()))),
            li(
              Title("Icon", Some(Icon()), Some(_.isInstanceOf[IconT])),
              ul(
                li(Title("Illus", Some(Illus()))),
                li(Title("Illus Folder", Some(IllusFolder())))
              )
            ),
            li(
              Title("Input", isExpanded = Some(_.isInstanceOf[InputT])),
              ul(
                li(Title("Text Box", Some(TextBox())))
              )
            ),
            li(
              Title("Portal", isExpanded = Some(_.isInstanceOf[PortalT])),
              ul(
                li(Title("Popover", Some(Popover()))),
                li(Title("Modal", Some(Modal())))
              )
            ),
            li(Title("Stepper", Some(Stepper()))),
            li(Title("Tab", Some(Tab()))),
            li(Title("Table", Some(Table()))),
            li(Title("Toggle", Some(Toggle()))),
            li(Title("Well", Some(Well())))
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
