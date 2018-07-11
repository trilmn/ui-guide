package anduin.guide.component

import japgolly.scalajs.react.extra.router.RouterCtl
import org.scalajs.dom.raw.DOMParser

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.container.Collapse
import anduin.component.icon.Icon
import anduin.guide.Main
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object NavElements {

  case class Props(
    ctl: RouterCtl[Main.Page],
    page: Main.Page
  )

  def link(content: VdomNode, target: Main.Page = Main.WIP())(
    implicit props: Props
  ): VdomElement = {
    if (target == Main.WIP()) {
      <.p(Style.color.gray6, content)
    } else {
      val isSelected = target.getClass == props.page.getClass
      val color = if (isSelected) Style.color.primary4 else Style.color.inherit
      props.ctl.link(target)(color, content)
    }
  }

  // ===

  private val parser = new DOMParser()

  private def getText(element: VdomElement) = {
    val markup = ReactDOMServer.renderToStaticMarkup(element)
    val document = parser.parseFromString(markup, "text/html")
    document.documentElement.textContent
  }

  private val emptyVdomElement: VdomElement = <.div

  private def isContain(content: VdomElement, page: String): Boolean = {
    getText(content).toLowerCase.replace(" ", "").contains(page)
  }

  def li(
    content: VdomElement,
    children: VdomElement = emptyVdomElement
  )(implicit props: Props): VdomElement = {
    // this is not good for performance, but to be honest this is a documentation,
    // not an app... we prefer usability and friendliness here
    val pageStr = props.page.getClass.getSimpleName.toLowerCase
    val hasCurrentPage =
      isContain(children, pageStr) || isContain(content, pageStr)

    Collapse(
      isExpanded = hasCurrentPage,
      render = (toggle, isExpanded) => {
        <.li(
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            if (children == emptyVdomElement) {
              Style.padding.left32
            } else {
              Button(
                style = ButtonStyle.StyleMinimal,
                size = ButtonStyle.SizeIcon,
                onClick = toggle
              )({
                val name =
                  if (isExpanded) Icon.NameCaretDown
                  else Icon.NameCaretRight
                Icon(name = name)()
              })
            },
            content
          ),
          TagMod.when(isExpanded) { children }
        )
      }
    )()
  }

  // ===

  def ul(content: VdomNode*): VdomElement = {
    <.ul(Style.listing.list.padding.left20, ReactFragment(content: _*))
  }

}
