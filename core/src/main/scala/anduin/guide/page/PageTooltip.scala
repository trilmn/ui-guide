package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.component.portal._
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTooltip {

  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Tooltip",
          description = "Tooltip helps identify or adds information to an element."
        )()
      ),
      Markdown(
        """
          |# Overview
          |
          |```scala
          |Tooltip(
          |  renderTarget: () => VdomNode,
          |  renderContent: () => String,
          |  isInline: Boolean = false,
          |  isDisabled: Boolean = false,
          |  position: Position = PositionBottom
          |)()
          |```
          |
          |Example:
          """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Tooltip(
              targetTag = <.span,
              renderTarget = "target",
              renderContent = () => "content"
            )()
          )
        )
      )(),
      Markdown(s"""
                  |# Usage
                  |
                  |## Hint
                  |
                  |Tooltip is quite powerful that it provides information without taking any space. However, because of that, these information are not visible at first sight but requires user's interaction. Worse, out of the box they have no hint at all until user's cursor is on them.
                  |
          """.stripMargin)(),
      ExampleSimple("**Example:** Without hovering, can you tell whether this button has a tooltip or not?")({
        val target = Button(isDisabled = true)("Archive Transaction")
        val content = "You don't have permission to archive this transaction"
        val tooltip = Tooltip(renderTarget = target, renderContent = () => content)()
        <.div(Style.flexbox.flex, tooltip)
      }),
      Markdown(
        """
          |Therefore, it's usually good to have an explicit hint that we have an explanation here:
          |
          """.stripMargin
      )(),
      ExampleSimple()({
        val button = <.div(Style.margin.right8, Button(isDisabled = true)("Archive Transaction"))
        val target = <.div(Style.cursor.pointer, Icon(name = Icon.NameInfo)())
        val content = "You don't have permission to archive this transaction"
        val tooltip = Tooltip(renderTarget = target, renderContent = () => content)()
        <.div(Style.flexbox.flex.flexbox.itemsCenter, button, tooltip)
      }),
      Markdown(
        """
          |Or even better, exp
          |
          |# Interface
          |
          |## `target` & `content`
          |
          |Like other consumers of [Portal][1], the Tooltip component has:
          |- [`renderTarget`][2] to render the target element – one that should have the tooltip shown when user's pointer is on it.
          |- [`renderContent`][3] to render the content of that tooltip.
          |
          |**Both `render` don't have any parameters,** because showing and hiding are already controlled by the component for you, while `status` is unnecessary since the "tip" already pointed to the source target.
          |
          |**`renderContent` must return a `String`,** which will also be wrapped too long. If you need anything more complex than a string, consider using [Popover][4].
          |
          |[1]: ${ctl.urlFor(Main.Portal()).value}
          |[2]: ${ctl.urlFor(Main.Portal("#target")).value}
          |[3]: ${ctl.urlFor(Main.Portal("#content")).value}
          |[4]: ${ctl.urlFor(Main.Popover()).value}
          """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Tooltip(
              targetTag = <.span,
              renderTarget = "Long content example",
              renderContent = () => """
                                      |Lorem ipsum dolor sit amet, porro errem ullamcorper
                                      |has eu, inermis recteque at mea. Quod feugait in vim.
                """.stripMargin
            )()
          )
        )
      )(),
      Markdown(
        """
          |
          |## `isInline`
          |
          """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.p(
            "This sentence has 2 tooltips: the ",
            Tooltip(
              targetTag = <.span,
              renderTarget = <.a(^.href := "#", "first"),
              renderContent = () => "First tooltip"
            )(),
            " one and the ",
            Tooltip(
              targetTag = <.span,
              renderTarget = <.a(^.href := "#", "second"),
              renderContent = () => "Second tooltip"
            )(),
            " one."
          )
        )
      )(),
      Markdown(
        """
          |## `isDisabled`
          |
          |The tooltip can be disabled dynamically via the `isDisabled` prop. This comes in handy when you want to show the tooltip only under a specific context. For example, when a button is disabled, there will be a tooltip to explain why. This tooltip
          |
          """.stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val isButtonDisabled = true /*>*/
          <.div(
            Style.flexbox.flex, /*<*/
            Tooltip(
              renderTarget = Button(isDisabled = isButtonDisabled)("Archive"),
              renderContent = () => "You don't have permission to archive",
              isDisabled = !isButtonDisabled
            )() /*>*/
          ) /*<*/
        })
      )(),
      Markdown(
        """
          |
          """.stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          Button(isDisabled = true)("Archive"),
          <.p(
            Style.margin.left16,
            "You don't have permission to archive. ",
            <.a(^.href := "#", "Request permission.")
          )
        )
      ),
      Markdown(
        """
          |
          |
          |## `position`
          |
          """.stripMargin
      )(),
      ExampleSimple()({
        val renderPos: (Position) => VdomNode = (position: Position) =>
          <.div(
            Style.flexbox.fixed.padding.hor8,
            Tooltip(
              renderTarget = <.div(
                Style.width.pc100.height.px64.backgroundColor.gray3.hover.backgroundGray2,
                Style.flexbox.flex.flexbox.justifyCenter.flexbox.itemsCenter,
                position.getClass.getSimpleName.replace("Position", "")
              ),
              renderContent = () => "This is an example content",
              position = position
            )()
        )
        val row = Style.flexbox.flex.flexbox.justifyCenter.margin.bottom16
        <.div(
          // format: off
          <.div(row, List(PositionTopLeft, PositionRightTop, PositionBottomLeft, PositionLeftTop).toVdomArray(renderPos)),
          <.div(row, List(PositionTop, PositionRight, PositionBottom, PositionLeft).toVdomArray(renderPos)),
          <.div(row, List(PositionTopRight, PositionRightBottom, PositionBottomRight, PositionLeftBottom).toVdomArray(renderPos)),
          // format: on
          ^.marginBottom := "-16px"
        )
      }),
      Markdown(
        """
          |# Usage
        """.stripMargin
      )()
    )
  }
}
