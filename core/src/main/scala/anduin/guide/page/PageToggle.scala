package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.toggle.Toggle

import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageToggle {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Toggle", obj = Some(Toggle))()
      ),
      Markdown(
        """
          |Toggle, a.k.a Accordion or Collapsible, is a state-only component
          |that provides you a simple boolean (`isExpanded`) and a callback
          |to toggle that value (`toggle`).
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Toggle(
          render = (toggle, isExpanded) => {
            val button = Button(onClick = toggle)(
              if (isExpanded) "Hide message" else "Show message"
            )
            val content = TagMod.when(isExpanded) {
              <.p(Style.margin.left8, "More content here")
            }
            val flex = Style.flexbox.flex.flexbox.itemsCenter
            <.div(flex, button, content)
          }
        )()
      }))(),
      Markdown(
        """
          |**Toggle does nothing in the UI at all.** You need to provide a
          |render function that uses the `isExpanded` boolean and `toggle`
          |callback to have a complete UI. This is similar to the
          |["render-prop"][rp] technique.
          |
          |[rp]: https://reactjs.org/docs/render-props.html
          |
          |**The default state is collapsed.** Set `isExpanded = true` to
          |have it expanded in initial render.
        """.stripMargin
      )()
    )
  }
}
