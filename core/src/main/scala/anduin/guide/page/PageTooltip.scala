package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
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
      Markdown("""
          |# Snippet
          |
          |```scala
          |Tooltip(
          |  renderTarget: () => VdomNode,
          |  renderContent: () => VdomNode,
          |  isInline: Boolean = false,
          |  isDisabled: Boolean = false,
          |  position: Position = PositionBottom
          |)()
          |```
          |
          |Example:
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div(
            Tooltip(
              isInline = true,
              renderTarget = () => "target",
              renderContent = () => "content"
            )()
          )
        )
      )(),
      Markdown("""
          |# `target` & `content`
          |
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div(
            Tooltip(
              isInline = true,
              renderTarget = () => "Target",
              renderContent = () =>
                """
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
          |# `isInline`
          |
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.p(
            "This sentence has 2 tooltips: the ",
            Tooltip(
              isInline = true,
              renderTarget = () => <.a(^.href := "#", "first"),
              renderContent = () => "First tooltip"
            )(),
            " one and the ",
            Tooltip(
              isInline = true,
              renderTarget = () => <.a(^.href := "#", "second"),
              renderContent = () => "Second tooltip"
            )(),
            " one."
          )
        )
      )(),
      Markdown("""
          |
          |
          |# `isDisabled`
          |
          |""".stripMargin)(),
      ExampleRich(
        Source.annotate({
          val isButtonDisabled = true /*>*/
          <.div(
            Style.flexbox.flex, /*<*/
            Tooltip(
              renderTarget = () => Button(isDisabled = isButtonDisabled)("Archive"),
              renderContent = () => "You don't have permission to archive",
              isDisabled = !isButtonDisabled
            )() /*>*/
          ) /*<*/
        })
      )(),
      Markdown("""
          |
          |""".stripMargin)(),
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
      Markdown("""
          |
          |
          |# `position`
          |
          |""".stripMargin)(),
      ExampleSimple()({
        val renderPos: (Position) => VdomNode = (position: Position) =>
          <.div(
            Style.flexbox.fixed.padding.hor8,
            Tooltip(
              renderTarget = () =>
                <.div(
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
      Markdown("""
          |
          |
        """.stripMargin)()
    )
  }
}
