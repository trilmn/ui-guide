package anduin.guide.page

import scala.util.Random

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.portal.{Modal, ModalBody, ModalFooter, ModalFooterWCancel}
import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style

object PageModal {

  private def sampleArchiveButton(open: Callback) = {
    Button(onClick = open)("Archive Deal")
  }

  private def sampleArchiveBody(isBold: Boolean = false): VdomElement = {
    ModalBody()(
      <.p(
        Style.fontWeight.bold,
        TagMod.when(isBold) { Style.fontSize.px16.margin.bottom12 },
        "Are you sure want to archive this 8VC deal? "
      ),
      <.ul(
        <.li("Deal can be restored later from \"Trash\" filter."),
        <.li("All 8V members will be notified")
      )
    )
  }

  private def sampleArchiveContent(isBold: Boolean)(close: Callback) = {
    ReactFragment(
      sampleArchiveBody(isBold),
      ModalFooterWCancel(cancel = close)(
        Button(
          color = ButtonStyle.ColorDanger,
          onClick = Callback.alert("Deal archived") >> close
        )("Archive Deal")
      )
    )
  }

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
          |If you do need to render something else manually, it is suggested to
          |base on the `<.button` tag to have good accessibility support.
          |
          |**Note that Modal does not render any additional wrapper tag.**
          |Modal's content is rendered via [React's Portal][1], and the place
          |where you use Modal is replaced with only what returned in your
          |`renderTarget`. Nothing more or less, and no matter the Modal is
          |opening or not.
          |
          |```scala
          |// so this:
          |val modal = Modal(
          |  renderTarget = open => <.button(...)
          |)()
          |<.p("foo", modal)
          |
          |// Become this:
          |<.p("foo", <.button(...))
          |```
          |
          |[1]: https://reactjs.org/docs/portals.html
          |
          |## Content
          |
          |```scala
          |renderContent: (close: Callback) => VdomNode
          |```
          |
          |Use the `renderContent` prop to render what should be shown in
          |your Modal. This usually consists of a body and a footer, since
          |the Modal's header is already defined by default.
          |
          |For maximum customization, Modal does not provide any predefined
          |styles (like padding) for your `renderContent`. Instead, you
          |should use `ModalBody` and `ModalFooter` to build your Modal's
          |content with nice, predefined padding:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "Error signing document",
          renderTarget = open => {
            Button(onClick = open)("Open Modal")
          }, /*<*/
          renderContent = close => {
            val body = ModalBody()(
              <.p("There was an unknown error signing your document."),
              <.p("Please try again or contact us for support.")
            )
            val footer = ModalFooter()(
              <.div(
                Style.flexbox.flex.flexbox.justifyBetween,
                Style.flexbox.itemsCenter,
                <.p(Style.color.gray6, "Error code: 8AS13FHS"),
                Button(
                  color = ButtonStyle.ColorPrimary,
                  onClick = close
                )("Okay")
              )
            )
            ReactFragment(body, footer)
          } /*>*/
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |### ModalFooterWCancel
          |
          |In practice, it is very common for a Modal to have a footer with 2
          |buttons: one to submit/execute an action and the other to close
          |(cancel/dismiss) the Modal.
          |
          |In these cases, use the `ModalFooterWCancel` component which
          |provide the necessary layout and a "Cancel" button. You only need to
          |attach the "cancel/close" callback and provide your "submit" button:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "Deal archive confirmation",
          renderTarget = sampleArchiveButton, /*<*/
          renderContent = close => {
            val footer = ModalFooterWCancel(cancel = close)(
              Button(
                color = ButtonStyle.ColorDanger,
                onClick = Callback.alert("Deal archived") >> close
              )("Archive Deal")
            ) /*>*/
            ReactFragment(sampleArchiveBody(), footer)
          }
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |## Title
          |
          |Modal comes with a default header, which has a title that can be
          |modified via the `title: String` prop.
          |
          |**It is required that all Modals have title.** If you omit the
          |`title` prop, Modal will remove the default header and expect you
          |to have your own header defined in `renderContent`. This should
          |rarely happen in practice.
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val modal = Modal(
          // Skipped title
          renderContent = sampleArchiveContent(isBold = true),
          renderTarget = sampleArchiveButton
        )()
        <.div(modal)
      }))(),
      Markdown(
        """
          |Moreover, having no default header (`title` prop is omitted)
          |means that there is no "x" button to close on top right.
          |Therefore, you should also define a clear "cancel/dismiss" in your
          |`renderContent` in this case.
          |
          |## Size
          |
          |**Modal's height depends on your `renderContent`.** If the height of
          |your content is larger than user's screen then there will be a
          |scrollbar outside of the Modal. However, in general, it's best
          |to avoid long Modal.
          |
          """.stripMargin
      )(),
      ExampleSimple()(
        Modal(
          title = "Long modal",
          renderTarget = open => Button(onClick = open)("Open a long modal"),
          renderContent = _ => {
            val text = Random.alphanumeric.take(100).mkString("\n")
            ModalBody()(<.div(Style.whiteSpace.pre, text))
          }
        )()
      ),
      Markdown(
        """
          |**Modal's width does not depend on your `renderContent`.**
          |Instead, it should be chosen from a predefined list via the `size`
          |prop:
          |
          || Size | Usage |
          ||---|---|
          || `Size480` | For confirmation (short text, 2 clear actions) |
          || `Size720` | For better action, pick and choose (list of item like contact), short form, Onboarding, etc |
          || `Size960` | A bit more complicated form with more stuff to handle like our current ComposeModal or Create Signature |
          || `Size1160` | A bit blurry between this one but ideally it’s about a small-flow (stepper) |
          || `SizeFull` | A bit blurry between this one but ideally it’s about a small-flow (stepper) |
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val modal = Modal(
          title = "960px Modal",
          renderTarget = open => Button(onClick = open)("Open 960px Modal"),
          renderContent = _ => ModalBody()("Hello world"),
          size = Modal.Size960
        )()
        <.div(modal)
      }))(),
      Markdown(
        """
          |
          |
          |### Full screen
          |
          |# Advanced
          |
          |## Custom render
          |
          |## Event hooks
          |
          |## Permanent
          |
          |## Closable
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
