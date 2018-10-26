package anduin.guide.page

import anduin.component.input.textbox.TextBox
import anduin.guide.Router
import anduin.guide.component.SimpleState
import anduin.mcro.Source
import anduin.scalajs.textmask.TextMask
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageTextBox {

  private val StrState = SimpleState.Str("1000", (_, _) => EmptyVdom)

  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header("Text Box", obj = Some(TextBox))()
      ),
      Markdown(
        """
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        SimpleState.Str("No mask", {
          TextBox(_, _, mask = None)()
        })()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str("1000", {
          TextBox(_, _, mask = Some(TextBox.MaskNumber))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str("1000", {
          TextBox(_, _, mask = Some(TextBox.MaskCurrency))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str("43", {
          TextBox(_, _, mask = Some(TextBox.MaskPercentage))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str("thien@anduin.co", {
          TextBox(_, _, mask = Some(TextBox.MaskEmail))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str(
          "(122) 222-2222", {
            val n = TextMask.RegExp("\\d")
            val c = TextMask.Char
            TextBox(_, _, mask = Some(TextBox.MaskArray({
              List(c("("), n, n, n, c(")"), c(" "), n, n, n, c("-"), n, n, n, n)
            })))()
          }
        )()
      }))()
    )
  }
}
