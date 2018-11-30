// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.app.main

import anduin.guide.app.main.Pages.Ctl

import scala.scalajs.js
import scala.scalajs.js.Promise

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Loader(
  ctl: Ctl,
  fetch: () => Promise[Ctl => VdomElement]
) {
  def apply(): VdomElement = Loader.component(this)
}

object Loader {

  private type Props = Loader

  private case class State(isLoaded: Boolean)

  private class Backend(scope: BackendScope[Props, State]) {

    var renderFnOpt: Option[Ctl => VdomElement] = None

    def render(props: Props, state: State): VdomElement = {
      if (state.isLoaded) {
        renderFnOpt
          .map(renderFn => renderFn(props.ctl))
          .getOrElse(<.div("nani"))
      } else {
        <.div("loading")
      }
    }

    def didMount(props: Props): Callback = Callback {
      val promise = props.fetch()
      promise.then[Unit](
        onFulfilled = renderFn => {
          renderFnOpt = Some(renderFn)
          scope.modState(_.copy(isLoaded = true)).runNow()
        },
        onRejected = js.undefined
      )
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialState(State(false))
    .renderBackend[Backend]
    .componentDidMount(scope => {
      scope.backend.didMount(scope.props)
    })
    .componentDidUpdate(scope => {
      Callback.when(scope.currentProps.fetch != scope.prevProps.fetch) {
        scope.backend.didMount(scope.currentProps)
      }
    })
    .build
}
