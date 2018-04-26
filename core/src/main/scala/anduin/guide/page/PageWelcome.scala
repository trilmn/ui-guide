package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
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
          Button()("a")
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
