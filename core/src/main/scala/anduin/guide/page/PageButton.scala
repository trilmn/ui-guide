package anduin.guide.page

import japgolly.scalajs.react.{Callback, ReactEventFromInput}
import japgolly.scalajs.react.vdom.html_<^._
import anduin.component.button.{Button, ButtonLink, ButtonStyle}
import anduin.component.icon.IconAcl
import anduin.guide._
import anduin.mcro.Source
import anduin.style.Style

object PageButton {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Button",
          description =
            """
              |Buttons let users perform actions. They are extremely flexible in both appearance and behaviour.
            """.stripMargin
        )()
      ),
      Markdown(s"""
          |# Snippet
          |
          |```scala
          |Button(
          |  tpe: Button.Tpe = Button.TpeDefault,
          |  onClick: Callback = Callback.empty,
          |  href: String = "",
          |  isDisabled: Boolean = false,
          |
          |  color: ButtonStyle.Color = ButtonStyle.ColorWhite,
          |  style: ButtonStyle.Style = ButtonStyle.StyleFull,
          |  size: ButtonStyle.Size = ButtonStyle.SizeMedium,
          |  isFullWidth: Boolean = false,
          |  isSelected: Boolean = false
          |)()
          |```
          |
          |Example:
          |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val cancel =/*<*/
            Button(
              onClick = Callback.alert("You cancelled")
            )("Cancel")
          /*>*/val submit =/*<*/
            Button(
              color = ButtonStyle.ColorPrimary,
              onClick = Callback.alert("You submitted")
            )("Submit")
          /*>*/<.div(Style.flexbox.flex,
                <.div(cancel, Style.margin.right8),
                <.div(submit))/*<*/
        })
        // format: on
      )(),
      Markdown(
        """
          |# Behaviour
          |
          |Use the `tpe` prop to define the HTML's `type` of the rendered `button`, or to render an `a` tag instead. The value of `tpe` also affects the usages of other behaviour props (`onClick`, `href`, `isDisabled`).
          |
          |## Button types
          |
          |The following values of `tpe` will render the Button component using a `button` tag, with the corresponding `type` attribute:
          |
          |- `TpeButton` (equivalent to `type=button` in HTML)
          |- `TpeReset` (`type=reset`)
          |- `TpeSubmit` (`type=submit`).
          |
          |Unlike HTML, our default value is `TpeButton`. This is because most of the time we will want a button that is outside of a `form` and need an `onClick` handler to do something.
          |
          |Meanwhile, `TpeReset` and `TpeSubmit` should be used inside a `form` to take advantage of their native function (e.g: `TpeSubmit` auto attached to its form's `onSubmit`). This means you often don't need to provide `onClick` for these 2 types.
          |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val formSubmit = (e: ReactEventFromInput) =>
            e.preventDefaultCB >> Callback.alert("Form submitted")

          <.div(Style.flexbox.flex,
            <.form(^.onSubmit ==> formSubmit,/*<*/
              // this does not need `onClick`
              Button(tpe = Button.TpeSubmit)("Form's Submit")/*>*/
            ),
            <.div(Style.margin.left16,/*<*/
              // this needs `onClick`
              Button(onClick = Callback.alert("Done"))("Generic Action")/*>*/
            ))/*<*/
        })
        // format: on
      )(),
      Markdown(s"""
          |### `isDisabled`
          |
          |Since these are actual buttons, we can prevent users from interacting with them via the `isDisabled` prop.
          |
          |**On functional side,** this does nothing but use the native `disabled` attribute of `button`, which done a good job preventing interaction, including those via mouse, keyboard, touch or voice.
          |
          |**On appearance side,** this observes other props to provides correct styling. For example, a [`StyleMinimal`](#style) button would have a simpler disabled style than a `StyleFull` (default) one. That being said, it is intentional that variations of [`Color`](#color) (e.g: `Primary` or `Success`) shared the same disabled style at the moment:
          |""".stripMargin)(),
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
      )(),
      Markdown(
        """
          |## Link type
          |
          |The `tpe` prop also has a special `TpeLink` value to render an actual link (`a` tag). This should be used when you want something that works like a link (i.e: change URL and can be opened in new tab) but looks like a button.
          |
          |**Example:** (try hover and right click each)
          |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.flexbox.flex,
            <.div(/*<*/Button()("Button")/*>*/, Style.margin.right16),
            <.div(/*<*/ButtonLink(href = "https://google.com")("Link")/*>*/)
          )/*<*/
        )
        // format: on
      )(),
      Markdown(s"""
          |Since this is an actual link, `TpeLink` requires a valid `href`.
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val page = Main.ButtonVsLink()
          <.div(
            Style.flexbox.flex,
            ButtonLink( href = ctl.urlFor(page).value )("Go to ButtonVsLink")
          )
        })
      )(),
      Markdown(
        """
          |In the example above:
          |
          |- The `onClick` event is handled by `RouterCtl`, so page change happens immediatelly on click.
          |- Meanwhile, it still has a valid `href`, so you can right click and open the link in new tab.
          |
          |### Usage Note
          |
          |**`TpeLink` takes 100% width by default,** due to browser's default rendering of `flex` display. However, this behaviour is eliminated if the button is placed inside another `flex`.
          |
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val button = ButtonLink()("Link")
          <.div(
            <.div(Style.margin.bottom8, button),
            <.div(Style.flexbox.flex, button)
          )
        })
      )(),
      Markdown(
        """
          |**`isDisabled` is not available,** since a link should always be accessible. The page at an URL might be 404 or 401, but nothing should prevent users from accessing that URL.
          |
          |**`isSelected` is not available,** since a link should not have this state at all.
          |
          |# Appearance
          |
          |## `Style`
          |
          |The `style` controls a button's overall style, allow engineers to use button based on its semantic sense instead of appearance.
          |""".stripMargin)(),
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
        val sep    = <.div(Style.margin.left8.padding.left8.border.left.borderWidth.px2.borderColor.gray4)
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
          |""".stripMargin)(),
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
      Markdown(s"""
          |>::warning::**`StyleLink` should not be used with `TpeLink`.** That case means you want something that works like a link and looks like a link, which is a simple `a` tag. Learn more in [Button vs Link](${ctl
                    .urlFor(Main.ButtonVsLink())
                    .value}) page.
          |
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
          |""".stripMargin)(),
      ExampleSimple("**GOOD:** Users know which is the primary action that should be taken")(
        <.div(Style.flexbox.flex,
              <.div(Button(color = ButtonStyle.ColorPrimary)("Create Transaction")),
              <.div(Button()("Create Organization"), Style.margin.left8))
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
          |""".stripMargin)(),
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
          <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorPrimary)("Primary"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorSuccess)("Success"), Style.margin.left8),
          <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorWarning)("Warning"), Style.margin.left8),
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
          |""".stripMargin)(),
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
          |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val icon = IconAcl(name = IconAcl.NameLightBolt)()
          <.div(Style.flexbox.flex,
            <.div(/*<*/Button()(icon)/*>*/, Style.margin.right16),
            <.div(/*<*/Button(size = ButtonStyle.SizeIcon)(icon)/*>*/))/*<*/
        })
        // format: on
      )()
    )
  }
}
