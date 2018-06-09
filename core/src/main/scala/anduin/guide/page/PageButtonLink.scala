package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonLink, ButtonStyle}
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageButtonLink {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "ButtonLink")()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |ButtonLink(
          |  href: String,
          |  target: String
          |)()
          |```
          |
          |ButtonLink is a component that works like a link but has the visual of a Button.
          |
          |In technical words: it is rendered as an ["a" tag](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a), but still supports appearance customization via ButtonStyle.
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/
              ButtonLink(
                href = ctl.urlFor(Main.Color()).value,
                color = ButtonStyle.ColorPrimary
              )("Go to Color guide")/*>*/
            ),
            <.div(/*<*/
              ButtonLink(
                href = "https://google.com"
              )("Go to Google"),
              Style.margin.left8/*>*/
            )
          )/*<*/
        })
        // format: on
      )(),
      Markdown(
        """
          |
          |# Usage
          |
          |**The ButtonLink component should be used like the "a" tag,** that is, a hyperlink to another place. For example: "View Transaction", "Go to Setting", etc. At the moment, ButtonLink supports "href" and "target" props, which work exactly like in an `a` tag.
          |
          |**ButtonLink can also be used to link to a file *(Download)*,** if it's a static file that always available at a fixed URL. However, if it's a dynamically generated file then it's better to use Button.
          |
          |**`StyleLink` is not available,** because the main purpose of ButtonLink is to bring the strong appearance of Button to a link. If you don't need that then just simply use an `a` tag.
          |
          |**`isDisabled` is not available,** since a link should always be accessible. The URL might be 404 or 401, but nothing should prevent users from accessing that URL.
          |
          |**`isSelected` is not available,** since a link should not have this state at all.
          |""".stripMargin
      )()
    )
  }
}
