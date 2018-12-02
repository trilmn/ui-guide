package anduin.guide.app.main.layout

import anduin.guide.app.main.Pages
import anduin.style.Style
import japgolly.scalajs.react.extra.router.Resolution
import japgolly.scalajs.react.vdom.html_<^._

object Layout {
  private type Res = Resolution[Pages.Page]

  private val logo = {
    <.div(
      Style.color.gray3,
      // Eliminate svg's inline block
      Style.flexbox.flex,
      ^.dangerouslySetInnerHtml :=
        """
          |<svg width="48" height="48" xmlns="http://www.w3.org/2000/svg">
          |  <g fill="none" fill-rule="evenodd">
          |    <path d="M25 0L0 40h10l5-8 5 8h10l5-8 5 8h10L25 0zm0
          |    31.5l-4.75-7.75 4.75-8 4.75 8L25 31.5z"
          |    fill="currentColor"/>
          |  </g>
          |</svg>
        """.stripMargin
    )
  }

  private def renderSidebar(ctl: Pages.Ctl, res: Res) = {
    <.div(
      Style.position.fixed.coordinate.top0.coordinate.left0.zIndex.idx1,
      Style.flexbox.flex.flexbox.column.height.pc100,
      <.div(
        Style.flexbox.none,
        ^.padding := "48px 56px 32px",
        logo
      ),
      <.div(
        Style.flexbox.fixed.overflow.autoY,
        Style.padding.bottom32,
        LayoutNav(ctl = ctl, page = res.page)()
      )
    )
  }

  private def renderBody(res: Res) = {
    <.div(
      Style.margin.horAuto,
      ^.width := "576px",
      ^.padding := "48px 0",
      res.render()
    )
  }

  def render(ctl: Pages.Ctl, res: Res): VdomElement = {
    <.div(
      Style.lineHeight.px32,
      ^.fontSize := "19px",
      ^.className := "ad-ff-sans",
      renderSidebar(ctl, res),
      renderBody(res)
    )
  }
}
