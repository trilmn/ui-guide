package anduin.guide.pages.style
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import anduin.guide.components._
import japgolly.scalajs.react.vdom.html_<^._

object PageFixedLineHeight {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header(title = "Why fixed line height?")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |In short, fixed line height gives us better control in building 
          |layout and aligning things, which is a main focus of building web 
          |apps.
          |
          |Let's have an example, in which we need to vertically centering an
          |icon (whose height is 16 pixels) and a body, which has a title 
          |and a description.
          |
          |The HTML should look like below:
          |
          |```scala
          |<.div(
          |  Style.flexbox.flex.flexbox.itemsCenter,
          |  <.div(Style.margin.right8, Icon(...)()), // icon
          |  <.div(<.h3("Title"), <.p("Description")) // body
          |)
          |```
          |
          |Now, considering the height of body:
          |
          |**With a dynamic line height,** like 1.5, we would have:
          |
          |- Description's height is 21 pixels (1.5 ✕ 14)
          |- Title's height is 30 pixels (1.5 ✕ 20)
          |
          |→ Body's height is 51 pixels.
          |
          |**With fixed line heights,** we would have:
          |
          |- Description's height is 20 pixels (base line height)
          |- Title's height is 24 pixels (24-pixel line height for 20-pixel font)
          |- Description should also have a 4-pixel top margin
          |
          |→ Body's height is 48 pixels.
          |
          |The result of centering a 16 pixel icon with 48 pixel object is obviously better than with a 51 pixel object, both visually and in code.
          |
          |In other words, fixed line heights give us a major benefit: most elements, by default, would be wrapped in a box with a reasonable and predictable height (like 48 pixels), instead of a random, oddly one (like 51 pixels).
        """.stripMargin
      )()
    )
  }
}
