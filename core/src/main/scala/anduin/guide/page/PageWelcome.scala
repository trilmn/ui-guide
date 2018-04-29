package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.portal.Tooltip
import anduin.guide.Main
import anduin.style.Style

object PageWelcome {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Style.whiteSpace.pre,
      "Welcome",
      Markdown("""
        | # heading 1
        | ## heading 2a
        |""".stripMargin)(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex,
          Tooltip(
            renderTarget = () => <.span("Target"),
            renderContent = () => <.span("Content")
          )()
        )
      ),
      Markdown(
        """
          |
        | ### heading 3
        | asdasd *a*
        |""".stripMargin)()
    )
  }
}
