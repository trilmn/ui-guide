package anduin.guide.page

import anduin.component.button.Button
import anduin.component.portal.Popover
import anduin.guide.Router
import anduin.guide.component.SimpleState
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PagePopover {

  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Popover")()
      ),
      ExampleRich(Source.annotate({
        val p = Popover(
          renderTarget = (toggle,  _) => {
            Button(onClick = toggle)("show")
          },
          renderContent = _ => {
            SimpleState.Bool(
              initialValue = false,
              render = (value, onChange) => {
                <.div(
                  Button(onClick = onChange(!value))("a"),
                  if (!value) "a" else "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                )
              }
            )()
          }
        )()
        <.div(p)
      }))()
    )
  }
}
