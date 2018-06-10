package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.container.Collapse
import anduin.component.input.TextInput
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageCollapse {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Collapse")()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |Collapse(
          |  render: (toggle: Callback, isExpanded: Boolean) => VdomNode,
          |  isExpanded: Boolean = false
          |)()
          |```
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Collapse(
              render = (toggle, isExpanded) => {
                val button = Button(onClick = toggle)("Toggle")
                val content = TagMod.when(isExpanded) { <.p(Style.margin.left8, "More content here") }
                <.div(Style.flexbox.flex.flexbox.itemsCenter, button, content)
              }
            )()
          )
        )
      )(),
      Markdown(
        """
          |# Usage
          |
          |**Collapse is a state-only component,** which means it does nothing in the UI, but only provide you the current state, and callbacks to change that state. You need to decide what to do/render with them to have a complete UI.
          |
          |**For example, in the top snippet,** we render a Button to allow user to toggle a text. Below is another example, in which we toggle the hint on focus events:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Collapse((toggle, isExpanded) => {
              <.div(
                Style.flexbox.flex.flexbox.itemsCenter,
                TextInput(
                  isUncontrolled = true,
                  onFocus = toggle,
                  onBlur = toggle,
                  placeholder = "Enter your email"
                )(),
                TagMod.when(isExpanded) {
                  <.p(Style.margin.left8, "e.g. john@company.com")
                }
              )
            })()
          )
        )
      )(),
      Markdown(
        """
          |Moreover, instead of render nothing when not expanded, you can also hide it with CSS (i.e. Style.display.none) or show a truncated version.
          |
          |**By default, the initial state is collapsed.** Set `isExpanded = true` to have it expanded initially.
          |
          |## Learn more
          |
          |**This technique is often called "render-prop".** It is nicely explained at [React's official guide](https://reactjs.org/docs/render-props.html). It gives the engineer a great flexibility in UI, without repeating the state logic.
        """.stripMargin
      )()
    )
  }
}
