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
        Header("Field", obj = Some(Field))()
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
              label = Some("Pre-money valuation")
            )(
              TextBox(value, onChange, id = Some("foo"))()
            )
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
              label = Some("Pre-money valuation"),
              desc = Some("10-20% are industry standard")
            )(
              TextBox(value, onChange, id = Some("foo2"))()
            )
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
