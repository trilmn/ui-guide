package anduin.guide.pages.components.indicators

import anduin.component.progressindicators.{CircleIndicator, RippleIndicator}
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object PageCircleIndicator {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("CircleIndicator", Some(CircleIndicator))(),
      Toc(Source.getTocHeadings)(),
      Markdown(
        """
          |Button lets users take action:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex,
          ^.justifyContent := "space-evenly",
          Style.color.primary4,
          <.div(CircleIndicator()()),
          <.div(CircleIndicator(size = CircleIndicator.Size.Px48)()),
          <.div(RippleIndicator()()),
        )
      }))()
    )
  }
}
