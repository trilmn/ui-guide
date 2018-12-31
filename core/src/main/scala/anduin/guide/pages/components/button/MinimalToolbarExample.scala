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

final case class MinimalToolbarExample() {
  def apply(): VdomElement = MinimalToolbarExample.component(this)
}

object MinimalToolbarExample {

  private type Props = MinimalToolbarExample

  private def button(icon: Icon.Name): VdomElement = {
    Button(style = Button.Style.Minimal(icon = Some(icon)))()
  }

  private def render(props: Props): VdomElement = {
    val sep = <.div(Style.margin.left8.padding.left8.border.left.borderWidth.px2.borderColor.gray4)
    ExampleSimple()(
      <.div(
        Style.flexbox.flex,
        button(Icon.NameAlignLeft),
        button(Icon.NameAlignCenter),
        button(Icon.NameAlignRight),
        button(Icon.NameAlignJustify),
        sep,
        button(Icon.NameBold),
        button(Icon.NameItalic),
        button(Icon.NameUnderline),
        button(Icon.NameStrikeThrough),
        sep,
        button(Icon.NameListBullet),
        button(Icon.NameListNumber),
        button(Icon.NameTable)
      )
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
