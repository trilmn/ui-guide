package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.icon.Icon
import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style

object PageLogo {

  def renderLink(fileNameAndExtension: String): String = {
    s"https://s3.amazonaws.com/anduin-static-assets/gondor/logos/$fileNameAndExtension"
  }

  def renderLogo(fileName: String, downloadName: String): VdomElement = {
    <.div(
      ^.width := "50%",
      ^.height := "160px",
      ^.paddingBottom := "40px",
      Style.border.all.borderWidth.px1.borderColor.gray3.position.relative.textAlign.center,
      <.img(
        Style.flexbox.none,
        ^.src := renderLink(s"$fileName.svg"),
        TagMod(^.width := "278", ^.height := "278")
      ),
      <.a(
        ^.bottom := "0",
        ^.left := "0",
        ^.right := "0",
        ^.height := "40px",
        Style.flexbox.flex.flexbox.itemsCenter.flexbox.justifyCenter.position.absolute.fontSize.px12,
        Style.backgroundColor.gray2.textAlign.center.border.all.borderWidth.px1.borderColor.gray3,
        ^.href := renderLink(s"$downloadName.zip"),
        <.span(
          Style.margin.right8,
          Icon(name = Icon.NameFileDownload)()
        ),
        <.strong(
          ^.marginRight := "2px", ".Zip"),
        " (Ai, png, eps included)"
      )
    )
  }

  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Logo",
          description = "Anduin’s logo has some variants as listing below."
        )()
      ),

      // Single logo row
      Markdown(
        """
          |# Default
        """.stripMargin
      )(),
      <.div(
        ^.cls := "row",
        Style.flexbox.flex.flexbox.itemsStart,
        renderLogo("logo_anduin_rgb", "anduin_logo"),
        <.div(
          ^.width := "50%",
          Style.margin.left20,
          "Widely used in multiple cases. Most of the time, we will use this to display our brand logo."
        )
      ),

      // Single logo row
      Markdown(
        """
          |# Default on dark background
        """.stripMargin
      )(),
      <.div(
        ^.cls := "row",
        Style.flexbox.flex.flexbox.itemsStart,
        renderLogo("logo_white_anduin_rgb", "anduin_logo_white"),
        <.div(
          ^.width := "50%",
          Style.margin.left20,
          "Use when a dark background is required"
        )
      ),

      // Single logo row
      Markdown(
        """
          |# Logo only
        """.stripMargin
      )(),
      <.div(
        ^.cls := "row",
        Style.flexbox.flex.flexbox.itemsStart,
        renderLogo("logo_mark_anduin_rgb", "anduin_logo_mark"),
        <.div(
          ^.width := "50%",
          Style.margin.left20,
          "Use this when we want to use logo as an avatar or somewhere that requires a square logo."
        )
      ),

      // Single logo row
      Markdown(
        """
          |# Logo only on dark background
        """.stripMargin
      )(),
      <.div(
        ^.cls := "row",
        Style.flexbox.flex.flexbox.itemsStart,
        renderLogo("logo_mark_white_anduin_rgb", "anduin_logo_mark_white"),
        <.div(
          ^.width := "50%",
          Style.margin.left20,
          "Similar to the case above but with a required dark background"
        )
      )
    )
  }
}
