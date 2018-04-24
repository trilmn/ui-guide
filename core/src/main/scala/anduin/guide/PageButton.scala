package anduin.guide

import japgolly.scalajs.react.vdom.html_<^._

import anduin.mcro.Source
import anduin.component.button.Button
import anduin.component.icon.IconAcl
import anduin.style.Style

object PageButton {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Button",
          description = """
              |a
            """.stripMargin
        )()
      ),
      Markdown("""
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
          |The `tpe` prop reflects the native `type` attribute of HTML `button` tag, thus it also has 3 values:
          |
          |- `TpeDefault` (equivalent to `button`)
          |- `TpeReset` (`reset`)
          |- `TpeSubmit` (`submit`).
          |
          |Although their appearances are the same, it's strongly suggested to use the correct one to take advantage of their native functionality. For example, a `TpeSubmit` button correct submit button with an `onSubmit` handler allows user to submit by both clicking or pressing "enter", without the need to add any other event listener.
          |
          |That being said, most of the time it is suitable to use the default value, especially when the button is outside of a form.
          |
          |
          |
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
      Markdown("""
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
          |## `href`
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
