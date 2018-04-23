package anduin.guide

import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

object PageButton {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Button",
          description =
            """
              |a
            """.stripMargin
        )()
      ),
      Markdown(
        """
          |# Snippet
          |
          |```scala
          |Button(
          |  style: Button.Style = Button.StyleFull,
          |  color: Button.Color = Button.ColorWhite,
          |  size: Button.Size = Button.SizeMedium,
          |  isFullWidth: Boolean = false,
          |
          |  tpe: Button.Tpe = Button.TpeDefault,
          |  onClick: Callback = Callback.empty,
          |  href: String = "" // if tpe == link
          |  isDisabled: Boolean = false, // if tpe != link
          |)()
          |```
        """.stripMargin)()
    )
  }
}
