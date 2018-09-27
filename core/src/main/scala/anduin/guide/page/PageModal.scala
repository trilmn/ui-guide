package anduin.guide.page

import scala.util.Random
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import anduin.component.button.{Button, ButtonStyle}
import anduin.component.input.TextInput
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
    React.Fragment(
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
        Header(
          title = "Modal",
          """
            |Modal focuses user on critical information or task. The task might
            |be as simple as picking contacts, or as complex as signing a
            |document.
          """.stripMargin
        )()
      ),
      Markdown(
        """
          |# Basic Usage
          |
          |Modal is an [uncontrolled component][uc] that provides you
          |callbacks to open and close it via the [render props][rp]:
          |
          |[uc]: https://reactjs.org/docs/uncontrolled-components.html
          |[rp]: https://reactjs.org/docs/render-props.html
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Modal(
          title = "Hello World",
          renderContent = _ => {
            ModalBody()("This is Modal's content")
          },
          renderTarget = open => {
            Button(onClick = open)("Open a Modal")
          }
        )()
      }))(),
      Markdown(
        s"""
          |**Modal blocks user's main workflow.** While it is opening, no other
          |interactions on the main app can be made. Consider using
          |[Popover][pv] for non-critical information or task that should not
          |block the main workflow.
          |
          |[pv]: ${ctl.pathFor(Pages.Popover()).value}
          |
          |**Modal kept user's main workflow.** Unlike page navigation,
          |Modal does not affect the current page at all, leaving user's
          |current work and data untouched, although not accessible.
          |
          |# Target
          |
          |```scala
          |renderTarget: (open: Callback) => VdomNode
          |```
          |
          |`renderTarget` should render an element that user can interact
          |with to open the Modal (thus the name "target"), using the
          |`open: Callback` provided.
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
          |If you really need to render something other than Button, it is
          |suggested to still base on the `<.button` tag to have good
          |accessibility support.
          |
          |## No wrapper
          |
          |Modal does not render any additional wrapper tag around the target
          |element.
          |
          |Modal's content is rendered via [React's Portal][1], and the place
          |where you use Modal is replaced with only what returned in your
          |`renderTarget`. Nothing more or less, no matter the Modal is
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
          |# Content
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
          |styles (like padding) for your `renderContent`. Instead, you will
          |usually want to use `ModalBody` and `ModalFooter` to build your
          |Modal's content with nice, consistent padding:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
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
            React.Fragment(body, footer)
          } /*>*/
        )() /*<*/
      }))(),
      Markdown(
        """
          |## Footer with Cancel
          |
          |In practice, it is very common for a Modal to have a footer with 2
          |buttons: one to submit/execute an action and the other to close
          |(cancel/dismiss) the Modal.
          |
          |In these cases, use the `ModalFooterWCancel` component which
          |provide the necessary layout and a "Cancel" button. You only need to
          |attach the "close" callback and provide your "submit" button:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
          title = "Deal archive confirmation",
          renderTarget = sampleArchiveButton, /*<*/
          renderContent = close => {
            val footer = ModalFooterWCancel(cancel = close)(
              Button(
                color = ButtonStyle.ColorDanger,
                onClick =
                  Callback.alert("Deal archived") >> close
              )("Archive Deal")
            ) /*>*/
            React.Fragment(sampleArchiveBody(), footer)
          }
        )() /*<*/
      }))(),
      Markdown(
        """
          |# Title
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
        Modal(
          // Skipped title
          renderContent = sampleArchiveContent(isBold = true),
          renderTarget = sampleArchiveButton
        )()
      }))(),
      Markdown(
        """
          |Moreover, having no default header (`title` prop is omitted)
          |means that there is no "x" button to close on top right.
          |Therefore, you should also define a clear "cancel/dismiss" in your
          |`renderContent` in this case.
          |
          |# Size
          |
          |**Modal's height depends on your `renderContent`.** When it's longer
          |than user's screen then there will be a vertical scrollbar in
          |Modal's background (outside of the content box). In general,
          |however, it's best to avoid long Modal in the first place.
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
        Modal(
          title = "960px Modal",
          renderTarget = open => Button(onClick = open)("Open 960px Modal"),
          renderContent = _ => ModalBody()("Hello world"),
          /*<*/
          size = Modal.Size960 /*>*/
        )() /*<*/
      }))(),
      Markdown(
        """
          |
          |Note that there will be no horizontal scrollbar (i.e. it's
          |`overflowX.hidden`). If your content is wider than the
          |Modal's width then it will be hidden unless you define your own
          |`overflow` method.
          |
          |## Full screen
          |
          |The `size` prop also accepts a special `SizeFull` value, in which:
          |
          |- There will be no background. Your `renderContent` will take 100%
          |of user screen's width and height.
          |- There will be no vertical scrollbar out-of-the-box. If your
          |content might be longer than user's screen, define an overflow
          |method somewhere in your `renderContent`.
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
          title = "Full-screen Modal",
          renderTarget = open =>
            Button(onClick = open)(
              "Open a Full-screen Modal"
          ),
          renderContent = _ => ModalBody()("Hello world"), /*<*/
          size = Modal.SizeFull /*>*/
        )() /*<*/
      }))(),
      Markdown(
        """
          |
          |# Focus
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
          |For example, this Modal has the "Submit" button auto-focused:
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
          title = "Example Modal",
          renderTarget = open => Button(onClick = open)("Open Modal"), /*<*/
          renderContent = close => {
            val body = ModalBody()("Content")
            val submit = Callback.alert("Submitted") >> close
            val button = Button(
              color = ButtonStyle.ColorPrimary,
              autoFocus = true,
              onClick = submit
            )("Submit")
            val footer = ModalFooterWCancel(close)(button)
            React.Fragment(body, footer)
          } /*>*/
        )() /*<*/
      }))(),
      Markdown(
        """
          |
          |If the "close" button does not exist (`title` is not set) then
          |it is required for the engineer to set focus to an element in
          |`renderContent`, or else keyboard navigation will suffer.
          |
          |# Closable
          |
          |Out-of-the-box, a Modal can be closed via several ways:
          |
          |1. Click on the "close" button on Modal's header
          |2. Click on Modal's background (the dark area)
          |3. Press ESC key while Modal [has focused](#focus)
          |
          |By default, all 3 are enabled. They can be customized by passing an
          |`Option[PortalUtils.IsClosable]` to the `isClosable` prop:
          |
          |```scala
          |case class IsClosable(
          |  onEsc: Boolean,
          |  onOutsideClick: Boolean
          |)
          |
          |isClosable: Option[IsClosable]
          |```
          |
          |## `isClosable: Some`
          |
          |If `isClosable` is defined, then 1 will always be enabled, while 2
          |and 3 depend on the value of `onOutsideClick` and `onEsc`,
          |respectively.
          |
          |For example, the below Modal can not be closed via clicking
          |outside, but "esc" and "close" button works fine:
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
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
        )() /*<*/
      }))(),
      Markdown(
        """
          |
          |Currently it is not possible to disable 1 while leaving 2 or 3
          |enabled.
          |
          |## `isClosable: None`
          |
          |When `isClosable` is not defined, all 3 ways will be disabled. The
          |"close" button will not be rendered at all. In other words, there
          |is no way for user to close the Modal unless defined in your
          |`renderContent`.
          |
          |Because of this, `isClosable: None` often be used to require user to
          |do something. For example, the Modal below can only be closed if
          |user submitted some text:
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderContent = (close: Callback) =>
          SimpleState.Str(
            initialValue = "",
            render = (value, onChange) => {
              val input = TextInput(value = value, onChange = onChange)()
              val body = ModalBody()(input)

              val onClick = Callback.alert(s"input: $value") >> close
              val button = Button(isDisabled = value.isEmpty, onClick = onClick)("Submit")
              val footerStyle = Style.flexbox.flex.flexbox.justifyEnd
              val footer = ModalFooter()(<.div(footerStyle, button))

              React.Fragment(body, footer)
            }
          )()
        /*>*/
        Modal(
          title = "Example Modal", /*<*/
          isClosable = None, /*>*/
          renderTarget = open => Button(onClick = open)("Open Modal"),
          renderContent = renderContent
        )() /*<*/
      }))(),
      Markdown(
        """
          |
          |**In general, avoid using `isClosable: None`.** This should only
          |be used if there is no reasonable going back. Otherwise, always
          |try to allow user to dismiss a Modal easily.
          |
          |A good example is to ask user to do something when there is a
          |critical issue with their account, which should prevent them from
          |using our app altogether.
          |
          |A bad example is to ask user to do something to do something to
          |access a page or feature. The Modal in this case should still
          |allow user to dismiss it, which simply take them back to the
          |previous page or dashboard.
          |
          |# Advanced
          |
          |>::warning:: **Heads-up: Be super careful when using these advanced
          |>features.**
          |>
          |>They are built for edge cases, which might results in unstable
          |>behaviour or bad user experience if being used incorrectly.
          |>Ensure that you read carefully to understand the underlying work
          |>before using them.
          |
          |## URL
          |
          |>::warning:: **This feature is not available yet.** It is
          |currently a proposal and here for early evaluation.
          |
          |**Sometimes Modal's content can also be accessible as a standalone
          |page.** This is especially common for full-screen or large modals.
          |In these cases we usually want to update browser's address to
          |match the new page in the Modal, so bookmark or link sharing
          |will work properly.
          |
          |**In these cases, use the `url` prop to ask Modal to update
          |browser's url** when its content is shown (i.e. opening). Its
          |value should be the path to the new page, which usually come from
          |`routerCtl.pathFor`:
          |
          |[ps]: https://developer.mozilla.org/en-US/docs/Web/API/History_API#The_pushState()_method
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Modal(
          title = "URL Modal",
          renderTarget = open => Button(onClick = open)("Open Modal"),
          renderContent = _ => ModalBody()("welcome content"), /*<*/
          /* url = ctl.pathFor(Pages.Welcome()).value, */ /*>*/
          size = Modal.Size1160
        )() /*<*/
      }))(),
      Markdown(
        """
          |Modal will also bring back the previous URL for you after it is
          |closed or unmounted.
          |
          |## Event hooks
          |
          |Modal provides 2 event hooks: `onOpen` and `onClose`. These
          |hooks are only called after user's interactions, which is when
          |Modal is:
          |
          |- Opened via `open` in `renderTarget`
          |- Closed via `close` in `renderContent`
          |- Closed via "close" button
          |- Closed by clicking outside of Modal
          |- Closed by pressing Esc
          |
          |These hooks will not be called when Modal is:
          |
          |- Opened in initial render because of `defaultIsOpened`
          |- Opened or closed via `isOpen` prop
          |- Unmounted
          |
          |## Unmount & Permanent
          |
          |Like other React component, Modal will be unmounted when
          |
          |**1\. Its consumer does not render it anymore**
          |
          |```scala
          |// This is bad
          |TagMod.when(...) { Modal(...)() }
          |```
          |
          |This is an anti-pattern, which usually leads to unstable
          |behaviour. If you want to programmatically control Modal's
          |visibility, use a [Controlled Modal](#controlled-modal).
          |
          |**2\. Its consumer get unmounted**
          |
          |This, however, is an expected use case. Sometimes we need to
          |show a Modal after its consumer is removed or destroyed. One
          |example is to send a message after archiving a deal.
          |
          |In these cases, set `isPermanent` prop to `true` to keep the
          |Modal stays after its consumer is unmounted:
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val renderModal = (setValue: Boolean => Callback) => {
          Modal(
            title = "Example Modal", /*<*/
            isPermanent = true, /*>*/
            renderTarget = open => Button(onClick = open)("Open Modal"),
            renderContent = close => {
              val body = <.div(
                Style.flexbox.flex,
                Button(onClick = setValue(false))("Destroy parent"),
                <.div(Style.width.px16),
                Button(onClick = close)("Close Modal")
              )
              ModalBody()(body)
            }
          )()
        } /*<*/
        SimpleState.Bool(
          initialValue = true,
          render = (value, setValue) => {
            if (value) {
              val modal = renderModal(setValue)
              <.div(modal) // Modal's consumer
            } else {
              <.p("Modal's parent is destroyed")
            }
          }
        )() /*>*/
      }))(),
      Markdown(
        """
          |### Break of data flow
          |
          |In order to live beyond its parent's lifespan, `isPermanent` Modal
          |will be re-mounted to `body` via `ReactDOM.render`. This remount
          |is called on Modal's `willUnmount`, which also trigger a new
          |`render` with its last set of props.
          |
          |After remounted, Modal is now a new, independent React root, and
          |broken all relationship with its parent (which should already be
          |destroyed).
          |
          |Therefore, avoid giving `isPermanent` Modal any callback that
          |update its parent's state. Calling these callbacks after unmounted
          |will result in a React error and having no effect.
          |
          |## Open by default
          |
          |Usually Modal is opened after user's interaction, like clicking on
          |a button. However there are cases when you want to show it as soon
          |as user accessed a page or a feature, to show some important
          |information for example.
          |
          |In these cases, set the `defaultIsOpen` prop to `true` to make
          |Modal opened in its initial render. This works in a similar way
          |with the [`defaultValue` prop][1] in React's uncontrolled form
          |components.
          |
          |[1]: https://reactjs.org/docs/uncontrolled-components.html#default-values
          |
          |```scala
          |Modal(
          |  defaultIsOpened = true,
          |  /* ... */
          |)()
          |```
          |
          |Be careful when there are several `defaultIsOpen=true` Modals in
          |the same view. Their [stack order][2] will depend on the render
          |order, so whichever get rendered later will be above.
          |
          |[2]: https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Positioning/Understanding_z_index/Stacking_without_z-index
          |
          |## Controlled Modal
          |
          |**By default Modal is an [uncontrolled component][3],** in the mean
          |that it has its own state and only provides you callbacks (e.g.
          |`open` in `renderTarget`) to modify that state to show or hide
          |Modal's content. This would be useful most of the time since you
          |won't have to maintain your own state.
          |
          |[3]: https://reactjs.org/docs/uncontrolled-components.html
          |
          |However, there are cases when you want total control over Modal's
          |content's visibility. **In these cases, provide a defined value for
          |`isOpen`.** This value should come from your own state or an
          |external source (e.g. data from API).
          |
          |When `isOpen` is defined, `isClosable` only help you to have
          |`onClose` called properly, without actually close the Modal for
          |you. However, you can always update your internal state (or call
          |API) to close Modal yourself using these [event hooks](#event-hooks):
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        SimpleState.Bool(
          initialValue = false, /*<*/
          render = (isOpened, setIsOpened) => {
            val modal = Modal(
              title = "Example Modal",
              renderContent = _ => ModalBody()("Content"),
              isOpened = Some(isOpened),
              // ===
              afterUserClose = setIsOpened(false),
              isClosable = Some(
                IsClosable(
                  onEsc = true,
                  onOutsideClick = false
                )
              )
            )()
            val button = Button(
              onClick = setIsOpened(true)
            )("Open Modal") /*>*/
            <.div(modal, button)
          }
        )() /*<*/
      }))()
    )
  }
}
