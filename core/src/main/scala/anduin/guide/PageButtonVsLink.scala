package anduin.guide

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.icon.IconAcl
import anduin.mcro.Source
import anduin.style.Style

object PageButtonVsLink {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.h1("Button vs Link"),
      Markdown("""
          |""".stripMargin)(),
      Example(
        Source.annotate(
          <.div(
            // actual button, looks like a button
            Button()("One"),
            // actual link, looks like a button
//            Button(tpe = Button.TpeLink, href = "...")("Two"),
            // actual button, looks like a link
//            Button(style = Button.StyleLink)("Three"),
            // actual link, looks like a link
//            <.a(^.href := "#", "Four"),
            <.div(
              Style.margin.left8,
              IconAcl(name = IconAcl.NameTrash)()
            )
          )
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
