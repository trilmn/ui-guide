package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.container.Collapse
import anduin.component.input.TextInput
import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageCollapse {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Collapse")()
      ),
      Markdown(
        """
          |Collapse, a.k.a Accordion or Toggler, is a state-only component that provides you a simple boolean (`isExpanded`) and a callback to toggle that value (`toggle`).
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val collapse = Collapse(
            render = (toggle, isExpanded) => {
              val button = Button(onClick = toggle)(
                if (isExpanded) "Hide message" else "Show message"
              )
              val content = TagMod.when(isExpanded) {
                <.p(Style.margin.left8, "More content here")
              } /*>*/
              val flex = Style.flexbox.flex.flexbox.itemsCenter
              <.div(flex, button, content)
            }
          )()
          <.div(collapse) /*<*/
        })
      )(),
      Markdown(
        """
          |**Collapse does nothing in the UI at all.** You need to provide a render function that uses the `isExpanded` boolean and `toggle` callback to have a complete UI. This is similar to the ["render-prop"](https://reactjs.org/docs/render-props.html) technique.
          |
          |**The default state is collapsed.** Set `isExpanded = true` to have it expanded in initial render.
        """.stripMargin
      )()
    )
  }
}
