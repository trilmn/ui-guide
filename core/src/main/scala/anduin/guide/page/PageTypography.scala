package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTypography {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Typography",
          description = """
              |Typography structures and emphasizes content in a meaningful way.
            """.stripMargin
        )()
      ),
      Markdown(
        """
          |# Font Family
          |
          |We use [system fonts](https://css-tricks.com/snippets/css/system-font-stack/) by default. This was set at global level (i.e: in `body` tag) so most of the time you won't need to worry about font family at all.
          |
          |The only exception being when you want to display code then you should use mono-space fonts, available at `Style.fontFamily.mono`:
        """.stripMargin)(),
      ExampleRich(
        Source.annotate(
          /*>*/<.div(
            <.span("Your secret code: "),
            <.code(
              Style.backgroundColor.gray2.padding.ver4.padding.hor8,
              /*<*/Style.fontFamily.mono/*>*/,
              "746123"))/*<*/
        )
      )(),
      Markdown(
        s"""
          |# Font Size
          |Our base font size is 14 pixels, which was also set at global, so most of the text on our UI should have this size by default.
          |
          |That being said, using different font sizes to structure and emphasize content is quite common. In those cases, you should choose one from `Style.fontSize`:
          |
          |```text
          |Style.fontSize.px<value>
          |​
          |  value: 12, 14, 16, 20, 26, 32, 48
          |```
          |
          |**Note that you might need to update `line-height` too.** Because our default `line-height` is 20 pixels, it might not be suitable for font sizes that are larger than 16 pixels. See "Line Height" section below to learn more.
          |
          |**Also, try to see if you could use Heading tag first.** All Heading tags have proper `fontSize` and `lineHeight` predefined for you. Only when they are not suitable for your use cases (e.g: you are styling the label of an information) then you should use `Style.fontSize` manually.
          |
          |# Line Height
          |
          |We use a set of [fixed line height](${ctl.urlFor(Main.FixedLineHeight()).value}), with the default value being 20 pixels. The default value should work for most use cases, including paragraph (since the default font size is 14 pixels) or short string of text on UI (e.g: label, information, value).
          |
          |However, there are still cases where we want to change the line height, especially when we have a large font size as mentioned in the Font Size section. These should be done by choosing a value under `Style.lineHeight`:
          |
          |```text
          |Style.lineHeight.<value>
          |​
          |  value: px16, px20, px24, px40, px64
          |         ratio1p5 (copy)
          |```
          |
          |In general:
          |
          |**Use `ratio1p5` for paragraph.** This sets a dynamic 1.5 value for line height, which makes it easy to read long text that has several sentences or lines. This works no matter the font size. The `copy` value is simply alias for `ratio1p5`.
          |
          |**Use `pxX` for other elements,** including headings, titles, labels or values (information). This sets a fixed value in pixel. The following table suggests which value should be used with which font size:
          |
          || Font Size   | 12 | 14 | 16 | 20 | 26 | 32 | 48 |
          ||-------------|----|----|----|----|----|----|----|
          || Line Height | 20 | 20 | 24 | 24 | 40 | 40 | 64 |
          |
        """.stripMargin)()
    )
  }
}
