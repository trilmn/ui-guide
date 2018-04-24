package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.style.Style

object PageStyle {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Style",
          description =
            """Style is the lowest level of UI Engineering, provides basic visual
              |characteristics of a platform.""".stripMargin
        )()
      ),
      Markdown(
        """
            |## `Style` is a global package.
            |
            |It should be available anywhere in our apps.
            |```scala
            |import anduin.style.Style
            |​
            |<.div(Style.property.value)
            |```
            |
            |## `Style` defines simple, single-purpose visual properties.
            |
            |For example: `color` or `margin`. `Style` does not cover
            |combination of properties, so don't expect to find concrete
            |elements like `Style.table` or `Style.heading`. They should be
            |covered in Components instead.
            |
            |## Under the hood, `Style` simply adds corresponding classes.
            |
            |This was done using `TagMod`. Although simple, this brings many
            |benefits such as code completion, easier maintenance and reducing
            |of human mistakes.
            |
            |```text
            |Input (Scala):    <.p(Style.margin.left16, "text")
            |Output (HTML):    <p class="ml4">text</p>
            |```
            |
            |>**Avoid using literal classes for styling purpose**.
            |>
            |>Although the result might be the
            |>same at the implement time, it might be broken later since
            |>class names can be changed without safe migration.
            |>
            |>Also, the name of classes are not guaranteed to make sense. In
            |>the near future, it might be machine-generated for performance
            |>purpose.
            |
            |## Numeric values are often prefixed with unit
            |
            |For example, `Style.fontSize.px20` or `Style.width.percent100`.
            |Moreover, the values themselves are often literal values, such
            |as `Style.padding.left16` means a 16-pixel left padding.
            |
            |## `Style` can (and should be) chained.
            |
            |Multiple attributes can be defined together under one or several
            |`Style`. The order of attributes in `Style` does not matter,
            |so it is good to group related ones under one `Style`.
            |
            |```scala
            |/*>*/<.div(
            |  /*<*/Style.background.primary4.color.white,
            |  Style.margin.left8.padding.all4,/*>*/
            |  ...
            |)/*<*/
            |```
            |
            |## `Style` can be used together with literal classes.
            |
            |However, you should rarely see this since classes should never be
            |used for styling purpose, thus is rarely be used in general.
            |Current usages of literal classes in our system should mostly are
            |for 3rd-party library or identification.
            |
            |```scala
            |<.div(
            |  /* This class should not be used for styling */
            |  ^.cls := "entity-header",
            |  Style.background.gray2,
            |  ...
            |)
            |```
          """.stripMargin)()
    )
  }
}
