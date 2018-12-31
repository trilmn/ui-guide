// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class CommonSizeExample(
                          ) {
  def apply(): VdomElement = CommonSizeExample.component(this)
}

object CommonSizeExample {

  private type Props = CommonSizeExample

  private def render(props: Props): VdomElement = {
    <.div()
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}