package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{ButtonLink, ButtonStyle}
import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style

object PageButtonLink {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "ButtonLink", obj = Some(ButtonLink))()
      ),
      Markdown(
        """
          |ButtonLink is a component that works like a link but has the visual of a Button.
          |
          |In technical words: it is rendered as an ["a" tag](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a), but still supports appearance customization via ButtonStyle.
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/<.div(
            Style.flexbox.flex,
            <.div(/*<*/
              ButtonLink(
                href = ctl.urlFor(Pages.Color()).value,
                color = ButtonStyle.ColorBlue
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
        s"""
           |# Usage
           |
           |**The ButtonLink component should be used like the "a" tag,** that is, a hyperlink to another place. For example: "View Transaction", "Go to Setting", etc. Like an "a" tag, ButtonLink supports `href` and `target` props.
           |
           |**ButtonLink can also be used to link to a file *(Download)*,** if it's a static file that always available at a fixed URL. However, if it's a dynamically generated file then it's better to use Button.
           |
           |**`isDisabled` is not available,** since a link should always be accessible. The URL might be 404 or 401, but nothing should prevent users from accessing that URL.
           |
           |# Appearance
           |
           |ButtonLink supports most customization via [ButtonStyle][2], with the following exceptions:
           |
           |[2]: ${ctl.urlFor(Pages.ButtonStyle()).value}
           |
           |**`StyleLink` is not available,** because the main purpose of ButtonLink is to bring the strong appearance of Button to a link. If you don't need that then just simply use an `a` tag.
           |
           |**`isSelected` is not available,** since a link should not have this state at all.
          """.stripMargin
      )()
    )
  }
}
