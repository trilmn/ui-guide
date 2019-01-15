package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.component.icon.Icon.Nego._
import anduin.component.tooltip.Tooltip
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIconNego {

  private def renderIcon(name: Icon.Name): VdomElement = {
    val sName = name.getClass.getSimpleName
    val tooltip = Tooltip(
      renderTarget = IconSample(name, Icon.Size.Px24, Style.padding.all16.color.gray7)(),
      renderContent = () => sName
    )()
    <.div(^.key := sName, tooltip)
  }

  private def renderTitle(title: String): VdomElement = {
    <.p(
      Style.fontSize.px13.fontWeight.semiBold,
      ^.width := "80px",
      title
    )
  }

  private def renderIcons(title: String, names: Seq[Icon.Name]): VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.itemsCenter.padding.ver8,
      Style.border.bottom.borderColor.gray3,
      renderTitle(title),
      names.toVdomArray(renderIcon)
    )
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Negotiation Icons")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
           |`Icon.Nego` contains [Icon's names] that are specifically
           |designed for [negotiation actions][nc]:
           |
           |[Icon's names]: ${ctl.urlFor(Pages.Icon("#name")).value}
           |[nc]: ${ctl.urlFor(Pages.IconGlyph("#negotiation")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(name = Sign, size = Icon.Size.Px24)()
      }))(),
      Markdown(
        s"""
           |Negotiation Icons are designed to work best at 24 pixels, thus
           |having more details than Glyph. They, however, still have only
           |one color:
           |
           |""".stripMargin
      )(),
      <.div(
        Style.padding.ver16,
        renderIcons("Request", Vector(RequestDocs, UploadDoc, UploadMultipleDocs, UploadDraft, EditDoc)),
        renderIcons("Review", Vector(ReviewDoc, Comment, ShareViaEmail, Compare, Redline, MarkAsFinal, MarkLDAsComplete)),
        renderIcons("Sign", Vector(RequestToSign, Sign, UploadSignedDoc, ReleaseSignedDoc)),
        renderIcons("Wire", Vector(ShareBankInfo, ConfirmFundSent, ShareWireInfo, ConfirmFundReceived, ConfirmCertSent)),
        renderIcons("Other", Vector(ShareChecklist, ViewChecklist, MarkAsComplete, PlusSquare)),
      ),
      Markdown(
        s"""
           |Like Glyph, the color of a Negotiation Icon can be customized via
           |its parent:
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.color.primary4,
          Icon(name = Icon.Nego.MarkAsFinal, size = Icon.Size.Px24)()
        )
      }))(),
    )
  }
}
