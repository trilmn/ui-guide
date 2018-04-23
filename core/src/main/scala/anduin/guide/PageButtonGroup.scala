package anduin.guide

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.icon.IconAcl
import anduin.mcro.Source
import anduin.style.Style

object PageButtonGroup {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Button Group",
          description = """
              |
            """.stripMargin
        )()
      ),
      Markdown("""
          |>**Do not use this.** This is implementation idea for discussion only.
          |>
          |>Please wait until we have `ButtonGroup` component implemented.
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate({
          val border = Style.border.right.borderColor.gray4
          <.div(
            Style.flexbox.flex,
            <.div(
              Style.flexbox.flex.borderRadius.px2,
              Style.backgroundColor.gray1.shadow.borderGray4s,
              <.div(
                Button(style = Button.StyleMinimal)("Refresh"),
                border),
              <.div(
                Button(style = Button.StyleMinimal)("Edit"),
                border),
              <.div(
                Button(style = Button.StyleMinimal, color = Button.ColorDanger)(
                  IconAcl(name = IconAcl.NameTrash)(),
                  <.span(Style.margin.left8, "Remove")
                ))
            )
          )
        })
        // format: on
      )(),
      Markdown("""
          |
        """.stripMargin)()
    )
  }
}
