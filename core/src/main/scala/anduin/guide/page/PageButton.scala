package anduin.guide.page

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
          description = """
              |a
            """.stripMargin
        )()
      ),
      Markdown(s"""
          |# Snippet
          |
          |```scala
          |Button(
          |  color: Button.Color = Button.ColorWhite,
          |  style: Button.Style = Button.StyleFull,
          |  size: Button.Size = Button.SizeMedium,
          |  isFullWidth: Boolean = false,
          |
          |  tpe: Button.Tpe = Button.TpeDefault,
          |  onClick: Callback = Callback.empty,
          |  href: String = "",
          |  isDisabled: Boolean = false
          |)()
          |```
          |
          |# Behaviour
          |
          |## `Tpe`
          |
          |### Button types
          |
          |The `tpe` prop has 3 values that reflect the native `type` attribute of the HTML `button` tag:
          |
          |- `TpeDefault` (equivalent to `button`)
          |- `TpeReset` (`reset`)
          |- `TpeSubmit` (`submit`).
          |
          |Generally, `TpeReset` and `TpeSubmit` should be used in a `form` tag to take advantage of their native function (e.g: `TpeReset` clears all form's inputs on click). This means you often don't need to provide `onClick` for these 2 types.
          |
          |Meanwhile, `TpeDefault` should be used for all other cases, where the button is outside of a form and must have an `onClick` to make it do something.
          |
          |```scala
          |<.form(
          |  ^.onSubmit := ...,
          |  // this does not need `onClick`
          |  Button(tpe = Button.TpeSubmit)("Submit")
          |)
          |
          |<.div(
          |  // this needs `onClick`
          |  Button(onClick = ...)("Do something")
          |)
          |```
          |
          |### Link type
          |
          |The `tpe` prop also has a special `TpeLink` value to make it renders an actual link tag (`a`). This should be used when you want an actual link but looks like a button. Learn more in [Button vs Link](${ctl.urlFor(Main.ButtonVsLink()).value}) page.
          |
          |Example: (try hover and right click each)
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button()("Button")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(tpe = Button.TpeLink, href = "https://google.com")("Link")/*>*/)
          )/*<*/
        )
        // format: off
      )(),
      Markdown(s"""
          |As in the example above, since this is an actual link, you should provide a valid `href`. Moreover, the `onClick` callback is still available for `TpeLink` in order to override page change event.
          |
          |One common use case of this is to allow the `RouterCtl` (of `scalajs-react`) to handle the `onClick` event, so page change happens immediately, while still have a valid `href` so it can be opened in new tab:
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
          |
          |>**Note:** If you want an actual button that looks like a link (e.g: an inline text to open a modal) then use `StyleLink`. Learn more in [Style](${ctl.urlFor(Main.Button("style")).value}) section or [Button vs Link](${ctl.urlFor(Main.ButtonVsLink()).value}) page.
          |
          |## `onClick`
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
          |## `isDisabled`
          |
          |""".stripMargin)(),
      Example(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/Button(isDisabled = true)("Default")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(isDisabled = true, color = Button.ColorPrimary)("Primary")/*>*/, Style.margin.right16),
            <.div(/*<*/Button(isDisabled = true)(IconAcl(name = IconAcl.NameLayer)())/*>*/, Style.margin.right16),
            <.div(/*<*/Button(isDisabled = true, style = Button.StyleMinimal)("Minimal")()/*>*/)
          )/*<*/
        )
        // format: off
      )(),
      Markdown("""
          |
          |# Appearance
          |
          |## `Style`
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
          |## `Color`
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
          <.div(
            Style.flexbox.flex,
            <.div(/*<*/Button()(icon)/*>*/, Style.margin.right16),
            <.div(/*<*/Button()(<.span(Style.margin.right8, icon), "Text"/*>*/), Style.margin.right16),
            <.div(/*<*/Button()("Text", <.span(Style.margin.left8, icon))/*>*/)
          )/*<*/
        })
        // format: off
      )(),
      Markdown("""
          |
        """.stripMargin)()
    )
  }
}
