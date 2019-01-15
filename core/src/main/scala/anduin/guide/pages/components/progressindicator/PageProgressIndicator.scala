package anduin.guide.pages.components.progressindicator

import anduin.component.button.Button
import anduin.component.progressindicators.{BarIndicator, BlockIndicator, CircleIndicator}
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
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
          |These indicators reassure that the app is not hung or waiting for
          |user input. [In some cases](#percent), they can also display how far
          |the operation has been processed.
          |
          |There are 2 types of indicators: [Circle](#circle) and [Bar](#bar):
        """.stripMargin
      )(),
      Markdown(
        """
          |# Circle
          |
          |`CircleIndicator` animate the indicator along a circular track,
          |covering a square area:
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
          |## Size
          |
          |```scala
          |size: CircleIndicator.Size = CircleIndicator.Size.Px16
          |```
          |
          |`CircleIndicator` supports 2 sizes: `Px16` (default) and `Px48`.
          |Each size has its own usages:
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
        s"""
           |**`Px16` is suitable for small areas** like inside a button (to
           |indicate the progress of button's action). In fact, it is already
           |supported via Button's [isBusy][busy] prop:
           |
           |[busy]: ${ctl.urlFor(Pages.Button("#full-busy")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(isBusy = true)
        )("Sample Label")
      }))(),
      Markdown(
        """
          |**Meanwhile, `Px48` is suitable for large areas** such as page's
          |or tab's content. In practice, you should usually use
          |[`BlockIndicator`](#block) for these cases to have proper spacing
          |around the indicator.
          |
          |# Bar
          |
          |`BarIndicator` animates the indicator along a horizontal bar:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(Style.width.px128, BarIndicator()())
      }))(),
      Markdown(
        """
          |Due to this appearance, it is usually attached to an existed
          |element to indicate a related progress:
          |""".stripMargin
      )(),
      BarExample()(),
      Markdown(
        """
          |
          |## Color [bar-color]
          |
          |Similar to `CircleIndicator`, `BarIndicator`'s color is inherits from
          |its parent:
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
          |The width of a `BarIndicator` is the same as its parent (similar to
          |`Style.display.block`):
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
          |
          |```scala
          |percent: Some[Double] = None
          |```
          |
          |By default, `BarIndicator` displays an indeterminate progress.
          |Passing a value from 0.0 to 1.0 to the `percent` prop to display a
          |determinate progress:
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
          |
          |`BlockIndicator` helps use progress indicators for large areas like
          |page or tab's content easier. The indicator will be placed at the
          |center of an area with full width and 256-pixel height:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator()()
      }))(),
      Markdown(
        """
          |## Title
          |
          |```scala
          |title: Option[String] = None
          |```
          |
          |`BlockIndicator` supports a `title` prop to explain what is the
          |progress about:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator(title = Some("Loading Deal…"))()
      }))(),
      Markdown(
        """
          |## Indicator
          |
          |```scala
          |indicator: VdomElement = CircleIndicator(...)()
          |```
          |
          |`BlockIndicator` uses a 48-pixel `CircleIndicator` by default. This
          |can be replaced via the `indicator` prop. For example, to use
          |`BarIndicator`:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        BlockIndicator(
          indicator = <.div(Style.width.px128, BarIndicator()())
        )()
      }))(),
      Markdown(
        """
          |It's worth to note that `BlockIndicator` already provided
          |`Style.color.primary4` to its indicator. This can easily be
          |overridden by the consumer via [CSS inheritance][mdn].
          |
          |[mdn]: https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS/Cascade_and_inheritance
          |
          |## Height
          |
          |```scala
          |isFullHeight: Boolean = false
          |```
          |
          |The default 256-pixel height should work fine for most cases.
          |However, `BlockIndicator` can also be set to use a custom height
          |to better represent its target content:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.height.px128,
          BlockIndicator(isFullHeight = true)()
        )
      }))()
    )
  }
}
