package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.icon.Icon

object PageButton {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Button", Some(Button))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Button lets users take action:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          onClick = Callback.alert("Hello")
        )("Say Hi")
      }))(),
      Markdown(
        """
          |# Event Listener
          |
          |```scala
          |onClick: Callback = Callback.empty
          |onDoubleClick: Callback = Callback.empty
          |```
          |
          |Buttons can have event listeners attached in response to users'
          |interactions, such as click or double-click:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          onDoubleClick = Callback.alert("Hello")
        )("Say Hi")
      }))(),
      Markdown(
        """
          |Although supported by all types, event listeners are usually
          |defined for [Plain](#plain) buttons only. Other types like
          |[Submit](#submit) and [Link](#link) already have useful behaviours
          |out of the box.
          |
          |# Disable
          |
          |```scala
          |isDisabled: Boolean = false
          |```
          |
          |Set `isDisabled = true` to prevent users' interactions with the
          |button. This disables both event listeners and built-in behaviours
          |(eg. in case of [Submit](#submit) buttons):
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          onClick = Callback.alert("Hello"),
          isDisabled = true
        )("Say Hi")
      }))(),
      Markdown(
        """
          |When disabled, the button's appearance is grayed out:
          |""".stripMargin
      )(),
      ExampleSimple()({
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Style.margin.right16, Button(isDisabled = true)("Full")),
          <.div(Style.margin.right16, Button(style = Button.Style.Ghost(), isDisabled = true)("Ghost")),
          <.div(Style.margin.right16, Button(style = Button.Style.Minimal(), isDisabled = true)("Minimal")),
          <.div(Style.margin.right16, Button(style = Button.Style.Text(), isDisabled = true)("Text"))
        )
      }),
      Markdown(
        """
          |# Style
          |
          |```scala
          |style: ButtonStyle = Button.Style.Full()
          |```
          |
          |The `style` prop defines how a button looks. There are 4 styles,
          |with further customization (like color) in each:
          |
          |## Box
          |""".stripMargin
      )(),
      ExampleSimple()({
        import Button.Style._
        import Button.Color._
        val margin = <.div(Style.margin.right12)
        val icon = Some(Icon.Glyph.Download)
        val border = Style.margin.bottom16.padding.bottom16.border.bottom.borderColor.gray3
        val label = <.p(Style.flexbox.none.width.px64.margin.right24.fontWeight.semiBold)
        <.div(
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            border,
            label("Full"),
            margin(Button(style = Full(color = Blue))("Submit")),
            margin(Button(style = Full(color = White))("Cancel")),
            margin(Button(style = Full(color = White, icon = icon))()),
          ),
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            border,
            label("Ghost"),
            margin(Button(style = Ghost(color = Blue))("Submit")),
            margin(Button(style = Ghost(color = Black))("Cancel")),
            margin(Button(style = Ghost(color = Black, icon = icon))()),
          ),
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            label("Minimal"),
            margin(Button(style = Minimal(color = Blue))("Submit")),
            margin(Button(style = Minimal(color = Black))("Cancel")),
            margin(Button(style = Minimal(color = Black, icon = icon))()),
          ),
        )
      }),
      Markdown(
        """
          |`Full`, `Ghost` and `Minimal` styles provide box-like appearances to
          |buttons via a padding around their labels. These buttons are usually
          |places in their own areas (like a Toolbar).
          |""".stripMargin
      )(),
      BigButtonLink(ctl, Pages.ButtonBox(), "View Box Button guide")(),
      Markdown(
        """
          |## Text
          |""".stripMargin
      )(),
      ExampleSimple()({
        <.p(
          "You can ",
          Button(style = Button.Style.Text())("Accept"),
          " or ",
          Button(style = Button.Style.Text(color = Button.Color.Red))("Decline"),
          " this invitation."
        )
      }),
      Markdown(
        """
          |The `Text` style provides a text-only appearance, similar to
          |HTML's `a` tag. These buttons are usually parts of sentences, such
          |as words or phrases.
        """.stripMargin
      )(),
      BigButtonLink(ctl, Pages.ButtonText(), "View Text Button guide")(),
      Markdown(
        """
          |# Type
          |
          |```scala
          |tpe: ButtonType = Button.Tpe.Plain(),
          |```
          |
          |The `tpe` prop defines the built-in behaviours of a button. There
          |are 3 options:
          |
          |## Plain
          |
          |```scala
          |case class Button.Tpe.Plain(
          |  isAutoFocus: Boolean = false
          |)
          |```
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button()()
      }))(),
      Markdown(
        """
          |## Submit
          |
          |```scala
          |case class Button.Tpe.Submit(...)
          |```
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button()()
      }))(),
      Markdown(
        """
          |
          |## Link
          |
          |```scala
          |case class Button.Tpe.Link(...)
          |```
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button()()
      }))(),
      Markdown(
        """
        """.stripMargin
      )()
    )
  }
}
