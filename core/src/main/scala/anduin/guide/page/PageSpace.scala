package anduin.guide.page

import anduin.component.button.{Button, ButtonStyle}
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageSpace {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Space")()
      ),
      Markdown(
        """
          |Space refers to the visual distance between or within objects.
          |Space reflects elements' relationship and helps building structure.
          |
          |# Overview
          |
          |```text
          |Style.<type>.<direction><value>
          |​
          |  type:       padding, margin
          |
          |  direction:  top, right, bottom, left,
          |              vertical, horizontal,
          |              all
          |
          |  value:      0, 4, 8, 12, 16, 20, 24, 32
          |```
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.backgroundColor.gray2.flexbox.flex.flexbox.itemsCenter,/*<*/
                Style.padding.all16/*>*/,
                <.div(/*<*/Style.margin.right8/*>*/, Button()("Cancel")),
                <.div(Button(color = ButtonStyle.ColorBlue)("Submit")))/*<*/
        )
        // format: on
      )(),
      Markdown(
        """
          |# Usage Notes
          |
          |**Distance reflects relationship,** so smaller step implies closer
          |connection and vice versa. For example, the margins between
          |elements inside a card are often smaller than the margin between
          |cards themselves.
          |
          |**The smallest space is currently 4 pixel,** so if you need
          |something smaller, consider using Border or Color for separating
          |things instead. Currently there is no plan to add space that is
          |smaller than 4 pixel.
          |
          |**In the other hand, the largest space is 32 pixel,** so if you
          |need something bigger, consider using relative position (such as
          |centering it and then optionally offset), or inline the style
          |(such as `^.margin := 56px`).
          |
          |>::warning::**Under consideration: Support for larger than 24 pixels**
          |>
          |>The current scale focuses more on small spaces to fine-tune
          |>details, therefore it stops at 32 pixel.
          |>
          |>However, this makes it's difficult to have wide, spacious spacing
          |>at app-level (i.e: margin between areas of main layout). Therefore,
          |>we are considering to add more values after 32: 40 48 56 and 64
          |>pixel (8-pixel scale).
        """.stripMargin
      )()
    )
  }
}
