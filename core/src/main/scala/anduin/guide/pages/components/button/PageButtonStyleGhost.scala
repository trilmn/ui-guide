// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.button.Button.Color
import anduin.guide.components.{ExampleRich, ExampleSimple, Markdown}
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageButtonStyleGhost() {
  def apply(): VdomElement = PageButtonStyleGhost.component(this)
}

object PageButtonStyleGhost {

  private type Props = PageButtonStyleGhost

  def getHeadings: List[(Int, String)] = Source.getTocHeadings

  private def render(props: Props): VdomElement = <.div(
    Markdown(
      """
        |## Ghost
        |
        |```scala
        |case class Ghost(/* further customization */)
        |```
        |
        |The `Ghost` style has a medium emphasis, thank to its border. They
        |should be used to separate your Button from nearby elements, without
        |attracting too much attention like `Full`:
        |
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      <.div(
        Style.flexbox.flex,
        Button(
          style = Button.Style.Ghost(color = Button.Color.Blue)
        )("Reply"),
        <.div(Style.margin.left8),
        Button(
          style = Button.Style.Ghost()
        )("Forward")
      )
    }))(),
    Markdown(
      """
        |### Color [ghost-color]
        |
        |```scala
        |color: Button.Color = Button.Color.Black
        |```
        |
        |The `color` parameter of a Ghost Button controls its text and border
        |color:
        |
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      Button(
        style = Button.Style.Ghost(color = Button.Color.Blue)
      )("Reply")
    }))(),
    Markdown(
      """
        |The default color of Ghost Button is `Black`. Together with `Blue`
        |and `Red`, they work well on light background:
      """.stripMargin
    )(),
    CommonColorExample(
      bgColor = ExampleSimple.BgColor.White,
      getStyle = color => Button.Style.Ghost(color = color),
      colors = List(Color.Black, Color.Blue, Color.Red),
      default = Some(Color.Black)
    )(),
    Markdown(
      """
        |For black or colored background, there is a `White` color:
      """.stripMargin
    )(),
    CommonColorExample(
      bgColor = ExampleSimple.BgColor.Gray8,
      getStyle = color => Button.Style.Ghost(color = color),
      colors = List(Color.White),
      default = None
    )(),
    Markdown(
      """
        |For further advices on when to use which, please see the [Color
        |Usage](#color-usage) section under Full Style.
        |""".stripMargin
    )(),
    Markdown(
      """
        |### Other parameters [#ghost-other]
        |
        |Like `Full`, `Ghost` also supports other parameters such as
        |[`size`](#full-size), [`icon`](#full-icon),
        |[`isSelected`](#full-selected), [`isFullWidth`](#full-width) and
        |[`isBusy`](#full-busy). Their usages and effects are also similar to
        |those in `Full`.
        |
      """.stripMargin
    )(),
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
