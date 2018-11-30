package anduin.guide.components

import anduin.scalajs.markedjs.{Marked, MarkedOptions, MarkedRenderer}
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Markdown(
  source: String
) {
  def apply(): VdomElement = {
    Markdown.component(this)
  }
}

object Markdown {

  private val rnd = ReactDOMServer.renderToStaticMarkup _

  private val renderHead = (content: String, level: Int) => {
    val styles = Style.padding.bottom12.padding.top32
    val heading = Heading(content = content, level = level)()
    rnd(<.div(styles, heading))
  }

  private val renderHr = () => {
    rnd(<.hr(^.height := "48px"))
  }

  private val renderParagraph = (content: String) => {
    rnd(<.p(Style.padding.ver12, ^.dangerouslySetInnerHtml := content))
  }

  private val renderCodeSpan = (content: String) => {
    val parts: List[String] = content.split("::").toList
    val (lngOpt, newContent) = parts match {
      case a :: b :: Nil => (Some(a), b)
      case _             => (None, content)
    }
    val codeSpan = <.code(
      Style.fontFamily.mono.backgroundColor.gray2,
      lngOpt.whenDefined(lng => ^.cls := s"language-$lng"),
      ^.padding := "2px 4px",
      ^.dangerouslySetInnerHtml := newContent
    )
    rnd(codeSpan)
  }

  private val renderCode = (content: String, language: String) => {
    val codeBlock = CodeBlock(content = content, language = language)()
    rnd(<.div(Style.padding.ver12, codeBlock))
  }

  private val renderBlockquote = (content: String) => {
    val quoteBlock = QuoteBlock(content = content)()
    rnd(<.div(Style.padding.ver12, quoteBlock))
  }

  private val renderList = (content: String, ordered: Boolean) => {
    val tag = if (ordered) <.ol else <.ul
    val html = ^.dangerouslySetInnerHtml := content
    rnd(tag(Style.padding.left32.padding.ver12, html))
  }

  private val renderTable = (header: String, body: String) => {
    val element = <.table(
      Style.table.collapse.width.pc100,
      <.thead(Style.backgroundColor.gray1, ^.dangerouslySetInnerHtml := header),
      <.tbody(^.dangerouslySetInnerHtml := body)
    )
    rnd(<.div(Style.padding.ver12, element))
  }

  private val renderTableCell =
    (content: String, flags: scala.scalajs.js.Object) => {
      val isHeader: Boolean = flags
        .asInstanceOf[scala.scalajs.js.Dynamic]
        .selectDynamic("header")
        .asInstanceOf[Boolean]

      val styles = TagMod(
        Style.border.all.borderColor.gray2.borderWidth.px1,
        Style.padding.all12.textAlign.left.fontWeight.normal
      )
      val html = ^.dangerouslySetInnerHtml := content
      val tag = if (isHeader) <.th else <.td
      rnd(tag(styles, html))
    }

  private val options: MarkedOptions = MarkedOptions(
    renderer = MarkedRenderer(
      heading = renderHead,
      codespan = renderCodeSpan,
      code = renderCode,
      hr = renderHr,
      blockquote = renderBlockquote,
      list = renderList,
      table = renderTable,
      tablecell = renderTableCell,
      paragraph = renderParagraph
    )
  )

  private case class Backend(scope: BackendScope[Markdown, _]) {
    def render(props: Markdown): VdomElement = {
      val html = Marked(props.source, options)
      <.div(^.dangerouslySetInnerHtml := html)
    }
  }

  private val component = ScalaComponent
    .builder[Markdown](this.getClass.getSimpleName)
    .stateless
    .renderBackend[Backend]
    .build
}
