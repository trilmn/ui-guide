package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.component.Markdown

object PageButtonVsLink {
  def render: VdomElement = {
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
