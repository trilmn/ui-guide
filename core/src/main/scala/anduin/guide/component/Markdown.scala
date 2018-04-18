package anduin.guide.component

import com.karasiq.markedjs.{Marked, MarkedOptions, MarkedRenderer}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils

final case class Markdown(
  source: String
) {
  def apply(): VdomElement = {
    Markdown.component(this)
  }
}

object Markdown {

  private final val ComponentName = ComponentUtils.name(this)

  private val head = (text: String, depth: Int) => {
    val element = <.p(text, depth.toString)
    ReactDOMServer.renderToStaticMarkup(element)
  }

  private val options = MarkedOptions(renderer = MarkedRenderer(heading = head))

  private case class Backend(scope: BackendScope[Markdown, _]) {
    def render(props: Markdown): VdomElement = {
      val html = Marked(props.source, options)
      <.div(^.dangerouslySetInnerHtml := html)
    }
  }

  private val component = ScalaComponent
    .builder[Markdown](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
