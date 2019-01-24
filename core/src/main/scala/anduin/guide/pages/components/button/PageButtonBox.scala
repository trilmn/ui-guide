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
      ExampleSimple()(BoxExampleButtons()()),
      Markdown(
        s"""
           |Box buttons have the following in common:
           |
           |# Icon
           |
           |```scala
           |icon: Option[Icon.Name] = None
           |```
           |
           |Box buttons support an `icon` parameter which places [an icon] next
           |to a button's label to clarify its action:
           |
           |[an icon]: ${ctl.urlFor(Pages.Icon("#name")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(icon = Some(Icon.Glyph.Search))
        )("Search")
      }))(),
      Markdown(
        """
          |Button's label can also be omitted when the icon (and nearby context)
          |is enough to represent the action. This is common in toolbars where
          |related icons are grouped together:
        """.stripMargin
      )(),
      ExampleSimple()(BoxExampleEmail.toolbar),
      Markdown(
        """
          |# Hierarchy
          |
          |It's common to have multiple buttons the same view. In these cases,
          |use their appearances and placements to communicate their levels
          |of emphasis to users.
          |
          |## Style
          |
          |```scala
          |style: ButtonStyle = Button.Style.Full()
          |```
          |
          |The `style` prop define a button's essential appearance. Buttons
          |in the same group should have the same style, which represent the
          |level of emphasis of the whole group:
          |""".stripMargin
      )(),
      BoxExampleEmail()(),
      Markdown(
        """
          |Because of this, avoid mixing different button styles in the same
          |group of buttons. Color should be used to separate primary from
          |secondary actions in the same group.
          |
          |## Color
          |
          |```scala
          |color: ButtonStyle.Color = /* ... */
          |```
          |
          |The `color` parameter controls the color of a button. Depend on
          |each style, it could be text, background or other properties:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(color = Button.Color.Blue)
        )("Submit")
      }))(),
      Markdown(
        """
          |Most colors work best on white background, with the exception of
          |`White` of `Ghost` and `Minimal` styles, which are prepared for
          |dark or colorful background only:
        """.stripMargin
      )(),
      BoxExampleColor()(),
      Markdown(
        """
          |### Color Usage
          |
          |`Color.Blue` is usually used to highlight the primary action among
          |a group of related buttons:
        """.stripMargin
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
      Markdown(
        """
          |`Color.Red` should be used for destructive actions, such as
          |archiving deals. Usually only the final actions, like those in
          |confirmation modals, need the color Red:
        """.stripMargin
      )(),
      BoxExampleArchive()(),
      Markdown(
        """
          |# Size
          |
          |## Width
          |
          |```scala
          |isFullWidth: Boolean = false
          |```
          |
          |""".stripMargin
      )(),
      Markdown(
        """
          |## Height
          |
          |```scala
          |height: ButtonStyle.Height = Height.Fix32
          |```
          |
          |""".stripMargin
      )(), {
        import Button.Height._
        val i = Some(Icon.Glyph.Home)
        ExampleSimple()(
          BoxExampleButtons(icon = i, height = Fix24, content = "Fix24")(),
          border,
          BoxExampleButtons(icon = i, height = Fix32, content = "Fix32")(),
          border,
          BoxExampleButtons(icon = i, height = Fix40, content = "Fix40")(),
        )
      },
      Markdown(
        """
          |# Status
          |
          |## Selected
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
           |## Busy
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
