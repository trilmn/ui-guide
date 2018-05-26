package anduin.guide.component

import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.style.Style

object Layout {
  def render(ctl: RouterCtl[Main.Page], resolution: Resolution[Main.Page]): VdomElement =
    <.div(
      Style.fontSize.px16.lineHeight.px24,
      ^.paddingLeft := "288px",
      <.div(
        ^.width := "288px",
        Style.position.fixed.coordinate.left0.coordinate.top0,
        Style.height.pc100.overflow.auto,
        Nav(ctl = ctl, page = resolution.page)()
      ),
      <.div(
        ^.width := "768px",
        Style.backgroundColor.white,
        ^.padding := "64px 96px",
        resolution.render()
      )
    )
}
