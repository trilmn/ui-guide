package anduin.guide.components

import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class ExampleRich(
  content: (String, VdomElement),
  isBgGray: Boolean = false
) {
  def apply(): VdomElement = ExampleRich.component(this)
}

object ExampleRich {

  private type Props = ExampleRich

  private def render(props: Props): VdomElement = {
    <.figure(Style.padding.ver16, Example(props.content, props.isBgGray)())
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
