// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.input.textbox.TextBox
import anduin.guide.components.{ExampleRich, Markdown, SimpleState}
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[button] final case class PageButtonType() {
  def apply(): VdomElement = PageButtonType.component(this)
}

private[button] object PageButtonType {

  private type Props = PageButtonType

  def getHeadings: List[(Int, String)] = Source.getTocHeadings

  private def render(props: Props): VdomElement = {
    <.div(
      Markdown(
        """
          |# Type
          |
          |```scala
          |tpe: Button.Tpe = Button.Tpe.Plain
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
          |Button.Tpe.Plain(isAutoFocus: Boolean = false)
          |// same as <.button(^.tpe := "button")
          |```
          |
          |The default value of `tpe` is `Tpe.Plain`, which has no
          |built-in behaviour. They usually have the actual action defined via
          |the `onClick` or `onDoubleClick` prop:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          tpe = Button.Tpe.Plain(), // usually omitted
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
            <.form(
              Style.flexbox.flex,
              ^.onSubmit ==> (_.preventDefaultCB >> Callback.alert("a")),
              <.div(Style.width.px128, TextBox(value, setValue, placeholder = "Name...")()),
              <.div(Style.margin.left8, Button(tpe = Button.Tpe.Submit())("Submit"))
            )
          }
        )()
      }))(),
      Markdown(
        """
          |Whenever possible, prefer form's `onSubmit` over Button's
          |`onClick` so user can submit via several ways. For example, try
          |pressing "Enter" while you are still in the Text Box above!
          |
          |""".stripMargin
      )(),
      Markdown(
        """
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
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
