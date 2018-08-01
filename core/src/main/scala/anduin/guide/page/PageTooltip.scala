package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.portal2.Tooltip
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageTooltip {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Tooltip")()
      ),
      Markdown(
        """
          |# Snippet
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val tooltip = Tooltip(target = "target")()
        <.div(Style.flexbox.flex, tooltip)
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
