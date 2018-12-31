package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.button.Button._
import anduin.component.input.textbox.TextBox
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

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
          |# Type
          |
          |```scala
          |tpe: Button.Tpe = Button.Tpe.TpeButton
          |```
          |
          |The `tpe` prop defines the behaviour of a Button: whether it
          |should work like a [link](#type-link), a [submit button](#type-submit)
          |or a [normal button](#type-button). The `tpe` prop does not affect
          |the appearance of a Button.
          |
          |""".stripMargin
      )(),
      Markdown(
        """
          |## Button
          |
          |```scala
          |Button.Tpe.TpeButton(isAutoFocus: Boolean = false)
          |// same as <.button(^.tpe := "button")
          |```
          |
          |The default value of `tpe` is `Tpe.TpeButton`, which has no
          |built-in behaviour. They usually have the actual action defined via
          |the `onClick` or `onDoubleClick` prop:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          tpe = Button.Tpe.TpeButton(), // usually omitted
          onClick = Callback.alert("You clicked!")
        )("Click me")
      }))(),
      Markdown(
        """
          |## Submit
          |
          |```scala
          |Button.Tpe.Submit(isAutoFocus: Boolean = false)
          |// same as <.button(^.tpe := "submit")
          |```
          |
          |The `Submit` option will trigger the `onSubmit` of the associated
          |[form][mdn-form] when users click on the Button. This is a native,
          |built-in behaviour so no `onClick` needs to be defined:
          |
          |[mdn-form]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        SimpleState.Str(
          initialValue = "",
          render = (value, setValue) => {
            val text = TextBox(value, setValue, placeholder = "Name...")()
            val button = Button(tpe = Button.Tpe.Submit())("Submit")
            <.form(
              Style.flexbox.flex,
              ^.onSubmit ==> (_.preventDefaultCB >> Callback.alert("a")),
              <.div(Style.width.px128, text),
              <.div(Style.margin.left8, button)
            )
          }
        )()
      }))(),
      Markdown(
        s"""
           |Whenever possible, prefer form's `onSubmit` over Button's
           |`onClick` so user can submit via several ways. For example, try
           |pressing "Enter" while you are still in the Text Box above!
           |
           |""".stripMargin
      )(),
      Markdown(
        s"""
           |
           |## Link [type-link]
           |
           |```scala
           |Button.Tpe.Link(
           |  href: String,
           |  target: Button.Target = Button.Target.Self
           |)
           |// same as <.a(^.href := ...)
           |```
           |
           |The `Link` option renders an actual [`anchor` element][mdn-a] and
           |thus should be used when the Button's target is a page, file,
           |location on a page, email address, or other URL:
           |
           |[mdn-a]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a
           |
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          tpe = Button.Tpe.Link("https://anduin.design")
        )("Go to anduin.design")
      }))(),
      Markdown(
        """
          |The `Link` option has a `target` parameter, which is the
          |equivalent of the [`target` attribute](mdn-target) of the `anchor`
          |element. It can be `Self` (default), `Blank` or `Parent`:
          |
          |[mdn-target]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-target
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          tpe = Button.Tpe.Link(
            href = "https://anduin.design",
            target = Button.Target.Blank
          )
        )("Open anduin.design")
      }))(),
      Markdown(
        s"""
           |# Style
           |
           |```scala
           |style: Button.Style = Button.Style.Full
           |```
           |
           |The `style` prop defines Button's appearance. There are four
           |styles, with further customization (e.g. color) in each:
           |
           |## Full
           |
           |```scala
           |final case class Full(
           |  color: Color = Color.White,
           |  size: Size = Size.Fix32,
           |  icon: Option[Icon.Name] = None,
           |  isFullWidth: Boolean = false,
           |  isSelected: Boolean = false,
           |  isBusy: Boolean = false
           |)
           |```
           |
           |Button's default style, `Full`, has the highest emphasis with
           |background color, border and shadow. They should be used when the
           |Button(s) needs to stand out from nearby elements:
           |
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button()("Full")
      }))(),
      Markdown(
        s"""
           |### Color [full-color]
           |
           |Style `Full` provides 4 colors to choose from. The name of each
           |color indicates its background, with "White" is the default one:
           |
           |""".stripMargin
      )(),
      CommonColorExample(
        bgColor = ExampleSimple.BgColor.White,
        getStyle = color => Button.Style.Full(color = color),
        colors = List(Color.White, Color.Black, Color.Blue, Color.Red),
        default = Some(Color.White)
      )(),
      Markdown(
        s"""
           |On light background, `White` should be used for most cases, with a
           |`Blue` to highlight the primary one when necessary:
           |""".stripMargin
      )(),
      FullColorExample(ExampleSimple.BgColor.White, Color.Blue, Color.White)(),
      Markdown(
        s"""
           |`Black` can be used as an alternative primary on light background:
         """.stripMargin
      )(),
      FullColorExample(ExampleSimple.BgColor.White, Button.Color.Black, Button.Color.White)(),
      Markdown(
        s"""
           |…or as a secondary one on dark background:
         """.stripMargin
      )(),
      FullColorExample(ExampleSimple.BgColor.Gray8, Color.Blue, Color.Black)(),
      Markdown(
        s"""
           |`Red` should be used for destructive actions, such as archiving
           |deals. Note that only the final action, usually in confirmation
           |Modals, does need the color `Red`:
           |
           |""".stripMargin
      )(),
      ExampleSimple()(FullArchiveExample()()),
      Markdown(
        s"""
           |
           |### Size
           |
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex,
          Button(style = Button.Style.Full(size = Button.Size.Fix24))("Fix24"),
          Button(style = Button.Style.Full(size = Button.Size.Fix32))("Fix32"),
          Button(style = Button.Style.Full(size = Button.Size.Fix40))("Fix40")
        )
      }))(),
      Markdown(
        s"""
           |
           |### Width
           |
           |### Icon
           |
           |### Selected
           |
           |### Busy
           |
           |## Ghost
           |
           |### Color [ghost-color]
           |
           |""".stripMargin
      )(),
      CommonColorExample(
        bgColor = ExampleSimple.BgColor.White,
        getStyle = color => Button.Style.Ghost(color = color),
        colors = List(Color.Black, Color.Blue, Color.Red),
        default = Some(Color.Black)
      )(),
      Markdown(
        s"""
           |
           |## Minimal
           |
           |## Link [style-link]
           |
           |# Disabled
           |
           |The Button component should be used where we would use the native HTML's [button](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button) tag. That is, for in-page actions such as:
           |
           |- Submit or reset a form
           |- Show a modal, popover, or menu
           |- Add or remove element from a list
           |- Collapse or expand an area
           |- Doing something with server
           |
           |As a rule of thumb, use Button for actions that does not change the URL (i.e. navigating user to other page).
           |
           |If you want something that should change the URL (and still has the appearance of a Button) then use [ButtonLink][1].
           |
           |[1]: ${ctl.urlFor(Pages.ButtonLink()).value}
           |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val formSubmit = (e: ReactEventFromInput) => e.preventDefaultCB >> Callback.alert("Form submitted")
        <.div(
          Style.flexbox.flex,
          <.form(
            ^.onSubmit ==> formSubmit, /*<*/
            // this does not need `onClick`
            Button(tpe = Button.Tpe.Submit())("Form's Submit") /*>*/
          ),
          <.div(
            Style.margin.left16, /*<*/
            // this needs `onClick`
            Button(onClick = Callback.alert("Done"))("Generic Action") /*>*/
          )
        ) /*<*/
      }))(),
      Markdown(
        s"""
           |
           |Since these are actual `button` tags, we can prevent users from interacting with them via the `isDisabled` prop.
           |
           |**On functional side,** this does nothing but use the native `disabled` attribute of Button, which done a good job preventing interaction, including those via mouse, keyboard, touch or voice.
           |
           |**On appearance side,** thank to ButtonStyle, this observes other props to provides correct styling for different colors and styles. Learn more and see examples at [ButtonStyle][1].
           |
           |[1]: ${ctl.urlFor(Pages.ButtonStyle("#with-isdisabled")).value}
          """.stripMargin
      )()
    )
  }
}
