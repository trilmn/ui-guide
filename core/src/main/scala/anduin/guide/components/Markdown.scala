package anduin.guide.components

import anduin.scalajs.markedjs.{Marked, MarkedOptions, MarkedRenderer}
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js

final case class Markdown(source: String) {
  def apply(): VdomElement = Markdown.component(this)
}

object Markdown {

  private type Props = Markdown
  private val rnd = ReactDOMServer.renderToStaticMarkup _

  private val renderHead = (string: String, level: Int) => {
    val styles = Style.padding.bottom16.padding.top32
    val heading = Heading(content = string, level = level)()
    rnd(<.div(styles, heading))
  }

  private val renderHr = () => {
    rnd(<.hr(^.height := "48px"))
  }

  private val renderStrong = (string: String) => {
    rnd(<.strong(Style.fontWeight.medium, ^.dangerouslySetInnerHtml := string))
  }

  private val renderParagraph = (string: String) => {
    rnd(<.p(Style.padding.ver16, ^.dangerouslySetInnerHtml := string))
  }

  private val renderCodeSpan = (string: String) => {
    val parts: List[String] = string.split("::").toList
    val (lngOpt, newContent) = parts match {
      case a :: b :: Nil => (Some(a), b)
      case _             => (None, string)
    }
    val codeSpan = <.code(
      Style.fontFamily.mono.backgroundColor.gray2,
      lngOpt.whenDefined(lng => ^.cls := s"language-$lng"),
      ^.padding := "0 4px",
      ^.dangerouslySetInnerHtml := newContent
    )
    rnd(codeSpan)
  }

  private val renderCode = (string: String, language: String) => {
    val codeBlock = CodeBlock(content = string, language = language)()
    rnd(<.div(Style.padding.ver16, codeBlock))
  }

  private val renderBlockQuote = (string: String) => {
    val quoteBlock = QuoteBlock(content = string)()
    rnd(<.div(Style.padding.ver16, quoteBlock))
  }

  private val renderList = (string: String, ordered: Boolean) => {
    val tag = if (ordered) <.ol else <.ul
    val html = ^.dangerouslySetInnerHtml := string
    rnd(tag(Style.padding.left32, html))
  }

  private val renderTable = (header: String, body: String) => {
    val element = <.table(
      Style.table.collapse.width.pc100,
      <.thead(Style.backgroundColor.gray1, ^.dangerouslySetInnerHtml := header),
      <.tbody(^.dangerouslySetInnerHtml := body)
    )
    rnd(<.div(Style.padding.ver16, element))
  }

  private val renderTableCell = (string: String, flags: js.Object) => {
    val isHeader: Boolean = flags
      .asInstanceOf[js.Dynamic]
      .selectDynamic("header")
      .asInstanceOf[Boolean]

    val styles = TagMod(
      Style.border.all.borderColor.gray2.borderWidth.px1,
      Style.padding.all12.textAlign.left.fontWeight.normal
    )
    val html = ^.dangerouslySetInnerHtml := string
    val tag = if (isHeader) <.th else <.td
    rnd(tag(styles, html))
  }

  private val renderLink = (href: String, title: String, text: String) => {
    val link = <.a(
      Style.color.gray7.hover.colorPrimary4.transition.all,
      Style.border.bottom.borderColor.gray3.borderWidth.px2,
      Style.hover.borderPrimary3.hover.underlineNone,
      ^.title := title,
      ^.href := href,
      ^.dangerouslySetInnerHtml := text
    )
    rnd(link)
  }

  private val options: MarkedOptions = MarkedOptions(
    renderer = MarkedRenderer(
      heading = renderHead,
      codespan = renderCodeSpan,
      code = renderCode,
      strong = renderStrong,
      hr = renderHr,
      blockquote = renderBlockQuote,
      list = renderList,
      link = renderLink,
      table = renderTable,
      tablecell = renderTableCell,
      paragraph = renderParagraph
    )
  )

  private def render(props: Props): VdomElement = {
    val html = Marked(props.source, options)
    <.div(^.dangerouslySetInnerHtml := html)
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
