// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.tag.Tag
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[button] final case class ColorIntro(
  getStyle: Button.Color => Button.Style,
  colors: Seq[Button.Color],
  default: Button.Color
) {
  def apply(): VdomElement = ColorIntro.component(this)
}

private[button] object ColorIntro {

  private type Props = ColorIntro

  private val tag: VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.justifyCenter.margin.top8,
      Tag(color = Tag.ColorBlue)("Default")
    )
  }

  private def renderButton(props: Props)(color: Button.Color): VdomElement = {
    val name = color.getClass.getSimpleName
    val tagOpt = if (props.default == color) Some(tag) else None
    val button = Button(style = props.getStyle(color))(name)
    <.div(^.key := name, Style.margin.right8, button, tagOpt)
  }

  private def render(props: Props): VdomElement = {
    <.div(
      Style.flexbox.flex,
      props.colors.toVdomArray(renderButton(props))
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
