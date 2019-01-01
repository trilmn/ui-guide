// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.button.Button.Size._
import anduin.component.tag.Tag
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class FullSizeExample(
  getStyle: Button.Size => Button.Style,
) {
  def apply(): VdomElement = FullSizeExample.component(this)
}

object FullSizeExample {

  private type Props = FullSizeExample

  private val sizes = List(Fix24, Fix32, Fix40)

  private val tag: VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.justifyCenter.margin.top8,
      Tag(color = Tag.ColorBlue)("Default")
    )
  }

  private def renderButton(props: Props)(size: Button.Size): VdomElement = {
    val name = size.getClass.getSimpleName
    val tagOpt = if (size == Fix32) Some(tag) else None
    val button = Button(style = props.getStyle(size))(name)
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
