// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.guide.components.{ExampleRich, ExampleSimple, Markdown, SimpleState}
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageButtonDisabled() {
  def apply(): VdomElement = PageButtonDisabled.component(this)
}

object PageButtonDisabled {

  private type Props = PageButtonDisabled

  def getHeadings: List[(Int, String)] = Source.getTocHeadings

  private def render(props: Props): VdomElement = <.div(
    Markdown(
      """
        |# Disabled
        |
        |```scala
        |isDisabled: Boolean = false
        |```
        |
        |Set `isDisabled = true` to prevent users' interactions with the
        |Button completely:
        |""".stripMargin
    )(),
    ExampleRich(Source.annotate({
      Button(
        onClick = Callback.alert("Hello"),
        isDisabled = true
      )("Disabled")
    }))(),
    Markdown(
      """
        |To be specific, when `isDisabled` is set:
        |- Event handlers such as `onClick` are removed
        |- Built-in behaviour of [`Submit`](#submit) type is disabled
        |- `href` attribute of [`Link`](#type-link) type is removed
        |
        |`isDisabled` also affects Button's appearance:
      """.stripMargin
    )(),
    ExampleSimple()({
      SimpleState.Bool(
        initialValue = false,
        render = (isDisabled, setIsDisabled) => {
          val B = Button(isDisabled = isDisabled)
          <.div(
            <.div(
              Style.margin.bottom16.padding.bottom16,
              Style.border.bottom.borderColor.gray4,
              Button(onClick = setIsDisabled(!isDisabled))("Toggle isDisabled")
            ),
            <.div(
              Style.flexbox.flex,
              B.copy(style = Button.Style.Full(color = Button.Color.Blue))("Submit"),
              <.div(Style.margin.left8),
              B.copy()("Cancel"),
              <.div(Style.margin.left8),
              B.copy(style = Button.Style.Full(icon = Some(Icon.Glyph.Sign)))("Sign"),
              <.div(Style.margin.left8),
              B.copy(style = Button.Style.Full(icon = Some(Icon.Glyph.Download)))(),
              <.div(Style.margin.left8),
              B.copy(style = Button.Style.Full(isBusy = true, icon = Some(Icon.Glyph.Sign)))("Sign")
            ),
            <.div(
              Style.flexbox.flex.margin.top8,
              B.copy(style = Button.Style.Ghost(color = Button.Color.Blue))("Submit"),
              <.div(Style.margin.left8),
              B.copy(style = Button.Style.Ghost())("Cancel")
            ),
            <.div(
              Style.flexbox.flex.margin.top8,
              B.copy(style = Button.Style.Minimal(color = Button.Color.Blue))("Submit"),
              <.div(Style.margin.left8),
              B.copy(style = Button.Style.Minimal())("Cancel")
            ),
            <.p(
              Style.margin.top8,
              B.copy(style = Button.Style.Text())("Accept"),
              " or ",
              B.copy(style = Button.Style.Text(color = Button.Color.Red))("Decline")
            )
          )
        }
      )()
    })
  )

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
