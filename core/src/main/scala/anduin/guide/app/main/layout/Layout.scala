package anduin.guide.app.main.layout

import anduin.guide.app.main.Pages
import anduin.style.Style
import japgolly.scalajs.react.extra.router.Resolution
import japgolly.scalajs.react.vdom.html_<^._

object Layout {
  private type Res = Resolution[Pages.Page]

  def render(ctl: Pages.Ctl, res: Res): VdomElement =
    <.div(
      Style.backgroundColor.white,
      /*
      ^.paddingLeft := "288px",
      <.div(
        ^.width := "288px",
        Style.position.fixed.coordinate.left0.coordinate.top0,
        Style.height.pc100.overflow.auto.backgroundColor.gray2,
        LayoutNav(ctl = ctl, page = res.page)()
      ), */
      <.div(
        Style.margin.horAuto,
        ^.fontSize := "19px",
        Style.lineHeight.px32,
        ^.width := "576px",
        ^.padding := "64px 0",
        res.render()
      )
    )
}
