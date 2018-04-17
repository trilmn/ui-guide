package anduin.guide

import scala.scalajs.js

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom

import anduin.guide.component.Layout
import anduin.guide.page._

object Main {
  sealed trait Page
  case object Welcome extends Page
  case object Button extends Page

  // Prism only runs once on page load, so we need to manually tell it to
  // run again on client routing
  private val postRenderFn = (prev: Option[Main.Page], _: Main.Page) =>
    Callback { if (prev.isDefined) { js.Dynamic.global.Prism.highlightAll() } }

  private val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._
    (trimSlashes
      | staticRoute(root, Welcome) ~> render(PageWelcome.render)
      | staticRoute("#button", Button) ~> render(PageButton.render))
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
      .onPostRender(postRenderFn)
  }

  private val baseUrl = BaseUrl.fromWindowOrigin / {
    dom.window.location.hostname match {
      case "localhost" | "127.0.0.1" | "0.0.0.0" => ""
      case _                                     => "ui-guide/"
    }
  }

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router = Router(baseUrl, routerConfig)
    router().renderIntoDOM(container)
  }
}
