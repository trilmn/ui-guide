package anduin.guide

import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.style.Style

object Layout {
  private type Res = Resolution[Pages.Page]

  def render(ctl: Router.Ctl, res: Res): VdomElement =
    <.div(
      Style.fontSize.px16.lineHeight.px24.backgroundColor.white,
      ^.paddingLeft := "288px",
      <.div(
        ^.width := "288px",
        Style.position.fixed.coordinate.left0.coordinate.top0,
        Style.height.pc100.overflow.auto.backgroundColor.gray2,
        LayoutNav(ctl = ctl, page = res.page)()
      ),
      <.div(
        ^.width := "768px",
        ^.padding := "64px 96px",
        res.render()
      )
    )
}
