// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button.Color._
import anduin.component.button.Button.Style._
import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.Icon
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class BoxExampleButtons(
  height: ButtonStyle.Height = Button.Height.Fix32,
  icon: Option[Icon.Name] = None,
  isSelected: Boolean = false,
  isBusy: Boolean = false,
  content: VdomNode = "Text"
) {
  def apply(): VdomElement = BoxExampleButtons.component(this)
}

object BoxExampleButtons {

  private type Props = BoxExampleButtons

  private def getFull(c: ButtonStyle.Color, props: Props): ButtonStyle =
    Full(c, props.height, props.icon, isSelected = props.isSelected, isBusy = props.isBusy)

  private def getGhost(c: ButtonStyle.Color, props: Props): ButtonStyle =
    Ghost(c, props.height, props.icon, isSelected = props.isSelected, isBusy = props.isBusy)

  private def getMinimal(c: ButtonStyle.Color, props: Props): ButtonStyle =
    Minimal(c, props.height, props.icon, isSelected = props.isSelected, isBusy = props.isBusy)

  private val small = <.div(Style.margin.right8)
  private val big = <.div(Style.border.right.borderColor.gray3.height.px16.padding.right16.margin.right16)

  private def render(props: Props): VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.itemsCenter,
      React.Fragment(Button(getFull(Blue, props))(props.content), small),
      React.Fragment(Button(getFull(White, props))(props.content), big),
      React.Fragment(Button(getGhost(Blue, props))(props.content), small),
      React.Fragment(Button(getGhost(Black, props))(props.content), big),
      React.Fragment(Button(getMinimal(Blue, props))(props.content), small),
      React.Fragment(Button(getMinimal(Black, props))(props.content)),
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
