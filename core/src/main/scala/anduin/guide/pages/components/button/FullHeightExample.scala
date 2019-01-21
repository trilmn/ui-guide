// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.button.Button.Height._
import anduin.component.tag.Tag
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class FullHeightExample(
  getStyle: ButtonStyle.Height => ButtonStyle,
) {
  def apply(): VdomElement = FullHeightExample.component(this)
}

object FullHeightExample {

  private type Props = FullHeightExample

  private val sizes = List(Fix24, Fix32, Fix40)

  private val tag: VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.justifyCenter.margin.top8,
      Tag(color = Tag.ColorBlue)("Default")
    )
  }

  private def renderButton(props: Props)(height: ButtonStyle.Height): VdomElement = {
    val name = height.getClass.getSimpleName
    val tagOpt = if (height == Fix32) Some(tag) else None
    val button = Button(style = props.getStyle(height))(name)
    <.div(^.key := name, Style.margin.right8, button, tagOpt)
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple()({
      <.div(
        Style.flexbox.flex,
        sizes.toVdomArray(renderButton(props))
      )
    })
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
