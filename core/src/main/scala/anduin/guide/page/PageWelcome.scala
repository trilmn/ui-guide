package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.mcro.Source
import anduin.style.Style

object PageWelcome {
  val render: VdomElement = {
    val (text1, node1) = Source.annotate(
      <.div(
        Style.flexbox.flex,
        <.div(
          Style.margin.left12,
          Button()("Action")
        ),
        <.div(
          Style.margin.left12,
          Button(color = Button.ColorPrimary)("Submit")
        )
      )
    )
    <.div(
      Style.whiteSpace.pre,
      "Welcome",
      <.div(text1),
      <.div(node1)
    )
  }
}
