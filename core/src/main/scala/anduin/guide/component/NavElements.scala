package anduin.guide.component

import anduin.component.container.Collapse
import anduin.component.icon.Icon
import anduin.guide.Router
import anduin.guide.Pages.Page
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object NavElements {

  case class Props(ctl: Router.Ctl, page: Page)

  case class Title(
    text: String,
    page: Option[Page] = None,
    isExpanded: Option[Page => Boolean] = None
  )

  // ===

  private def renderLiIcon(children: VdomNode, isExpanded: Boolean): VdomElement = {
    val name =
      if (children == EmptyVdom) Icon.NameBlank
      else if (isExpanded) Icon.NameCaretDown
      else Icon.NameCaretRight
    Icon(name = name)()
  }

  // This is similar to ScalaJS React's setOnLinkClick but we need it as a
  // callback for composition purpose
  private def setPage(page: Page, props: Props, e: ReactMouseEvent): Callback = {
    CallbackOption.unless(ReactMouseEvent targetsNewTab_? e) >> props.ctl.setEH(page)(e)
  }

  private def getColor(current: Page, targetOpt: Option[Page]): TagMod = {
    targetOpt.fold[TagMod](Style.color.inherit) { target =>
      val cond = target.getClass.getSimpleName == current.getClass.getSimpleName
      if (cond) Style.color.primary4 else Style.color.inherit
    }
  }

  private def renderLi(
    title: Title,
    children: VdomNode,
    props: Props
  )(toggle: Callback, isExpanded: Boolean): VdomElement = {
    val titleBody = TagMod(
      Style.flexbox.flex.flexbox.itemsCenter,
      getColor(props.page, title.page),
      renderLiIcon(children, isExpanded),
      <.span(Style.margin.left8, title.text)
    )
    val titleNode = title.page.fold[VdomElement] {
      <.button(
        ^.onClick --> toggle,
        titleBody
      )
    } { page =>
      <.a(
        ^.href := props.ctl.urlFor(page).value,
        ^.onClick ==> { e => setPage(page, props, e) >> toggle },
        titleBody
      )
    }
    <.li(titleNode, TagMod.when(isExpanded) { children })
  }

  def li(title: Title, children: VdomNode = EmptyVdom)(implicit props: Props): VdomElement = {
    Collapse(
      isExpanded = title.isExpanded.fold(false)(_(props.page)),
      render = renderLi(title, children, props)
    )()
  }

  // ===

  def ul(content: VdomNode*): VdomElement = {
    <.ul(Style.listing.list.padding.left20, ReactFragment(content: _*))
  }

}
