package anduin.guide.page

import japgolly.scalajs.react.{Callback, ReactEventFromInput}
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
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
          |  color: Button.Color = Button.ColorWhite,
          |  style: Button.Style = Button.StyleFull,
          |  size: Button.Size = Button.SizeMedium,
          |  isFullWidth: Boolean = false,
          |  isSelected: Boolean = false /* WIP */
          |)()
          |```
          |
          |Example:
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate({
          /*>*/val cancel =/*<*/
            Button(
              onClick = Callback.alert("You cancelled")
            )("Cancel")
          /*>*/val submit =/*<*/
            Button(
              color = Button.ColorPrimary,
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
      Example(
        // format: off
        Source.annotate({
          /*>*/val formSubmit = (e: ReactEventFromInput) => e.preventDefaultCB >> Callback.alert("Form submitted")
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
      Markdown(
        s"""
          |### `isDisabled`
          |
          |Since these are actual buttons, we can prevent them from user interaction via the `isDisabled` prop.
          |
          |**On functional side,** this does nothing but use the native `disabled` attribute of `button`, which done a good job preventing interaction, via not only mouse but also keyboard, touch or voice.
          |
          |**On appearance side,** this observes other props to provides correct styling. For example, a [`StyleMinimal`](#style) button should have a simpler disabled style than a `StyleFull` (default) one. That being said, it is intentional that variations of [`Color`](#color) shared the same disabled style at the moment:
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button(isDisabled = true)("Default")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(isDisabled = true, color = Button.ColorPrimary)("Primary")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(isDisabled = true, style = Button.StyleMinimal)("Minimal")()/*>*/)
          )/*<*/
        )
        // format: off
      )(),
      Markdown("""
          |## Link type
          |
          |The `tpe` prop also has a special `TpeLink` value to render an actual link (`a` tag). This should be used when you want something that works like a link (i.e: change URL and can be opened in new tab) but looks like a button.
          |
          |**Example:** (try hover and right click each)
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.flexbox.flex,
            <.div(/*<*/Button()("Button")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(tpe = Button.TpeLink, href = "https://google.com")("Link")/*>*/)
          )/*<*/
        )
        // format: off
      )(),
      Markdown(s"""
          |Since this is an actual link, `TpeLink` requires a valid `href`. Moreover, the `onClick` callback can also be used to override page change event. One common example is with `RouterCtl` (of `scalajs-react`):
          |""".stripMargin)(),
      Example(
        Source.annotate({
          val page = Main.ButtonVsLink()
          Button(
            tpe = Button.TpeLink,
            href = ctl.urlFor(page).value,
            onClick = ctl.set(page)
          )("Go to ButtonVsLink")
        })
      )(),
      Markdown(
        """
          |In the example above:
          |
          |- The `onClick` event is handled by `RouterCtl`, so page change happens immediatelly on click.
          |- Meanwhile, it still has a valid `href`, so you can right click and open the link in new tab.
          |
          |**Also note that `isDisabled` is not available for `TpeLink`,** since a link should always be accessible. The page at an URL might be 404 or 401, but nothing should prevent users from accessing that URL.
          |
          |# Appearance
          |
          |## `Style`
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.div(/*<*/Button(style = Button.StyleFull)("Full")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(style = Button.StyleMinimal)("Minimal")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(style = Button.StyleLink, href = "#")("Link")/*>*/, Style.margin.right16)
          )/*<*/
        )
        // format: off
      )(),
      Markdown(s"""
          |
          |> If you see yourself are using both `StyleLink` and `TpeLink`, that means you are doing it wrong. It should be a simple `a` tag instead. Learn more in [Button vs Link](${Main.ButtonVsLink()}) page.
          |
          |## `Color`
          |
          |Use the `color` prop to communicate the intention
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button()("Default")/*>*/),
            <.div(/*<*/Button(color = Button.ColorPrimary)("Primary")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = Button.ColorSuccess)("Success")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = Button.ColorWarning)("Warning")/*>*/, Style.margin.left16),
            <.div(/*<*/Button(color = Button.ColorDanger)("Danger")/*>*/, Style.margin.left16)
          )/*<*/
        )
        // format: off
      )(),
      Markdown("""
          |
          |- Primary
          |- Success
          |- Warning
          |- Danger
          |
          |""".stripMargin)(),
      <.div(
        Style.flexbox.flex.padding.ver16,
        <.div(Button(style = Button.StyleMinimal)("Default")),
        <.div(Button(style = Button.StyleMinimal, color = Button.ColorPrimary)("Primary"), Style.margin.left16),
        <.div(Button(style = Button.StyleMinimal, color = Button.ColorSuccess)("Success"), Style.margin.left16),
        <.div(Button(style = Button.StyleMinimal, color = Button.ColorWarning)("Warning"), Style.margin.left16),
        <.div(Button(style = Button.StyleMinimal, color = Button.ColorDanger)("Danger"), Style.margin.left16)
      ),
      <.div(
        Style.flexbox.flex.padding.ver16,
        <.div(Button(style = Button.StyleLink)("Default")),
        <.div(Button(style = Button.StyleLink, color = Button.ColorPrimary)("Primary"), Style.margin.left16),
        <.div(Button(style = Button.StyleLink, color = Button.ColorSuccess)("Success"), Style.margin.left16),
        <.div(Button(style = Button.StyleLink, color = Button.ColorWarning)("Warning"), Style.margin.left16),
        <.div(Button(style = Button.StyleLink, color = Button.ColorDanger)("Danger"), Style.margin.left16)
      ),
      Markdown(
        """
          |
          |## `Size`
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button(size = Button.SizeSmall)("Small"),/*>*/ Style.margin.right16),
            <.div(/*<*/Button()("Medium"),/*>*/ Style.margin.right16),
            <.div(/*<*/Button(size = Button.SizeLarge)("Large"))
          )/*<*/
        )
        // format: off
      )(),
      Markdown("""
          |
          |## `isFullWidth`
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          <.div("placeholder")
        )
        // format: off
      )(),
      Markdown("""
          |
          |# Content
          |
          |## With icon
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate({
          /*>*/val icon = IconAcl(name = IconAcl.NameLightBolt)()
          <.div(Style.flexbox.flex,
            <.div(/*<*/Button(size = Button.SizeIcon)(icon)/*>*/, Style.margin.right16),
            <.div(/*<*/Button()(icon)/*>*/, Style.margin.right16),
            <.div(/*<*/Button()(<.span(Style.margin.right8, icon), "Text"/*>*/), Style.margin.right16),
            <.div(/*<*/Button()("Text", <.span(Style.margin.left8, icon))/*>*/)
          )/*<*/
        })
        // format: off
      )(),
      Markdown("""
          |## `isSelected`
          |
          |> This option is not available at the moment
        """.stripMargin)()
    )
  }
}
