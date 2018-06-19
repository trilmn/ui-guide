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
          |Table is a high level component, so you don't need to explicitly render each Row or Cell. Instead, you provide the data and Table will take care of the actual rendering for you.
          |
          |More specific, to have the simplest Table, you need to:
          |1. [Init](#init) Table with the type of your data
          |2. Provide your data via [`rows`](#rows)
          |3. Describe how they should be identified via [`getKey`](#getkey)
          |4. Describe how they should be rendered via [`columns`](#columns)
          |
          |## Init
          |
          |Table is a [Generic Component](https://docs.scala-lang.org/tour/generic-classen.html), therefore you must first init it with the type of your data (rows):
          |
          |```scala
          |// In practice, this type/model is often defined already and
          |// you can use just that. You should rarely need to define a
          |// new type/model to use Table
          |case class Member(id: Int, name: String, email: String)
          |
          |// From now on you will use this MemberTable, like in your
          |// `render` method
          |private val MemberTable = (new Table[Member])()
          |```
          |
          |**Note that this initialization should happen only once for each type.** We suggest to define it as a `val` inside an `object`. Defining in a `def` (like your `render` method) should be avoided.
          |
          |If you are not sure what is the type of your Table, just think what is the list of thing that you want to render. In this case, that is a list of "Member".
          |
          |## Rows
          |
          |Next, provide your data via the `rows` prop. Your data should be a List of your initialized type in the above step (e.g. `Member`).
          |
          |```scala
          |MemberTable(
          |  // In practice, this list of data should already defined
          |  // somewhere else (e.g. `props.members`)
          |  rows = List(
          |    Member(0, "Vincent", "vincent@anduin.co"),
          |    Member(1, "Ada", "ada@anduin.co")
          |  ),
          |  /* ... */
          |)()
          |```
          |
          |Each item in the list will later be rendered as a row.
          |
          |## getKey
          |
          |Since these rows will be rendered dynamically, they need to have keys in order for [React's reconciliation](https://reactjs.org/docs/reconciliation.html#keys) to work properly.
          |
          |To have that, you need to provide a `getKey` function that receives a row data and returns a String to be used as key for that row:
          |
          |```scala
          |MemberTable(
          |  rows = /* ... */,
          |  getKey = _.id.toString,
          |  // or in full style:
          |  // getKey = (member: Member) => member.id.toString
          |)()
          |```
          |
          |## Columns
          |
          |Finally, you describe how should your data be rendered via the `columns` prop, which should be a list of `Table.Column`.
          |
          |Each Table.Column represents a column in your table and requires at least 2 attributes:
          |1. `head`: a VdomNode that will be used as column's head (`thead`, to be exact).
          |2. `render`: a function that receives a row and return the rendered content of that row for this column (`td`, technically). This content must be wrapped inside `Table.Cell`.
          |
          |For example:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val table = MemberTable(
          rows = List(
            Member(0, "Vincent", "vincent@anduin.co"),
            Member(1, "Ada", "ada@anduin.co")
          ),
          getKey = _.id.toString,
          columns = List(
            Table.Column(
              head = "name",
              render = member => Table.Cell(member.name)
            ),
            Table.Column(
              head = "email",
              render = member => Table.Cell(member.email)
            )
          )
        )()
        <.div(table)
      }))(),
      Markdown(
        """
          |More options of Table.Column and Table.Cell will be described in this guide later.
          |
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
