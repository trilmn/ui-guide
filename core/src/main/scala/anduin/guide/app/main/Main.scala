package anduin.guide.app.main

import anduin.guide.app.router.Router
import anduin.guide.scalajs.prism.Prism
import anduin.scalajs.focusvisible.FocusVisible
import japgolly.scalajs.react.extra.router.{BaseUrl, Router => RawRouter}
import org.scalajs.dom

object Main {
  private val baseUrl = BaseUrl.fromWindowOrigin / ""

  def main(args: Array[String]): Unit = {
    // Nothing, just mention so they are included by scalajs bundler
    FocusVisible
    Prism

    // The actual initialization
    val container = dom.document.getElementById("root")
    val router = RawRouter(baseUrl, Router.config)
    router().renderIntoDOM(container)
  }
}
