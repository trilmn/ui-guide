package anduin.guide.pages.components.field

import anduin.guide.components._
import anduin.component.field.Field
import anduin.component.input.textbox.TextBox
import anduin.guide.app.main.Pages
import anduin.guide.components.DemoState
import anduin.mcro.Source
import japgolly.scalajs.react.vdom.html_<^._

object PageField {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Field", Some(Field))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |
        """.stripMargin
      )(),
      // xxxxxxxxxxxxxxx
      ExampleRich(Source.annotate({
        DemoState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.Layout.Hor(),
              id = "foo",
              label = Some("Pre-money valuation")
            )(
              TextBox(value, onChange, id = Some("foo"))()
            )
          }
        )()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.Layout.Hor(),
              id = "foo2",
              label = Some("Pre-money valuation"),
              desc = Some("10-20% are industry standard")
            )(
              TextBox(value, onChange, id = Some("foo2"))()
            )
          }
        )()
      }))(),
      ExampleRich(Source.annotate({
        DemoState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.Layout.Hor(),
              id = "foo2",
              label = Some("Pre-money valuation"),
              desc = Some("10-20% are industry standard"),
              help = Some(
                """
                  |The sum of the percentage of company owned by the
                  |investors and the ESOP cannot exceed 100%.
                """.stripMargin
              ),
              error = Some(
                """
                  |The sum of the percentage of company owned by the
                  |investors and the ESOP cannot exceed 100%.
                """.stripMargin
              )
            )(
              TextBox(value, onChange, id = Some("foo2"))()
            )
          }
        )()
      }))()
    )
  }
}
