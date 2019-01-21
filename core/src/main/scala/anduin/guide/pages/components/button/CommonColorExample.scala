// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.tag.Tag
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[button] final case class CommonColorExample(
  bgColor: ExampleSimple.BgColor,
  getStyle: ButtonStyle.Color => ButtonStyle,
  colors: Seq[ButtonStyle.Color],
  default: Option[ButtonStyle.Color]
) {
  def apply(): VdomElement = CommonColorExample.component(this)
}

private[button] object CommonColorExample {

  private type Props = CommonColorExample

  private val tag: VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.justifyCenter.margin.top8,
      Tag(color = Tag.ColorBlue)("Default")
    )
  }

  private def renderButton(props: Props)(color: ButtonStyle.Color): VdomElement = {
    val name = color.getClass.getSimpleName
    val tagOpt = if (props.default.contains(color)) Some(tag) else None
    val button = Button(style = props.getStyle(color))(name)
    <.div(^.key := name, Style.margin.right8, button, tagOpt)
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple(
      bgColor = props.bgColor
    )({
      <.div(
        Style.flexbox.flex,
        props.colors.toVdomArray(renderButton(props))
      )
    })
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
