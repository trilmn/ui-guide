package anduin.guide.app.router

import anduin.guide.app.main.layout.Layout
import anduin.guide.app.main.PageWrapper
import anduin.guide.app.main.Pages._
import anduin.guide.pages.brand.logo.PageLogo
import anduin.guide.pages.components.card.PageCard
import anduin.guide.pages.components.checkbox.PageCheckbox
import anduin.guide.pages.components.dropdown.PageDropdown
import anduin.guide.pages.components.button.PageButton
import anduin.guide.pages.components.field.PageField
import anduin.guide.pages.components.home.PageComponent
import anduin.guide.pages.components.icon.PageIcon
import anduin.guide.pages.components.illus.{PageIllus, PageIllusFolder}
import anduin.guide.pages.components.progressindicator.PageProgressIndicator
import anduin.guide.pages.components.modal.PageModal
import anduin.guide.pages.components.popover.PagePopover
import anduin.guide.pages.components.stepper.PageStepper
import anduin.guide.pages.components.tab.PageTab
import anduin.guide.pages.components.table.PageTable
import anduin.guide.pages.components.textbox.PageTextBox
import anduin.guide.pages.components.toggle.PageToggle
import anduin.guide.pages.components.well.PageWell
import anduin.guide.pages.style._
import anduin.guide.pages.welcome.PageWelcome
import anduin.guide.pages.wip.PageWIP
import japgolly.scalajs.react.extra.router.{Redirect, RouterConfig, RouterConfigDsl}
import japgolly.scalajs.react.vdom.VdomElement

import scala.scalajs.js.Promise

object Router {

  private type RenderFn = Ctl => VdomElement

  val config: RouterConfig[Page] = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    val hash = string("(#.*|)$")

    def getRender(promiseFn: () => Promise[RenderFn]) = {
      renderR(ctl => PageWrapper(ctl, promiseFn)())
    }

    (trimSlashes
      | staticRoute(root, Welcome) ~> getRender(() => Promise.resolve[RenderFn](PageWelcome.render _))
      // Branding
      | dynamicRouteCT("logo" ~ hash.caseClass[Logo]) ~> getRender(() => Promise.resolve[RenderFn](PageLogo.render _))
      // Style
      | dynamicRouteCT("style" ~ hash.caseClass[Style]) ~> getRender(() => Promise.resolve[RenderFn](PageStyle.render _))
      | dynamicRouteCT("color" ~ hash.caseClass[Color]) ~> getRender(() => Promise.resolve[RenderFn](PageColor.render _))
      | dynamicRouteCT("flexbox" ~ hash.caseClass[Flexbox]) ~> getRender(() => Promise.resolve[RenderFn](PageFlexbox.render _))
      | dynamicRouteCT("space" ~ hash.caseClass[Space]) ~> getRender(() => Promise.resolve[RenderFn](PageSpace.render _))
      | dynamicRouteCT("typography" ~ hash.caseClass[Typography]) ~> getRender(() => Promise.resolve[RenderFn](PageTypography.render _))
      | dynamicRouteCT("typography-fixed" ~ hash.caseClass[FixedLineHeight]) ~> getRender(() => Promise.resolve[RenderFn](PageFixedLineHeight.render _))
      // Component
      | dynamicRouteCT("component" ~ hash.caseClass[Component]) ~> getRender(() => Promise.resolve[RenderFn](PageComponent.render _))
      | dynamicRouteCT("button" ~ hash.caseClass[Button]) ~> getRender(() => Promise.resolve[RenderFn](PageButton.render _))
      | dynamicRouteCT("card" ~ hash.caseClass[Card]) ~> getRender(() => Promise.resolve[RenderFn](PageCard.render _))
      | dynamicRouteCT("checkbox" ~ hash.caseClass[Checkbox]) ~> getRender(() => Promise.resolve[RenderFn](PageCheckbox.render _))
      | dynamicRouteCT("progress-indicator" ~ hash.caseClass[ProgressIndicator]) ~> getRender(() => Promise.resolve[RenderFn](PageProgressIndicator.render _))
      | dynamicRouteCT("collapse" ~ hash.caseClass[Toggle]) ~> getRender(() => Promise.resolve[RenderFn](PageToggle.render _))
      | dynamicRouteCT("dropdown" ~ hash.caseClass[Dropdown]) ~> getRender(() => Promise.resolve[RenderFn](PageDropdown.render _))
      | dynamicRouteCT("field" ~ hash.caseClass[Field]) ~> getRender(() => Promise.resolve[RenderFn](PageField.render _))
      | dynamicRouteCT("icon" ~ hash.caseClass[Icon]) ~> getRender(() => Promise.resolve[RenderFn](PageIcon.render _))
      | dynamicRouteCT("illus" ~ hash.caseClass[Illus]) ~> getRender(() => Promise.resolve[RenderFn](PageIllus.render _))
      | dynamicRouteCT("illus-folder" ~ hash.caseClass[IllusFolder]) ~> getRender(() => Promise.resolve[RenderFn](PageIllusFolder.render _))
      | dynamicRouteCT("modal" ~ hash.caseClass[Modal]) ~> getRender(() => Promise.resolve[RenderFn](PageModal.render _))
      | dynamicRouteCT("multi-dropdown" ~ hash.caseClass[MultiDropdown]) ~> getRender(() => Promise.resolve[RenderFn](PageWIP.render _))
      | dynamicRouteCT("multi-suggest" ~ hash.caseClass[MultiSuggest]) ~> getRender(() => Promise.resolve[RenderFn](PageWIP.render _))
      | dynamicRouteCT("popover" ~ hash.caseClass[Popover]) ~> getRender(() => Promise.resolve[RenderFn](PagePopover.render _))
      | dynamicRouteCT("radio" ~ hash.caseClass[Radio]) ~> getRender(() => Promise.resolve[RenderFn](PageWIP.render _))
      | dynamicRouteCT("stepper" ~ hash.caseClass[Stepper]) ~> getRender(() => Promise.resolve[RenderFn](PageStepper.render _))
      | dynamicRouteCT("suggest" ~ hash.caseClass[Suggest]) ~> getRender(() => Promise.resolve[RenderFn](PageWIP.render _))
      | dynamicRouteCT("tab" ~ hash.caseClass[Tab]) ~> getRender(() => Promise.resolve[RenderFn](PageTab.render _))
      | dynamicRouteCT("table" ~ hash.caseClass[Table]) ~> getRender(() => Promise.resolve[RenderFn](PageTable.render _))
      | dynamicRouteCT("text-box" ~ hash.caseClass[TextBox]) ~> getRender(() => Promise.resolve[RenderFn](PageTextBox.render _))
      | dynamicRouteCT("well" ~ hash.caseClass[Well]) ~> getRender(() => Promise.resolve[RenderFn](PageWell.render _))
      | emptyRule)
      .notFound(redirectToPage(Welcome)(Redirect.Replace))
      .renderWith(Layout.render)
  }
}
