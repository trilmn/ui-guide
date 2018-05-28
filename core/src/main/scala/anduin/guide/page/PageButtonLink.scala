package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonLink}
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageButtonLink {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "ButtonLink",
          description = ""
        )()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |ButtonLink(
          |)()
          |
          |
          |```
          |
          |Example:
        """.stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div()
        )
      )(),
      Markdown(
        """
          |## Link type
          |
          |The `tpe` prop also has a special `TpeLink` value to render an actual link (`a` tag). This should be used when you want something that works like a link (i.e: change URL and can be opened in new tab) but looks like a button.
          |
          |**Example:** (try hover and right click each)
          |""".stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.flexbox.flex,
            <.div(/*<*/Button()("Button")/*>*/, Style.margin.right16),
            <.div(/*<*/ButtonLink(href = "https://google.com")("Link")/*>*/)
          )/*<*/
        )
        // format: on
      )(),
      Markdown(s"""
                  |Since this is an actual link, `TpeLink` requires a valid `href`.
                  |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val page = Main.ButtonStyle()
          <.div(
            Style.flexbox.flex,
            ButtonLink(href = ctl.urlFor(page).value)("Go to ButtonStyle page")
          )
        })
      )(),
      Markdown(
        """
          |In the example above:
          |
          |- The `onClick` event is handled by `RouterCtl`, so page change happens immediatelly on click.
          |- Meanwhile, it still has a valid `href`, so you can right click and open the link in new tab.
          |
          |### Usage Note
          |
          |**`TpeLink` takes 100% width by default,** due to browser's default rendering of `flex` display. However, this behaviour is eliminated if the button is placed inside another `flex`.
          |
          |""".stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val button = ButtonLink()("Link")
          <.div(
            <.div(Style.margin.bottom8, button),
            <.div(Style.flexbox.flex, button)
          )
        })
      )(),
      Markdown(
        """
          |**`isDisabled` is not available,** since a link should always be accessible. The page at an URL might be 404 or 401, but nothing should prevent users from accessing that URL.
          |
          |**`isSelected` is not available,** since a link should not have this state at all.
          |
          | """.stripMargin
      )()
    )
  }
}
