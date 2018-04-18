package anduin.guide.component

import com.karasiq.markedjs.{Marked, MarkedOptions, MarkedRenderer}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class Markdown(
  source: String
) {
  def apply(): VdomElement = {
    Markdown.component(this)
  }
}

object Markdown {

  private val rnd = ReactDOMServer.renderToStaticMarkup _

  private final val ComponentName = ComponentUtils.name(this)

  private val renderHead = (content: String, depth: Int) => {
    rnd(<.p(content, depth.toString))
  }

  private val renderParagraph = (content: String) => {
    println(content)
    rnd(<.p(Style.padding.ver12, ^.dangerouslySetInnerHtml := content))
  }

  private val renderer = MarkedRenderer(
    heading = renderHead,
    paragraph = renderParagraph
  )

  private val options: MarkedOptions = MarkedOptions(renderer = renderer)

  private case class Backend(scope: BackendScope[Markdown, _]) {
    def render(props: Markdown): VdomElement = {
      val html = Marked(props.source, options)
      println(html)
      <.div(^.dangerouslySetInnerHtml := html)
    }
  }

  private val component = ScalaComponent
    .builder[Markdown](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
