// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.button.Button.Color._
import anduin.component.button.Button.Style._
import anduin.component.tag.Tag
import anduin.guide.components.{DemoState, ExampleSimple}
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[button] final case class BoxExampleColor() {
  def apply(): VdomElement = BoxExampleColor.component(this)
}

private[button] object BoxExampleColor {

  private type Props = BoxExampleColor

  private case class StyleRow(
    name: String,
    default: ButtonStyle.Color,
    getStyle: ButtonStyle.Color => ButtonStyle
  )

  private def renderColor(row: StyleRow)(color: ButtonStyle.Color): VdomElement = {
    <.div(
      ^.key := color.getClass.getSimpleName,
      Style.flexbox.flex.flexbox.column.flexbox.itemsCenter.margin.right12,
      Button(style = row.getStyle(color))(color.getClass.getSimpleName),
      TagMod.when(row.default == color) {
        <.div(Style.margin.top8, Tag(color = Tag.ColorBlue, isSolid = true)("Default"))
      }
    )
  }

  private def renderLabel(isDark: Boolean, row: StyleRow): VdomElement = {
    <.p(
      Style.fontWeight.semiBold.lineHeight.px32.width.px64.margin.right24,
      if (isDark) Style.color.white else Style.color.gray8,
      row.name
    )
  }

  private def renderRow(isDark: Boolean)(row: StyleRow): VdomElement = {
    <.div(
      ^.key := row.name,
      Style.flexbox.flex.flexbox.itemsStart,
      Style.border.top.borderColor.gray3.margin.top16.padding.top16,
      renderLabel(isDark, row),
      List(White, Black, Red, Blue).toVdomArray(renderColor(row))
    )
  }

  private val render = ExampleSimple()(
    DemoState.BoolF.copy(
      render = (isDark, setDark) => {
        <.div(
          ^.margin := "-16px",
          Style.padding.all16,
          TagMod.when(isDark)(Style.backgroundColor.gray8),
          Button(
            style = Full(color = if (isDark) Black else White),
            onClick = setDark(!isDark)
          )("Toggle Bg Color"),
          List(
            StyleRow("Full", White, c => Full(c)),
            StyleRow("Ghost", Black, c => Ghost(c)),
            StyleRow("Minimal", Black, c => Minimal(c)),
          ).toVdomArray(renderRow(isDark))
        )
      }
    )()
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .renderStatic(render)
    .build
}
