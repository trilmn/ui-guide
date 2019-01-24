// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.guide.components.{ExampleRich, ExampleSimple, Markdown, _}
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PageButtonBox {

  private val border = <.div(Style.border.top.borderColor.gray3.margin.top16.padding.top16)

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Box Button")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
           |Button's box [styles], such as `Style.Full`, `Style.Ghost` and
           |`Style.Minimal`, have padding wraps around their label:
           |
           |[styles]: ${ctl.urlFor(Pages.Button("#style")).value}
           |""".stripMargin
      )(),
      ExampleSimple()(BoxExampleButtons()()),
      Markdown(
        """
          |# Emphasis
          |
          |## Type
          |""".stripMargin
      )(),
      BoxExampleEmail()(),
      Markdown(
        """
          |## Color
          |
          |```scala
          |color: ButtonStyle.Color = /* ... */
          |```
          |
          |The `color` parameter controls the color of a box style. Depend on
          |each style, it could be text, background or other properties:
          |""".stripMargin
      )(),
      ExampleSimple()({
        val m = <.div(Style.margin.right8)
        val f = Button.Style.Full
        <.div(
          Style.flexbox.flex,
          m(Button(style = f(color = Button.Color.Blue, icon = Some(Icon.Glyph.Check)))("Mark as Final")),
          m(Button(style = f(icon = Some(Icon.Glyph.Upload)))("Upload")),
          m(Button(style = f(icon = Some(Icon.Glyph.Share)))("Share")),
        )
      }),
      BoxExampleColor()(),
      BoxExampleArchive()(),
      Markdown(
        """
          |## Size
          |
          |### Width
          |
          |```scala
          |isFullWidth: Boolean = false
          |```
          |
          |""".stripMargin
      )(),
      Markdown(
        """
          |### Height
          |
          |```scala
          |height: ButtonStyle.Height = Height.Fix32
          |```
          |
          |""".stripMargin
      )(),
      ExampleSimple()(
        BoxExampleButtons(height = Button.Height.Fix24, content = "Fix24")(),
        border,
        BoxExampleButtons(height = Button.Height.Fix32, content = "Fix32")(),
        border,
        BoxExampleButtons(height = Button.Height.Fix40, content = "Fix40")(),
      ),
      Markdown(
        """
          |# Icon
          |
          |```scala
          |icon: Option[Icon.Name] = None
          |```
          |
          |""".stripMargin
      )(),
      ExampleSimple()(
        BoxExampleButtons(icon = Some(Icon.Glyph.Search), content = "Go")(),
        border,
        BoxExampleButtons(icon = Some(Icon.Glyph.Search), content = EmptyVdom)(),
      ),
      Markdown(
        """
          |# Selected
          |
          |Set `isSelected = true` to always display a button in its active
          |state:
          |
          |```scala
          |isSelected: Boolean = false
          |```
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(isSelected = true)
        )("Full")
      }))(),
      ExampleSimple()(BoxExampleButtons(isSelected = true)()),
      Markdown(
        """
          |This is useful for on-off actions, like text formatting:
        """.stripMargin
      )(),
      BoxExampleFormat()(),
      BoxExampleMenu()(),
      Markdown(
        s"""
           |# Busy
           |
           |```scala
           |isBusy: Boolean = false
           |```
           |
           |When a button's action (eg. the callback at `onClick`) is being
           |handled for a significant time, set `isBusy = true` to
           |replace the button's body with a [circle process indicator][cpi]:
           |
           |[cpi]: ${ctl.urlFor(Pages.ProgressIndicator("#circle")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(isBusy = true)
        )("Title")
      }))(),
      Markdown(
        """
          |This reassures users that our app is not hang or waiting for their
          |input.
        """.stripMargin
      )(),
      ExampleSimple()(BoxExampleButtons(isBusy = true)())
    )
  }
}
