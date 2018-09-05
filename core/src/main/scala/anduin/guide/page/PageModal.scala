package anduin.guide.page

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.portal.{Modal, ModalBody, ModalFooter, ModalFooterWCancel}
import japgolly.scalajs.react.vdom.html_<^._
import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.Callback

object PageModal {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Modal")()
      ),
      ExampleRich(Source.annotate({
        val modal = Modal(
          title = "Hello World",
          renderContent = _ => {
            ModalBody()("This is Modal's content")
          },
          renderTarget = open => {
            Button(onClick = open)("Open a Modal")
          }
        )()
        <.div(modal)
      }))(),
      Markdown(
        s"""
          |# Basic
          |
          |Most Modal instances often have `renderTarget`, `renderContent` and
          |`title` props defined:
          |
          |## Target
          |
          |```scala
          |renderTarget: (open: Callback) => VdomNode
          |```
          |
          |`renderTarget` should render an element ("target") that user can
          |interact with to open the Modal, using the `open: Callback` provided.
          |
          |Technically, you can attach `open` to any element. However, it is
          |suggested to use the [`Button`][1] component for best
          |accessibility result. `Button` also offers [rich
          |customization][2] so you are unlikely to hit any appearance
          |limitation:
          |
          |[1]: ${ctl.urlFor(Pages.Button()).value}
          |[2]: ${ctl.urlFor(Pages.ButtonStyle()).value}
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "Hello World",
          renderContent = _ => {
            ModalBody()("Modal's content")
          }, /*<*/
          renderTarget = open => {
            Button(
              style = ButtonStyle.StyleLink,
              onClick = open
            )("open a modal")
          } /*>*/
        )() /*<*/
        <.p(
          "For example you can ",
          modal,
          " in the middle of a sentence."
        )
      }))(),
      Markdown(
        """
          |However, if you do need to render something else manually, it is
          |suggested to base on the `<.button` tag to have good
          |accessibility support.
          |
          |**Note that Modal does not render any additional wrapper tag.**
          |Modal's content is rendered via [React's Portal][1], and the place
          |where you use Modal is replaced with only what returned in your
          |`renderTarget`. Nothing more or less, and no matter the Modal is
          |opening or not.
          |
          |[1]: https://reactjs.org/docs/portals.html
          |
          |## Title
          |
          |Use the `title` prop to define the title in the header of the Modal. 
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val modal = Modal(
          renderContent = close =>
            ReactFragment(
              ModalBody()(
                <.p(Style.fontWeight.bold, "This is Modal's content"),
                <.p("")
              ),
              ModalFooterWCancel(cancel = close)(
                Button(
                  color = ButtonStyle.ColorDanger,
                  onClick = Callback.alert("Deal archived") >> close
                )("Delete")
              )
          ),
          renderTarget = open => {
            Button(onClick = open)("Archive")
          }
        )()
        <.div(modal)
      }))(),
      Markdown(
        """
          |
          |## Size
          |
          |##
          |
          |# Advanced
          |
          |## Custom render
          |
          |## Event hooks
          |
          |## Permanent
          |
          |## Open by default
          |
          |## Controlled Modal
          |
        """.stripMargin
      )()
    )
  }
}
