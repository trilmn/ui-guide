package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.style.Style
import anduin.guide.SourceMacro

object PageButton {

  private val source = SourceMacro.exampleSource
  println(source)

  val render: VdomElement = {
    <.div(
      Style.flexbox.flex,
      <.div(
        ^.cls := "guide",
        <.pre(
          <.code(
            ^.cls := "language-scala",
            "Button()(\"Action\")"
          )
        ),
        <.p(
          "Dimension: Choose raised or flat depending on the container it will be in and how many z-space layers you have on screen. There should not be many layers of objects on the screen."
        )
      ),
      <.div(
        Style.border.left.borderWidth.px2.borderColor.gray2,
        Style.margin.left24,
        <.div(
          ^.width := "320px",
          Style.padding.hor24,
          // EXAMPLE:START
          Button()("Action")
          // EXAMPLE:END
        )
      )
    )
  }
}
