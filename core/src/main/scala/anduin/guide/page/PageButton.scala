package anduin.guide.page

import japgolly.scalajs.react.{Callback, ReactEventFromInput}
import japgolly.scalajs.react.vdom.html_<^._
import anduin.component.button.{Button, ButtonStyle}
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
            "Buttons let users perform actions. They are extremely flexible in both appearance and behaviour."
        )()
      ),
      Markdown(s"""
          |# Overview
          |
          |```scala
          |Button(
          |  tpe: Button.Tpe = Button.TpeButton,
          |  onClick: Callback = Callback.empty,
          |  isDisabled: Boolean = false,
          |)()
          |```
          |
          |Beside the above props, `Button` also supports appearance customization via `ButtonStyle` (such as `color`, `size`, `style` props). Learn more at [`ButtonStyle`][1].
          |
          |[1]: ${ctl.urlFor(Main.ButtonStyle()).value}
          |
          |Example:
          |""".stripMargin)(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/<.div(Style.flexbox.flex,
            <.div(/*<*/
              Button(
                onClick = Callback.alert("You cancelled")
              )("Cancel"),/*>*/
              Style.margin.right8
            ),
            <.div(/*<*/
              Button(
                color = ButtonStyle.ColorPrimary,
                onClick = Callback.alert("You submitted")
              )("Submit")/*>*/
            )
          ) /*<*/
        })
        // format: on
      )(),
      Markdown(
        s"""
          |# Usage
          |
          |The `Button` component should be used where we would use the native HTML's [`button`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button) tag. That is, for in-page actions such as:
          |- Submit or reset a form
          |- Show a modal, popover, or menu
          |- Add or remove element from a list
          |- Collapse or expand an area
          |- Doing something with server
          |- etc.
          |
          |A rule of thumb is that `Button` should be used for actions that does not change the URL (i.e. navigating user to other page).
          |
          |If you want something that should change the URL (and still has the appearance of our `Button`) then use [`ButtonLink`][1].
          |
          |[1]: ${ctl.urlFor(Main.ButtonLink()).value}
          |
          |# Type
          |
          |Since `Button` component is rendered as `button` tag, the `tpe` prop is used as the `type` attribute. Like in HTML, we have 3 options:
          |
          |- `TpeButton` (equivalent to `type=button` in HTML)
          |- `TpeReset` (`type=reset`)
          |- `TpeSubmit` (`type=submit`).
          |
          |Unlike HTML, our default value is `TpeButton`, because most of the time we will want a button that is outside of a `form` and need an `onClick` handler to do something.
          |
          |Meanwhile, `TpeReset` and `TpeSubmit` should be used inside a `form` to take advantage of their native function (e.g: `TpeSubmit` auto attached to its form's `onSubmit`). This means you often don't need to provide `onClick` for these 2 types:
          |""".stripMargin
      )(),
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
          |# `isDisabled`
          |
          |Since these are actual buttons, we can prevent users from interacting with them via the `isDisabled` prop.
          |
          |**On functional side,** this does nothing but use the native `disabled` attribute of `button`, which done a good job preventing interaction, including those via mouse, keyboard, touch or voice.
          |
          |**On appearance side,** thank to `ButtonStyle`, this observes other props to provides correct styling for different colors and styles. Learn more and see examples at [`ButtonStyle`][1].
          |
          |[1]: ${ctl.urlFor(Main.ButtonStyle("isdisabled")).value}
          """.stripMargin)()
    )
  }
}
