package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.ReactDOMServer
import com.karasiq.markedjs.{Marked, MarkedOptions, MarkedRenderer}

import anduin.component.button.Button
import anduin.mcro.Source
import anduin.style.Style

object PageWelcome {

  private val markdown =
    """
      |# Title
    """.stripMargin
  private val options = MarkedOptions(
    renderer = MarkedRenderer(
      heading = { (text: String, depth: Int) =>
        val element = <.p(text, depth.toString)
        ReactDOMServer.renderToStaticMarkup(element)
      }
    ),
  )
  private val a = Marked(markdown, options)
  println(a)

  val render: VdomElement = {
    val (text1, node1) = Source.annotate(
      <.div(
        Style.flexbox.flex,
        <.div(
          Style.margin.left12,
          Button()("Action")
        ),
        <.div(
          Style.margin.left12,
          Button(color = Button.ColorPrimary)("Submit")
        )
      )
    )
    <.div(
      Style.whiteSpace.pre,
      "Welcome",
      <.div(
        ^.dangerouslySetInnerHtml := a
      ),
      <.div(text1),
      <.div(node1)
    )
  }
}
