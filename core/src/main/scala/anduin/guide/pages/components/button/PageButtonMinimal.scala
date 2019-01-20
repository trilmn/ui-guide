// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PageButtonMinimal {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Ghost Button", Some(Button.Style.Ghost))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |## Minimal
          |
          |```scala
          |case class Minimal(/* further customization */)
          |```
          |
          |`Minimal` style is text-only, thus having the lowest emphasis:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex,
          Button(
            style = Button.Style.Minimal(color = Button.Color.Blue)
          )("Submit"),
          Button(
            style = Button.Style.Minimal()
          )("Cancel")
        )
      }))(),
      Markdown(
        """
          |`Minimal` should only be used where the surrounding context already
          |provided enough visual hint that they are interactive content. A good
          |example is actions inside a Toolbar:
          |""".stripMargin
      )(),
      MinimalToolbarExample()(),
      Markdown(
        """
          |### Parameters
          |
          |`Minimal` supports the same parameters as `Ghost`, which are
          |[`color`](#ghost-color), [`size`](#full-size), [`icon`](#full-icon),
          |[`isSelected`](#full-selected), [`isFullWidth`](#full-width) and
          |[`isBusy`](#full-busy). Their usages and default values are the same.
          |""".stripMargin
      )()
    )
  }
}
