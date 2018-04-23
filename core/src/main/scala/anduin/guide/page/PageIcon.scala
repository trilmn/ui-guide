package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.icon.IconAcl
import anduin.guide.Guide
import anduin.guide.component.{Example, Header, Markdown}
import anduin.mcro.Source
import anduin.style.Style

object PageIcon {

  private def renderIcons: VdomElement = {
    val flex = TagMod(
      Style.flexbox.flex.flexbox.itemsCenter,
      Style.flexbox.none.width.pcOneRd
    )
    <.div(
      Style.flexbox.flex.flexbox.wrap,
      Style.fontSize.px12.lineHeight.px40,
      <.div(flex, IconAcl(name = IconAcl.NameAlignCenter)(), " ", "AlignCenter"),
      <.div(flex, IconAcl(name = IconAcl.NameAlignJustify)(), " ", "AlignJustify"),
      <.div(flex, IconAcl(name = IconAcl.NameAlignLeft)(), " ", "AlignLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameAlignRight)(), " ", "AlignRight"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowDown)(), " ", "ArrowDown"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowDownLeft)(), " ", "ArrowDownLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowDownRight)(), " ", "ArrowDownRight"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowHorizontal)(), " ", "ArrowHorizontal"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowLeft)(), " ", "ArrowLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowRight)(), " ", "ArrowRight"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowTopLeft)(), " ", "ArrowTopLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowTopRight)(), " ", "ArrowTopRight"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowUp)(), " ", "ArrowUp"),
      <.div(flex, IconAcl(name = IconAcl.NameArrowVertical)(), " ", "ArrowVertical"),
      <.div(flex, IconAcl(name = IconAcl.NameAutoTrack)(), " ", "AutoTrack"),
      <.div(flex, IconAcl(name = IconAcl.NameBell)(), " ", "Bell"),
      <.div(flex, IconAcl(name = IconAcl.NameBell2)(), " ", "Bell2"),
      <.div(flex, IconAcl(name = IconAcl.NameBell2Line)(), " ", "Bell2Line"),
      <.div(flex, IconAcl(name = IconAcl.NameBellLine)(), " ", "BellLine"),
      <.div(flex, IconAcl(name = IconAcl.NameBold)(), " ", "Bold"),
      <.div(flex, IconAcl(name = IconAcl.NameBookmark)(), " ", "Bookmark"),
      <.div(flex, IconAcl(name = IconAcl.NameBookmarkLine)(), " ", "BookmarkLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCalendar)(), " ", "Calendar"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretDown)(), " ", "CaretDown"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretHorizontal)(), " ", "CaretHorizontal"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretLeft)(), " ", "CaretLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretRight)(), " ", "CaretRight"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretUp)(), " ", "CaretUp"),
      <.div(flex, IconAcl(name = IconAcl.NameCaretVertical)(), " ", "CaretVertical"),
      <.div(flex, IconAcl(name = IconAcl.NameChannel)(), " ", "Channel"),
      <.div(flex, IconAcl(name = IconAcl.NameChartBarHorizontal)(), " ", "ChartBarHorizontal"),
      <.div(flex, IconAcl(name = IconAcl.NameChartBarHorizontal2)(), " ", "ChartBarHorizontal2"),
      <.div(flex, IconAcl(name = IconAcl.NameChartBarVertical)(), " ", "ChartBarVertical"),
      <.div(flex, IconAcl(name = IconAcl.NameChartPie)(), " ", "ChartPie"),
      <.div(flex, IconAcl(name = IconAcl.NameChartProgress)(), " ", "ChartProgress"),
      <.div(flex, IconAcl(name = IconAcl.NameChartProgress2)(), " ", "ChartProgress2"),
      <.div(flex, IconAcl(name = IconAcl.NameCheck)(), " ", "Check"),
      <.div(flex, IconAcl(name = IconAcl.NameCheckCircle)(), " ", "CheckCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameCheckCircleLine)(), " ", "CheckCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCheckList)(), " ", "CheckList"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleDown)(), " ", "ChevronCircleDown"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleLeft)(), " ", "ChevronCircleLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleLineDown)(), " ", "ChevronCircleLineDown"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleLineLeft)(), " ", "ChevronCircleLineLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleLineRight)(), " ", "ChevronCircleLineRight"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleLineUp)(), " ", "ChevronCircleLineUp"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleRight)(), " ", "ChevronCircleRight"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronCircleUp)(), " ", "ChevronCircleUp"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronDoubleDown)(), " ", "ChevronDoubleDown"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronDoubleLeft)(), " ", "ChevronDoubleLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronDoubleRight)(), " ", "ChevronDoubleRight"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronDoubleUp)(), " ", "ChevronDoubleUp"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronDown)(), " ", "ChevronDown"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronLeft)(), " ", "ChevronLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronRight)(), " ", "ChevronRight"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronUp)(), " ", "ChevronUp"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronVerticalLeft)(), " ", "ChevronVerticalLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameChevronVerticalRight)(), " ", "ChevronVerticalRight"),
      <.div(flex, IconAcl(name = IconAcl.NameCircle)(), " ", "Circle"),
      <.div(flex, IconAcl(name = IconAcl.NameCitation)(), " ", "Citation"),
      <.div(flex, IconAcl(name = IconAcl.NameClipboard)(), " ", "Clipboard"),
      <.div(flex, IconAcl(name = IconAcl.NameClipboardLine)(), " ", "ClipboardLine"),
      <.div(flex, IconAcl(name = IconAcl.NameClipper)(), " ", "Clipper"),
      <.div(flex, IconAcl(name = IconAcl.NameClock)(), " ", "Clock"),
      <.div(flex, IconAcl(name = IconAcl.NameCloud)(), " ", "Cloud"),
      <.div(flex, IconAcl(name = IconAcl.NameCloudDownload)(), " ", "CloudDownload"),
      <.div(flex, IconAcl(name = IconAcl.NameCloudDownloadLine)(), " ", "CloudDownloadLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCloudLine)(), " ", "CloudLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCloudUpload)(), " ", "CloudUpload"),
      <.div(flex, IconAcl(name = IconAcl.NameCloudUploadLine)(), " ", "CloudUploadLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCode)(), " ", "Code"),
      <.div(flex, IconAcl(name = IconAcl.NameCodeLine)(), " ", "CodeLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCog)(), " ", "Cog"),
      <.div(flex, IconAcl(name = IconAcl.NameCollapse)(), " ", "Collapse"),
      <.div(flex, IconAcl(name = IconAcl.NameComment)(), " ", "Comment"),
      <.div(flex, IconAcl(name = IconAcl.NameComputer)(), " ", "Computer"),
      <.div(flex, IconAcl(name = IconAcl.NameCross)(), " ", "Cross"),
      <.div(flex, IconAcl(name = IconAcl.NameCrossCircle)(), " ", "CrossCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameCrossCircleLine)(), " ", "CrossCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameCrossSmall)(), " ", "CrossSmall"),
      <.div(flex, IconAcl(name = IconAcl.NameData)(), " ", "Data"),
      <.div(flex, IconAcl(name = IconAcl.NameDownload)(), " ", "Download"),
      <.div(flex, IconAcl(name = IconAcl.NameDuplicate)(), " ", "Duplicate"),
      <.div(flex, IconAcl(name = IconAcl.NameEdit)(), " ", "Edit"),
      <.div(flex, IconAcl(name = IconAcl.NameEdit2)(), " ", "Edit2"),
      <.div(flex, IconAcl(name = IconAcl.NameEdit3)(), " ", "Edit3"),
      <.div(flex, IconAcl(name = IconAcl.NameEdit4)(), " ", "Edit4"),
      <.div(flex, IconAcl(name = IconAcl.NameEllipsisHorizontal)(), " ", "EllipsisHorizontal"),
      <.div(flex, IconAcl(name = IconAcl.NameEllipsisVertical)(), " ", "EllipsisVertical"),
      <.div(flex, IconAcl(name = IconAcl.NameEnter)(), " ", "Enter"),
      <.div(flex, IconAcl(name = IconAcl.NameEnvelope)(), " ", "Envelope"),
      <.div(flex, IconAcl(name = IconAcl.NameEnvelopeLine)(), " ", "EnvelopeLine"),
      <.div(flex, IconAcl(name = IconAcl.NameExclamation)(), " ", "Exclamation"),
      <.div(flex, IconAcl(name = IconAcl.NameExclamationCircle)(), " ", "ExclamationCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameExclamationCircleLine)(), " ", "ExclamationCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameExpand)(), " ", "Expand"),
      <.div(flex, IconAcl(name = IconAcl.NameEye)(), " ", "Eye"),
      <.div(flex, IconAcl(name = IconAcl.NameEyeLine)(), " ", "EyeLine"),
      <.div(flex, IconAcl(name = IconAcl.NameEyeOff)(), " ", "EyeOff"),
      <.div(flex, IconAcl(name = IconAcl.NameEyeOffLine)(), " ", "EyeOffLine"),
      <.div(flex, IconAcl(name = IconAcl.NameFastBackward)(), " ", "FastBackward"),
      <.div(flex, IconAcl(name = IconAcl.NameFastForward)(), " ", "FastForward"),
      <.div(flex, IconAcl(name = IconAcl.NameFile)(), " ", "File"),
      <.div(flex, IconAcl(name = IconAcl.NameFileAdd)(), " ", "FileAdd"),
      <.div(flex, IconAcl(name = IconAcl.NameFileDoc)(), " ", "FileDoc"),
      <.div(flex, IconAcl(name = IconAcl.NameFileDownload)(), " ", "FileDownload"),
      <.div(flex, IconAcl(name = IconAcl.NameFileExport)(), " ", "FileExport"),
      <.div(flex, IconAcl(name = IconAcl.NameFileEye)(), " ", "FileEye"),
      <.div(flex, IconAcl(name = IconAcl.NameFileGenerate)(), " ", "FileGenerate"),
      <.div(flex, IconAcl(name = IconAcl.NameFilePdf)(), " ", "FilePdf"),
      <.div(flex, IconAcl(name = IconAcl.NameFileSend)(), " ", "FileSend"),
      <.div(flex, IconAcl(name = IconAcl.NameFileSend2)(), " ", "FileSend2"),
      <.div(flex, IconAcl(name = IconAcl.NameFileText)(), " ", "FileText"),
      <.div(flex, IconAcl(name = IconAcl.NameFileUnknown)(), " ", "FileUnknown"),
      <.div(flex, IconAcl(name = IconAcl.NameFileXls)(), " ", "FileXls"),
      <.div(flex, IconAcl(name = IconAcl.NameFlag)(), " ", "Flag"),
      <.div(flex, IconAcl(name = IconAcl.NameFlagLine)(), " ", "FlagLine"),
      <.div(flex, IconAcl(name = IconAcl.NameFolder)(), " ", "Folder"),
      <.div(flex, IconAcl(name = IconAcl.NameFolderAdd)(), " ", "FolderAdd"),
      <.div(flex, IconAcl(name = IconAcl.NameFolderAddLine)(), " ", "FolderAddLine"),
      <.div(flex, IconAcl(name = IconAcl.NameFolderLine)(), " ", "FolderLine"),
      <.div(flex, IconAcl(name = IconAcl.NameFolderOpen)(), " ", "FolderOpen"),
      <.div(flex, IconAcl(name = IconAcl.NameFolderOpenLine)(), " ", "FolderOpenLine"),
      <.div(flex, IconAcl(name = IconAcl.NameHeader)(), " ", "Header"),
      <.div(flex, IconAcl(name = IconAcl.NameHeader1)(), " ", "Header1"),
      <.div(flex, IconAcl(name = IconAcl.NameHeader2)(), " ", "Header2"),
      <.div(flex, IconAcl(name = IconAcl.NameHome)(), " ", "Home"),
      <.div(flex, IconAcl(name = IconAcl.NameHomeLine)(), " ", "HomeLine"),
      <.div(flex, IconAcl(name = IconAcl.NameInbox)(), " ", "Inbox"),
      <.div(flex, IconAcl(name = IconAcl.NameInboxLine)(), " ", "InboxLine"),
      <.div(flex, IconAcl(name = IconAcl.NameInboxReceive)(), " ", "InboxReceive"),
      <.div(flex, IconAcl(name = IconAcl.NameInboxReceiveLine)(), " ", "InboxReceiveLine"),
      <.div(flex, IconAcl(name = IconAcl.NameInboxSend)(), " ", "InboxSend"),
      <.div(flex, IconAcl(name = IconAcl.NameInboxSendLine)(), " ", "InboxSendLine"),
      <.div(flex, IconAcl(name = IconAcl.NameInfo)(), " ", "Info"),
      <.div(flex, IconAcl(name = IconAcl.NameInfoCircle)(), " ", "InfoCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameInfoCircleLine)(), " ", "InfoCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameInput)(), " ", "Input"),
      <.div(flex, IconAcl(name = IconAcl.NameItalic)(), " ", "Italic"),
      <.div(flex, IconAcl(name = IconAcl.NameLabel)(), " ", "Label"),
      <.div(flex, IconAcl(name = IconAcl.NameLabelLine)(), " ", "LabelLine"),
      <.div(flex, IconAcl(name = IconAcl.NameLayer)(), " ", "Layer"),
      <.div(flex, IconAcl(name = IconAcl.NameLightBolt)(), " ", "LightBolt"),
      <.div(flex, IconAcl(name = IconAcl.NameLightBoltCircle)(), " ", "LightBoltCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameLink)(), " ", "Link"),
      <.div(flex, IconAcl(name = IconAcl.NameListBullet)(), " ", "ListBullet"),
      <.div(flex, IconAcl(name = IconAcl.NameListNumber)(), " ", "ListNumber"),
      <.div(flex, IconAcl(name = IconAcl.NameLock)(), " ", "Lock"),
      <.div(flex, IconAcl(name = IconAcl.NameLockLine)(), " ", "LockLine"),
      <.div(flex, IconAcl(name = IconAcl.NameLogin)(), " ", "Login"),
      <.div(flex, IconAcl(name = IconAcl.NameLogout)(), " ", "Logout"),
      <.div(flex, IconAcl(name = IconAcl.NameMinus)(), " ", "Minus"),
      <.div(flex, IconAcl(name = IconAcl.NameMinusCircle)(), " ", "MinusCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameMinusCircleLine)(), " ", "MinusCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameMinusSmall)(), " ", "MinusSmall"),
      <.div(flex, IconAcl(name = IconAcl.NameMultiSelect)(), " ", "MultiSelect"),
      <.div(flex, IconAcl(name = IconAcl.NameOffice)(), " ", "Office"),
      <.div(flex, IconAcl(name = IconAcl.NameParagraph)(), " ", "Paragraph"),
      <.div(flex, IconAcl(name = IconAcl.NamePlus)(), " ", "Plus"),
      <.div(flex, IconAcl(name = IconAcl.NamePlusCircle)(), " ", "PlusCircle"),
      <.div(flex, IconAcl(name = IconAcl.NamePlusCircleLine)(), " ", "PlusCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NamePlusSmall)(), " ", "PlusSmall"),
      <.div(flex, IconAcl(name = IconAcl.NameProperty)(), " ", "Property"),
      <.div(flex, IconAcl(name = IconAcl.NamePulse)(), " ", "Pulse"),
      <.div(flex, IconAcl(name = IconAcl.NamePulseCircle)(), " ", "PulseCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameQuestion)(), " ", "Question"),
      <.div(flex, IconAcl(name = IconAcl.NameQuestionCircle)(), " ", "QuestionCircle"),
      <.div(flex, IconAcl(name = IconAcl.NameQuestionCircleLine)(), " ", "QuestionCircleLine"),
      <.div(flex, IconAcl(name = IconAcl.NameRedo)(), " ", "Redo"),
      <.div(flex, IconAcl(name = IconAcl.NameRepeat)(), " ", "Repeat"),
      <.div(flex, IconAcl(name = IconAcl.NameReply)(), " ", "Reply"),
      <.div(flex, IconAcl(name = IconAcl.NameReplyAll)(), " ", "ReplyAll"),
      <.div(flex, IconAcl(name = IconAcl.NameReTweet)(), " ", "ReTweet"),
      <.div(flex, IconAcl(name = IconAcl.NameRollBack)(), " ", "RollBack"),
      <.div(flex, IconAcl(name = IconAcl.NameSearch)(), " ", "Search"),
      <.div(flex, IconAcl(name = IconAcl.NameSearchInline)(), " ", "SearchInline"),
      <.div(flex, IconAcl(name = IconAcl.NameSign)(), " ", "Sign"),
      <.div(flex, IconAcl(name = IconAcl.NameSort)(), " ", "Sort"),
      <.div(flex, IconAcl(name = IconAcl.NameSortAlphaAsc)(), " ", "SortAlphaAsc"),
      <.div(flex, IconAcl(name = IconAcl.NameSortAlphaDes)(), " ", "SortAlphaDes"),
      <.div(flex, IconAcl(name = IconAcl.NameSortAsc)(), " ", "SortAsc"),
      <.div(flex, IconAcl(name = IconAcl.NameSortDes)(), " ", "SortDes"),
      <.div(flex, IconAcl(name = IconAcl.NameSortNumberAsc)(), " ", "SortNumberAsc"),
      <.div(flex, IconAcl(name = IconAcl.NameSortNumberDes)(), " ", "SortNumberDes"),
      <.div(flex, IconAcl(name = IconAcl.NameStar)(), " ", "Star"),
      <.div(flex, IconAcl(name = IconAcl.NameStarLine)(), " ", "StarLine"),
      <.div(flex, IconAcl(name = IconAcl.NameStepBackward)(), " ", "StepBackward"),
      <.div(flex, IconAcl(name = IconAcl.NameStepForward)(), " ", "StepForward"),
      <.div(flex, IconAcl(name = IconAcl.NameStrikeThrough)(), " ", "StrikeThrough"),
      <.div(flex, IconAcl(name = IconAcl.NameSwap)(), " ", "Swap"),
      <.div(flex, IconAcl(name = IconAcl.NameSwapLeft)(), " ", "SwapLeft"),
      <.div(flex, IconAcl(name = IconAcl.NameSwapRight)(), " ", "SwapRight"),
      <.div(flex, IconAcl(name = IconAcl.NameTable1)(), " ", "Table1"),
      <.div(flex, IconAcl(name = IconAcl.NameTable2)(), " ", "Table2"),
      <.div(flex, IconAcl(name = IconAcl.NameTable3)(), " ", "Table3"),
      <.div(flex, IconAcl(name = IconAcl.NameTextStyle)(), " ", "TextStyle"),
      <.div(flex, IconAcl(name = IconAcl.NameToolbox)(), " ", "Toolbox"),
      <.div(flex, IconAcl(name = IconAcl.NameToolboxLine)(), " ", "ToolboxLine"),
      <.div(flex, IconAcl(name = IconAcl.NameTrash)(), " ", "Trash"),
      <.div(flex, IconAcl(name = IconAcl.NameTrashLine)(), " ", "TrashLine"),
      <.div(flex, IconAcl(name = IconAcl.NameUnderline)(), " ", "Underline"),
      <.div(flex, IconAcl(name = IconAcl.NameUndo)(), " ", "Undo"),
      <.div(flex, IconAcl(name = IconAcl.NameUnlink)(), " ", "Unlink"),
      <.div(flex, IconAcl(name = IconAcl.NameUnlock)(), " ", "Unlock"),
      <.div(flex, IconAcl(name = IconAcl.NameUnlockLine)(), " ", "UnlockLine"),
      <.div(flex, IconAcl(name = IconAcl.NameUpload)(), " ", "Upload"),
      <.div(flex, IconAcl(name = IconAcl.NameUser)(), " ", "User"),
      <.div(flex, IconAcl(name = IconAcl.NameUserAdd)(), " ", "UserAdd"),
      <.div(flex, IconAcl(name = IconAcl.NameUserBlock)(), " ", "UserBlock"),
      <.div(flex, IconAcl(name = IconAcl.NameUserCompany)(), " ", "UserCompany"),
      <.div(flex, IconAcl(name = IconAcl.NameUserEdit)(), " ", "UserEdit"),
      <.div(flex, IconAcl(name = IconAcl.NameUserGroup)(), " ", "UserGroup"),
      <.div(flex, IconAcl(name = IconAcl.NameUserInvestor)(), " ", "UserInvestor"),
      <.div(flex, IconAcl(name = IconAcl.NameUserRemove)(), " ", "UserRemove"),
      <.div(flex, IconAcl(name = IconAcl.NameViewGrid)(), " ", "ViewGrid"),
      <.div(flex, IconAcl(name = IconAcl.NameViewList)(), " ", "ViewList"),
      <.div(flex, IconAcl(name = IconAcl.NameWaring)(), " ", "Waring"),
      <.div(flex, IconAcl(name = IconAcl.NameWaringLine)(), " ", "WaringLine"),
      <.div(flex, IconAcl(name = IconAcl.NameWrench)(), " ", "Wrench")
    )
  }

  def render(ctl: Guide.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Icon",
          description =
            """
              |Icons are metaphors that represent, or provide additional information, for objects or actions in an effective space.
            """.stripMargin
        )()
      ),
      Markdown(
        """
          |# Snippet
          |
          |```scala
          |Icon(
          |  name: Icon.Name,
          |  size: Icon.Size = Icon.SizeMedium
          |)()
          |
          |// Name
          |case object NameChevronUp extends Name { val path: String = "M8 ... 079z" }
          |...
          |
          |// Size
          |case object SizeMedium extends Size { val value = "16" }
          |case object SizeLarge extends Size { val value = "32" }
          |```
          |
          |> Currently the name of this component is `IconAcl` instead of `Icon`. Its name will be changed back to `Icon` after completing migration.
          |
          |Example:
          |""".stripMargin)(),
      Example(
        Source.annotate(
          /*>*/ <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            /*<*/ IconAcl(name = IconAcl.NameOffice)() /*>*/,
            <.span(Style.margin.left8, "Office")
          ) /*<*/
        )
      )(),
      Markdown("""
          |# Names
          |
          |""".stripMargin)(),
      renderIcons(),
      Markdown(
        s"""
          |# Usage notes
          |
          |### Icon is always a block element
          |
          |Out of the box, `inline` element is aligned in the same row with its surrounding siblings. However, this alignment is usually not suitable for us, as we want absolute centering.
          |
          |Meanwhile, the `block` display prevents the default alignment, and placed the icon on its own row. This enforces the engineers to properly align the icon.
          |
          |- [This Codepen](https://codepen.io/dvkndn/pen/wmQmbm) explains in detail why we prefer `block` over `inline` icons.
          |- For alignment, we suggest to see the [Flexbox guide](${ctl.urlFor(Guide.Flexbox()).value}).
          |
          |### Icon's shape is designed to touch the bound
          |
          |In other words, there is no inner space (padding) in an icon. Therefore, the engineers usually need to wrap the icon inside a `div` or `span`, then provide necessary spacing in that wrapper:
          |""".stripMargin)(),
      Example(
        Source.annotate(
          /*>*/ <.div(
            Style.padding.all8, {
              val icon = <.span(/*<*/
                Style.margin.right8, /*>*/
                IconAcl(name = IconAcl.NameUpload)()
              )/*<*/
              val styles = Style.flexbox.flex.flexbox.itemsCenter /*>*/
              <.div(styles, icon, "Upload")
            }
          ) /*<*/
        )
      )(),
      Markdown(
        """
          |### Icon has one color, and it is inherited
          |
          |To be more specific, the `fill` color of an icon is the same with its parent's (text) `color`. Thus, to change color of the icon, you will usually want to do that in its wrapper:
          |""".stripMargin)(),
      Example(
        Source.annotate(
          /*>*/ <.div(/*<*/
            Style.color.primary4, /*>*/
            IconAcl(name = IconAcl.NameLayer)()
          ) /*<*/
        )
      )(),
      Markdown(
        """
          |### Icons should follow industry standard metaphors
          |
          |Following standard ensures that icons are predictable for end users. Unique objects and actions that have no standard metaphors yet should have explicit label to support the icon.
          |
          |"Inscrutable icons litter the face of the devices even though the research community has long demonstrated that people cannot remember the meaning of more than a small number of icons […] – Don Norman"
          |
          |Source: [ia.net/topics/on-icons/​](​https://ia.net/topics/on-icons/)
        """.stripMargin)()
    )
  }
}
