package anduin.guide.pages.brand.logo

import anduin.guide.components._
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageLogo {

  private final case class Logo(svg: String, zip: String, isDark: Boolean = false)

  // file should include extension already
  private def renderLink(file: String): String = {
    s"//s3.amazonaws.com/anduin-static-assets/gondor/logos/$file"
  }

  private val logoContainerStyles = TagMod(
    Style.border.all.borderWidth.px1.borderColor.gray3,
    Style.position.relative.textAlign.center
  )

  private val logoImgStyles = TagMod(
    Style.margin.horAuto.display.block,
    TagMod(^.width := "190px", ^.height := "80px")
  )

  private def logoLinkStyles = TagMod(
    Style.flexbox.flex.flexbox.itemsCenter.flexbox.justifyCenter,
    Style.fontSize.px12.padding.ver8,
    Style.backgroundColor.gray1.hover.backgroundWhite.active.backgroundGray2,
    Style.transition.allWithOutline.focus.outline,
    Style.border.top.borderColor.gray3
  )

  private def renderLogo(logo: Logo): VdomElement = {
    <.div(
      logoContainerStyles,
      <.div(
        Style.padding.ver12,
        TagMod.when(logo.isDark) { Style.backgroundColor.primary3 },
        <.img(
          logoImgStyles,
          ^.src := renderLink(s"${logo.svg}.svg")
        )
      ),
      <.a(
        logoLinkStyles,
        ^.href := renderLink(s"${logo.zip}.zip"),
        <.span(Style.margin.right8, Icon(name = Icon.Glyph.FileDownload)()),
        <.span(
          "Download"
        )
      )
    )
  }

  private def renderSection(title: VdomNode, logo: Logo, desc: String): VdomElement = {
    val descNode = <.div(Style.flexbox.fixed.margin.left20, desc)
    val logoNode = <.div(Style.flexbox.fixed, renderLogo(logo))
    val body = <.div(Style.flexbox.flex, logoNode, descNode)
    <.div(title, body)
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Logo", None)(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Anduin’s logo has some variants as listing below. Each package is already included ai, png, eps format.
          |
          |## Default
        """.stripMargin
      )(), {
        renderSection(
          "",
          Logo("logo_anduin_rgb", "anduin_logo"),
          "Widely used in multiple cases. Most of the time, we will " +
            "use this to display our brand logo."
        )
      }, {
        renderSection(
          Markdown(
            """
              |### Default on dark background
          """.stripMargin
          )(),
          Logo("logo_white_anduin_rgb", "anduin_logo_white", isDark = true),
          "Use when a dark background is required"
        )
      },
      Markdown(
        """
          |## Logo only
        """.stripMargin
      )(), {
        renderSection(
          "",
          Logo("logo_mark_anduin_rgb", "anduin_logo_mark"),
          "Use this when we want to use logo as an avatar or somewhere that" +
            "requires a square logo."
        )
      }, {
        renderSection(
          Markdown(
            """
              |### Logo only on dark background
          """.stripMargin
          )(),
          Logo("logo_mark_white_anduin_rgb", "anduin_logo_mark_white", isDark = true),
          "Similar to the case above but with a required dark background"
        )
      }
    )
  }
}
