package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIconGlyph {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Glyph Icons")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
          |`Icon.Glyph` are the main set of [Icon's names] that should be
          |used all around the platform:
          |
          |[Icon's names]: ${ctl.urlFor(Pages.Icon("#name")).value}
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(name = Icon.Glyph.Sign)()
      }))(),
      Markdown(
        s"""
           |Glyph Icons are simple and clear, as they are designed to work best
           |at 16 pixels. For more complex and detailed icons, see the [File]
           |and [Negotiation] sets.
           |
           |[File]: ${ctl.urlFor(Pages.IconFile()).value}
           |[Negotiation]: ${ctl.urlFor(Pages.IconNego()).value}
           |
           |# Names
           |
           |For documentation purpose, Glyph Icons can be categorized into the
           |following groups:
        """.stripMargin
      )(),
      PageIconGlyphName(ctl)(),
      Markdown(
        s"""
           |# Color
           |
           |Glyph Icons have one color and it is inherited from the parent:
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.color.primary4,
          Icon(name = Icon.Glyph.Tag)()
        )
      }))()
    )
  }
}
