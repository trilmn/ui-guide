package anduin.guide

import scala.scalajs.js

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom

import anduin.guide.page._

object Main {
  sealed trait Page {}
  case object Welcome extends Page

  case class Style(hash: String = "") extends Page
  case class Space(hash: String = "") extends Page
  case class Flexbox(hash: String = "") extends Page
  case class Color(hash: String = "") extends Page
  case class Typography(hash: String = "") extends Page
  case class FixedLineHeight(hash: String = "") extends Page

  case class Component(hash: String = "") extends Page
  case class Icon(hash: String = "") extends Page
  case class Button(hash: String = "") extends Page
  case class ButtonVsLink(hash: String = "") extends Page
  case class Tooltip(hash: String = "") extends Page
  case class Portal(hash: String = "") extends Page
  case class Popover(hash: String = "") extends Page

  case class WIP(hash: String = "") extends Page

  type Ctl = RouterCtl[Page]

  // Prism only runs once on page load, so we need to manually tell it to
  // run again on client routing
  private val postRenderFn = (prev: Option[Main.Page], _: Main.Page) =>
    Callback { if (prev.isDefined) { js.Dynamic.global.Prism.highlightAll() } }

  private val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    val hash = string("(#.*|)$")

    (trimSlashes
      | staticRoute(root, Welcome) ~> renderR(PageWelcome.render)

      | dynamicRouteCT("style" ~ hash.caseClass[Style]) ~> renderR(PageStyle.render)
      | dynamicRouteCT("color" ~ hash.caseClass[Color]) ~> renderR(PageColor.render)
      | dynamicRouteCT("space" ~ hash.caseClass[Space]) ~> renderR(PageSpace.render)
      | dynamicRouteCT("flexbox" ~ hash.caseClass[Flexbox]) ~> renderR(PageFlexbox.render)
      | dynamicRouteCT("typography" ~ hash.caseClass[Typography]) ~> renderR(PageTypography.render)
      | dynamicRouteCT("fixed-line-height" ~ hash.caseClass[FixedLineHeight]) ~> renderR(PageFixedLineHeight.render)

      | dynamicRouteCT("component" ~ hash.caseClass[Component]) ~> renderR(PageComponent.render)
      | dynamicRouteCT("icon" ~ hash.caseClass[Icon]) ~> renderR(PageIcon.render)
      | dynamicRouteCT("button" ~ hash.caseClass[Button]) ~> renderR(PageButton.render)
      | dynamicRouteCT("button-vs-link" ~ hash.caseClass[ButtonVsLink]) ~> renderR(PageButtonVsLink.render)
      | dynamicRouteCT("portal" ~ hash.caseClass[Portal]) ~> renderR(PagePortal.render)
      | dynamicRouteCT("tooltip" ~ hash.caseClass[Tooltip]) ~> renderR(PageTooltip.render)
      | dynamicRouteCT("popover" ~ hash.caseClass[Popover]) ~> renderR(PagePopover.render)

      | emptyRule)
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
      .onPostRender(postRenderFn)
  }

  private val isLocal = dom.window.location.hostname == "localhost"
  private val baseUrlPath = if (isLocal) "" else "ui-guide/"
  private val baseUrl = BaseUrl.fromWindowOrigin / baseUrlPath

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router = Router(baseUrl, routerConfig)
    router().renderIntoDOM(container)
  }
}
