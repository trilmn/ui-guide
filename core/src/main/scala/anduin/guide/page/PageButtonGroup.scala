package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.IconAcl
import anduin.guide.Main
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
          |>::warning::**Do not use this.** This is implementation idea for discussion only.
          |>
          |>Please wait until we have `ButtonGroup` component implemented.
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val negMargin = ^.margin := "0 -1px"
          val border    = Style.border.right.borderWidth.px1.borderColor.gray4
          <.div(
            Style.flexbox.flex,
            <.div(
              Style.flexbox.flex.backgroundColor.gray1.shadow.borderGray4s.borderRadius.px2,
              <.div(<.div(Button(style = ButtonStyle.StyleMinimal)("Refresh"), negMargin), border),
              <.div(<.div(Button(style = ButtonStyle.StyleMinimal)("Edit"), negMargin), border),
              <.div(
                <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorDanger)(
                        IconAcl(name = IconAcl.NameTrash)(),
                        <.span(Style.margin.left8, "Remove")
                      ),
                      negMargin))
            )
          )
        })
      )(),
      Markdown("""
          |
        """.stripMargin)()
    )
  }
}
