package anduin.guide.page

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.component.input.{CustomDropdown, DropdownOption}
import anduin.style.Style

object PageDropdown {

  private val IntDropdown = (new CustomDropdown[Int])()

  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Dropdown")()
      ),
      ExampleRich(Source.annotate({
        val dropdown = IntDropdown(
          value = 1,
          renderValue = _.toString,
          options = List(1, 2, 3).map(DropdownOption(_)),
          renderOption = _.toString,
          onChange = a => Callback.alert(a.toString)
        )()
        <.div(dropdown)
      }))(),
      Markdown(
        """
          |# Snippet
          |
          |```scala
          |Dropdown(
          |)()
          |```
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
