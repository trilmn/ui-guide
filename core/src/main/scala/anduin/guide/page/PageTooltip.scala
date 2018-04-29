package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.portal.Tooltip
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTooltip {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Tooltip",
          description = "Tooltip helps identify or adds information to an element."
        )()
      ),
      Markdown("""
          |# Snippet
          |
          |```scala
          |Tooltip(
          |  position: Position = PositionBottom,
          |  isDisabled: Boolean = false,
          |  isInline: Boolean = false,
          |  renderTarget: () => VdomNode,
          |  renderContent: () => VdomNode
          |)()
          |```
          |
          |>::danger::hello hello hello
          |
          |>::primary::hello hello hello
          |
          |>::info::hello hello hello
          |
          |>::warning::hello hello hello
          |
          |>::success::hello hello hello
          |
          |>hello hello hello
          |
          |Example:
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          Tooltip(
            isInline = true,
            renderTarget = () => "target",
            renderContent = () => "content"
          )()
        )
      )(),
      Markdown("""
          |a
          |
          |a
          |
          |a
          |
          |a
          |
          |a
          |
          |a
          |
          |
          |a
          |a
          |
          |a
          |a
          |a
          |a
          |
          |a
          |a
          |
          |
          |a
          |a
          |
          |
          |a
          |a
          |
          |
          |a
          |a
          |
          |a
          |
          |a
          |
          |
          |a
          |
          |a
          |
          |a
          |
          |
          |a
          |
          |
          |a
          |
          |aa
          |
          |a
          |
          |
          |a
          |
          |a
          |
          |a
          |
          |a
          |
          |
          |a
          |
          |a
          |
          |a
          |
          |a
          |b
        """.stripMargin)()
    )
  }
}
