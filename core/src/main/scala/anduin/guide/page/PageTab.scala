package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.container.Tab
import anduin.component.icon.Illus
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
          |# Basic
          |
          |To use Tab, you only need to provide a list of Panel. Each Panel should provide its title and what to render when user choose it:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val panels = List(
          Tab.Panel(title = "First", renderContent = () => "First tab"),
          Tab.Panel(title = "Second", renderContent = () => "Second tab"),
          Tab.Panel(title = "Third", renderContent = () => "Third tab")
        )
        Tab(panels = panels)()
      }))(),
      Markdown(
        """
          |## Default Panel
          |
          |By default, the first panel is chosen in the initial render. You can select another one by passing its index to the `defaultPanel` prop.
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val panels = List(
          Tab.Panel(title = "First", renderContent = () => "First tab"),
          Tab.Panel(title = "Second", renderContent = () => "Second tab")
        )
        Tab(panels = panels, defaultPanel = 1)()
      }))(),
      Markdown(
        """
          |Be careful that the compiler cannot catch if you choose an invalid `defaultPanel` value (e.g. a negative number). It will result in a run-time error.
          |
          |## Style
          |
          |At the moment there is only one StyleFull. More styles (e.g. StyleMinimal) will be added in the future and can be chosen via the `style` prop.
          |
          |# Advanced
          |
          |## Rich title
          |
          |The `title` prop of each Panel accepts not only String but VdomNode, allow you to have complex rendering. E.g. With additional info and illustration inside:
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderTitle = (heading: String, desc: String, illus: Illus.Name) =>
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            Illus(name = illus)(),
            <.div(
              Style.margin.left12.textAlign.left,
              <.p(Style.fontWeight.medium, heading),
              <.p(Style.color.gray6.fontWeight.normal.fontSize.px12, desc)
            )
        )
        val panels = List(
          Tab.Panel(
            title = renderTitle("Internal Folder", "23 files", Illus.NameFolder),
            renderContent = () => "First tab"
          ),
          Tab.Panel(
            title = renderTitle("Shared Folder", "12 files", Illus.NameFolderShared),
            renderContent = () => "Second tab"
          )
        )
        <.div(Tab(panels = panels)())
      }))(),
      Markdown(
        """
          |## Switch panel dynamically
          |
          |Tab Panel has a `renderContent_S` prop, which provide a callback (`Int => Callback`) to switch panel.
          |
          |This is useful to allow user to go to another panel from the current one.
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val panels = List(
          Tab.Panel(title = "First", renderContent_S = switch => {
            Button(onClick = switch(1), style = ButtonStyle.StyleLink)("Go to second tab")
          }),
          Tab.Panel(title = "Second", renderContent = () => "Second tab")
        )
        <.div(Tab(panels = panels)())
      }))(),
      Markdown(
        """
          |## Stateless Tab
          |
          |**Tab is a stateful component by default.** It has its own state to store the index of the active panel. It updates that state when user clicks on a panel's title. This makes using Tab simple as you don't need to setup a new state.
          |
          |However, there are cases where the consumer should have total control over which panel to show. For example, when the active panel should based on the current URL, and thus, switching panel should update the URL.
          |
          |**In these cases, the consumer should provide:**
          |
          |- The `active` prop, which is the index of the panel that should be shown.
          |- The `setActive` prop, which is what should happen when user click on a panel's title.
          |
          |Then Tab will become a stateless component, only provides the appearance.
          |
          |```scala
          |/*>*/object MyComponent {/*<*/
          |  case class State(tab: Int)
          |  /*>*/
          |  private class Backend(scope: BackendScope[_, State]) {/*<*/
          |    private def setTab(nextTab: Int) = scope.modState {
          |      state => state.copy(tab = nextTab)
          |    }/*>*/
          |
          |    def render(state: State) {
          |      Tab(
          |        panels = /* ... */,/*<*/
          |        active = Some(state.tab),
          |        setActive = Some(setTab)/*>*/
          |      )()
          |    }
          |  }
          |
          |  private val component = ScalaComponent
          |   .builder[Unit](this.getClass.getSimpleName)/*<*/
          |   .initialState(State(0))/*>*/
          |   .renderBackend[Backend]
          |   .build
          |}/*<*/
          |```
          |
          |When Tab is stateless, `defaultPanel` has no effect at all.
          """.stripMargin
      )()
    )
  }
}
