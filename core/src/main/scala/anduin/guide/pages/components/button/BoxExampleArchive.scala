// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.modal.{Modal, ModalBody, ModalFooterWCancel}
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class BoxExampleArchive() {
  def apply(): VdomElement = BoxExampleArchive.component(this)
}

object BoxExampleArchive {

  private type Props = BoxExampleArchive

  private def renderContent(closeModal: Callback): VdomElement = {
    React.Fragment(
      ModalBody()(
        <.p(
          Style.fontSize.px16.fontWeight.semiBold,
          "Are you sure want to archive this deal?"
        ),
        <.ul(
          Style.margin.top8,
          <.li("Archived deals can be restored from your dashboard"),
          <.li("All of deal's participants will be notified")
        )
      ),
      ModalFooterWCancel(closeModal)(
        Button(
          style = Button.Style.Full(color = Button.Color.Red),
          onClick = Callback.alert("Deal archived") >> closeModal
        )("Archive")
      )
    )
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple()(
      Modal(
        renderTarget = open => Button(onClick = open)("Archive Deal"),
        renderContent = renderContent
      )()
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
