package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Router
import anduin.mcro.Source
import anduin.style.Style

object PageModal {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Modal")()
      )
    )
  }
}
