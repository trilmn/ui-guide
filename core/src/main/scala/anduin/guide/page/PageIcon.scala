package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.icon.IconAcl
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

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
        |# Names
        |
        |Use the `name` prop to specify which icon to render. The grouping below is for documentation purpose only.
        |
        |## Action
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameCheck)(), " ", "Check"),
      <.div(cell, IconAcl(name = IconAcl.NameCheckSmall)(), " ", "CheckSmall"),
      <.div(cell, IconAcl(name = IconAcl.NameCheckCircle)(), " ", "CheckCircle"),
      <.div(cell, IconAcl(name = IconAcl.NameCross)(), " ", "Cross"),
      <.div(cell, IconAcl(name = IconAcl.NameCrossSmall)(), " ", "CrossSmall"),
      <.div(cell, IconAcl(name = IconAcl.NameCrossCircle)(), " ", "CrossCircle"),
      <.div(cell, IconAcl(name = IconAcl.NamePlus)(), " ", "Plus"),
      <.div(cell, IconAcl(name = IconAcl.NamePlusSmall)(), " ", "PlusSmall"),
      <.div(cell, IconAcl(name = IconAcl.NamePlusCircle)(), " ", "PlusCircle"),
      <.div(cell, IconAcl(name = IconAcl.NameMinus)(), " ", "Minus"),
      <.div(cell, IconAcl(name = IconAcl.NameMinusSmall)(), " ", "MinusSmall"),
      <.div(cell, IconAcl(name = IconAcl.NameMinusCircle)(), " ", "MinusCircle"),
      <.div(cell, IconAcl(name = IconAcl.NameArchive)(), " ", "Archive"),
      <.div(cell, IconAcl(name = IconAcl.NameTrash)(), " ", "Trash"),
      <.div(cell, IconAcl(name = IconAcl.NameAutoTrack)(), " ", "AutoTrack"),
      <.div(cell, IconAcl(name = IconAcl.NameBookmark)(), " ", "Bookmark"),
      <.div(cell, IconAcl(name = IconAcl.NameTag)(), " ", "Tag"),
      <.div(cell, IconAcl(name = IconAcl.NameFilter)(), " ", "Filter"),
      <.div(cell, IconAcl(name = IconAcl.NameShare)(), " ", "Share"),
      <.div(cell, IconAcl(name = IconAcl.NameComment)(), " ", "Comment"),
      <.div(cell, IconAcl(name = IconAcl.NameFlag)(), " ", "Flag"),
      <.div(cell, IconAcl(name = IconAcl.NameFlagLine)(), " ", "FlagLine"),
      <.div(cell, IconAcl(name = IconAcl.NameDuplicate)(), " ", "Duplicate"),
      <.div(cell, IconAcl(name = IconAcl.NameMultiSelect)(), " ", "MultiSelect"),
      <.div(cell, IconAcl(name = IconAcl.NameLock)(), " ", "Lock"),
      <.div(cell, IconAcl(name = IconAcl.NameUnlock)(), " ", "Unlock"),
      <.div(cell, IconAcl(name = IconAcl.NameSign)(), " ", "Sign"),
      <.div(cell, IconAcl(name = IconAcl.NameSignatureRequest)(), " ", "SignatureRequest"),
      <.div(cell, IconAcl(name = IconAcl.NameSignatureEdit)(), " ", "SignatureEdit"),
      <.div(cell, IconAcl(name = IconAcl.NameClipboard)(), " ", "Clipboard"),
      <.div(cell, IconAcl(name = IconAcl.NameClipboardCopied)(), " ", "ClipboardCopied"),
      <.div(cell, IconAcl(name = IconAcl.NameExpand)(), " ", "Expand"),
      <.div(cell, IconAcl(name = IconAcl.NameCollapse)(), " ", "Collapse"),
      <.div(cell, IconAcl(name = IconAcl.NameUpload)(), " ", "Upload"),
      <.div(cell, IconAcl(name = IconAcl.NameDownload)(), " ", "Download"),
      <.div(cell, IconAcl(name = IconAcl.NameCloudDownload)(), " ", "CloudDownload"),
      <.div(cell, IconAcl(name = IconAcl.NameCloudUpload)(), " ", "CloudUpload"),
      <.div(cell, IconAcl(name = IconAcl.NameCloudDownloadLine)(), " ", "CloudDownloadLine"),
      <.div(cell, IconAcl(name = IconAcl.NameCloudUploadLine)(), " ", "CloudUploadLine"),
      <.div(cell, IconAcl(name = IconAcl.NameEdit)(), " ", "Edit"),
      <.div(cell, IconAcl(name = IconAcl.NameEditInline)(), " ", "EditInline"),
      <.div(cell, IconAcl(name = IconAcl.NameEditField)(), " ", "EditField"),
      <.div(cell, IconAcl(name = IconAcl.NameLink)(), " ", "Link"),
      <.div(cell, IconAcl(name = IconAcl.NameUnlink)(), " ", "Unlink"),
      <.div(cell, IconAcl(name = IconAcl.NameSearch)(), " ", "Search"),
      <.div(cell, IconAcl(name = IconAcl.NameZoomIn)(), " ", "ZoomIn"),
      <.div(cell, IconAcl(name = IconAcl.NameZoomOut)(), " ", "ZoomOut"),
      <.div(cell, IconAcl(name = IconAcl.NameSearchInline)(), " ", "SearchInline"),
      <.div(cell, IconAcl(name = IconAcl.NameCompare)(), " ", "Compare"),
      <.div(cell, IconAcl(name = IconAcl.NameMove)(), " ", "Move"),
      <.div(cell, IconAcl(name = IconAcl.NameEnter)(), " ", "Enter"),
      <.div(cell, IconAcl(name = IconAcl.NameRollBack)(), " ", "RollBack"),
      <.div(cell, IconAcl(name = IconAcl.NameSwap)(), " ", "Swap"),
      <.div(cell, IconAcl(name = IconAcl.NameSwapLeft)(), " ", "SwapLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameSwapRight)(), " ", "SwapRight"),
      <.div(cell, IconAcl(name = IconAcl.NameRedo)(), " ", "Redo"),
      <.div(cell, IconAcl(name = IconAcl.NameUndo)(), " ", "Undo"),
      <.div(cell, IconAcl(name = IconAcl.NameRefresh)(), " ", "Refresh"),
      <.div(cell, IconAcl(name = IconAcl.NameRepeat)(), " ", "Repeat"),
      <.div(cell, IconAcl(name = IconAcl.NameReTweet)(), " ", "ReTweet"),
      <.div(cell, IconAcl(name = IconAcl.NameReply)(), " ", "Reply"),
      <.div(cell, IconAcl(name = IconAcl.NameReplyAll)(), " ", "ReplyAll"),
      <.div(cell, IconAcl(name = IconAcl.NameLogin)(), " ", "Login"),
      <.div(cell, IconAcl(name = IconAcl.NameLogout)(), " ", "Logout"),
      <.div(cell, IconAcl(name = IconAcl.NameStepBackward)(), " ", "StepBackward"),
      <.div(cell, IconAcl(name = IconAcl.NameStepForward)(), " ", "StepForward"),
      <.div(cell, IconAcl(name = IconAcl.NameFastBackward)(), " ", "FastBackward"),
      <.div(cell, IconAcl(name = IconAcl.NameFastForward)(), " ", "FastForward")
    ),
    Markdown(
      """
        |## Brand
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameBrandAnduin)(), " ", "BrandAnduin"),
      <.div(cell, IconAcl(name = IconAcl.NameBrandMicrosoft)(), " ", "BrandMicrosoft"),
      <.div(cell, IconAcl(name = IconAcl.NameBrandGoogle)(), " ", "BrandGoogle")
    ),
    Markdown(
      """
        |## Direction
      """.stripMargin
    )(),
    renderIcons(
      //Arrows
      <.div(cell, IconAcl(name = IconAcl.NameArrowTopLeft)(), " ", "ArrowTopLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowUp)(), " ", "ArrowUp"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowTopRight)(), " ", "ArrowTopRight"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowLeft)(), " ", "ArrowLeft"),
      <.div(cell),
      <.div(cell, IconAcl(name = IconAcl.NameArrowRight)(), " ", "ArrowRight"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowDownLeft)(), " ", "ArrowDownLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowDown)(), " ", "ArrowDown"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowDownRight)(), " ", "ArrowDownRight"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowHorizontal)(), " ", "ArrowHorizontal"),
      <.div(cell, IconAcl(name = IconAcl.NameArrowVertical)(), " ", "ArrowVertical"),
      break,
      //Carets
      <.div(cell, IconAcl(name = IconAcl.NameCaretUp)(), " ", "CaretUp"),
      <.div(cell, IconAcl(name = IconAcl.NameCaretLeft)(), " ", "CaretLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameCaretRight)(), " ", "CaretRight"),
      <.div(cell, IconAcl(name = IconAcl.NameCaretDown)(), " ", "CaretDown"),
      <.div(cell, IconAcl(name = IconAcl.NameCaretHorizontal)(), " ", "CaretHorizontal"),
      <.div(cell, IconAcl(name = IconAcl.NameCaretVertical)(), " ", "CaretVertical"),
      break,
      //Chevrons
      <.div(cell, IconAcl(name = IconAcl.NameChevronUp)(), " ", "ChevronUp"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleUp)(), " ", "ChevronCircleUp"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleLineUp)(), " ", "ChevronCircleLineUp"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronDown)(), " ", "ChevronDown"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleDown)(), " ", "ChevronCircleDown"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleLineDown)(), " ", "ChevronCircleLineDown"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronLeft)(), " ", "ChevronLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleLeft)(), " ", "ChevronCircleLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleLineLeft)(), " ", "ChevronCircleLineLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronRight)(), " ", "ChevronRight"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleRight)(), " ", "ChevronCircleRight"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronCircleLineRight)(), " ", "ChevronCircleLineRight"),
      break,
      <.div(cell, IconAcl(name = IconAcl.NameChevronDoubleUp)(), " ", "ChevronDoubleUp"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronHorizontalUp)(), " ", "ChevronHorizontalUp"),
      <.div(cell),
      <.div(cell, IconAcl(name = IconAcl.NameChevronDoubleDown)(), " ", "ChevronDoubleDown"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronHorizontalDown)(), " ", "ChevronHorizontalDown"),
      <.div(cell),
      <.div(cell, IconAcl(name = IconAcl.NameChevronDoubleLeft)(), " ", "ChevronDoubleLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronVerticalLeft)(), " ", "ChevronVerticalLeft"),
      <.div(cell),
      <.div(cell, IconAcl(name = IconAcl.NameChevronDoubleRight)(), " ", "ChevronDoubleRight"),
      <.div(cell, IconAcl(name = IconAcl.NameChevronVerticalRight)(), " ", "ChevronVerticalRight"),
      <.div(cell)
    ),
    Markdown(
      """
        |## Email & Editor
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameInbox)(), " ", "Inbox"),
      <.div(cell, IconAcl(name = IconAcl.NameInboxReceive)(), " ", "InboxReceive"),
      <.div(cell, IconAcl(name = IconAcl.NameInboxSend)(), " ", "InboxSend"),
      <.div(cell, IconAcl(name = IconAcl.NameEnvelope)(), " ", "Envelope"),
      <.div(cell, IconAcl(name = IconAcl.NameEnvelopeSend)(), " ", "EnvelopeSend"),
      <.div(cell, IconAcl(name = IconAcl.NameAttachment)(), " ", "Attachment"),
      <.div(cell, IconAcl(name = IconAcl.NameAlignCenter)(), " ", "AlignCenter"),
      <.div(cell, IconAcl(name = IconAcl.NameAlignJustify)(), " ", "AlignJustify"),
      <.div(cell, IconAcl(name = IconAcl.NameAlignLeft)(), " ", "AlignLeft"),
      <.div(cell, IconAcl(name = IconAcl.NameAlignRight)(), " ", "AlignRight"),
      <.div(cell, IconAcl(name = IconAcl.NameTextStyle)(), " ", "TextStyle"),
      <.div(cell, IconAcl(name = IconAcl.NameBold)(), " ", "Bold"),
      <.div(cell, IconAcl(name = IconAcl.NameItalic)(), " ", "Italic"),
      <.div(cell, IconAcl(name = IconAcl.NameUnderline)(), " ", "Underline"),
      <.div(cell, IconAcl(name = IconAcl.NameStrikeThrough)(), " ", "StrikeThrough"),
      <.div(cell, IconAcl(name = IconAcl.NameHeader)(), " ", "Header"),
      <.div(cell, IconAcl(name = IconAcl.NameHeader1)(), " ", "Header1"),
      <.div(cell, IconAcl(name = IconAcl.NameHeader2)(), " ", "Header2"),
      <.div(cell, IconAcl(name = IconAcl.NameInput)(), " ", "Input"),
      <.div(cell, IconAcl(name = IconAcl.NameCitation)(), " ", "Citation"),
      <.div(cell, IconAcl(name = IconAcl.NameParagraph)(), " ", "Paragraph"),
      <.div(cell, IconAcl(name = IconAcl.NameListBullet)(), " ", "ListBullet"),
      <.div(cell, IconAcl(name = IconAcl.NameListNumber)(), " ", "ListNumber")
    ),
    Markdown(
      """
        |## File & Folder
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameFile)(), " ", "File"),
      <.div(cell, IconAcl(name = IconAcl.NameFileAdd)(), " ", "FileAdd"),
      <.div(cell, IconAcl(name = IconAcl.NameFileGenerate)(), " ", "FileGenerate"),
      <.div(cell, IconAcl(name = IconAcl.NameFileDownload)(), " ", "FileDownload"),
      <.div(cell, IconAcl(name = IconAcl.NameFileExport)(), " ", "FileExport"),
      <.div(cell, IconAcl(name = IconAcl.NameFileSend)(), " ", "FileSend"),
      <.div(cell, IconAcl(name = IconAcl.NameFilePreview)(), " ", "FilePreview"),
      <.div(cell, IconAcl(name = IconAcl.NameFileText)(), " ", "FileText"),
      <.div(cell, IconAcl(name = IconAcl.NameFileUnknown)(), " ", "FileUnknown"),
      <.div(cell, IconAcl(name = IconAcl.NameFilePdf)(), " ", "FilePdf"),
      <.div(cell, IconAcl(name = IconAcl.NameFileDoc)(), " ", "FileDoc"),
      <.div(cell, IconAcl(name = IconAcl.NameFileXls)(), " ", "FileXls"),
      <.div(cell, IconAcl(name = IconAcl.NameFolder)(), " ", "Folder"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderOpen)(), " ", "FolderOpen"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderAdd)(), " ", "FolderAdd"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderSend)(), " ", "FolderSend"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderLine)(), " ", "FolderLine"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderOpenLine)(), " ", "FolderOpenLine"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderAddLine)(), " ", "FolderAddLine"),
      <.div(cell, IconAcl(name = IconAcl.NameFolderSendLine)(), " ", "FolderSendLine")
    ),
    Markdown(
      """
        |## Interface
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameHome)(), " ", "Home"),
      <.div(cell, IconAcl(name = IconAcl.NameOffice)(), " ", "Office"),
      <.div(cell, IconAcl(name = IconAcl.NameSnapshot)(), " ", "Snapshot"),
      <.div(cell, IconAcl(name = IconAcl.NameDashboard)(), " ", "Dashboard"),
      <.div(cell, IconAcl(name = IconAcl.NameViewGrid)(), " ", "ViewGrid"),
      <.div(cell, IconAcl(name = IconAcl.NameViewList)(), " ", "ViewList"),
      <.div(cell, IconAcl(name = IconAcl.NameCheckList)(), " ", "CheckList"),
      <.div(cell, IconAcl(name = IconAcl.NameToolbox)(), " ", "Toolbox"),
      <.div(cell, IconAcl(name = IconAcl.NameCog)(), " ", "Cog"),
      <.div(cell, IconAcl(name = IconAcl.NameWrench)(), " ", "Wrench"),
      <.div(cell, IconAcl(name = IconAcl.NameKey)(), " ", "Key"),
      <.div(cell, IconAcl(name = IconAcl.NameLayer)(), " ", "Layer"),
      <.div(cell, IconAcl(name = IconAcl.NameCalendar)(), " ", "Calendar"),
      <.div(cell, IconAcl(name = IconAcl.NameClock)(), " ", "Clock"),
      <.div(cell, IconAcl(name = IconAcl.NameSandClock)(), " ", "SandClock"),
      <.div(cell, IconAcl(name = IconAcl.NameBell)(), " ", "Bell"),
      <.div(cell, IconAcl(name = IconAcl.NameChannel)(), " ", "Channel"),
      <.div(cell, IconAcl(name = IconAcl.NameData)(), " ", "Data"),
      <.div(cell, IconAcl(name = IconAcl.NameDataRoom)(), " ", "DataRoom"),
      <.div(cell, IconAcl(name = IconAcl.NameKnowledge)(), " ", "Knowledge"),
      <.div(cell, IconAcl(name = IconAcl.NameInfo)(), " ", "Info"),
      <.div(cell, IconAcl(name = IconAcl.NameInfoCircleLine)(), " ", "InfoCircleLine"),
      <.div(cell, IconAcl(name = IconAcl.NameWarning)(), " ", "Warning"),
      <.div(cell, IconAcl(name = IconAcl.NameError)(), " ", "Error"),
      <.div(cell, IconAcl(name = IconAcl.NameComputer)(), " ", "Computer"),
      <.div(cell, IconAcl(name = IconAcl.NameMobile)(), " ", "Mobile"),
      <.div(cell, IconAcl(name = IconAcl.NameMobileAuthenticate)(), " ", "MobileAuthenticate"),
      <.div(cell, IconAcl(name = IconAcl.NameCloud)(), " ", "Cloud"),
      <.div(cell, IconAcl(name = IconAcl.NameCloudLine)(), " ", "CloudLine"),
      <.div(cell, IconAcl(name = IconAcl.NameCode)(), " ", "Code"),
      <.div(cell, IconAcl(name = IconAcl.NameCodeLine)(), " ", "CodeLine"),
      <.div(cell, IconAcl(name = IconAcl.NameCircle)(), " ", "Circle"),
      <.div(cell, IconAcl(name = IconAcl.NameCircleLine)(), " ", "CircleLine"),
      <.div(cell, IconAcl(name = IconAcl.NameEllipsisHorizontal)(), " ", "EllipsisHorizontal"),
      <.div(cell, IconAcl(name = IconAcl.NameEllipsisVertical)(), " ", "EllipsisVertical"),
      <.div(cell, IconAcl(name = IconAcl.NameEye)(), " ", "Eye"),
      <.div(cell, IconAcl(name = IconAcl.NameEyeOff)(), " ", "EyeOff"),
      <.div(cell, IconAcl(name = IconAcl.NameStar)(), " ", "Star"),
      <.div(cell, IconAcl(name = IconAcl.NameStarLine)(), " ", "StarLine"),
      <.div(cell, IconAcl(name = IconAcl.NamePulse)(), " ", "Pulse"),
      <.div(cell, IconAcl(name = IconAcl.NamePulseCircle)(), " ", "PulseCircle"),
      <.div(cell, IconAcl(name = IconAcl.NameLightBolt)(), " ", "LightBolt"),
      <.div(cell, IconAcl(name = IconAcl.NameLightBoltCircle)(), " ", "LightBoltCircle")
    ),
    Markdown(
      """
        |## Graph
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameTable)(), " ", "Table"),
      <.div(cell, IconAcl(name = IconAcl.NameSort)(), " ", "Sort"),
      <.div(cell, IconAcl(name = IconAcl.NameSortAsc)(), " ", "SortAsc"),
      <.div(cell, IconAcl(name = IconAcl.NameSortDes)(), " ", "SortDes"),
      <.div(cell, IconAcl(name = IconAcl.NameSortAlphaAsc)(), " ", "SortAlphaAsc"),
      <.div(cell, IconAcl(name = IconAcl.NameSortAlphaDes)(), " ", "SortAlphaDes"),
      <.div(cell, IconAcl(name = IconAcl.NameSortNumberAsc)(), " ", "SortNumberAsc"),
      <.div(cell, IconAcl(name = IconAcl.NameSortNumberDes)(), " ", "SortNumberDes"),
      <.div(cell, IconAcl(name = IconAcl.NameChartProgress)(), " ", "ChartProgress"),
      <.div(cell, IconAcl(name = IconAcl.NameChartBarHorizontal)(), " ", "ChartBarHorizontal"),
      <.div(cell, IconAcl(name = IconAcl.NameChartBarVertical)(), " ", "ChartBarVertical"),
      <.div(cell, IconAcl(name = IconAcl.NameChartPie)(), " ", "ChartPie")
    ),
    Markdown(
      """
        |## User
      """.stripMargin
    )(),
    renderIcons(
      <.div(cell, IconAcl(name = IconAcl.NameUser)(), " ", "User"),
      <.div(cell, IconAcl(name = IconAcl.NameUserGroup)(), " ", "UserGroup"),
      <.div(cell, IconAcl(name = IconAcl.NameUserInvestor)(), " ", "UserInvestor"),
      <.div(cell, IconAcl(name = IconAcl.NameUserCompany)(), " ", "UserCompany"),
      <.div(cell, IconAcl(name = IconAcl.NameUserAdd)(), " ", "UserAdd"),
      <.div(cell, IconAcl(name = IconAcl.NameUserAccept)(), " ", "UserAccept"),
      <.div(cell, IconAcl(name = IconAcl.NameUserBlock)(), " ", "UserBlock"),
      <.div(cell, IconAcl(name = IconAcl.NameUserRemove)(), " ", "UserRemove"),
      <.div(cell, IconAcl(name = IconAcl.NameUserEdit)(), " ", "UserEdit"),
      <.div(cell, IconAcl(name = IconAcl.NameUserLink)(), " ", "UserLink"),
      <.div(cell, IconAcl(name = IconAcl.NameUserInfo)(), " ", "UserInfo")
    )
  )

  private def renderUsage(ctl: Main.Ctl): VdomNode = ReactFragment(
    Markdown(
      s"""
         |# Usage
         |
         |## Alignment
         |
         |**Icon is always a block element.** This is to prevent the default `inline` alignment, which is based on `baseline`, while we usually want an absolute centering.
         |
         |`block` display placed the icon on its own row, which enforces the engineers to align the icon later with proper technique. See example in the [Spacing](#spacing) section below.
         |
         |- [This Codepen](https://codepen.io/dvkndn/pen/wmQmbm) explains in detail why we prefer `block` over `inline` icons.
         |- For alignment, we suggest to see the [Flexbox guide](${ctl.urlFor(Main.Flexbox()).value}).
         |
         |## Spacing
         |
         |**Icon's shape is designed to touch the bound.** In other words, there is no inner space (padding) in an icon. Therefore, the engineers usually need to wrap the icon inside a `div` or `span`, then provide necessary spacing in that wrapper:
        """.stripMargin
    )(),
    ExampleRich(
      Source.annotate(
        // Wrap the icon
        /*>*/ <.div(
          Style.flexbox.flex.flexbox.itemsCenter, /*<*/
          <.span(Style.margin.right8, IconAcl(name = IconAcl.NameDownload)()),
          "Download" /*>*/
        ) /*<*/
      )
    )(),
    Markdown(
      """
        |Or you can also wrap the adjacent element of the icon:
      """.stripMargin
    )(),
    ExampleRich(
      Source.annotate(
        // Wrap the adjacent text
        /*>*/ <.div(
          Style.flexbox.flex.flexbox.itemsCenter, /*<*/
          IconAcl(name = IconAcl.NameUpload)(),
          <.span(Style.margin.left8, "Upload") /*>*/
        ) /*<*/
      )
    )(),
    Markdown(
      """
        |## Color
        |
        |**Icon has one color, and it is inherited from the parent.** To be more specific, the `fill` color of an icon is the same with its parent's (text) `color`. Thus, to change color of the icon, you will usually want to do that in its wrapper:
      """.stripMargin
    )(),
    ExampleRich(
      Source.annotate(
        <.div(
          Style.color.primary4,
          IconAcl(name = IconAcl.NameLayer)()
        )
      )
    )(),
    Markdown(
      """
        |## Meaning
        |
        |**Icons should follow industry standard metaphors** to ensure they are predictable for end users. Unique objects and actions that have no standard metaphors yet should have explicit label to support the icon.
        |
        |>Inscrutable icons litter the face of the devices even though the research community has long demonstrated that people cannot remember the meaning of more than a small number of icons […] – Don Norman"
        |>
        |>Source: [ia.net/topics/on-icons/​](​https://ia.net/topics/on-icons/)
      """.stripMargin
    )(),
  )

  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Icon")()
      ),
      // format: off
      ExampleRich(Source.annotate({
        /*>*/ <.div(
          Style.flexbox.flex.flexbox.itemsCenter, /*<*/
          IconAcl(name = IconAcl.NameOffice)() /*>*/,
          <.span(Style.margin.left8, "Office")
        ) /*<*/
      }))(),
      // format: on
      renderNames,
      renderUsage(ctl)
    )
  }

}
