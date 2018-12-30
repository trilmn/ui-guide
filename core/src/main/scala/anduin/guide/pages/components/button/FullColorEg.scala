// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class FullColorEg(
  bg: ExampleSimple.BgColor,
  primary: Button.Color,
  secondary: Button.Color
) {
  def apply(): VdomElement = FullColorEg.component(this)
}

object FullColorEg {

  private type Props = FullColorEg

  private def renderButton(color: Button.Color, icon: Icon.Name, text: VdomNode): VdomElement = {
    val button = Button(style = Button.Style.Full(color, icon = Some(icon)))(text)
    <.div(Style.margin.right8, button)
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple(bgColor = props.bg)({
      <.div(
        Style.flexbox.flex,
        renderButton(props.primary, Icon.NameCheck, "Mark as final"),
        renderButton(props.secondary, Icon.NameUpload, "Upload"),
        renderButton(props.secondary, Icon.NameShare, "Share"),
        renderButton(props.secondary, Icon.NameEllipsisHorizontal, EmptyVdom)
      )
    })
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
