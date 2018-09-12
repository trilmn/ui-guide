package anduin.guide

import japgolly.scalajs.react.extra.router.{BaseUrl, Router => RawRouter}
import org.scalajs.dom

import anduin.scalajs.focusvisible.FocusVisible
import anduin.scalajs_own.prism.Prism

object Main {
  private val baseUrl = BaseUrl.fromWindowOrigin / ""

  def main(args: Array[String]): Unit = {
    // Nothing, just mention so it is included by scalajs bundler
    FocusVisible
    Prism

    // The actual initialization
    val container = dom.document.getElementById("root")
    val router = RawRouter(baseUrl, Router.config)
    router().renderIntoDOM(container)
  }
}
