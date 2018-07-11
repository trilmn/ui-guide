package anduin.guide.page

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.input.Checkbox
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageCheckbox {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Checkbox")()
      ),
      ExampleRich(Source.annotate({
        val checkbox = Checkbox(
          isChecked = true,
          onChange = isChecked => {
            val msg = s"Checkbox should be changed to ${isChecked.toString}"
            Callback.alert(msg)
          }
        )("Hello World")
        <.div(checkbox)
      }))(),
      Markdown(
        """
          |# Snippet
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div()
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
