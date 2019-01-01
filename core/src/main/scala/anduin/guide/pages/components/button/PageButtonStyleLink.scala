// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.button.Button.Color
import anduin.guide.components.{ExampleRich, ExampleSimple, Markdown}
import anduin.mcro.Source

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageButtonStyleLink() {
  def apply(): VdomElement = PageButtonStyleLink.component(this)
}

object PageButtonStyleLink {

  private type Props = PageButtonStyleLink

  def getHeadings: List[(Int, String)] = Source.getTocHeadings

  private def render(props: Props): VdomElement = <.div(
    Markdown(
      """
        |## Link [style-link]
        |
        |```scala
        |case class Link(/* further customization */)
        |```
        |
        |The `Link` style makes a Button looks exactly like a link (i.e. one
        |that is rendered via [`a`][mdn-a] tag):
        |
        |[mdn-a]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      <.p(
        Button(
          style = Button.Style.Link()
        )("Accept"),
        " or ",
        Button(
          style = Button.Style.Link(color = Button.Color.Red)
        )("Decline")
      )
    }))(),
    Markdown(
      """
        |`Link` is usually used for Buttons that are parts of a sentence,
        |like words or phrases (i.e. [inline elements][mdn-inline]):
        |
        |[mdn-inline]: https://developer.mozilla.org/en-US/docs/Web/HTML/Inline_elements
        |""".stripMargin
    )(),
    ExampleSimple()({
      <.p(
        "John has not accepted your invitation yet. You can ",
        Button(style = Button.Style.Link())("remind him"),
        " or ",
        Button(style = Button.Style.Link(color = Button.Color.Red))("cancel it"),
        "."
      )
    }),
    Markdown(
      """
        |### Color [link-color]
        |
        |```scala
        |color: Button.Color = Button.Color.Blue
        |```
        |
        |The `color` parameter of a Link Button defines its text color:
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      Button(
        style = Button.Style.Link(color = Button.Color.Red)
      )("Decline")
    }))(),
    Markdown(
      """
        |There are 3 colors to choose from, with `Blue` is the default one:
        |""".stripMargin
    )(),
    CommonColorExample(
      bgColor = ExampleSimple.BgColor.White,
      getStyle = color => Button.Style.Link(color = color),
      colors = List(Color.Black, Color.Blue, Color.Red),
      default = None
    )()
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
