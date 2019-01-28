// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.React

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PageButtonBox {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Box Button")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Box buttons are buttons that use `Style.Full`, `Style.Ghost` or
          |`Style.Minimal`. They all have padding around their label, result
          |in box-like appearances:
        """.stripMargin
      )(),
      ExampleSimple()({
        import Button.Style._
        import Button.Color._
        val small = <.div(Style.margin.right8)
        val big = <.div(Style.border.right.borderColor.gray3.height.px16.padding.right16.margin.right16)
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          React.Fragment(Button(Full(Blue))("Full"), small),
          React.Fragment(Button(Full(White))("Full"), big),
          React.Fragment(Button(Ghost(Blue))("Ghost"), small),
          React.Fragment(Button(Ghost(Black))("Ghost"), big),
          React.Fragment(Button(Minimal(Blue))("Minimal"), small),
          React.Fragment(Button(Minimal(Black))("Minimal")),
        )
      }),
      Markdown(
        s"""
           |
           |
           |Box buttons have the following in common:
           |
           |# Icon
           |
           |```scala
           |icon: Option[Icon.Name] = None
           |```
           |
           |Box buttons support an `icon` parameter which places [an icon] next
           |to the button's label to clarify its action:
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
          |The `style` prop define a button's essential appearance:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(style = Button.Style.Ghost())("Label")
      }))(),
      Markdown(
        """
          |There are 03 styles, from high to low emphasis: `Full`, `Ghost` and
          |`Minimal`. Buttons in the same group should have the same style,
          |which represent the level of emphasis of that group:
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
          |By default, a button's width depends on its content. To make
          |the button's width the same as its container's (so it's
          |controllable), set `isFullWidth = true`:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.width.px256,
          Button(
            style = Button.Style.Full(isFullWidth = true)
          )("Full Width Button")
        )
      }))(),
      Markdown(
        """
          |The content of a full-width button, including its icon if defined,
          |is centered by default:
        """.stripMargin
      )(),
      ExampleSimple()({
        val s = Button.Style.Full(isFullWidth = true, icon = Some(Icon.Glyph.Reply))
        <.div(Style.width.px256, Button(s)("Reply"))
      }),
      Markdown(
        """
          |## Height
          |
          |```scala
          |height: ButtonStyle.Height = Height.Fix32
          |```
          |
          |The `height` parameter controls not ony a button's height but
          |also its font size, padding and icon to have unified results:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(
            icon = Some(Icon.Glyph.FileGenerate),
            height = Button.Height.Fix40
          )
        )("New file")
      }))(),
      Markdown(
        """
          |There are 2 types of height. The first one are fixed values, named
          |by their height in pixel. These are suitable for single-line
          |buttons, which should be most cases:
          |""".stripMargin
      )(),
      BoxExampleHeight()(),
      Markdown(
        """
          |The other one is `Free`, which has no pre-defined styles. It allows
          |having multiple lines or other complex layout inside the button's
          |body:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(height = Button.Height.Free)
        )(
          <.span(
            Style.flexbox.flex.flexbox.column.flexbox.itemsCenter,
            Style.padding.ver8.padding.hor12,
            Icon(name = Icon.Glyph.LightBolt)(),
            <.span(Style.margin.top4, "Strike")
          )
        )
      }))(),
      Markdown(
        """
          |Note that buttons will be rendered as `a` or `button` tags, so
          |ensure your custom body is valid as their's children. For example,
          |[avoid having `div` inside buttons' body][so].
          |
          |[so]: https://stackoverflow.com/q/12982269
          |
          |""".stripMargin
      )(),
      Markdown(
        """
          |# Status
          |
          |## Selected
          |
          |```scala
          |isSelected: Boolean = false
          |```
          |
          |Set `isSelected = true` to always display a button in its
          |active/pressed state:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          style = Button.Style.Full(isSelected = true)
        )("Full")
      }))(),
      Markdown(
        """
          |This is useful for on-off actions, like text formatting:
        """.stripMargin
      )(),
      BoxExampleFormat()(),
      Markdown(
        """
          |Or to indicate the connection between elements, such as a popover's
          |content (e.g. a menu) and its target (e.g. a button):
        """.stripMargin
      )(),
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
        s"""
          |This reassures users that our app is not hang or waiting for their
          |input. It also displays the button's active/pressed appearance
          |(similar to having [`isSelected = true`](#selected)).
        """.stripMargin
      )()
    )
  }
}
