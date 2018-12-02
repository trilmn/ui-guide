package anduin.guide.pages.components.card

import anduin.guide.components._
import anduin.component.card.Card
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageCard {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Card", Some(Card))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Card is a simple container to group content:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val card = Card()("Card's content")
        <.div(card)
      }), isBgGray = true)(),
      Markdown(
        """
          |Due to the appearance of a Card, avoid using it over a white
          |background as it will result in poor visual contrast. Instead,
          |Card should be used over gray background, especially `gray-1` and
          |`gray-2`.
          |
          |# Header
          |
          |Card accepts a `header: VdomNode` prop that has some built-in
          |styles suitable for quickly having a simple title:
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val card = Card(header = "Title")("Content")
        <.div(card)
      }), isBgGray = true)(),
      Markdown(
        """
          |However, since this is `VdomNode`, you can also have a complex
          |header:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val header = <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.div(Icon(name = Icon.NameDashboard)()),
            <.div("Dashboard", Style.flexbox.fixed.margin.left8),
            <.div(<.a(^.href := "#", "View Detail"))
          )
          val card = Card(header = header)("Content")
          <.div(card)
        }),
        isBgGray = true
      )()
    )
  }
}
