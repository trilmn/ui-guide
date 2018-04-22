package anduin.guide

import scala.scalajs.js

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.router.StaticDsl.Route
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom

import page._

object Guide {
  sealed trait Page {}
  case object Welcome                              extends Page
  case class StyleLayoutSpace(hash: String = "")   extends Page
  case class StyleLayoutFlexbox(hash: String = "") extends Page
  case class StyleColor(hash: String = "")         extends Page
  case class StyleTypography(hash: String = "")    extends Page
  case class Button(hash: String = "")             extends Page
  case class ButtonVsLink(hash: String = "")       extends Page
  case class WIP(hash: String = "")                extends Page
  case class Style(hash: String = "")              extends Page

  // Prism only runs once on page load, so we need to manually tell it to
  // run again on client routing
  private val postRenderFn = (prev: Option[Guide.Page], _: Guide.Page) =>
    Callback { if (prev.isDefined) { js.Dynamic.global.Prism.highlightAll() } }

  private val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    val hash = string("#?.*")

    (trimSlashes
      | staticRoute(root, Welcome) ~> render(PageWelcome.render)

      | dynamicRouteCT("style" ~ hash.caseClass[Style]) ~> render(PageStyle.render)
      | dynamicRouteCT("color" ~ hash.caseClass[StyleColor]) ~> render(PageStyleColor.render)
      | dynamicRouteCT("space" ~ hash.caseClass[StyleLayoutSpace]) ~> render(PageStyleLayoutSpace.render)
      | dynamicRouteCT("flexbox" ~ hash.caseClass[StyleLayoutFlexbox]) ~> render(PageStyleLayoutFlexbox.render)
      | dynamicRouteCT("typography" ~ hash.caseClass[StyleTypography]) ~> render(PageStyleTypography.render)

      | dynamicRouteCT("button" ~ hash.caseClass[Button]) ~> render(PageButton.render)
      | dynamicRouteCT("button-vs-link" ~ hash.caseClass[ButtonVsLink]) ~> render(PageButtonVsLink.render))
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
      .onPostRender(postRenderFn)
      .logToConsole
  }

  private val isLocal     = dom.window.location.hostname == "localhost"
  private val baseUrlPath = if (isLocal) "" else "ui-guide/"
  private val baseUrl     = BaseUrl.fromWindowOrigin / baseUrlPath

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router    = Router(baseUrl, routerConfig)
    router().renderIntoDOM(container)
  }
}
