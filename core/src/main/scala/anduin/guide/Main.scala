package anduin.guide

import japgolly.scalajs.react.extra.router.{BaseUrl, Router => RawRouter}
import org.scalajs.dom

object Main {
  private val isLocal = dom.window.location.hostname == "localhost"
  private val baseUrlPath = if (isLocal) "" else "ui-guide/"
  private val baseUrl = BaseUrl.fromWindowOrigin / baseUrlPath

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router = RawRouter(baseUrl, Router.config)
    router().renderIntoDOM(container)
  }
}
