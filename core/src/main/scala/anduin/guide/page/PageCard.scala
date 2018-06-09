package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.container.Card
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageCard {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Card")()
      ),
      Markdown("""
          |Card is a simple component that wraps its content inside a white rectangle with padding, border and is a little rounded. It doesn't have shadow, however.
          |
          |Example:
        """.stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.backgroundColor.gray2.padding.all32,
            Card()("Card's content")
          )
        )
      )(),
      Markdown("""
          |
        """.stripMargin)()
    )
  }
}
