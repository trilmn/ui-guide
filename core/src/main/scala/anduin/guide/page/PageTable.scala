package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.container.Table
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTable {

  case class Member(id: Int, name: String, email: String)
  private val MemberTable = (new Table[Member])()

  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Table")()
      ),
      Markdown(
        """
          |# Basic
          |
          |Table is a high level component.
          |
          |Table is a [Generic Component](https://docs.scala-lang.org/tour/generic-classen.html), so it must be initialized with a specific type first:
          |
          |```scala
          |// It is likely that the type/model is already available from parent
          |case class Member(id: Int, name: String, email: String)
          |
          |// Sorry for the extra parentheses. Don't know how to make it works
          |// without them atm
          |private val MemberTable = (new Table[Member])()
          |```
          |
          |Note that the initialization should happen only once. For example, init it in a render method is incorrect.
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val table = MemberTable(
          rows = List(
            Member(0, "Vincent", "vincent@anduin.co"),
            Member(1, "Ada", "ada@anduin.co")
          ),
          columns = List(
            Table.Column(head = "name", render = member => Table.Cell(member.name)),
            Table.Column(head = "email", render = member => Table.Cell(member.email))
          ),
          getKey = _.id.toString
        )()
        <.div(table)
      }))(),
      Markdown(
        """
          |# Sort
          |
          |# Content
          |
          |## Span
          |
          |## Footer
          |
          |# Appearance
          |
          |## Style
          |
          |## (Vertical) Align
          |
          |## Width
          |
          |## Sticky Head
          |
        """.stripMargin
      )()
    )
  }
}
