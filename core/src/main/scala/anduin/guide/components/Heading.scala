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

  private def getTag(props: Props) = props.level match {
    case 1 => <.h2(Style.fontSize.px32)
    case 2 => <.h3(Style.fontSize.px24.fontWeight.bold)
    case 3 => <.h4(Style.fontSize.px20)
    case 4 => <.h5(Style.fontSize.px20)
  }

  private def renderLinkSelf(id: String): VdomElement = {
    <.a(
      Style.position.absolute.fontWeight.normal,
      ^.left := "-1em",
      ^.title := "Link to this heading",
      ^.href := s"#$id",
      "#"
    )
  }

  private val linkTop: VdomElement = {
    <.a(
      Style.position.absolute.fontWeight.normal,
      Style.coordinate.right0.color.gray4.transition.all,
      Style.hover.underlineNone.hover.colorPrimary4,
      ^.title := "Go to top",
      ^.href := "#",
      "↑"
    )
  }

  private def render(props: Props): VdomElement = {
    val id = getId(props.content)
    getTag(props)(
      Style.position.relative,
      ^.id := id,
      <.span(^.dangerouslySetInnerHtml := props.content),
      renderLinkSelf(id),
      linkTop
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
