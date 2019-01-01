// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.guide.app.main.Pages
import anduin.guide.components.Markdown
import anduin.mcro.Source

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[button] final case class PageButtonStyle(ctl: Pages.Ctl) {
  def apply(): VdomElement = PageButtonStyle.component(this)
}

private[button] object PageButtonStyle {

  private type Props = PageButtonStyle

  def getHeadings: Seq[(Int, String)] =
    Source.getTocHeadings ++
      PageButtonStyleFull.getHeadings ++
      PageButtonStyleGhost.getHeadings ++
      PageButtonStyleMinimal.getHeadings ++
      PageButtonStyleLink.getHeadings

  private def render(props: Props): VdomElement = <.div(
    Markdown(
      """
        |# Style
        |
        |```scala
        |style: Button.Style = Button.Style.Full
        |```
        |
        |The `style` prop defines Button's appearance. There are four
        |styles, with further customization (e.g. color) in each:
        |
        |""".stripMargin
    )(),
    PageButtonStyleFull(props.ctl)(),
    PageButtonStyleGhost()(),
    PageButtonStyleMinimal()(),
    PageButtonStyleLink()()
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
