package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Heading(
  content: String,
  level: Int,
) {
  def apply(): VdomElement = Heading.component(this)
}

object Heading {

  private type Props = Heading

  def getId(text: String): String =
    text
      .toLowerCase()
      .replaceAll("<.*?>", "") // HTML tags like <code>Foo</code>
      .replaceAll("[^a-z]+", "-") // symbols like "," "." "("
      .replaceAll("-$", "") // trailing "-" (originally ".")

  private def render(props: Heading): VdomElement = {
    val id = getId(props.content)
    val tag = props.level match {
      case 1 => <.h2(Style.fontSize.px32)
      case 2 => <.h3(Style.fontSize.px24)
      case 3 => <.h4(Style.fontSize.px20)
      case 4 => <.h5(Style.fontSize.px20)
    }
    val hash = <.a(
      Style.position.absolute,
      ^.left := "-1em",
      ^.title := "Link to this heading",
      ^.href := s"#$id",
      "#"
    )
    val content = <.span(^.dangerouslySetInnerHtml := props.content)
    tag(Style.position.relative, ^.id := id, content, hash)
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
