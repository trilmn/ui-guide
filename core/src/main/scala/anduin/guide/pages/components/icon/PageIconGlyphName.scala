// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.component.icon.Icon.Glyph._
import anduin.component.input.textbox.TextBox
import anduin.component.tooltip.Tooltip
import anduin.guide.app.main.Pages
import anduin.guide.components.Markdown
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageIconGlyphName(ctl: Pages.Ctl) {
  def apply(): VdomElement = PageIconGlyphName.component(this)
}

object PageIconGlyphName {

  private type Props = PageIconGlyphName

  private case class State(filter: String)

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

  private class Backend(scope: BackendScope[Props, State]) {

    private def isFilter(state: State)(name: Icon.Name): Boolean = {
      name.getClass.getSimpleName.toLowerCase().contains(state.filter.toLowerCase())
    }

    private def renderSection(
      state: State,
      header: VdomNode,
      footer: VdomNode = EmptyVdom
    )(rows: (String, Seq[Icon.Name])*): VdomNode = {
      val filteredRows = rows
        .map(row => row.copy(_2 = row._2.filter(isFilter(state))))
        .filter(_._2.nonEmpty)
      if (filteredRows.isEmpty) {
        EmptyVdom
      } else {
        <.div(Style.padding.ver8, header, filteredRows.toVdomArray(renderRow), footer)
      }
    }

    private def renderFilter(state: State): VdomElement = {
      TextBox(
        value = state.filter,
        onChange = value => scope.modState(_.copy(filter = value)),
        placeholder = "Type to search icon…"
      )()
    }

    def render(props: Props, state: State): VdomElement = {
      <.div(
        renderFilter(state),
        // format: off
        renderSection(state, Markdown("### Actions")())(
          ("Validate", Vector(Check, CheckSmall, CheckCircle, CheckCircleLine, Cross, CrossSmall, CrossCircle, CrossCircleLine)),
          ("Quantity", Vector(Minus, MinusSmall, MinusCircle, MinusCircleLine, Plus, PlusSmall, PlusCircle, PlusCircleLine)),
          ("Line", Vector(Enter, Undo, Redo, Refresh, Repeat, ReTweet, RollBack, Swap, SwapLeft, SwapRight)),
          ("User", Vector(UserAdd, UserAccept, UserBlock, UserRemove, UserEdit, UserLink)),
          ("UnCategorized", Vector(Access, FastBackward, FastForward, StepBackward, StepForward, Login, Logout, OpenNewWindow, Input)),
        ),
        renderSection(state, Markdown("### Brands")())(
          ("UnCategorized", Vector(Anduin, Google, Microsoft)),
        ),
        renderSection(state, Markdown("### Direction")())(
          ("Arrow", Vector(ArrowUp, ArrowRight, ArrowDown, ArrowLeft, ArrowTopLeft, ArrowTopRight, ArrowDownRight, ArrowDownLeft, ArrowHorizontal, ArrowVertical)),
          ("Caret", Vector(CaretUp, CaretRight, CaretDown, CaretLeft, CaretHorizontal, CaretVertical)),
          ("Chevron", Vector(ChevronUp, ChevronRight, ChevronDown, ChevronLeft)),
          ("Chevron2", Vector(ChevronCircleUp, ChevronCircleRight, ChevronCircleDown, ChevronCircleLeft, ChevronCircleLineUp, ChevronCircleLineRight, ChevronCircleLineDown, ChevronCircleLineLeft)),
          ("Chevron3", Vector(ChevronDoubleUp, ChevronDoubleRight, ChevronDoubleDown, ChevronDoubleLeft, ChevronHorizontalUp, ChevronVerticalRight, ChevronHorizontalDown, ChevronVerticalLeft)),
        ),
        renderSection(
          state,
          Markdown("### Files and Folders")(),
          Markdown(s"See also: [File Icons](${props.ctl.urlFor(Pages.IconFile()).value})")()
        )(
          ("File", Vector(FileGeneric, FileAdd, FileGenerate, FileExport, FileSend, FileDownload, FileEye, FileText, FileUnknown, FilePdf, FileDoc, FileXls)),
          ("Folder", Vector(FolderClose, FolderOpen, FolderAdd, FolderSend, FolderCloseLine, FolderOpenLine, FolderAddLine, FolderSendLine)),
          ("View", Vector(ViewGrid, ViewList, Filter, MultiSelect, SortGeneric, SortAlphaAsc, SortAlphaDes, SortAsc, SortDes, SortNumberAsc, SortNumberDes)),
          ("Transfer", Vector(Download, Upload, CloudDownload, CloudDownloadLine, CloudUpload, CloudUploadLine, Duplicate, Move, Clipboard, ClipboardCopied)),
        ),
        renderSection(state, Markdown("### Text Format")())(
          ("Block", Vector(AlignCenter, AlignJustify, AlignLeft, AlignRight, Icon.Glyph.Heading, Heading1, Heading2, Citation, ListBullet, ListNumber)),
          ("Inline", Vector(TextStyle, Bold, Italic, Underline, StrikeThrough)),
        ),
        renderSection(state, Markdown("### Interface")())(
          ("Helper", Vector(CheckBold, Circle, CircleLine, CircleLargeLine, Blank, EllipsisHorizontal, EllipsisVertical, Collapse, Expand)),
          ("Sign", Vector(QuestionCircleLine, Warning, Question, Error, Info, InfoCircleLine)),
        ),
        renderSection(
          state,
          Markdown("### Negotiation")(),
          Markdown(s"See also: [Negotiation Icons](${props.ctl.urlFor(Pages.IconNego()).value})")()
        )(
          ("View", Vector(Eye, EyeOff, ZoomIn, ZoomOut, Search, SearchInline)),
          ("Communication", Vector(Comment, Attachment, Envelope, EnvelopeSend, Reply, ReplyAll, Share, Compare, AutoTrack)),
          ("Marking", Vector(Bookmark, Tag, StarLine, Star, FlagLine, Flag, Unlock, Lock, Unlink, Link)),
          ("Editing", Vector(Edit, EditField, EditInline, Sign, SignatureEdit, SignatureRequest, LightBolt, LightBoltCircle)),
        ),
        renderSection(state, Markdown("### Places")())(
          ("Anduin", Vector(CheckList, DataRoom, Deal, Questionnaire, Snapshot, Task, Template, Toolbox, Vault, Dashboard)),
          ("Storage", Vector(DataBase, Trash, Cloud, CloudLine, Computer, Inbox, InboxReceive, InboxSend, Mobile, Archive)),
          ("Data", Vector(Table, ChartBarHorizontal, ChartBarVertical, ChartPie, ChartProgress, Pulse, PulseCircle)),
          ("UnCategorized", Vector(Paragraph, Code, CodeLine, MobileAuthenticate, Channel, Cog, Home, Office, Wrench, Layer, Key, Money)),
          ("Time", Vector(Bell, Calendar, Clock, SandClock)),
          ("User", Vector(UserSingle, UserGroup, UserInvestor, UserCompany, UserInfo)),
        )
        // format: on
      )
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialState(State(""))
    .renderBackend[Backend]
    .build
}
