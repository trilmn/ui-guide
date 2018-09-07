package anduin.guide.page

import scala.util.Random

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.input.TextInput
import anduin.component.portal
import anduin.component.portal.PortalUtils.IsClosable
import anduin.component.portal.{Modal, ModalBody, ModalFooter, ModalFooterWCancel}
import anduin.guide.component.SimpleState
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

  private val SimpleStateString = (new SimpleState[String])()

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
          |**Modal's height depends on your `renderContent`.** When it's longer
          |than user's screen then there will be a vertical scrollbar in Modal's
          |background (outside of the content box). In general, however, it's
          |best to avoid long Modal in the first place.
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
          |**Modal's width does not depend on your `renderContent`.** It is
          |480 pixels by default, and can be changed via the `size` prop. You
          |can only choose it from a predefined list:
          |
          || Size | Usage |
          ||------|-------|
          || `Size480` | **This is the default value**, mostly used for simple actions like confirmation (short text, 2 clear actions). |
          || `Size720` | Complex actions such as picking an item from a list, simple form, onboarding. |
          || `Size960` | Complex forms, might have additional elements like Sidebar (Create Signature) or Toolbar (Email Editor). |
          || `Size1160` | Complex views like multi-step form or document editor. Usually these can also be a page. |
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "960px Modal",
          renderTarget = open => Button(onClick = open)("Open 960px Modal"),
          renderContent = _ => ModalBody()("Hello world"),
          /*<*/
          size = Modal.Size960 /*>*/
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |
          |Note that there will be no horizontal scrollbar (i.e. it's
          |`overflowX.hidden`). If your content is wider than the
          |Modal's width then it will be hidden unless you define your own
          |scrollbar.
          |
          |### Full screen
          |
          |The `size` prop also accepts a special `SizeFull` value, in which:
          |
          |- There will be no background. Your `renderContent` will take 100%
          |of user screen's width and height.
          |- There will be no vertical scrollbar out-of-the-box. If your
          |content might be longer than user's screen, define a scrollbar (i.e.
          |set `Style.overflow`) somewhere in your `renderContent`.
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "Full-screen Modal",
          renderTarget = open => Button(onClick = open)("Open a Full-screen Modal"),
          renderContent = _ => ModalBody()("Hello world"),
          /*<*/
          size = Modal.SizeFull /*>*/
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |
          |## Focus
          |
          |Accessibility-wise, [it's important][1] that user's keyboard focus
          |is moved to the Modal when it's opened, so user can navigate through
          |the Modal's content using their keyboard.
          |
          |[1]: https://reactjs.org/docs/accessibility.html#focus-control
          |
          |**Therefore, by default, Modal focuses on the "close" button.**
          |Since the "close" button is on top of the Modal (i.e. rendered
          |first) it's easy for the engineer to focus to another element
          |inside `renderContent` if needed.
          |
          |If the "close" button does not exist (`title` is not set) then
          |it is required for the engineer to set focus to an element in
          |`renderContent`, or else keyboard navigation will not available.
          |
          |# Advanced
          |
          |## Closable
          |
          |There are actually many ways a Modal can be closed. These ways are
          |controlled by 3 props:
          |
          |### `isClosable`
          |
          |This props controls the following ways to close a Modal:
          |
          |1. Click on the "close" button on Modal's header
          |2. Click on Modal's background (the dark area)
          |3. Press ESC key while Modal [has focused](#focus)
          |
          |By default, all 3 ways are enabled. They can be customized by 
          |passing an `Option[PortalUtils.IsClosable]`:
          |
          |```scala
          |case class IsClosable(
          |  onEsc: Boolean,
          |  onOutsideClick: Boolean
          |)
          |```
          |
          |- If it's `None` then all 3 ways will be disabled. The "close"
          |button will not be rendered at all.
          |- If it's `Some` then 1 will be enabled, while 2 and 3 depend on
          |`onOutsideClick` and `onEsc`, respectively.
          |- Currently it is not possible to disable 1 while leaving 2 or 3
          |enabled.
          |
          |For example, the below Modal can not be closed via clicking 
          |outside, but "esc" and "close" button works fine:
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val modal = Modal(
          title = "Example Modal",
          renderTarget = open => {
            Button(onClick = open)("Open Modal")
          },
          renderContent = _ => {
            ModalBody()("You cannot close this by clicking outside")
          }, /*<*/
          isClosable = Some(
            IsClosable(onEsc = true, onOutsideClick = false)
          ) /*>*/
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |
          |### `renderContent`
          |
          |The `renderContent` function is provided with a `close` callback,
          |allow you to render an element that user can interact with to
          |close the Modal, like an explicit "cancel" button or to close the
          |Modal after submission.
          |
          |Moreover, this can be used with `isClosable = None` to require user
          |to do something in order to dismiss the Modal. For example,
          |the Modal below can only be closed if user submitted some text:
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderContent = (close: Callback) =>
          SimpleStateString(
            initialValue = "",
            render = (value, onChange) => {
              val input = TextInput(value = value, onChange = onChange)()
              val body = ModalBody()(input)

              val onClick = Callback.alert(s"input: $value") >> close
              val button = Button(isDisabled = value.isEmpty, onClick = onClick)("Submit")
              val footerStyle = Style.flexbox.flex.flexbox.justifyEnd
              val footer = ModalFooter()(<.div(footerStyle, button))

              ReactFragment(body, footer)
            }
          )()
        /*>*/
        val modal = Modal(
          title = "Example Modal", /*<*/
          isClosable = None, /*>*/
          renderTarget = open => Button(onClick = open)("Open Modal"),
          renderContent = renderContent
        )()
        <.div(modal) /*<*/
      }))(),
      Markdown(
        """
          |
          |### `isOpen`
          |
          |`isOpen` is a special prop that makes Modal a controlled
          |component, in which the closing can only be controlled from
          |outside. Learn more about this in the
          |[Controlled Modal](#controlled-modal) section.
          |
          |## Event hooks
          |
          |Modal provides 2 event hooks: `onOpen` and `onClose`. These
          |hooks are only called after user's interactions, which means they
          |will not be called if Modal is closed programmatically or unmounted.
          |
          || Event | `onOpen` | `onClose` |
          ||-------|----------|-----------|
          || `open` in `renderTarget` | Yes | |
          || `close` in `renderContent` | | Yes |
          || click on "close" button | | Yes |
          || click on outside | | Yes |
          || press Esc | | Yes |
          || `defaultIsOpened` | | |
          || parent is unmounted | | |
          || `isOpen` is changed | | |
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
