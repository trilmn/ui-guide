package anduin.guide.pages.components.illus

import anduin.guide.components._
import anduin.component.icon.{Icon, IllusFolder}
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIllusFolder {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("IllusFolder", Some(IllusFolder))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
           |This is a special [Illus][1] introduced due to the common needs
           |of a customizable Folder symbol:
           |
           |[1]: ${ctl.urlFor(Pages.Illus()).value}
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val illus = IllusFolder(
          color = IllusFolder.ColorWarning,
          icon = Icon.NameLightBolt
        )()
        <.div(illus)
      }))(),
      Markdown(
        s"""
           |# Color
           |
           |To change the color of the folder, use the `color` prop with one
           |of the predefined values:
           |[1]: ${ctl.urlFor(Pages.Color()).value}
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val margin = Style.margin.right8
        <.div(
          Style.flexbox.flex, /*<*/
          <.div(IllusFolder(color = IllusFolder.ColorNeutral)(), margin),
          <.div(IllusFolder(color = IllusFolder.ColorPrimary)(), margin),
          <.div(IllusFolder(color = IllusFolder.ColorSuccess)(), margin),
          <.div(IllusFolder(color = IllusFolder.ColorWarning)(), margin),
          <.div(IllusFolder(color = IllusFolder.ColorDanger)()) /*>*/
        ) /*<*/
      }))(),
      Markdown(
        s"""
           |# Icon
           |
           |To add an icon on the folder, use the `icon` prop with an [Icon's
           |Name][1]:
           |
           |[1]: ${ctl.urlFor(Pages.Icon("#name")).value}
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.right8
        <.div(
          Style.flexbox.flex,
          <.div(IllusFolder(icon = Icon.NameCaretDown)(), margin),
          <.div(IllusFolder(icon = Icon.NameCheckList)(), margin),
          <.div(
            IllusFolder(
              icon = Icon.NameCheckList,
              color = IllusFolder.ColorNeutral
            )()
          )
        )
      }))(),
      Markdown(
        s"""
           |The icon will be dimmed and resized to suit the folder for you.
           |
           |# Size
           |
           |Like Illus, IllusFolder also accepts resizing via a `size` prop.
           |It works exactly like [Illus's Size][1].
           |
           |[1]: ${ctl.urlFor(Pages.Illus("#size")).value}
        """.stripMargin
      )()
    )
  }
}
