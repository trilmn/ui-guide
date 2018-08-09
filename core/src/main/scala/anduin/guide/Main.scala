package anduin.guide

import japgolly.scalajs.react.extra.router.{BaseUrl, Router => RawRouter}
import org.scalajs.dom

object Main {
  private val baseUrl = BaseUrl.fromWindowOrigin / ""

  def main(args: Array[String]): Unit = {
    val container = dom.document.getElementById("root")
    val router = RawRouter(baseUrl, Router.config)
    router().renderIntoDOM(container)
  }
}
