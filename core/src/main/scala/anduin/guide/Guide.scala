package anduin.guide

import scala.scalajs.js

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom

import page._

object Guide {
  sealed trait Page
  case object Welcome extends Page
  case object Style extends Page
  case object StyleLayoutSpace extends Page
  case object StyleLayoutFlexbox extends Page
  case object StyleColor extends Page
  case object Button extends Page
  case object ButtonVsLink extends Page
  case object WIP extends Page

  // Prism only runs once on page load, so we need to manually tell it to
  // run again on client routing
  private val postRenderFn = (prev: Option[Guide.Page], _: Guide.Page) =>
    Callback { if (prev.isDefined) { js.Dynamic.global.Prism.highlightAll() } }

  private val isLocal = dom.window.location.hostname == "0.0.0.0"

  private val hash = if (isLocal) "#" else ""

  private val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._
    (trimSlashes
      | staticRoute(root, Welcome) ~> render(PageWelcome.render)
      | staticRoute(s"${hash}style", Style) ~> render(PageStyle.render)
      | staticRoute(s"${hash}space", StyleLayoutSpace) ~> render(PageStyleLayoutSpace.render)
      | staticRoute(s"${hash}color", StyleColor) ~> render(PageStyleColor.render)
      | staticRoute(s"${hash}flexbox", StyleLayoutFlexbox) ~> render(PageStyleLayoutFlexbox.render)
      | staticRoute(s"${hash}button", Button) ~> render(PageButton.render)
      | staticRoute(s"${hash}button-vs-link", ButtonVsLink) ~> render(PageButtonVsLink.render))
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
      .onPostRender(postRenderFn)
  }

  private val baseUrlPath = if (isLocal) "" else "ui-guide/"
  private val baseUrl = BaseUrl.fromWindowOrigin / baseUrlPath

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router = Router(baseUrl, routerConfig)
    router().renderIntoDOM(container)
  }
}
