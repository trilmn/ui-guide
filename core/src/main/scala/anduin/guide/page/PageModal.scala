package anduin.guide.page

import anduin.component.button.Button
import anduin.component.portal.Modal
import japgolly.scalajs.react.vdom.html_<^._
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
          |# Basic
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val modal = Modal(
          title = "Hello",
          renderContent = _ => "Content",
          renderTarget = toggle => Button(onClick = toggle)("Target")
        )()
        <.div(modal)
      }))()
    )
  }
}
