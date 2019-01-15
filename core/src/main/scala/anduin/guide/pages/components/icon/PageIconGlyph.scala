package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.component.icon.Icon.Glyph._
import anduin.component.tooltip.Tooltip
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIconGlyph {

  private def renderIcon(name: Icon.Name): VdomElement = {
    val sName = name.getClass.getSimpleName
    val tooltip = Tooltip(
      renderTarget = IconSample(name, Icon.Size.Px16, Style.padding.all8.color.gray7)(),
      renderContent = () => sName
    )()
    <.div(^.key := sName, tooltip)
  }
  private def renderRow(tuple: (String, Seq[Icon.Name])): VdomElement = {
    <.div(
      ^.key := tuple._1,
      Style.flexbox.flex.flexbox.wrap,
      tuple._2.toVdomArray(renderIcon)
    )
  }

  private def renderIcons(rows: (String, Seq[Icon.Name])*): VdomElement = {
    <.div(Style.padding.ver8, rows.toVdomArray(renderRow))
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Glyph Icons")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
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
      // format: off
      Markdown("### Actions")(),
      renderIcons(
        ("Validate", Vector(Check, CheckSmall, CheckCircle, CheckCircleLine, Cross, CrossSmall, CrossCircle, CrossCircleLine)),
        ("Quantity", Vector(Minus, MinusSmall, MinusCircle, MinusCircleLine, Plus, PlusSmall, PlusCircle, PlusCircleLine)),
        ("Line", Vector(Enter, Undo, Redo, Refresh, Repeat, ReTweet, RollBack, Swap, SwapLeft, SwapRight)),
        ("User", Vector(UserAdd, UserAccept, UserBlock, UserRemove, UserEdit, UserLink)),
        ("UnCategorized", Vector(Access, FastBackward, FastForward, StepBackward, StepForward, Login, Logout, OpenNewWindow, Input)),
      ),
      Markdown("### Brands")(),
      renderIcons(
        ("UnCategorized", Vector(Anduin, Google, Microsoft)),
      ),
      Markdown("### Direction")(),
      renderIcons(
        ("Arrow", Vector(ArrowUp, ArrowRight, ArrowDown, ArrowLeft, ArrowTopLeft, ArrowTopRight, ArrowDownRight, ArrowDownLeft, ArrowHorizontal, ArrowVertical)),
        ("Caret", Vector(CaretUp, CaretRight, CaretDown, CaretLeft, CaretHorizontal, CaretVertical)),
        ("Chevron", Vector(ChevronUp, ChevronRight, ChevronDown, ChevronLeft)),
        ("Chevron2", Vector(ChevronCircleUp, ChevronCircleRight, ChevronCircleDown, ChevronCircleLeft, ChevronCircleLineUp, ChevronCircleLineRight, ChevronCircleLineDown, ChevronCircleLineLeft)),
        ("Chevron3", Vector(ChevronDoubleUp, ChevronDoubleRight, ChevronDoubleDown, ChevronDoubleLeft, ChevronHorizontalUp, ChevronVerticalRight, ChevronHorizontalDown, ChevronVerticalLeft)),
      ),
      Markdown("### Files and Folders")(),
      renderIcons(
        ("File", Vector(FileGeneric, FileAdd, FileGenerate, FileExport, FileSend, FileDownload, FileEye, FileText, FileUnknown, FilePdf, FileDoc, FileXls)),
        ("Folder", Vector(FolderClose, FolderOpen, FolderAdd, FolderSend, FolderCloseLine, FolderOpenLine, FolderAddLine, FolderSendLine)),
        ("View", Vector(ViewGrid, ViewList, Filter, MultiSelect, SortGeneric, SortAlphaAsc, SortAlphaDes, SortAsc, SortDes, SortNumberAsc, SortNumberDes)),
        ("Transfer", Vector(Download, Upload, CloudDownload, CloudDownloadLine, CloudUpload, CloudUploadLine, Duplicate, Move, Clipboard, ClipboardCopied)),
      ),
      Markdown(s"See also: [File Icons](${ctl.urlFor(Pages.IconFile()).value})")(),
      Markdown("### Text Format")(),
      renderIcons(
        ("Block", Vector(AlignCenter, AlignJustify, AlignLeft, AlignRight, Icon.Glyph.Heading, Heading1, Heading2, Citation, ListBullet, ListNumber)),
        ("Inline", Vector(TextStyle, Bold, Italic, Underline, StrikeThrough)),
      ),
      Markdown("### Interface")(),
      renderIcons(
        ("Helper", Vector(CheckBold, Circle, CircleLine, CircleLargeLine, Blank, EllipsisHorizontal, EllipsisVertical, Collapse, Expand)),
        ("Sign", Vector(QuestionCircleLine, Warning, Question, Error, Info, InfoCircleLine)),
      ),
      Markdown("### Negotiation")(),
      renderIcons(
        ("View", Vector(Eye, EyeOff, ZoomIn, ZoomOut, Search, SearchInline)),
        ("Communication", Vector(Comment, Attachment, Envelope, EnvelopeSend, Reply, ReplyAll, Share, Compare, AutoTrack)),
        ("Marking", Vector(Bookmark, Tag, StarLine, Star, FlagLine, Flag, Unlock, Lock, Unlink, Link)),
        ("Editing", Vector(Edit, EditField, EditInline, Sign, SignatureEdit, SignatureRequest, LightBolt, LightBoltCircle)),
      ),
      Markdown(s"See also: [Negotiation Icons](${ctl.urlFor(Pages.IconNego()).value})")(),
      Markdown("### Places")(),
      renderIcons(
        ("Anduin", Vector(CheckList, DataRoom, Deal, Questionnaire, Snapshot, Task, Template, Toolbox, Vault, Dashboard)),
        ("Storage", Vector(DataBase, Trash, Cloud, CloudLine, Computer, Inbox, InboxReceive, InboxSend, Mobile, Archive)),
        ("Data", Vector(Table, ChartBarHorizontal, ChartBarVertical, ChartPie, ChartProgress, Pulse, PulseCircle)),
        ("UnCategorized", Vector(Paragraph, Code, CodeLine, MobileAuthenticate, Channel, Cog, Home, Office, Wrench, Layer, Key, Money)),
        ("Time", Vector(Bell, Calendar, Clock, SandClock)),
        ("User", Vector(UserSingle, UserGroup, UserInvestor, UserCompany, UserInfo)),
      ),
      // format: on
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
      }))(),
    )
  }
}
