package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageColor {

  private def renderGray(color: Style, name: String, value: String, note: String = ""): VdomElement =
    <.div(
      Style.flexbox.flex.padding.all16.fontWeight.medium,
      color,
      <.dt(Style.flexbox.fixed, Style.fontFamily.mono, name),
      <.dd(Style.flexbox.fixed, Style.fontFamily.mono, value),
      <.dd(Style.flexbox.fixed, ^.flexGrow := "2", if (note.isEmpty) " " else note)
    )

  private def renderColor(color: Style, name: String, value: String): VdomElement =
    <.div(
      Style.fontFamily.mono.fontWeight.medium,
      Style.flexbox.flex.flexbox.justifyBetween.padding.all16,
      color,
      <.dt(name),
      <.dd(value)
    )

  private def renderHeader(sColor: Style, sBg: Style, name: String, content: String): VdomElement =
    <.p(Style.padding.all16, sBg, <.span(Style.fontWeight.semiBold, sColor, name), content)

  private val bg = Style.backgroundColor
  private val c = Style.color

  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Color",
          description =
            "Gray colors define the bare bone of the interface, while purposeful ones express meanings or intentions to user."
        )()
      ),
      Markdown(
        """
          |# Overview
          |
          |```text
          |Style.<type>.<color_name>
          |​
          |  type:        color, backgroundColor, borderColor,
          |               hover, active
          |
          |  color_name:  see "Names" section
          |```
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.p(Style.color.success4, "This text should have green color")
      }))(),
      Markdown(
        """
          |# Names
          |
          |## Gray set
          |
          |Gray colors should be used to define the bare bone of the interface, such as containers, headers, sections, boxes, etc. There are 10 of them: a `white` color, followed by 9 shades of gray, named from `gray1` to `gray9`:
        """.stripMargin
      )(),
      <.dl(
        Style.padding.ver12,
        renderGray(bg.white.color.gray8, "white", "#FFFFFF", "Panel background"),
        renderGray(bg.gray1.color.gray8, "gray1", "#F5F9FC", "App main background"),
        renderGray(bg.gray2.color.gray8, "gray2", "#EBF2F7"),
        renderGray(bg.gray3.color.gray8, "gray3", "#DFE9F0", "Border light"),
        renderGray(bg.gray4.color.gray8, "gray4", "#BACDDB", "Border"),
        renderGray(bg.gray5.color.gray8, "gray5", "#9BB0C2"),
        renderGray(bg.gray6.color.gray8, "gray6", "#809AAD", "Disabled text"),
        renderGray(bg.gray7.color.gray2, "gray7", "#4F6F89"),
        renderGray(bg.gray8.color.gray2, "gray8", "#20394D", "Primary text"),
        renderGray(bg.gray9.color.gray2, "gray9", "#182A38")
      ),
      Markdown(
        """
          |## Purposeful set
          |
          |On the other hand, a purposeful color communicates the meaning or intention of an element (like a Button) or an area (like a Well) on the interface. We currently have 4 main colors, with 5 shades of each:
        """.stripMargin
      )(),
      <.div(
        Style.flexbox.flex.padding.top12,
        <.dl(
          Style.flexbox.fixed,
          renderHeader(
            c.primary4,
            bg.primary1,
            "Primary: ",
            "Primary actions and results: submit, highlight, selected…"
          ),
          renderColor(bg.primary1.color.gray8, "primary1", "#D3EEFF"),
          renderColor(bg.primary2.color.gray8, "primary2", "#ACDBF8"),
          renderColor(bg.primary3.color.gray8, "primary3", "#48AFF0"),
          renderColor(bg.primary4.color.white, "primary4", "#2B95D6"),
          renderColor(bg.primary5.color.white, "primary5", "#137CBD")
        ),
        <.dl(
          Style.flexbox.fixed.margin.left16,
          renderHeader(
            c.success4,
            bg.success1,
            "Success: ",
            "Positive actions and results: add, success, complete, available…"
          ),
          renderColor(bg.success1.color.gray8, "success1", "#C7F2DF"),
          renderColor(bg.success2.color.gray8, "success2", "#76D5AD"),
          renderColor(bg.success3.color.gray8, "success3", "#3DCC91"),
          renderColor(bg.success4.color.white, "success4", "#15B371"),
          renderColor(bg.success5.color.white, "success5", "#0F9960")
        )
      ),
      <.div(
        Style.flexbox.flex.padding.top16.padding.bottom12,
        <.dl(
          Style.flexbox.fixed,
          renderHeader(
            c.warning4,
            bg.warning1,
            "Warning: ",
            "Warning actions and results. Also used for Demo related things."
          ),
          renderColor(bg.warning1.color.gray8, "warning1", "#FAECDE"),
          renderColor(bg.warning2.color.gray8, "warning2", "#EFBE8E"),
          renderColor(bg.warning3.color.gray8, "warning3", "#FFB366"),
          renderColor(bg.warning4.color.white, "warning4", "#F29D49"),
          renderColor(bg.warning5.color.white, "warning5", "#D9822B")
        ),
        <.dl(
          Style.flexbox.fixed.margin.left16,
          renderHeader(
            c.danger4,
            bg.danger1,
            "Danger: ",
            "Negative actions and results: remove, error, failed, danger, etc…"
          ),
          renderColor(bg.danger1.color.gray8, "danger1", "#FCF0F0"),
          renderColor(bg.danger2.color.gray8, "danger2", "#F5AAAA"),
          renderColor(bg.danger3.color.gray8, "danger3", "#FF7373"),
          renderColor(bg.danger4.color.white, "danger4", "#F55656"),
          renderColor(bg.danger5.color.white, "danger5", "#DB3737")
        )
      ),
      Markdown(
        s"""
           |**Note that the 4th shade is the main one**, which should be used most of the time.
           |
           |# Usage notes
           |
           |>**Use color judiciously for communication.** The power of color to call attention to important information is heightened when used sparingly. For example, a red triangle that warns people of a critical problem becomes less effective when red is used elsewhere in an app for noncritical reasons.
           |>
           |>Source: [Color - Visual Design - iOS Human Interface Guidelines (developer.apple.com)](https://developer.apple.com/ios/human-interface-guidelines/visual-design/color/)
           |
           |**Complex color combinations are usually offered by components.** For example, the `Button` component has a `color` prop, which includes changes in `color`, `backgroundColor` and `shadow.border`.
        """.stripMargin
      )()
    )
  }
}
