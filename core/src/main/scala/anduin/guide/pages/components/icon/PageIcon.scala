package anduin.guide.pages.components.icon

import anduin.guide.components._
import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIcon {
  private val cell = TagMod(
    Style.flexbox.flex.flexbox.itemsCenter,
    Style.flexbox.none.width.pcOneRd
  )

  private val break = <.div(Style.flexbox.none.width.pc100.height.px24)

  private def renderIcons(nodes: VdomNode*) = {
    <.div(
      Style.flexbox.flex.flexbox.wrap,
      Style.fontSize.px12.lineHeight.px40,
      nodes.toTagMod
    )
  }

  private def renderNames: VdomNode = ReactFragment(
    Markdown(
      """
        |# Name
        |
        |Use the `name` prop to specify which icon to be rendered:
        |
        |## Action
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameCheck)(), " ", "Check"),
      <.div(cell, Icon(name = Icon.NameCheckSmall)(), " ", "CheckSmall"),
      <.div(cell, Icon(name = Icon.NameCheckCircle)(), " ", "CheckCircle"),
      <.div(cell, Icon(name = Icon.NameCross)(), " ", "Cross"),
      <.div(cell, Icon(name = Icon.NameCrossSmall)(), " ", "CrossSmall"),
      <.div(cell, Icon(name = Icon.NameCrossCircle)(), " ", "CrossCircle"),
      <.div(cell, Icon(name = Icon.NamePlus)(), " ", "Plus"),
      <.div(cell, Icon(name = Icon.NamePlusSmall)(), " ", "PlusSmall"),
      <.div(cell, Icon(name = Icon.NamePlusCircle)(), " ", "PlusCircle"),
      <.div(cell, Icon(name = Icon.NameMinus)(), " ", "Minus"),
      <.div(cell, Icon(name = Icon.NameMinusSmall)(), " ", "MinusSmall"),
      <.div(cell, Icon(name = Icon.NameMinusCircle)(), " ", "MinusCircle"),
      <.div(cell, Icon(name = Icon.NameArchive)(), " ", "Archive"),
      <.div(cell, Icon(name = Icon.NameTrash)(), " ", "Trash"),
      <.div(cell, Icon(name = Icon.NameAutoTrack)(), " ", "AutoTrack"),
      <.div(cell, Icon(name = Icon.NameBookmark)(), " ", "Bookmark"),
      <.div(cell, Icon(name = Icon.NameTag)(), " ", "Tag"),
      <.div(cell, Icon(name = Icon.NameFilter)(), " ", "Filter"),
      <.div(cell, Icon(name = Icon.NameShare)(), " ", "Share"),
      <.div(cell, Icon(name = Icon.NameComment)(), " ", "Comment"),
      <.div(cell, Icon(name = Icon.NameFlag)(), " ", "Flag"),
      <.div(cell, Icon(name = Icon.NameFlagLine)(), " ", "FlagLine"),
      <.div(cell, Icon(name = Icon.NameDuplicate)(), " ", "Duplicate"),
      <.div(cell, Icon(name = Icon.NameMultiSelect)(), " ", "MultiSelect"),
      <.div(cell, Icon(name = Icon.NameLock)(), " ", "Lock"),
      <.div(cell, Icon(name = Icon.NameUnlock)(), " ", "Unlock"),
      <.div(cell, Icon(name = Icon.NameSign)(), " ", "Sign"),
      <.div(cell, Icon(name = Icon.NameSignatureRequest)(), " ", "SignatureRequest"),
      <.div(cell, Icon(name = Icon.NameSignatureEdit)(), " ", "SignatureEdit"),
      <.div(cell, Icon(name = Icon.NameClipboard)(), " ", "Clipboard"),
      <.div(cell, Icon(name = Icon.NameClipboardCopied)(), " ", "ClipboardCopied"),
      <.div(cell, Icon(name = Icon.NameExpand)(), " ", "Expand"),
      <.div(cell, Icon(name = Icon.NameCollapse)(), " ", "Collapse"),
      <.div(cell, Icon(name = Icon.NameUpload)(), " ", "Upload"),
      <.div(cell, Icon(name = Icon.NameDownload)(), " ", "Download"),
      <.div(cell, Icon(name = Icon.NameCloudDownload)(), " ", "CloudDownload"),
      <.div(cell, Icon(name = Icon.NameCloudUpload)(), " ", "CloudUpload"),
      <.div(cell, Icon(name = Icon.NameCloudDownloadLine)(), " ", "CloudDownloadLine"),
      <.div(cell, Icon(name = Icon.NameCloudUploadLine)(), " ", "CloudUploadLine"),
      <.div(cell, Icon(name = Icon.NameEdit)(), " ", "Edit"),
      <.div(cell, Icon(name = Icon.NameEditInline)(), " ", "EditInline"),
      <.div(cell, Icon(name = Icon.NameEditField)(), " ", "EditField"),
      <.div(cell, Icon(name = Icon.NameLink)(), " ", "Link"),
      <.div(cell, Icon(name = Icon.NameUnlink)(), " ", "Unlink"),
      <.div(cell, Icon(name = Icon.NameSearch)(), " ", "Search"),
      <.div(cell, Icon(name = Icon.NameZoomIn)(), " ", "ZoomIn"),
      <.div(cell, Icon(name = Icon.NameZoomOut)(), " ", "ZoomOut"),
      <.div(cell, Icon(name = Icon.NameSearchInline)(), " ", "SearchInline"),
      <.div(cell, Icon(name = Icon.NameCompare)(), " ", "Compare"),
      <.div(cell, Icon(name = Icon.NameMove)(), " ", "Move"),
      <.div(cell, Icon(name = Icon.NameEnter)(), " ", "Enter"),
      <.div(cell, Icon(name = Icon.NameRollBack)(), " ", "RollBack"),
      <.div(cell, Icon(name = Icon.NameSwap)(), " ", "Swap"),
      <.div(cell, Icon(name = Icon.NameSwapLeft)(), " ", "SwapLeft"),
      <.div(cell, Icon(name = Icon.NameSwapRight)(), " ", "SwapRight"),
      <.div(cell, Icon(name = Icon.NameRedo)(), " ", "Redo"),
      <.div(cell, Icon(name = Icon.NameUndo)(), " ", "Undo"),
      <.div(cell, Icon(name = Icon.NameRefresh)(), " ", "Refresh"),
      <.div(cell, Icon(name = Icon.NameRepeat)(), " ", "Repeat"),
      <.div(cell, Icon(name = Icon.NameReTweet)(), " ", "ReTweet"),
      <.div(cell, Icon(name = Icon.NameReply)(), " ", "Reply"),
      <.div(cell, Icon(name = Icon.NameReplyAll)(), " ", "ReplyAll"),
      <.div(cell, Icon(name = Icon.NameLogin)(), " ", "Login"),
      <.div(cell, Icon(name = Icon.NameLogout)(), " ", "Logout"),
      <.div(cell, Icon(name = Icon.NameStepBackward)(), " ", "StepBackward"),
      <.div(cell, Icon(name = Icon.NameStepForward)(), " ", "StepForward"),
      <.div(cell, Icon(name = Icon.NameFastBackward)(), " ", "FastBackward"),
      <.div(cell, Icon(name = Icon.NameFastForward)(), " ", "FastForward")
    ),
    Markdown(
      """
        |## Brand
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameBrandAnduin)(), " ", "BrandAnduin"),
      <.div(cell, Icon(name = Icon.NameBrandMicrosoft)(), " ", "BrandMicrosoft"),
      <.div(cell, Icon(name = Icon.NameBrandGoogle)(), " ", "BrandGoogle")
    ),
    Markdown(
      """
        |## Direction
      """.stripMargin
    )(),
    renderIcons(
      //Arrows
      <.div(cell, Icon(name = Icon.NameArrowTopLeft)(), " ", "ArrowTopLeft"),
      <.div(cell, Icon(name = Icon.NameArrowUp)(), " ", "ArrowUp"),
      <.div(cell, Icon(name = Icon.NameArrowTopRight)(), " ", "ArrowTopRight"),
      <.div(cell, Icon(name = Icon.NameArrowLeft)(), " ", "ArrowLeft"),
      <.div(cell),
      <.div(cell, Icon(name = Icon.NameArrowRight)(), " ", "ArrowRight"),
      <.div(cell, Icon(name = Icon.NameArrowDownLeft)(), " ", "ArrowDownLeft"),
      <.div(cell, Icon(name = Icon.NameArrowDown)(), " ", "ArrowDown"),
      <.div(cell, Icon(name = Icon.NameArrowDownRight)(), " ", "ArrowDownRight"),
      <.div(cell, Icon(name = Icon.NameArrowHorizontal)(), " ", "ArrowHorizontal"),
      <.div(cell, Icon(name = Icon.NameArrowVertical)(), " ", "ArrowVertical"),
      break,
      //Carets
      <.div(cell, Icon(name = Icon.NameCaretUp)(), " ", "CaretUp"),
      <.div(cell, Icon(name = Icon.NameCaretLeft)(), " ", "CaretLeft"),
      <.div(cell, Icon(name = Icon.NameCaretRight)(), " ", "CaretRight"),
      <.div(cell, Icon(name = Icon.NameCaretDown)(), " ", "CaretDown"),
      <.div(cell, Icon(name = Icon.NameCaretHorizontal)(), " ", "CaretHorizontal"),
      <.div(cell, Icon(name = Icon.NameCaretVertical)(), " ", "CaretVertical"),
      break,
      //Chevrons
      <.div(cell, Icon(name = Icon.NameChevronUp)(), " ", "ChevronUp"),
      <.div(cell, Icon(name = Icon.NameChevronCircleUp)(), " ", "ChevronCircleUp"),
      <.div(cell, Icon(name = Icon.NameChevronCircleLineUp)(), " ", "ChevronCircleLineUp"),
      <.div(cell, Icon(name = Icon.NameChevronDown)(), " ", "ChevronDown"),
      <.div(cell, Icon(name = Icon.NameChevronCircleDown)(), " ", "ChevronCircleDown"),
      <.div(cell, Icon(name = Icon.NameChevronCircleLineDown)(), " ", "ChevronCircleLineDown"),
      <.div(cell, Icon(name = Icon.NameChevronLeft)(), " ", "ChevronLeft"),
      <.div(cell, Icon(name = Icon.NameChevronCircleLeft)(), " ", "ChevronCircleLeft"),
      <.div(cell, Icon(name = Icon.NameChevronCircleLineLeft)(), " ", "ChevronCircleLineLeft"),
      <.div(cell, Icon(name = Icon.NameChevronRight)(), " ", "ChevronRight"),
      <.div(cell, Icon(name = Icon.NameChevronCircleRight)(), " ", "ChevronCircleRight"),
      <.div(cell, Icon(name = Icon.NameChevronCircleLineRight)(), " ", "ChevronCircleLineRight"),
      break,
      <.div(cell, Icon(name = Icon.NameChevronDoubleUp)(), " ", "ChevronDoubleUp"),
      <.div(cell, Icon(name = Icon.NameChevronHorizontalUp)(), " ", "ChevronHorizontalUp"),
      <.div(cell),
      <.div(cell, Icon(name = Icon.NameChevronDoubleDown)(), " ", "ChevronDoubleDown"),
      <.div(cell, Icon(name = Icon.NameChevronHorizontalDown)(), " ", "ChevronHorizontalDown"),
      <.div(cell),
      <.div(cell, Icon(name = Icon.NameChevronDoubleLeft)(), " ", "ChevronDoubleLeft"),
      <.div(cell, Icon(name = Icon.NameChevronVerticalLeft)(), " ", "ChevronVerticalLeft"),
      <.div(cell),
      <.div(cell, Icon(name = Icon.NameChevronDoubleRight)(), " ", "ChevronDoubleRight"),
      <.div(cell, Icon(name = Icon.NameChevronVerticalRight)(), " ", "ChevronVerticalRight"),
      <.div(cell)
    ),
    Markdown(
      """
        |## Email & Editor
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameInbox)(), " ", "Inbox"),
      <.div(cell, Icon(name = Icon.NameInboxReceive)(), " ", "InboxReceive"),
      <.div(cell, Icon(name = Icon.NameInboxSend)(), " ", "InboxSend"),
      <.div(cell, Icon(name = Icon.NameEnvelope)(), " ", "Envelope"),
      <.div(cell, Icon(name = Icon.NameEnvelopeSend)(), " ", "EnvelopeSend"),
      <.div(cell, Icon(name = Icon.NameAttachment)(), " ", "Attachment"),
      <.div(cell, Icon(name = Icon.NameAlignCenter)(), " ", "AlignCenter"),
      <.div(cell, Icon(name = Icon.NameAlignJustify)(), " ", "AlignJustify"),
      <.div(cell, Icon(name = Icon.NameAlignLeft)(), " ", "AlignLeft"),
      <.div(cell, Icon(name = Icon.NameAlignRight)(), " ", "AlignRight"),
      <.div(cell, Icon(name = Icon.NameTextStyle)(), " ", "TextStyle"),
      <.div(cell, Icon(name = Icon.NameBold)(), " ", "Bold"),
      <.div(cell, Icon(name = Icon.NameItalic)(), " ", "Italic"),
      <.div(cell, Icon(name = Icon.NameUnderline)(), " ", "Underline"),
      <.div(cell, Icon(name = Icon.NameStrikeThrough)(), " ", "StrikeThrough"),
      <.div(cell, Icon(name = Icon.NameHeader)(), " ", "Header"),
      <.div(cell, Icon(name = Icon.NameHeader1)(), " ", "Header1"),
      <.div(cell, Icon(name = Icon.NameHeader2)(), " ", "Header2"),
      <.div(cell, Icon(name = Icon.NameInput)(), " ", "Input"),
      <.div(cell, Icon(name = Icon.NameCitation)(), " ", "Citation"),
      <.div(cell, Icon(name = Icon.NameParagraph)(), " ", "Paragraph"),
      <.div(cell, Icon(name = Icon.NameListBullet)(), " ", "ListBullet"),
      <.div(cell, Icon(name = Icon.NameListNumber)(), " ", "ListNumber")
    ),
    Markdown(
      """
        |## File & Folder
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameFile)(), " ", "File"),
      <.div(cell, Icon(name = Icon.NameFileAdd)(), " ", "FileAdd"),
      <.div(cell, Icon(name = Icon.NameFileGenerate)(), " ", "FileGenerate"),
      <.div(cell, Icon(name = Icon.NameFileDownload)(), " ", "FileDownload"),
      <.div(cell, Icon(name = Icon.NameFileExport)(), " ", "FileExport"),
      <.div(cell, Icon(name = Icon.NameFileSend)(), " ", "FileSend"),
      <.div(cell, Icon(name = Icon.NameFilePreview)(), " ", "FilePreview"),
      <.div(cell, Icon(name = Icon.NameFileText)(), " ", "FileText"),
      <.div(cell, Icon(name = Icon.NameFileUnknown)(), " ", "FileUnknown"),
      <.div(cell, Icon(name = Icon.NameFilePdf)(), " ", "FilePdf"),
      <.div(cell, Icon(name = Icon.NameFileDoc)(), " ", "FileDoc"),
      <.div(cell, Icon(name = Icon.NameFileXls)(), " ", "FileXls"),
      <.div(cell, Icon(name = Icon.NameFolder)(), " ", "Folder"),
      <.div(cell, Icon(name = Icon.NameFolderOpen)(), " ", "FolderOpen"),
      <.div(cell, Icon(name = Icon.NameFolderAdd)(), " ", "FolderAdd"),
      <.div(cell, Icon(name = Icon.NameFolderSend)(), " ", "FolderSend"),
      <.div(cell, Icon(name = Icon.NameFolderLine)(), " ", "FolderLine"),
      <.div(cell, Icon(name = Icon.NameFolderOpenLine)(), " ", "FolderOpenLine"),
      <.div(cell, Icon(name = Icon.NameFolderAddLine)(), " ", "FolderAddLine"),
      <.div(cell, Icon(name = Icon.NameFolderSendLine)(), " ", "FolderSendLine")
    ),
    Markdown(
      """
        |## Interface
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameHome)(), " ", "Home"),
      <.div(cell, Icon(name = Icon.NameOffice)(), " ", "Office"),
      <.div(cell, Icon(name = Icon.NameSnapshot)(), " ", "Snapshot"),
      <.div(cell, Icon(name = Icon.NameDashboard)(), " ", "Dashboard"),
      <.div(cell, Icon(name = Icon.NameViewGrid)(), " ", "ViewGrid"),
      <.div(cell, Icon(name = Icon.NameViewList)(), " ", "ViewList"),
      <.div(cell, Icon(name = Icon.NameCheckList)(), " ", "CheckList"),
      <.div(cell, Icon(name = Icon.NameToolbox)(), " ", "Toolbox"),
      <.div(cell, Icon(name = Icon.NameCog)(), " ", "Cog"),
      <.div(cell, Icon(name = Icon.NameWrench)(), " ", "Wrench"),
      <.div(cell, Icon(name = Icon.NameKey)(), " ", "Key"),
      <.div(cell, Icon(name = Icon.NameLayer)(), " ", "Layer"),
      <.div(cell, Icon(name = Icon.NameCalendar)(), " ", "Calendar"),
      <.div(cell, Icon(name = Icon.NameClock)(), " ", "Clock"),
      <.div(cell, Icon(name = Icon.NameSandClock)(), " ", "SandClock"),
      <.div(cell, Icon(name = Icon.NameBell)(), " ", "Bell"),
      <.div(cell, Icon(name = Icon.NameChannel)(), " ", "Channel"),
      <.div(cell, Icon(name = Icon.NameData)(), " ", "Data"),
      <.div(cell, Icon(name = Icon.NameDataRoom)(), " ", "DataRoom"),
      <.div(cell, Icon(name = Icon.NameKnowledge)(), " ", "Knowledge"),
      <.div(cell, Icon(name = Icon.NameInfo)(), " ", "Info"),
      <.div(cell, Icon(name = Icon.NameInfoCircleLine)(), " ", "InfoCircleLine"),
      <.div(cell, Icon(name = Icon.NameWarning)(), " ", "Warning"),
      <.div(cell, Icon(name = Icon.NameError)(), " ", "Error"),
      <.div(cell, Icon(name = Icon.NameComputer)(), " ", "Computer"),
      <.div(cell, Icon(name = Icon.NameMobile)(), " ", "Mobile"),
      <.div(cell, Icon(name = Icon.NameMobileAuthenticate)(), " ", "MobileAuthenticate"),
      <.div(cell, Icon(name = Icon.NameCloud)(), " ", "Cloud"),
      <.div(cell, Icon(name = Icon.NameCloudLine)(), " ", "CloudLine"),
      <.div(cell, Icon(name = Icon.NameCode)(), " ", "Code"),
      <.div(cell, Icon(name = Icon.NameCodeLine)(), " ", "CodeLine"),
      <.div(cell, Icon(name = Icon.NameCircle)(), " ", "Circle"),
      <.div(cell, Icon(name = Icon.NameCircleLine)(), " ", "CircleLine"),
      <.div(cell, Icon(name = Icon.NameCircleLargeLine)(), " ", "CircleLargeLine"),
      <.div(cell, Icon(name = Icon.NameEllipsisHorizontal)(), " ", "EllipsisHorizontal"),
      <.div(cell, Icon(name = Icon.NameEllipsisVertical)(), " ", "EllipsisVertical"),
      <.div(cell, Icon(name = Icon.NameEye)(), " ", "Eye"),
      <.div(cell, Icon(name = Icon.NameEyeOff)(), " ", "EyeOff"),
      <.div(cell, Icon(name = Icon.NameStar)(), " ", "Star"),
      <.div(cell, Icon(name = Icon.NameStarLine)(), " ", "StarLine"),
      <.div(cell, Icon(name = Icon.NamePulse)(), " ", "Pulse"),
      <.div(cell, Icon(name = Icon.NamePulseCircle)(), " ", "PulseCircle"),
      <.div(cell, Icon(name = Icon.NameLightBolt)(), " ", "LightBolt"),
      <.div(cell, Icon(name = Icon.NameLightBoltCircle)(), " ", "LightBoltCircle"),
      <.div(cell, Icon(name = Icon.NameBlank)(), " ", "Blank")
    ),
    Markdown(
      """
        |## Graph
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameTable)(), " ", "Table"),
      <.div(cell, Icon(name = Icon.NameSort)(), " ", "Sort"),
      <.div(cell, Icon(name = Icon.NameSortAsc)(), " ", "SortAsc"),
      <.div(cell, Icon(name = Icon.NameSortDes)(), " ", "SortDes"),
      <.div(cell, Icon(name = Icon.NameSortAlphaAsc)(), " ", "SortAlphaAsc"),
      <.div(cell, Icon(name = Icon.NameSortAlphaDes)(), " ", "SortAlphaDes"),
      <.div(cell, Icon(name = Icon.NameSortNumberAsc)(), " ", "SortNumberAsc"),
      <.div(cell, Icon(name = Icon.NameSortNumberDes)(), " ", "SortNumberDes"),
      <.div(cell, Icon(name = Icon.NameChartProgress)(), " ", "ChartProgress"),
      <.div(cell, Icon(name = Icon.NameChartBarHorizontal)(), " ", "ChartBarHorizontal"),
      <.div(cell, Icon(name = Icon.NameChartBarVertical)(), " ", "ChartBarVertical"),
      <.div(cell, Icon(name = Icon.NameChartPie)(), " ", "ChartPie")
    ),
    Markdown(
      """
        |## User
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, Icon(name = Icon.NameUser)(), " ", "User"),
      <.div(cell, Icon(name = Icon.NameUserGroup)(), " ", "UserGroup"),
      <.div(cell, Icon(name = Icon.NameUserInvestor)(), " ", "UserInvestor"),
      <.div(cell, Icon(name = Icon.NameUserCompany)(), " ", "UserCompany"),
      <.div(cell, Icon(name = Icon.NameUserAdd)(), " ", "UserAdd"),
      <.div(cell, Icon(name = Icon.NameUserAccept)(), " ", "UserAccept"),
      <.div(cell, Icon(name = Icon.NameUserBlock)(), " ", "UserBlock"),
      <.div(cell, Icon(name = Icon.NameUserRemove)(), " ", "UserRemove"),
      <.div(cell, Icon(name = Icon.NameUserEdit)(), " ", "UserEdit"),
      <.div(cell, Icon(name = Icon.NameUserLink)(), " ", "UserLink"),
      <.div(cell, Icon(name = Icon.NameUserInfo)(), " ", "UserInfo")
    )
  )

  private def renderUsage(ctl: Pages.Ctl): VdomNode = ReactFragment(
    Markdown(
      """
        |# Color
        |
        |**Icon has one color.** Icon's color is inherited from its parent's
        |color. Therefore, to set the color of an icon you should set it at
        |that icon's wrapper:
      """.stripMargin
    )(),
    ExampleRich(Source.annotate({
      <.div(
        Style.color.primary4,
        Icon(name = Icon.NameLayer)()
      )
    }))(),
    Markdown(
      """
        |# Size
        |
        |**The default size of an icon is 16 x 16 pixels.** This can be 
        |customized via the `size` prop:
      """.stripMargin
    )(),
    ExampleRich(Source.annotate({
      val margin = Style.margin.right8
      <.div(
        Style.flexbox.flex,
        <.div(Icon(name = Icon.NameLayer, size = Icon.Size16)(), margin),
        <.div(Icon(name = Icon.NameLayer, size = Icon.Size32)(), margin),
        <.div(Icon(name = Icon.NameLayer, size = Icon.SizeDynamic("48"))())
      )
    }))(),
    Markdown(
      """
        |It is intentional that `SizeDynamic` accepts a string value as it
        |will be passed directly to the `svg` tag of the icon:
        |
        |```scala
        |// Snippet from Icon's source code
        |private def render(props: Props): VdomElement = {
        |  <.svg(
        |    ^.viewBox := "0 0 16 16",
        |    ^.width := props.size.value,
        |    ^.height := props.size.value,
        |    /* ... */
        |  )
        |}
        |```
      """.stripMargin
    )()
  )

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Icon", Some(Icon))(),
      Toc(headings = Source.getTocHeadings)(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          Icon(name = Icon.NameOffice)(),
          <.span(Style.margin.left8, "Office")
        )
      }))(),
      renderNames,
      renderUsage(ctl)
    )
  }

}
