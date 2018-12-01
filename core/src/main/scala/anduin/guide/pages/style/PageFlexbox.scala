package anduin.guide.pages.style

import anduin.guide.components._
import anduin.component.button.Button
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageFlexbox {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header(title = "Flexbox")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Flexbox helps lay things out, align them, and distribute space in 
          |between.
          |
          |# Overview
          |
          |```text
          |Style.flexBox.<display|direction|alignment|children>
          |
          |  display:      flex, inlineFlex
          |
          |  direction:    row, column, wrap
          |
          |  alignment:    items..., justify...
          |
          |  children:     auto, fixed, none,
          |                grow..., shrink..., order...
          |```
          |
          |# Learn Flexbox
          |
          |[**Flexbox is a web standard**](https://www.w3.org/TR/css-flexbox-1/). Understand how it works is essential in building layout not only for a specific system but in the world of web in general.
          |
          |**Unlike other CSS properties, Flexbox is quite complex.**  Fortunately, it's been around for a while, thus there are many resources to learn now. If unsure, it is recommended to start with  [MDN's Basic concepts of flexbox](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Flexible_Box_Layout/Basic_Concepts_of_Flexbox).
          |
          |**When learning, it's always good to try different perspectives.** Especially if you are having trouble understanding complex concepts like Flexbox. Some other good resources to learn Flexbox could be:
          |
          |- [A Complete Guide to Flexbox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/), the famous article from CSS-Tricks
          |- [Layout with Flexbox](https://facebook.github.io/react-native/docs/flexbox.html) from React Native
          |- [An animated guide to Flexbox](https://medium.freecodecamp.org/an-animated-guide-to-flexbox-d280cf6afc35) from Scott Domes
          |- Or play a game with [Frogs](http://flexboxfroggy.com/) or [Tower](http://www.flexboxdefense.com/)
          |- Or even [the specification](https://www.w3.org/TR/css-flexbox-1/) itself
          |
          |# Sizing children
          |
          |[Controlling the size of a flex child could be tricky](https://css-tricks.com/flex-grow-is-weird/) via the `flex` property, or `flex-grow`, `flex-shrink` and especially `flex-basis`. Fortunately, there are some useful `flex` values that would be enough to cover most use cases. We made these values available under `Style.flexbox` with the following names:
          |
          |- `auto`: should grow or shrink its extra space (the space left after laying its content out).
          |- `fixed`: should grow or shrink the whole item (without taking its content into consideration).
          |- `none`: should not grow or shrink at all, just use its content's size.
          |
          |The example below could help understanding the difference between `auto (flex-basis: auto)` and `fixed (flex-basis: 0)`:
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.textAlign.center,
            <.div(Style.flexbox.flex.margin.bottom4,
              <.div(/*<*/"short", " none", Style.flexbox.none/*>*/, Style.margin.right4, Style.backgroundColor.gray2),
              <.div(/*<*/"loooooooooong", " none", Style.flexbox.none/*>*/, Style.backgroundColor.gray2)),
            <.div(Style.flexbox.flex.margin.bottom4,
              <.div(/*<*/"short", " none", Style.flexbox.none/*>*/, Style.margin.right4, Style.backgroundColor.gray2),
              <.div(/*<*/"short", " fixed", Style.flexbox.fixed/*>*/, Style.margin.right4, Style.backgroundColor.gray2),
              <.div(/*<*/"loooooooooong", " fixed", Style.flexbox.fixed/*>*/, Style.backgroundColor.gray2)),
            <.div(Style.flexbox.flex,
              <.div(/*<*/"short", " none", Style.flexbox.none/*>*/, Style.margin.right4, Style.backgroundColor.gray2),
              <.div(/*<*/"short", " auto", Style.flexbox.auto/*>*/, Style.margin.right4, Style.backgroundColor.gray2),
              <.div(/*<*/"loooooooooong", " auto", Style.flexbox.auto/*>*/, Style.backgroundColor.gray2))
          )
        )/*<*/
        // format: on
      )(),
      Markdown(
        """
          |- In the second row, the widths of "fixed" items are the same regardless of their content.
          |- Meanwhile, in the third row, the widths of "auto" items depend on their content's width.
          |
          |The following table shows a quick comparison between those 3 values:
          |
          ||     | `auto` | `fixed` | `none` |
          || --- | ------ | ------- | ------ |
          || Will it grow or shrink?  | Yes | Yes | No |
          || Does its content matter? | Yes | No  | No |
          || Raw value | `1 1 auto` | `1 1 0` | `0 0 auto` |
          |
          |The below graphic from W3C could also help understanding the difference between `fixed / flex-basis: 0` and `auto / flex-basis: auto`
          |
          |![relative vs absolute flex](https://www.w3.org/TR/css-flexbox-1/images/rel-vs-abs-flex.svg)
          |
          |# Usage Notes
          |
          |## `overflow` for `fixed` and `auto`
          |
          |Although `fixed` and `auto` both have `flex-shrink: 1`, there are cases they will not be resized to smaller than the size of their children.
          |
          |This is because by default Flexbox try to not break the unbreakable contents, like long words or text with `noWrap` or another Flexbox nested inside.
          |
          |In these cases, most of the time you will want an `overflow: hidden` on these flex items to let them free to shrink.
          """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(Style.whiteSpace.noWrap,
            <.div(Style.flexbox.flex,
              /*<*/<.div(Style.flexbox.fixed, "1111111111111111111111111111111111111111111"),
              <.div(Style.flexbox.fixed, "2222222222222222222222222222222222222222222")/*>*/),
            <.div(Style.flexbox.flex,
              /*<*/<.div(Style.flexbox.fixed.overflow.hidden, "1111111111111111111111111111111111111111111"),
              <.div(Style.flexbox.fixed.overflow.hidden, "2222222222222222222222222222222222222222222")/*>*/)
          )/*<*/
        )
        // format: on
      )(),
      Markdown(
        """
          |## Don't overuse
          |
          |Flexbox is too powerful that sometime it's easy to build layouts that looks nice but semantically incorrect and/or unnecessarily complex.
          |
          |Although quite useful, Flexbox should not be the first thought. We should only use it in complex cases, after considered other standard layout techniques:
          |
          |- If the children are all text and/or can form a sentence, try inline first.
          |- If they should be placed vertically (on top of each other), try using block elements first:
          """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate(
          /*>*/<.div(
            Style.flexbox.flex.flexbox.justifyAround,/*<*/

            // Bad: Unnecessary flex column
            <.div(Style.flexbox.flex.flexbox.column,
                  <.span("First"),
                  <.p("Second")),

            // Good: Use block elements instead
            <.div(<.p("First"), <.p("Second"))/*>*/
          )/*<*/
        )
        // format: on
      )(),
      Markdown(
        """
          |- Also considering the native `table` tag before using Flexbox. The flexibility of `table` is quite powerful but often underestimated. In fact, the way HTML's `table` computes the widths of its columns is very similar to `flex: 1 0 auto`.
          |
          |That being said, there are cases that seem like simple at first sight, but could be quite complex and should use Flexbox. One example is aligning icon and text, which can be found at Components/Icon page.
          |
          |## Keep it simple
          |
          |It's case by case, but in general the following ones could be simplified:
          |
          |- Nested flex
          |- Flex with only one child
          |
          |Example:
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val image = <.div(Style.backgroundColor.primary4, ^.width := "40px", ^.height := "40px")
          val info = <.div(Style.margin.left8, <.p("Title"), <.p("Description"))
          val button = <.div(Style.margin.left8, Button()("Action"))
          val flex = Style.flexbox.flex.flexbox.itemsCenter
          <.div(/*<*/

            // Bad: Unnecessary nested flex
            <.div(flex, /*>*/Style.margin.bottom16,/*<*/
                  <.div(flex, image, info),
                  <.div(flex, button)),

            // Good: Single level of flex
            <.div(flex, image, info, button)/*>*/
          )/*<*/
        })
        // format: on
      )()
    )
  }
}
