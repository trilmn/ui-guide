package anduin.guide.pages.components.button

import anduin.guide.components._
import anduin.component.button.{Button, ButtonStyle}
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ReactEventFromInput}

object PageButton {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Button", obj = Some(Button))()
      ),
      ExampleRich(Source.annotate({
        val cancelBtn = Button(
          onClick = Callback.alert("You cancelled")
        )("Cancel")

        val submitBtn = Button(
          color = ButtonStyle.ColorBlue,
          onClick = Callback.alert("You submitted")
        )("Submit")
        /*>*/
        <.div(
          Style.flexbox.flex,
          <.div(Style.margin.right8, cancelBtn),
          <.div(submitBtn)
        ) /*<*/
      }))(),
      Markdown(
        s"""
           |# Usage
           |
           |The Button component should be used where we would use the native HTML's [button](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button) tag. That is, for in-page actions such as:
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
           |# Appearance
           |
           |Button supports all customization via ButtonStyle. See the [ButtonStyle guide][2] for detail.
           |
           |[2]: ${ctl.urlFor(Pages.ButtonStyle()).value}
           |
           |# Type
           |
           |Like the native `button` tag, the Button component has a `tpe` prop with 3 options:
           |
           |- `TpeButton` (equivalent to `type=button`)
           |- `TpeReset` (`type=reset`)
           |- `TpeSubmit` (`type=submit`).
           |
           |**The default value is `TpeButton`,** which does nothing out of the box. You often need to provide an `onClick` handler to make it do something.
           |
           |Meanwhile, `TpeReset` and `TpeSubmit` have useful native function when used inside a `form` (e.g. `TpeSubmit` triggers form's `onSubmit`). You often don't need to provide `onClick` for these 2 types.
          """.stripMargin
      )(),
      // format: off
      ExampleRich(Source.annotate({
        /*>*/
        val formSubmit = (e: ReactEventFromInput) => e.preventDefaultCB >> Callback.alert("Form submitted")
        <.div(
          Style.flexbox.flex,
          <.form(
            ^.onSubmit ==> formSubmit, /*<*/
            // this does not need `onClick`
            Button(tpe = Button.TpeSubmit)("Form's Submit") /*>*/
          ),
          <.div(
            Style.margin.left16, /*<*/
            // this needs `onClick`
            Button(onClick = Callback.alert("Done"))("Generic Action") /*>*/
          )
        ) /*<*/
      }))(),
      // format: on
      Markdown(
        s"""
           |# `isDisabled`
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
