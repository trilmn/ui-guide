package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.container.Table
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageTable {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Table")()
      ),
      Markdown("""
          |# Snippet
          |
          |```scala
          |case class Table[T](
          |  rows: List[T],
          |  columns: List[Table.Column[T]]
          |)
          |
          |object Table {
          |  case class Column[T](
          |    title: String,
          |    render: T => VdomNode
          |  )
          |}
          |```
          |
          |Example:
        """.stripMargin)(),
      ExampleRich(
        Source.annotate({
          case class Doc(title: String, author: String, org: String)

          Table[Doc](
            rows = List(
              Doc("Term Sheet", "Virginia Lee", "Gunderson"),
              Doc("IRA", "Vincent Harris", "Gunderson")
            ),
            columns = List(
              Table.Column(title = "Title", render = doc => doc.title),
              Table.Column(title = "Author", render = doc => s"${doc.author} (${doc.org})")
            )
          )()
        })
      )(),
      Markdown("""
          |# Usage
          |
          |```scala
          |import anduin...Trxn
          |
          |object TrxnDashboard {
          |
          |  def renderCompany(trxn: Trxn): VdomNode = ???
          |  def renderType(trxn: Trxn): VdomNode = ???
          |  def renderStatus(trxn: Trxn): VdomNode = ???
          |  /* ... */
          |
          |  def render(trxnList: List[Trxn]): VdomElement = {
          |    Table[Trxn](
          |      rows = trxnList,
          |      columns = List(
          |        Table.Column("Company", renderCompany),
          |        Table.Column("Type", renderType),
          |        Table.Column("Status", renderStatus)
          |        /* ... */
          |      )
          |    )()
          |  }
          |}
          |```
          |
          |## Sort
          |
          """.stripMargin)(),
      ExampleRich(
        Source.annotate({
          case class Row(s: String, i1: Int, i2: Int)
          Table[Row](
            rows = List(
              Row("a", 3, 2),
              Row("d", 1, 3),
              Row("c", 2, 1),
              Row("b", 4, 4)
            ),
            columns = List(
              {
                val sort = Table.Sort[Row](asc = _.s < _.s, desc = _.s > _.s)
                Table.Column("String", _.s, Option(sort))
              }, {
                val sort = Table.Sort[Row](asc = _.i1 < _.i1, desc = _.i1 > _.i1)
                Table.Column("Int 1", _.i1, Option(sort))
              }, {
                // for demo purpose, let's say this column is not sortable
                Table.Column("Int 2", _.i2)
              }, {
                val getSum = (row: Row) => row.i1 + row.i2
                val sort = Table.Sort[Row](asc = getSum(_) < getSum(_), desc = getSum(_) > getSum(_))
                Table.Column[Row]("Sum", getSum(_).toString, Option(sort))
              }
            ),
            sortColumn = Option(3)
          )()
        })
      )(),
      Markdown("""
          |
          |
          |## Span
          |
          |## Filter
        """.stripMargin)()
    )
  }
}
