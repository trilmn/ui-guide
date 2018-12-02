package anduin.guide.app.main.layout

import anduin.component.toggle.Toggle
import anduin.guide.app.main.Pages
import anduin.guide.app.main.Pages.Page
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object NavElements {

  case class Props(ctl: Pages.Ctl, page: Page)

  case class Title(
    text: String,
    page: Option[Page] = None,
    isExpanded: Option[Page => Boolean] = None
  )

  // ===

  private def renderLiIcon(children: VdomNode, isExpanded: Boolean): VdomElement = {
    val text = if (children == EmptyVdom) " " else if (isExpanded) "−" else "+"
    // don't use left here as the link will be lost in the middle space
    <.div(Style.position.absolute, ^.right := "100%", text, " ")
  }

  // This is similar to ScalaJS React's setOnLinkClick but we need it as a
  // callback for composition purpose
  private def setPage(page: Page, props: Props, e: ReactMouseEvent): Callback = {
    CallbackOption.unless(ReactMouseEvent targetsNewTab_? e) >> props.ctl.setEH(page)(e)
  }

  private def getColor(current: Page, targetOpt: Option[Page]): TagMod = {
    targetOpt.fold[TagMod](Style.color.inherit) { target =>
      if (target.getClass.getSimpleName == current.getClass.getSimpleName) {
        Style.color.gray7.borderColor.gray3
      } else {
        Style.color.inherit.borderColor.transparent
      }
    }
  }

  private def renderLi(
    title: Title,
    children: VdomNode,
    props: Props
  )(toggle: Callback, isExpanded: Boolean): VdomElement = {
    val common = TagMod(
      Style.position.relative.hover.colorPrimary4.transition.all,
      getColor(props.page, title.page),
      renderLiIcon(children, isExpanded),
      title.text
    )
    val node = title.page.fold[VdomElement] {
      <.button(^.onClick --> toggle, common)
    } { page =>
      <.a(
        Style.hover.underlineNone.hover.borderPrimary3,
        Style.border.bottom.borderWidth.px2,
        ^.href := props.ctl.urlFor(page).value,
        ^.onClick ==> (e => setPage(page, props, e) >> toggle),
        common
      )
    }
    <.li(node, TagMod.when(isExpanded) { children })
  }

  def li(title: Title, children: VdomNode = EmptyVdom)(implicit props: Props): VdomElement = {
    Toggle(
      isExpanded = title.isExpanded.fold(false)(_(props.page)),
      render = renderLi(title, children, props)
    )()
  }

  // ===

  def ul(content: VdomNode*): VdomElement = {
    <.ul(Style.listing.list.padding.left20, React.Fragment(content: _*))
  }

}
