package anduin.guide.component

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class Heading(
  content: String,
  level: Int,
) {
  def apply(): VdomElement = {
    Heading.component(this)
  }
}

object Heading {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[Heading, _]) {
    def render(props: Heading): VdomElement = {
      val id = props.content
        .replaceAll("<.*?>", "")
        .toLowerCase()
        .replaceAll("[^\\w]+", "-")
      val tag = props.level match {
        case 1 => <.h2
        case 2 => <.h3
        case 3 => <.h4
      }
      tag(
        <.span(^.dangerouslySetInnerHtml := props.content),
        <.a(Style.margin.left8, ^.id := id, ^.href := s"#$id", "#")
      )
    }
  }

  private val component = ScalaComponent
    .builder[Heading](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
