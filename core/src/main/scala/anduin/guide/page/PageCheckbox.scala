package anduin.guide.page

import anduin.component.input.Checkbox
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

object PageCheckbox {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Checkbox")()
      ),
      Markdown(
        """
          |Checkbox follows the native [`<input type="checkbox">`][1] element
          |so make sure you're familar with it first.
          |
          |[1]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox
        """.stripMargin
      )(),
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
          |The live example above is not fully interact-able due to this
          |guide's limitation (guide is stateless). In practice, you will
          |usually use it with your state:
          |
          |```scala
          |case class State(isFoo: Boolean)
          |
          |def setIsFoo(isChecked: Boolean) = {
          |  scope.setState(isFoo = isChecked)
          |}
          |
          |def render() = {
          |  Checkbox(
          |    value = state.isFoo,
          |    onChange = setIsFoo
          |  )("Foo")
          |}
          |```
          |
          |# isChecked
          |
          |# onChange
          |
          |# Others
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex,
          Checkbox(isChecked = true, isDisabled = true)("Foo"),
          Checkbox(isChecked = false, isDisabled = true)("Foo")
        )
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
