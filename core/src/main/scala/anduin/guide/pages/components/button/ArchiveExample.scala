// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.modal.{Modal, ModalBody, ModalFooterWCancel}
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class ArchiveExample(
                               ) {
  def apply(): VdomElement = ArchiveExample.component(this)
}

object ArchiveExample {

  private type Props = ArchiveExample

  private def render(props: Props): VdomElement = {
    Modal(
      renderTarget = open => Button(onClick = open)("Archive"),
      renderContent = close => {
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
          ModalFooterWCancel(close)(
            Button(
              style = Button.Style.Full(color = Button.Color.Red),
              onClick = close
            )("Archive")
          )
        )
      }
    )()
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}