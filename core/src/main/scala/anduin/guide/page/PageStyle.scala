package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style

object PageStyle {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Style",
          description =
            """Style is the lowest level of UI Engineering, provides basic
              |visual characteristics of a platform.""".stripMargin
        )()
      ),
      Markdown(s"""
        |# Global
        |
        |**`Style` is a global package.** It should be available anywhere in
        |our apps.
        |
        |```scala
        |import anduin.style.Style
        |​
        |<.div(Style.property.value)
        |```
        |
        |# Simple
        |
        |**`Style` defines simple, single-purpose visual properties,** like
        |`color` or `margin`. `Style` does not cover combination of
        |properties, so don't expect to find concrete elements like `Style
        |.table` or `Style.heading`. They should be covered in
        |[Components][1] instead.
        |
        |[1]: ${ctl.urlFor(Pages.Component()).value}
        |
        |# Prefix
        |
        |**Numeric values are often prefixed with unit,** like `Style
        |.fontSize.px20` or `Style.width.percent100`. Moreover, the values
        |themselves are often literal values, such as `Style.padding.left16`
        |means a 16-pixel left padding.
        |
        |# Chain
        |
        |**`Style` can (and should be) chained.** Multiple attributes can be
        |defined together under one or several `Style`. The order of
        |attributes in `Style` does not matter, so it is good to group
        |related ones under one `Style`.
        |
        |```scala
        |/*>*/<.div(
        |  /*<*/Style.background.primary4.color.white, // Color
        |  Style.margin.left8.padding.all4, // Spacing/*>*/
        |  ...
        |)/*<*/
        |```
        |
        |# Classes
        |**Under the hood, `Style` simply adds corresponding classes.**
        |Although simple, this brings many benefits such as code completion,
        |easier maintenance and reducing of human mistakes.
        |
        |```text
        |Input (Scala):    <.p(Style.margin.left16, "text")
        |Output (HTML):    <p class="ml4">text</p>
        |```
        |
        |Moreover, `Style` can also be used with literal classes. This could
        |be helpful for integration with 3rd-party library, identification or
        | backward compatibility.
        |
        |```scala
        |<.div(
        |  /* This class should not be used for styling */
        |  ^.cls := "entity-header",
        |  Style.background.gray2,
        |  ...
        |)
        |```
        |
        |>::warning::**Avoid using literal classes for styling purpose**.
        |>
        |>Although the result might be the
        |>same at the implement time, it might be broken later since
        |>class names can be changed without safe migration.
        |>
        |>Also, the name of classes are not guaranteed to make sense. In
        |>the near future, it might be machine-generated for performance
        |>purpose.
          """.stripMargin)()
    )
  }
}
