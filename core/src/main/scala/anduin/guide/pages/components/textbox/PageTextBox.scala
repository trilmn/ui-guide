package anduin.guide.pages.components.textbox

import anduin.guide.components._
import anduin.component.input.textbox.TextBox
import anduin.guide.app.main.Pages
import anduin.guide.components.DemoState
import anduin.mcro.Source
import anduin.scalajs.textmask.TextMask
import japgolly.scalajs.react.vdom.html_<^._

object PageTextBox {

  private val StrState = DemoState.Str("1000", (_, _) => EmptyVdom)

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Text Box", Some(TextBox))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        DemoState.Str("No mask", {
          TextBox(_, _, mask = None)()
        })()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str("1000", {
          TextBox(_, _, mask = Some(TextBox.MaskNumber))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str("1000", {
          TextBox(_, _, mask = Some(TextBox.MaskCurrency))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str("43", {
          TextBox(_, _, mask = Some(TextBox.MaskPercentage))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str("thien@anduin.co", {
          TextBox(_, _, mask = Some(TextBox.MaskEmail))()
        })()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str(
          "(122) 222-2222", {
            val n = TextMask.RegExp("\\d")
            val c = TextMask.Char
            TextBox(_, _, mask = Some(TextBox.MaskCustomArray({
              List(c("("), n, n, n, c(")"), c(" "), n, n, n, c("-"), n, n, n, n)
            })))()
          }
        )()
      }))()
    )
  }
}
