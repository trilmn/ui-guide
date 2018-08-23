package anduin.guide.page

import japgolly.scalajs.react.{Callback, Ref}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import anduin.component.portal.Tooltip
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
        <.div(Style.flexbox.flex)
      }))(),
      Markdown(
        """
          |# Snippet a
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(Style.flexbox.flex)
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
