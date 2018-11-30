package anduin.guide.pages.components.well

import anduin.guide.components._
import anduin.component.toggle.Toggle
import anduin.component.well.Well
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

object PageWell {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Well", obj = Some(Well))()
      ),
      Markdown(
        s"""
           |Well is a container like [Card][1] but with stronger visual
           |attention:
           |
           |[1]: ${ctl.urlFor(Pages.Card()).value}
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Well(color = Well.ColorBlue)("Hello World!")
      }))(),
      Markdown(
        """
          |# Content
          |
          |Although Well accepts `VdomNode*` as children, it works best with
          |only one or two lines of text. When having more than one line,
          |Well's icon will be aligned with the first line:
          |""".stripMargin
      )(),
      ExampleSimple()(
        Well()(
          "Accumsan ornare hendrerit parturient leo nibh tincidunt " +
            "ullamcorper. Condimentum mus hac risus eleifend a elit mi."
        )
      ),
      Markdown(
        """
          |## Title
          |
          |When the content takes time to read (e.g. has several lines) it's
          |recommended to use `title: String` prop to help user quickly get
          |the content's topic:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        val well = Well(
          title = "Hello World"
        )("""
            |Accumsan ornare hendrerit parturient leo nibh tincidunt
            |ullamcorper. Condimentum mus hac risus eleifend a elit mi.
        """.stripMargin)
        <.div(well)
      }))(),
      Markdown(
        s"""
           |## Close
           |
           |**Well supports a common use case where it can be dismissed.**
           |However, it is only appearance support (i.e. Well is state-less), so
           |the engineer still needs to:
           |
           |1. Conditionally render the Well
           |2. Implement the callback to close it via `close` prop
           |
           |```scala
           |private case class State(didRead: Boolean)
           |
           |private def render(state: State): VdomNode = {
           |  if (state.didRead)
           |    Well(close = scope.setState(State(didRead = true)))(...)
           |  else
           |    EmptyVdom
           |}
           |```
           |
           |That boolean-toggling logic can be provided by the [Toggle][1]
           |component:
           |
           |[1]: ${ctl.urlFor(Pages.Toggle()).value}
           |
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        // In practice this render should be an object's method
        val render = (toggle: Callback, isExpanded: Boolean) => {
          if (isExpanded) Well(close = toggle)("Hello World")
          else EmptyVdom
        }
        val collapse = Toggle(render, isExpanded = true)()
        <.div(collapse)
      }))(),
      Markdown(
        """
          |# Color
          |
          |Well supports 5 main colors via the `color` prop. This also changes
          |the Well's icon to reflect the same meaning with the color:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.bottom8
        <.div(
          <.div(Well(color = Well.ColorGray)("Gray"), margin),
          <.div(Well(color = Well.ColorBlue)("Blue"), margin),
          <.div(Well(color = Well.ColorGreen)("Green"), margin),
          <.div(Well(color = Well.ColorOrange)("Orange"), margin),
          <.div(Well(color = Well.ColorRed)("Red"))
        )
      }))()
    )
  }
}
