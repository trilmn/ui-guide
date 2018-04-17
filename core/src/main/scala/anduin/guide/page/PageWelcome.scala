package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.mcro.Foo
import anduin.style.Style

object PageWelcome {
  val render: VdomElement = {
    val (text1, node1) = Foo.hello(
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
    val (text2, node2) = Foo.hello(
      <.div("lala2")
      /* end */
    )
    <.div(
      "Welcome",
      text1,
      node1,
      text2,
      node2
    )
  }
}
