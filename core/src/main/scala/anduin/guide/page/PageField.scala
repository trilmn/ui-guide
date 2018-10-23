package anduin.guide.page

import anduin.component.field.Field
import anduin.component.input.textbox.TextBox
import anduin.guide.Router
import anduin.guide.component.SimpleState
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageField {

  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header("Field", cls = Some(Field.getClass))()
      ),
      Markdown(
        """
          |
        """.stripMargin
      )(),
      // xxxxxxxxxxxxxxx
      ExampleRich(Source.annotate({
        SimpleState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.LayoutHor,
              id = "foo",
              input = TextBox(value, onChange, id = Some("foo"))(),
              label = Some("Pre-money valuation")
            )()
          }
        )()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.LayoutHor,
              id = "foo2",
              input = TextBox(value, onChange, id = Some("foo2"))(),
              label = Some("Pre-money valuation"),
              desc = Some("10-20% are industry standard")
            )()
          }
        )()
      }))(),
      ExampleRich(Source.annotate({
        SimpleState.Str(
          "",
          (value, onChange) => {
            Field(
              layout = Field.LayoutHor,
              id = "foo2",
              input = TextBox(value, onChange, id = Some("foo2"))(),
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
            )()
          }
        )()
      }))()
    )
  }
}
