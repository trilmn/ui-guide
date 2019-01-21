// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.progressindicator

import anduin.component.icon.Icon
import anduin.component.progressindicators.BarIndicator
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class BarExample() {
  def apply(): VdomElement = BarExample.component(this)
}

object BarExample {

  private type Props = BarExample

  private def render(props: Props): VdomElement = {
    ExampleSimple(bgColor = ExampleSimple.BgColor.Gray2)(
      <.div(
        Style.backgroundColor.white.border.all.borderColor.gray3,
        Style.width.px256,
        <.div(
          Style.padding.all8.flexbox.flex.flexbox.itemsCenter,
          <.div(
            Style.margin.right4,
            Icon(name = Icon.File.Word, size = Icon.Size.Px32)()
          ),
          <.div(
            Style.lineHeight.px16,
            <.p("Sample Document"),
            <.p(Style.fontSize.px12.color.gray6, "Uploading…")
          )
        ),
        <.div(
          Style.color.primary4,
          BarIndicator()()
        )
      )
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
