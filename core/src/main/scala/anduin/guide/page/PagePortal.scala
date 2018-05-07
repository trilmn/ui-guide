package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PagePortal {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "",
          description = ""
        )()
      ),
      Markdown(
        """
          |# Snippet
          |
          |```scala
          |Icon(
          |)()
          |https://reactjs.org/docs/render-props.html
          |
          |```
          |
          |Example:
        """.stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div()
        )
      )(),
      Markdown(
        """
          |# Target
          |
          |# Content
        """.stripMargin)()
    )
  }
}
