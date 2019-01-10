package anduin.guide.pages.components.progressindicator

import anduin.component.card.Card
import anduin.component.icon.Illus
import anduin.component.progressindicators.{BarIndicator, BlockIndicator, CircleIndicator}
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PageProgressIndicator {

  private val marginR = Style.margin.right16
  private val marginB = Style.margin.bottom16

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Progress Indicators")(),
      Toc(Source.getTocHeadings)(),
      Markdown(
        """
          |Progress Indicators inform that an operation is in progress:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(Style.color.primary4, CircleIndicator()())
      }))(),
      Markdown(
        """
          |They reassure that the app is not hung or waiting for user input.
          |[In some cases](#percent), they can also display how far the
          |operation has been processed.
          |
          |There are 2 types of indicators: [Circle](#circle) and [Bar](#bar):
        """.stripMargin
      )(),
      Markdown(
        """
          |# Circle
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        CircleIndicator()()
      }))(),
      Markdown(
        """
          |## Color [circle-color]
          |
          |The color of Circle Indicator is inherited from its parent:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex,
          <.div(CircleIndicator()(), marginR),
          <.div(CircleIndicator()(), Style.color.gray6, marginR),
          <.div(CircleIndicator()(), Style.color.primary4, marginR)
        )
      }))(),
      Markdown(
        """
          |
          |## Size
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex.color.primary4,
          <.div(CircleIndicator()(), marginR),
          <.div(CircleIndicator(size = CircleIndicator.Size.Px48)(), marginR)
        )
      }))(),
      Markdown(
        """
          |
          |# Bar
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(Style.width.px128, BarIndicator()())
      }))(),
      Markdown(
        """
          |
          |""".stripMargin
      )(),
      ExampleSimple(bgColor = ExampleSimple.BgColor.Gray2)({
        <.div(
          Style.backgroundColor.white.border.all.borderColor.gray3,
          Style.width.px256,
          <.div(
            Style.padding.all8.flexbox.flex.flexbox.itemsCenter,
            <.div(
              Style.margin.right4,
              Illus(name = Illus.NameDoc)()
            ),
            <.div(
              Style.lineHeight.px16,
              <.p("Sample Document"),
              <.p(Style.fontSize.px12.color.gray6, "Uploading")
            )
          ),
          <.div(
            Style.color.gray6,
            BarIndicator()()
          )
        )
      }),
      Markdown(
        """
          |
          |## Color [bar-color]
          |
          |Similar to Circle, Bar Indicator's color is inherits from its parent:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.width.px128,
          <.div(BarIndicator()(), marginB),
          <.div(BarIndicator()(), Style.color.gray6, marginB),
          <.div(BarIndicator()(), Style.color.primary4)
        )
      }))(),
      Markdown(
        """
          |## Width
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.color.primary4,
          <.div(BarIndicator()(), marginB),
          <.div(BarIndicator()(), Style.width.pc60, marginB),
          <.div(BarIndicator()(), Style.width.px128)
        )
      }))(),
      Markdown(
        """
          |## Percent
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.color.primary4.width.px256,
          BarIndicator(percent = Some(0.3))()
        )
      }))(),
      Markdown(
        """
          |# Block
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator()()
      }))(),
      Markdown(
        """
          |## Title
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator(title = Some("Loading Deal…"))()
      }))(),
      Markdown(
        """
          |## Indicator
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator(
          indicator = <.div(Style.width.px128, BarIndicator()())
        )()
      }))(),
      Markdown(
        """
          |
          |## Height
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.height.px128,
          BlockIndicator(isFullHeight = true)()
        )
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
