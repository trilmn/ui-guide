package anduin.guide.page

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.IconAcl
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageButtonStyle {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "ButtonStyle",
          description = ""
        )()
      ),
      Markdown(
        """
          |# Snippet
          |
          |  color: ButtonStyle.Color = ButtonStyle.ColorWhite,
          |  style: ButtonStyle.Style = ButtonStyle.StyleFull,
          |  size: ButtonStyle.Size = ButtonStyle.SizeMedium,
          |  isFullWidth: Boolean = false,
          |  isSelected: Boolean = false
          |```scala
          |ButtonStyle(
          |)()
          |
          |
          |```
          |# Appearance
          |
          |## `Style`
          |
          |The `style` controls a button's overall style, allow engineers to use button based on its semantic sense instead of appearance.
          |""".stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.div(/*<*/Button(style = ButtonStyle.StyleFull)("Full Style")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(style = ButtonStyle.StyleMinimal)("Minimal Style")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(style = ButtonStyle.StyleLink)("Link Style")/*>*/, Style.margin.right16)
          )/*<*/
        )
        // format: on
      )(),
      Markdown(s"""
                  |**`StyleFull`** is the default value, which provides background, border and shadow to make the button looks interact-able and attractive. `StyleFull` should be used to make a button stand out from other types of content surrounding it.
                  |
          |### `StyleMinimal`
                  |
          |`StyleMinimal` is a subtle, text-only alternative that comes in handy when the button is repetitive (like action in each table row) or you have several buttons in a row (like in a toolbar):
                  |""".stripMargin)(), {
        val button = Button(size = ButtonStyle.SizeIcon, style = ButtonStyle.StyleMinimal)
        val sep = <.div(Style.margin.left8.padding.left8.border.left.borderWidth.px2.borderColor.gray4)
        ExampleSimple()(
          <.div(
            Style.flexbox.flex,
            button(IconAcl(name = IconAcl.NameAlignLeft)()),
            button(IconAcl(name = IconAcl.NameAlignCenter)()),
            button(IconAcl(name = IconAcl.NameAlignRight)()),
            button(IconAcl(name = IconAcl.NameAlignJustify)()),
            sep,
            button(IconAcl(name = IconAcl.NameBold)()),
            button(IconAcl(name = IconAcl.NameItalic)()),
            button(IconAcl(name = IconAcl.NameUnderline)()),
            button(IconAcl(name = IconAcl.NameStrikeThrough)()),
            sep,
            button(IconAcl(name = IconAcl.NameListBullet)()),
            button(IconAcl(name = IconAcl.NameListNumber)()),
            button(IconAcl(name = IconAcl.NameTable3)())
          )
        )
      },
      Markdown(
        """
          |>::warning::`StyleMinimal` should only be used when there is enough context to call out the interaction. If it's not clear, use `StyleFull` to provide visual hint.
          |
          |### `StyleLink`
          |
          |`StyleLink` makes the button looks exactly like a link, commonly used as an `inline` element, like in a sentence or paragraph:
          |""".stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.p(
            "John has not accepted your invitation yet.",
            " ",
            Button(
              onClick = Callback.alert("Reminded"),
              style = ButtonStyle.StyleLink
            )("Remind him.")
          )
        )
      )(),
      Markdown("""
          |## `Color`
                  |
          |Use the `color` prop to communicate the intention or result of the action:
                  |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button()("Default")/*>*/),
            <.div(/*<*/Button(color = ButtonStyle.ColorPrimary)("Primary")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = ButtonStyle.ColorSuccess)("Success")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = ButtonStyle.ColorWarning)("Warning")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = ButtonStyle.ColorDanger)("Danger")/*>*/, Style.margin.left16)
          )/*<*/
        )
        // format: on
      )(),
      Markdown(
        """
          |
          |**`ColorPrimary`** should be saved for the strongest action in the current view. Using multiple `ColorPrimary`, especially with the default `StyleFull`, could lead to a confuse and too heavy UI.
          |
          |**`ColorSuccess`** is originally meant for positive actions. However, in reality it is rarely used since the positive actions are often the primary ones, thus used `ColorPrimary` already.
          |
          |**`ColorWarning`** is meant for warning actions and "Demo" mode. Since warning actions are quite close to danger ones, they often used `ColorDanger` anyway. Therefore, in reality most of `ColorWarning` instances are for "Demo"-related actions.
          |
          |**`ColorDanger`** should be used for actual destructive or negative actions, like in final or confirm step of deleting something. `ColorDanger` should not be used for all "possibly danger" or negative ones, since it would be too heavy.
          |
          |### Usage notes
          |
          |**Avoid having `ColorSuccess` and `ColorPrimary` in the same view,** as users might be confused about which action to take:
          |""".stripMargin
      )(),
      ExampleSimple("**GOOD:** Users know which is the primary action that should be taken")(
        <.div(
          Style.flexbox.flex,
          <.div(Button(color = ButtonStyle.ColorPrimary)("Create Transaction")),
          <.div(Button()("Create Organization"), Style.margin.left8)
        )
      ),
      ExampleSimple("**NOT GOOD:** Users don't know which is action should be taken")(
        <.div(
          Style.flexbox.flex,
          <.div(Button(color = ButtonStyle.ColorPrimary)("Create Transaction")),
          <.div(Button(color = ButtonStyle.ColorSuccess)("Create Organization"), Style.margin.left8)
        )
      ),
      Markdown(
        """
          |**Avoid using `ColorDanger` for "possibly danger" actions** - those that have confirm step (e.g: "Remove user") or can be undo easily. Such actions can be shown in the default color (white), while the confirm button (e.g: "Confirm remove user") is the one that should use `ColorDanger`.
          |""".stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex,
          <.div(Button()("Cancel"), Style.margin.right8),
          <.div(Button(color = ButtonStyle.ColorDanger)("Confirm remove"))
        )
      ),
      Markdown("""
                 |**`color` prop works with all types of `style`:**
                 |""".stripMargin)(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.p(Style.flexbox.none.width.pc10, "Full"),
          <.div(Button()("Default"), Style.margin.left8),
          <.div(Button(color = ButtonStyle.ColorPrimary)("Primary"), Style.margin.left8),
          <.div(Button(color = ButtonStyle.ColorSuccess)("Success"), Style.margin.left8),
          <.div(Button(color = ButtonStyle.ColorWarning)("Warning"), Style.margin.left8),
          <.div(Button(color = ButtonStyle.ColorDanger)("Danger"), Style.margin.left8)
        ),
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter.margin.top16.padding.top16,
          Style.border.top.borderColor.gray2.borderWidth.px2,
          <.p(Style.flexbox.none.width.pc10, "Minimal"),
          <.div(Button(style = ButtonStyle.StyleMinimal)("Default"), Style.margin.left8),
          <.div(
            Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorPrimary)("Primary"),
            Style.margin.left8
          ),
          <.div(
            Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorSuccess)("Success"),
            Style.margin.left8
          ),
          <.div(
            Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorWarning)("Warning"),
            Style.margin.left8
          ),
          <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorDanger)("Danger"), Style.margin.left8)
        ),
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter.margin.top16.padding.top16,
          Style.border.top.borderColor.gray2.borderWidth.px2,
          <.p(Style.flexbox.none.width.pc10, "Link"),
          <.div(Button(style = ButtonStyle.StyleLink)("Default"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorPrimary)("Primary"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorSuccess)("Success"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorWarning)("Warning"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorDanger)("Danger"), Style.margin.left8)
        )
      ),
      Markdown("""
                 |
                 |## `Size`
                 |
                 |Use the `size` prop to enlarge button in spacious context.
                 |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button()("Medium"),/*>*/ Style.margin.right16),
            <.div(/*<*/Button(size = ButtonStyle.SizeLarge)("Large"))
          )/*<*/
        )
        // format: on
      )(),
      Markdown(
        """
          |`SizeLarge` often goes with `isFullWidth` and requires their surrounding elements to be large too. Thus, they are quite uncommon in practice.
          |
          |The default `SizeMedium` should works most of the time.
          |
          |There is also a `SizeIcon` value to make icon-only button looks square, which is covered in [With icon](#with-icon) section below.
          |
          |## `isFullWidth`
          |
          |Although being a `block` element, the width of a `Button` depends on its content size. Set `isFullWidth` to `true` if you want the button's width to be 100% of its parent. This comes in handy in some situation like in a vertical form.
          |""".stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.width.px128,
            Button(
              color = ButtonStyle.ColorPrimary,
              isFullWidth = true
            )("Sign In")
          )
        )
      )(),
      Markdown(s"""
                  |Note that the content of a full-width button will be horizontally centered out of the box.
                  |
          |## `isSelected`
                  |
          |Use the `isSelected` prop to have the `active` style always visible. This is useful when the button is being used to toggle something on and off, like a popover. It works with all values of `color`:
                  |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val margin = Style.margin.left16
          <.div(
            Style.flexbox.flex,
            <.div(Button(isSelected = true)("Default")),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorPrimary)("Primary"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorSuccess)("Success"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorWarning)("Warning"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorDanger)("Danger"), margin)
          )
        })
      )(),
      Markdown(s"""
                  |Note that `isSelected` does not work with `TpeLink` or `StyleLink` as it would not make any sense. That being said, it works with both `StyleMinimal` and `StyleFull` though, and the results are intentionally identical:
                  |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.flexbox.flex,
            <.div(Button(isSelected = true)("Default"), Style.margin.right16),
            <.div(Button(isSelected = true, style = ButtonStyle.StyleMinimal)("Minimal"))
          )
        )
      )(),
      Markdown(s"""
                  |# Content
                  |
          |## With icon
                  |
          |It's common to have icon inside a button to add information for the label. In these cases, simply put the icon on left or right side of the text, [with proper spacing](${ctl
                    .urlFor(Main.Icon("#icon-s-shape-is-designed-to-touch-the-bound"))
                    .value}). Spacing can be put in either the text or the icon.
                  |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val icon = IconAcl(name = IconAcl.NameLightBolt)()
          val margin = Style.margin.right8
          <.div(Style.flexbox.flex,
            <.div(/*<*/Button()(<.span(margin, icon), "Text"/*>*/), Style.margin.right16),
            <.div(/*<*/Button()(<.span(margin, "Text"), icon)/*>*/))/*<*/
        })
        // format: on
      )(),
      Markdown(
        """
          |Button can also be icon-only. In these cases make sure the action can be clearly implied without text. Icon-only are often being used as a row, like in a toolbar, so their meaning can support each other.
          |
          |By default, button has unequal vertical and horizontal padding, which result in a rectangle for icon-only case. This might not look nice in some cases like toolbar. In such cases, use `size = ButtonStyle.SizeIcon` to make it square.
          |""".stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val icon = IconAcl(name = IconAcl.NameLightBolt)()
          <.div(Style.flexbox.flex,
            <.div(/*<*/Button()(icon)/*>*/, Style.margin.right16),
            <.div(/*<*/Button(size = ButtonStyle.SizeIcon)(icon)/*>*/))/*<*/
        })
        // format: on
      )(),
      Markdown(
        """
          |
          |**On appearance side,** thank to [`ButtonStyle`][1], this observes other props to provides correct styling. For example, a `StyleMinimal` button would have a simpler disabled style than a `StyleFull` (default) one.
          |
    """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          /*>*/ <.div(
            Style.flexbox.flex,
            <.div( /*<*/ Button(isDisabled = true)("Default") /*>*/, Style.margin.right16),
            <.div( /*<*/ Button(isDisabled = true, style = ButtonStyle.StyleMinimal)("Minimal")() /*>*/ ),
            <.div( /*<*/ Button(isDisabled = true, style = ButtonStyle.StyleLink)("Link")() /*>*/ )
          ) /*<*/
        )
      )(),
      Markdown(
        """
    |That being said, it is intentional that variations of `Color` like `Primary` or `Success` shared the same disabled style:
      |
    |""".stripMargin
      )(),
      ExampleRich(
        // format: off
      Source.annotate(
        /*>*/<.div(
          Style.flexbox.flex,
          <.div(/*<*/Button(isDisabled = true)("Default")/*>*/, Style.margin.right16),
          <.div(/*<*/Button(isDisabled = true, color = ButtonStyle.ColorPrimary)("Primary")/*>*/, Style.margin.right16),
          <.div(/*<*/Button(isDisabled = true, style = ButtonStyle.StyleMinimal)("Minimal")()/*>*/)
        )/*<*/
      )
      // format: on
      )()
    )
  }
}
