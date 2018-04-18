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

  private val renderHead = (content: String, level: Int) => {
    val id = content.toLowerCase().replaceAll("[^\\w]+", "-")
    val tag = level match {
      case 1 => <.h2
      case 2 => <.h3
      case 3 => <.h4
    }
    val element = tag(
      Style.padding.bottom12.padding.top24,
      <.span(^.dangerouslySetInnerHtml := content),
      <.a(Style.margin.left8, ^.id := id, ^.href := s"#$id", "#")
    )
    rnd(element)
  }

  private val renderParagraph = (content: String) => {
    rnd(<.p(Style.padding.ver12, ^.dangerouslySetInnerHtml := content))
  }

  private val renderCodespan = (content: String) => {
    val styles = TagMod(Style.fontFamily.mono.backgroundColor.gray2,
                        Style.padding.hor8.padding.ver4)
    val html = ^.dangerouslySetInnerHtml := content
    rnd(<.code(styles, html))
  }

  private val renderer = MarkedRenderer(
    heading = renderHead,
    codespan = renderCodespan,
    paragraph = renderParagraph
  )

  private val options: MarkedOptions = MarkedOptions(renderer = renderer)

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
