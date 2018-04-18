package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.component.icon.IconAcl
import anduin.guide.component.{Example, Markdown}
import anduin.mcro.Source
import anduin.style.Style

// format: off
object PageButton {
  val render: VdomElement = {
    <.div(
      // Content
      <.section(
        Markdown("# Content")(),
        // Content
        <.section(
          Markdown("""
              | ## Icon
              | WIP
            """.stripMargin)(),
          Example(
            Source.annotate({
              /*>*/val icon = IconAcl(name = IconAcl.NameLightBolt)()
              <.div(
                Style.flexbox.flex,
                <.div(/*<*/Button()(icon)/*>*/, Style.margin.right16),
                <.div(/*<*/Button()(<.span(Style.margin.right8, icon), "Text"/*>*/), Style.margin.right16),
                <.div(/*<*/Button()("Text", <.span(Style.margin.left8, icon))/*>*/)
              )/*<*/
            })
          )()
        )
      ),
      // Behavior
      <.section(
        Markdown("# Behavior")(),
        // tpe
        <.section(
          Markdown("""
              | ## `tpe`
              | Try hover and right click:
            """.stripMargin)(),
          Example(
            Source.annotate(
              /*>*/<.div(
                Style.flexbox.flex,
                <.div(/*<*/Button()("Button")/*>*/, Style.margin.right16),
                <.div(/*<*/Button(tpe = Button.TpeLink)("Link")/*>*/)
              )/*<*/
            )
          )()
        ),
        // isDisabled
        <.section(
          Markdown("""
              | ## `isDisabled`
              | WIP
            """.stripMargin)(),
          Example(
            Source.annotate(
              /*>*/<.div(
                Style.flexbox.flex,
                <.div(/*<*/Button(isDisabled = true)("Default")/*>*/, Style.margin.right16),
                <.div(/*<*/Button(isDisabled = true, color = Button.ColorPrimary)("Primary")/*>*/, Style.margin.right16),
                <.div(/*<*/Button(isDisabled = true)(IconAcl(name = IconAcl.NameLayer)())/*>*/, Style.margin.right16),
                <.div(/*<*/Button(isDisabled = true, isMinimal = true)("Minimal")()/*>*/)
              )/*<*/
            )
          )()
        )
      ),
      // Appearance
      <.section(
        Markdown("# Appearance")(),
        // color
        <.section(
          Markdown("""
              | ## `color`
              | WIP
            """.stripMargin)(),
          Example(
            Source.annotate(
              <.div(
                Style.flexbox.flex,
                <.div(Button()("Default")),
                <.div(Button(color = Button.ColorPrimary)("Primary"),
                      Style.margin.left16),
                <.div(Button(color = Button.ColorSuccess)("Success"),
                      Style.margin.left16),
                <.div(Button(color = Button.ColorWarning)("Warning"),
                      Style.margin.left16),
                <.div(Button(color = Button.ColorDanger)("Danger"),
                      Style.margin.left16)
              )
            )
          )()
        ),
        // size
        <.section(
          Markdown("""
              | ## `size`
              | WIP
            """.stripMargin)(),
          Example(
            Source.annotate(
              <.div(
                Style.flexbox.flex,
                <.div(Button(size = Button.SizeSmall)("Small"),
                      Style.margin.right16),
                <.div(Button()("Medium"), Style.margin.right16),
                <.div(Button(size = Button.SizeLarge)("Large"))
              )
            )
          )()
        )
      )
    )
  }
}
