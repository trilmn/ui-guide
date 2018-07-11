package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.container.Well
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageWell {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Well")()
      ),
      ExampleRich(Source.annotate({
        Well(color = Well.ColorPrimary)("Hello World!")
      }))(),
      Markdown(
        """
          |# Snippet
          |
          |```scala
          |Well(
          |)()
          |```
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div()
        )
      )(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
