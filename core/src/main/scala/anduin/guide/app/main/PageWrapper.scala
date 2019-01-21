// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.app.main

import anduin.component.progressindicators.BlockIndicator
import anduin.guide.app.main.Pages.Ctl

import scala.scalajs.js
import scala.scalajs.js.Promise

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class PageWrapper(
  ctl: Ctl,
  load: () => Promise[PageWrapper.RenderFn]
) {
  def apply(): VdomElement = PageWrapper.component(this)
}

object PageWrapper {

  private type Props = PageWrapper
  private type RenderFn = Ctl => VdomElement

  private case class State(renderFnOpt: Option[RenderFn])

  private val loading: VdomElement = BlockIndicator()()

  private def renderPage(props: Props)(renderFn: RenderFn): VdomElement = {
    <.div(^.padding := "48px 0", renderFn(props.ctl))
  }

  private class Backend(scope: BackendScope[Props, State]) {

    def render(props: Props, state: State): VdomElement = {
      state.renderFnOpt.fold(loading)(renderPage(props))
    }

    private def setRenderFn(renderFn: RenderFn): Unit = {
      val state = State(Some(renderFn))
      // Prism only runs once on page load, so we need to manually tell it to
      // run again on client routing
      val prismHighlight = Callback { js.Dynamic.global.Prism.highlightAll() }
      scope.setState(state, prismHighlight).runNow()
    }

    def loadRenderFn(props: Props): Callback = Callback {
      props.load().then[Unit](onFulfilled = fn => setRenderFn(fn))
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialState(State(renderFnOpt = None))
    .renderBackend[Backend]
    .componentDidMount(scope => {
      scope.backend.loadRenderFn(scope.props)
    })
    .componentDidUpdate(scope => {
      Callback.when(scope.currentProps.load != scope.prevProps.load) {
        val callback = scope.backend.loadRenderFn(scope.currentProps)
        scope.modState(_.copy(renderFnOpt = None), callback)
      }
    })
    .build
}
