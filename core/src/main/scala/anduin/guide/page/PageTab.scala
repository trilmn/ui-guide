package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.container.Tab
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTab {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Tab")()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |Tab(
          |  panels: List[Tab.Panel],
          |  style: Tab.Style = Tab.StyleFull,
          |
          |  // Stateful (The Tab component controls the state itself)
          |  defaultPanel: Int = 0,
          |
          |  // Stateless (The consumer controls the state)
          |  active: Option[Int] = None,
          |  setActive: Option[Int => Callback] = None
          |)()
          |```
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Tab(
              panels = List(
                Tab.Panel(title = "First", renderContent = () => "First tab"),
                Tab.Panel(title = "Second", renderContent = () => "Second tab"),
                Tab.Panel(title = "Third", renderContent = () => "Third tab")
              )
            )()
          )
        )
      )(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
