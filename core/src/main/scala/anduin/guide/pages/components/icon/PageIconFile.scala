package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.component.icon.Icon.File._
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIconFile {

  private def renderIcon(name: Icon.Name): VdomElement = {
    <.div(
      ^.key := name.getClass.getSimpleName,
      Style.flexbox.none.padding.ver16,
      ^.width := "25%",
      <.div(Style.width.maxContent, IconSizeSample(name)())
    )
  }

  private def renderIcons(names: Icon.Name*): VdomElement = {
    <.div(Style.flexbox.flex.flexbox.wrap, names.toVdomArray(renderIcon))
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("File Icons")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
           |`Icon.File` contains [Icon's names] that are specifically
           |designed for [files and folders][ff]:
           |
           |[Icon's names]: ${ctl.urlFor(Pages.Icon("#name")).value}
           |[ff]: ${ctl.urlFor(Pages.IconGlyph("#files-and-folders")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(name = Icon.File.Signed, size = Icon.Size.Px32)()
      }))(),
      Markdown(
        s"""
           |File Icons usually have several colors and include different
           |designs for each [size]:
           |
           |[size]: ${ctl.urlFor(Pages.Icon("#size")).value}
           |""".stripMargin
      )(),
      <.div(
        Style.padding.ver16,
        renderIcons(Pdf, Word, Text, Zip),
        renderIcons(Generic, Categorized, Draft, Redline, Final, Signed),
        renderIcons(Folder, FolderEmpty, Box, BoxEmpty)
      ),
      Markdown(
        """
          |Unlike Glyph, the colors of File Icons are fixed and cannot be
          |changed by their parent.
          |""".stripMargin
      )()
    )
  }
}
