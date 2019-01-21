// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.button.Button.Color
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PageButtonText {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Ghost Button", Some(Button.Style.Ghost))(),
      Toc(headings = Source.getTocHeadings)(),
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
            style = Button.Style.Text()
          )("Accept"),
          " or ",
          Button(
            style = Button.Style.Text(color = Button.Color.Red)
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
          Button(style = Button.Style.Text())("remind him"),
          " or ",
          Button(style = Button.Style.Text(color = Button.Color.Red))("cancel it"),
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
          style = Button.Style.Text(color = Button.Color.Red)
        )("Decline")
      }))(),
      Markdown(
        """
          |There are 3 colors to choose from, with `Blue` is the default one:
          |""".stripMargin
      )(),
      CommonColorExample(
        bgColor = ExampleSimple.BgColor.White,
        getStyle = color => Button.Style.Text(color = color),
        colors = List(Color.Black, Color.Blue, Color.Red),
        default = None
      )()
    )
  }

}
