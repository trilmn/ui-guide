package anduin.guide.component

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.style.Style

final case class CodeBlock() {
  def apply(children: VdomNode*): VdomElement = {
    CodeBlock.component(this)(children: _*)
  }
}

object CodeBlock {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[CodeBlock, _]) {
    def render(children: PropsChildren): VdomElement = {
      <.div(
        Style.backgroundColor.gray1.padding.ver12.padding.hor8,
        <.pre(
          Style.fontFamily.mono,
          ^.cls := "line-numbers",
          <.code(Style.display.block, ^.cls := "language-scala", children)
        )
      )
    }
  }

  private val component = ScalaComponent
    .builder[CodeBlock](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
