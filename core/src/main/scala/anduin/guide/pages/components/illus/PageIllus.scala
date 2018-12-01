package anduin.guide.pages.components.illus

import anduin.guide.components._
import anduin.component.icon.Illus
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIllus {

  private val cell = TagMod(
    Style.flexbox.flex.flexbox.itemsCenter,
    Style.flexbox.none.width.pcOneRd
  )

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header(title = "Illus")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Illus is the more complex version of Icon. Illus often has more
          |details and colors. Unlike Icon, the color(s) of an Illus is not
          |customizable.
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Illus(name = Illus.NameDoc)()
      }))(),
      Markdown(
        """
          |>::warning::
          |>**Rename is under consideration**
          |>
          |>Despite the name "Illus", at the moment it only deals with small
          |>symbols such as entity or file's icon. It, however, does not
          |>support large illustration like concept's figure or empty state's
          |>illustration.
          |>
          |>For that reason, this might be renamed to "Symbol" or
          |>"ProductIcon" in the near future.
          |
          |# Name
          |
          |Use the `name` prop to specify which illustration to be rendered:
        """.stripMargin
      )(),
      <.div(
        Style.flexbox.flex.flexbox.wrap,
        Style.fontSize.px12.lineHeight.px40,
        <.div(cell, Illus(Illus.NameDoc)(), " ", "Doc"),
        <.div(cell, Illus(Illus.NameSheet)(), " ", "Sheet"),
        <.div(cell, Illus(Illus.NamePdf)(), " ", "Pdf"),
        <.div(cell, Illus(Illus.NameDocEmpty)(), " ", "DocEmpty")
      ),
      Markdown(
        """
          |# Size
          |
          |**The default size of an Illus is 32 x 32 pixels.** This is double
          |of the default size of an Icon since Illus often has more details.
          |Like Icon, this can be customized via the `size` prop:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.right8
        <.div(
          Style.flexbox.flex,
          <.div(Illus(name = Illus.NameDoc, size = Illus.Size24)(), margin),
          <.div(Illus(name = Illus.NameDoc, size = Illus.Size32)(), margin),
          <.div(Illus(name = Illus.NameDoc, size = Illus.SizeDynamic("64"))())
        )
      }))(),
      Markdown(
        """
          |Like Icon, `SizeDynamic` accepts a string value which will be passed
          |directly to the width and height attributes of Illus's `svg` tag.
        """.stripMargin
      )()
    )
  }
}
