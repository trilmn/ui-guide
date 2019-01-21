// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.Icon
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class FullColorExample(
  bg: ExampleSimple.BgColor,
  primary: ButtonStyle.Color,
  secondary: ButtonStyle.Color
) {
  def apply(): VdomElement = FullColorExample.component(this)
}

object FullColorExample {

  private type Props = FullColorExample

  private def renderButton(color: ButtonStyle.Color, icon: Icon.Name, text: VdomNode): VdomElement = {
    val button = Button(style = Button.Style.Full(color, icon = Some(icon)))(text)
    <.div(Style.margin.right8, button)
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple(bgColor = props.bg)({
      <.div(
        Style.flexbox.flex,
        renderButton(props.primary, Icon.Glyph.Check, "Mark as final"),
        renderButton(props.secondary, Icon.Glyph.Upload, "Upload"),
        renderButton(props.secondary, Icon.Glyph.Share, "Share"),
        renderButton(props.secondary, Icon.Glyph.EllipsisHorizontal, EmptyVdom)
      )
    })
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
