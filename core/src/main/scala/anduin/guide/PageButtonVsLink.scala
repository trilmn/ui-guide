package anduin.guide

import japgolly.scalajs.react.vdom.html_<^._

object PageButtonVsLink {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.h1("Button vs Link"),
      Markdown(
        """
          | #Button()("...")
          |---> actual button, looks like a button
          |
          |Button(tpe = Button.TpeLink, href = "...")("...")
          |---> actual link, looks like a button
          |
          |Button(style = Button.StyleLink, onClick = "...")("...")
          |---> actual button, looks like a link
          |
          |a(^.href := ..., ^.onClick := ..., "...")
          |---> actual link, looks like a link
        """.stripMargin
      )()
    )
  }
}
