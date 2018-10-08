package anduin.guide

import scala.scalajs.js

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.router._
import anduin.guide.Pages._
import anduin.guide.page._

object Router {
  type Ctl = RouterCtl[Pages.Page]

  // Prism only runs once on page load, so we need to manually tell it to
  // run again on client routing
  private val postRenderFn = (prev: Option[Page], _: Page) =>
    Callback { if (prev.isDefined) { js.Dynamic.global.Prism.highlightAll() } }

  val config: RouterConfig[Page] = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    val hash = string("(#.*|)$")

    (trimSlashes
      | staticRoute(root, Welcome) ~>
        renderR(PageWelcome.render)

      | dynamicRouteCT("logo" ~ hash.caseClass[Logo]) ~>
        renderR(PageLogo.render)

      | dynamicRouteCT("style" ~ hash.caseClass[Style]) ~>
        renderR(PageStyle.render)
      | dynamicRouteCT("color" ~ hash.caseClass[Color]) ~>
        renderR(PageColor.render)
      | dynamicRouteCT("space" ~ hash.caseClass[Space]) ~>
        renderR(PageSpace.render)
      | dynamicRouteCT("flexbox" ~ hash.caseClass[Flexbox]) ~>
        renderR(PageFlexbox.render)
      | dynamicRouteCT("typography" ~ hash.caseClass[Typography]) ~>
        renderR(PageTypography.render)
      | dynamicRouteCT("typography-fixed" ~ hash.caseClass[FixedLineHeight]) ~>
        renderR(PageFixedLineHeight.render)

      | dynamicRouteCT("component" ~ hash.caseClass[Component]) ~>
        renderR(PageComponent.render)

      | dynamicRouteCT("button" ~ hash.caseClass[Button]) ~>
        renderR(PageButton.render)
      | dynamicRouteCT("button-style" ~ hash.caseClass[ButtonStyle]) ~>
        renderR(PageButtonStyle.render)
      | dynamicRouteCT("button-link" ~ hash.caseClass[ButtonLink]) ~>
        renderR(PageButtonLink.render)

      | dynamicRouteCT("card" ~ hash.caseClass[Card]) ~>
        renderR(PageCard.render)
      | dynamicRouteCT("collapse" ~ hash.caseClass[Collapse]) ~>
        renderR(PageCollapse.render)
      | dynamicRouteCT("tab" ~ hash.caseClass[Tab]) ~>
        renderR(PageTab.render)
      | dynamicRouteCT("table" ~ hash.caseClass[Table]) ~>
        renderR(PageTable.render)
      | dynamicRouteCT("well" ~ hash.caseClass[Well]) ~>
        renderR(PageWell.render)

      | dynamicRouteCT("icon" ~ hash.caseClass[Icon]) ~>
        renderR(PageIcon.render)
      | dynamicRouteCT("illus" ~ hash.caseClass[Illus]) ~>
        renderR(PageIllus.render)
      | dynamicRouteCT("illus-folder" ~ hash.caseClass[IllusFolder]) ~>
        renderR(PageIllusFolder.render)

      | dynamicRouteCT("checkbox" ~ hash.caseClass[Checkbox]) ~>
        renderR(PageCheckbox.render)
      | dynamicRouteCT("radio" ~ hash.caseClass[Radio]) ~>
        renderR(PageWIP.render)
      | dynamicRouteCT("text-box" ~ hash.caseClass[TextBox]) ~>
        renderR(PageTextBox.render)
      | dynamicRouteCT("suggest" ~ hash.caseClass[Suggest]) ~>
        renderR(PageWIP.render)
      | dynamicRouteCT("multi-suggest" ~ hash.caseClass[MultiSuggest]) ~>
        renderR(PageWIP.render)

      | dynamicRouteCT("dropdown" ~ hash.caseClass[Dropdown]) ~>
        renderR(PageDropdown.render)
      | dynamicRouteCT("multi-dropdown" ~ hash.caseClass[MultiDropdown]) ~>
        renderR(PageWIP.render)

      | dynamicRouteCT("modal" ~ hash.caseClass[Modal]) ~>
        renderR(PageModal.render)
      | dynamicRouteCT("popover" ~ hash.caseClass[Popover]) ~>
        renderR(PagePopover.render)

      | emptyRule)
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
      .onPostRender(postRenderFn)
  }
}
