// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.components

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

class SimpleState[V] {

  case class Props(
    initialValue: V,
    render: (V, V => Callback) => VdomNode
  ) {
    def apply(): VdomElement = component(this)
  }

  private case class State(value: V)

  private class Backend(scope: BackendScope[Props, State]) {

    def setValue(value: V): Callback = {
      scope.setState(State(value))
    }

    def render(props: Props, state: State): VdomNode = {
      props.render(state.value, setValue)
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialStateFromProps(props => State(value = props.initialValue))
    .renderBackend[Backend]
    .build

  def apply(): Props.type = Props
}

object SimpleState {
  val Str = (new SimpleState[String])()
  val Bool = (new SimpleState[Boolean])()
  val BoolF = Bool(initialValue = false, render = (_, _) => EmptyVdom)
}
