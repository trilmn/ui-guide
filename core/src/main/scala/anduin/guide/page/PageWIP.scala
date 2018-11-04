package anduin.guide.page

import anduin.component.button.{Button, ButtonStyle}
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageWIP {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Working in Progress")()
      ),
      ExampleRich(Source.annotate({
        <.div("a")
      }))(),
      ExampleRich(Source.annotate({
        <.div(
          "a",
          "b"
        )
      }))(),
      ExampleRich(Source.annotate({
        <.div("a", "b")
      }))(),
      ExampleRich(Source.annotate({
        Button(
          color = ButtonStyle.ColorBlue
        )("a")
      }))(),
      ExampleRich(Source.annotate({
        Button()("a")
      }))(),
      ExampleRich(Source.annotate({
        val button = Button()("a")
        <.div(button)
      }))(),
      ExampleRich(Source.annotate({
        val foo = "a"
        <.div(foo)
      }))(),
      ExampleRich(Source.annotate({
        <.div(Button()("a"))
      }))(),
      ExampleRich(Source.annotate({
        <.div(
          Button()("a")
        )
      }))(),
    )
  }
}
