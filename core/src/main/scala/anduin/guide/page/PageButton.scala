package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.style.Style

object PageButton {

  val render: VdomElement = {
    <.div(
      Style.flexbox.flex,
      <.div(
        ^.cls := "guide",
        <.div(
          <.div(
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
          Button()("Action")
        )
      )
    )
  }
}
