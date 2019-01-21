// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class IconSizeSample(name: Icon.Name) {
  def apply(): VdomElement = IconSizeSample.component(this)
}

object IconSizeSample {

  private type Props = IconSizeSample

  private def renderSize(name: Icon.Name)(size: Icon.Size): VdomElement = {
    <.div(
      ^.key := size.getClass.getSimpleName,
      IconSample(name, size, Style.padding.all4)()
    )
  }

  private def renderSizes(name: Icon.Name): VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.itemsEnd,
      Vector(Icon.Size.Px32, Icon.Size.Px24, Icon.Size.Px16).toVdomArray(renderSize(name))
    )
  }

  private def renderLabel(name: Icon.Name): VdomElement = {
    <.p(
      Style.padding.top4.margin.top4.border.top.borderColor.gray3.color.gray7,
      Style.fontSize.px13.lineHeight.px20.textAlign.center,
      name.getClass.getSimpleName
    )
  }

  private def render(props: Props): VdomElement = {
    <.div(renderSizes(props.name), renderLabel(props.name))
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
