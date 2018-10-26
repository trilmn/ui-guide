package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.tab.Tab
import anduin.component.icon.{Icon, IllusFolder}
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageTab {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Tab", obj = Some(Tab))()
      ),
      Markdown(
        """
          |# Basic
          |
          |To use Tab, you only need to provide a list of `Tab.Panel`. Each
          |`Tab.Panel` should provide its title and what to render when user
          |choose it:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val panels = List(
          Tab.Panel(title = "First", renderContent = () => "First tab"),
          Tab.Panel("Second", () => "Second tab"),
          Tab.Panel("Third", () => "Third tab")
        )
        Tab(panels = panels)()
      }))(),
      Markdown(
        """
          |## Default Panel
          |
          |By default, the first panel is chosen in the initial render. You
          |can select another one by passing its index to the `defaultPanel`
          |prop.
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val panels = List(
          Tab.Panel("First", () => "First tab"),
          Tab.Panel("Second", () => "Second tab")
        )
        Tab(
          panels = panels, /*<*/
          defaultPanel = 1 /*>*/
        )() /*<*/
      }))(),
      Markdown(
        """
          |Be careful that the compiler cannot catch if you choose an invalid
          |`defaultPanel` value (e.g. a negative number). It will result in
          |a run-time error.
          |
          |## Style
          |
          |At the moment there is only one option: `StyleFull`. More styles
          |(e.g. StyleMinimal) will be added in the future.
          |
          |# Advanced
          |
          |## Rich title
          |
          |The `title` prop of each Panel accepts not only String but
          |VdomNode, allow you to have complex rendering, e.g. With
          |additional info and illustration:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderTitle = (heading: String, desc: String, illus: VdomNode) =>
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            illus,
            <.div(
              Style.margin.left12.textAlign.left,
              <.p(Style.fontWeight.medium, heading),
              <.p(Style.color.gray6.fontWeight.normal.fontSize.px12, desc)
            )
        )
        val panels = List(
          Tab.Panel(
            title = renderTitle(
              "Internal Folder",
              "23 files",
              IllusFolder()()
            ),
            renderContent = () => "First tab"
          ),
          Tab.Panel(
            title = renderTitle(
              "Shared Folder",
              "12 files",
              IllusFolder(icon = Icon.NameUserGroup)()
            ),
            renderContent = () => "Second tab"
          )
        )
        <.div(Tab(panels = panels)())
      }))(),
      Markdown(
        """
          |## Switch panel
          |
          |Tab Panel has a `renderContent_S` prop, which provide a callback
          |(`Int => Callback`) to switch panel. This comes in handy when you
          |want to tell user to go to another panel:
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val panels = List(
          Tab.Panel(title = "First", renderContent_S = { switch =>
            Button(
              onClick = switch(1),
              style = ButtonStyle.StyleLink
            )("Go to second tab")
          }), /*>*/
          Tab.Panel(title = "Second", renderContent = () => "Second tab")
        )
        <.div(Tab(panels = panels)()) /*<*/
      }))(),
      Markdown(
        """
          |## Controlled Tab
          |
          |**Tab is an [uncontrolled component][1] by default.** It has its own
          |state to store the index of the active panel. It updates that state
          |when user clicks on a panel's title. This makes using Tab simple as
          |you don't need to setup a new state.
          |
          |[1]: https://reactjs.org/docs/uncontrolled-components.html
          |
          |However, there are cases where the consumer should have total
          |control over which panel to show. For example, when the active
          |panel should based on the current URL, and thus, switching panel
          |should update the URL.
          |
          |**In these cases, the consumer should make Tab a [controlled
          |component][2] by providing:**
          |
          |[2]: https://reactjs.org/docs/forms.html#controlled-components
          |
          |- The `active` prop, which is the index of the panel that should
          |be shown.
          |- The `setActive` prop, which is what should happen when user
          |click on a panel's title.
          |
          |Tab will then become a stateless component, controlled by
          |consumer's state and only provides the appearance.
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
