package anduin.guide.page

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.container.Collapse
import anduin.component.portal2.Modal
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageModal {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Modal")()
      ),
      Markdown(
        """
          |Simple:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderBody: Callback => VdomNode = (close: Callback) => {
          <.div("Body", Button(onClick = close)("Close"))
        }
        Button(onClick = Modal.create(renderBody))("Open")
      }))(),
      Markdown(
        """
          |Nested (Modal inside Modal):
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderBody2: Callback => VdomNode = (close: Callback) => {
          <.div("Body 2", Button(onClick = close)("Close 2"))
        }
        val renderBody1: Callback => VdomNode = (close: Callback) => {
          <.div(
            "Body 1",
            Button(onClick = Modal.create(renderBody2))("Keep 1 and open 2"),
            Button(onClick = close >> Modal.create(renderBody2))("Close 1 then open 2"),
            Button(onClick = close)("Close 1")
          )
        }
        Button(onClick = Modal.create(renderBody1))("Open 1")
      }))(),
      Markdown(
        """
          |Un-mount parent but keep Modal:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        // Collapse is being used here to simplify the example. In practice, it
        // is often a State here
        val content = Collapse({ (toggle: Callback, isExpand: Boolean) =>
          val renderBody: Callback => VdomNode = (close: Callback) => {
            <.div(
              "Body",
              Button(onClick = toggle)("Toggle parent"),
              Button(onClick = close)("Close")
            )
          }
          <.div(
            if (isExpand) Button(onClick = Modal.create(renderBody))("Open")
            else "Nothing"
          )
        }, isExpanded = true)()
        <.div(content)
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
