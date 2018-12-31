// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.guide.components.{ExampleRich, ExampleSimple, Markdown}
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageButtonStyleMinimal() {
  def apply(): VdomElement = PageButtonStyleMinimal.component(this)
}

object PageButtonStyleMinimal {

  private type Props = PageButtonStyleMinimal

  def getHeadings: List[(Int, String)] = Source.getTocHeadings

  private def render(props: Props): VdomElement = <.div(
    Markdown(
      """
        |## Minimal
        |
        |```scala
        |case class Minimal(/* further customization */)
        |```
        |
        |`Minimal` style is text-only, thus having the lowest emphasis:
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      <.div(
        Style.flexbox.flex,
        Button(
          style = Button.Style.Minimal(color = Button.Color.Blue)
        )("Submit"),
        Button(
          style = Button.Style.Minimal()
        )("Cancel")
      )
    }))(),
    Markdown(
      """
        |`Minimal` should only be used where the surrounding context already
        |provided enough visual hint that they are interactive content. For 
        |example, inside a Toolbar:
        |""".stripMargin
    )(),
    MinimalToolbarExample()(),
    Markdown(
      """
        |### Parameters
        |
        |`Minimal` supports the same parameters as `Ghost`, which are
        |[`color`](#ghost-color), [`size`](#full-size), [`icon`](#full-icon),
        |[`isSelected`](#full-selected), [`isFullWidth`](#full-width) and
        |[`isBusy`](#full-busy). Their usages and default values are the same.
        |""".stripMargin
    )(),
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
