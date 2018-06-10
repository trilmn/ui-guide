package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PagePopover {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Popover")()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |Popover(
          |)()
          |
          |
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
