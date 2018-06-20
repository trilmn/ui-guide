package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.container.Table
import anduin.component.text.Tag
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object Sample {
  case class Member(
    id: Int,
    name: String,
    level: Int,
    point: Double,
    testA: Boolean,
    testB: Boolean
  )
  private val MemberTable = (new Table[Member])()
  private val random = new util.Random
  private def rInt = 1 + random.nextInt(10)
  private def rDb = math.round(random.nextDouble() * 100) / 10.0
  private def rBoo = random.nextBoolean()
  private val rows = List(
    Member(0, "Vincent", rInt, rDb, rBoo, rBoo),
    Member(1, "Harry", rInt, rDb, rBoo, rBoo),
    Member(2, "Ada", rInt, rDb, rBoo, rBoo),
    Member(3, "Nancy", rInt, rDb, rBoo, rBoo),
    Member(4, "John", rInt, rDb, rBoo, rBoo),
    Member(5, "Virginia", rInt, rDb, rBoo, rBoo)
  )
  def renderPoint: Member => Table.Cell = (member: Member) => {
    val color = if (member.point > 5) Tag.ColorSuccess else Tag.ColorWhite
    val content = Tag(color = color)(member.point)
    Table.Cell(content)
  }
  def renderTotal: Member => Table.Cell = (member: Member) => {
    val total = member.point * member.level
    Table.Cell(math.round(total * 100) / 100.0)
  }
  def renderNameId: Member => Table.Cell = (member: Member) => {
    val content = <.div(
      <.p(member.name),
      <.p(Style.color.gray7, "Id: ", member.id)
    )
    Table.Cell(content)
  }
  private val columns = List(
    Table.Column[Member]("Id", member => Table.Cell(member.id)),
    Table.Column[Member]("Name", member => Table.Cell(member.name)),
    Table.Column[Member]("Level", member => Table.Cell(member.level)),
    Table.Column[Member]("Point", renderPoint),
    Table.Column[Member]("Total", renderTotal)
  )
  private val getKey = (e: Member) => e.id.toString
  val BaseTable = MemberTable(
    rows = rows,
    getKey = getKey,
    columns = columns
  )
}

object PageTable {

  private case class Member(id: Int, name: String, email: String)
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
          |Table is a high level component, so you don't need to explicitly render each Row or Cell. Instead, you provide the data and Table will take care of the rendering for you.
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
          |Therefore, you need to provide a `getKey` function that receives a row data and returns a String to be used as key for that row:
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
          |# Appearance
          |
          |## Style
          |
          """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val rows = Sample.BaseTable.rows.take(3)
        val table1 = Sample.BaseTable.copy(
          rows = rows, /*<*/
          style = Table.StyleFull /*>*/
        )()
        val table2 = Sample.BaseTable.copy(
          rows = rows, /*<*/
          style = Table.StyleMinimal /*>*/
        )()
        val p = (content: String) => <.p(Style.lineHeight.px40, content)
        <.div(p("Full (default):"), table1, p("Minimal:"), table2) /*<*/
      }))(),
      Markdown(
        """
          |
          |## (Vertical) Align
          |
          |""".stripMargin
      )(),
      ExampleSimple()(
        Sample.BaseTable.copy(
          columns = List(
            Table.Column("Name", Sample.renderNameId),
            Sample.BaseTable.columns(2),
            Sample.BaseTable.columns(3),
            Sample.BaseTable.columns(4)
          ),
          rows = Sample.BaseTable.rows.take(3)
        )()
      ),
      Markdown(
        """
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val renderLevel = (member: Sample.Member) => {
          val content = member.level
          if (member.id == 1) {
            Table.Cell(
              Tag(color = Tag.ColorDanger, isSolid = true)(content), /*<*/
              align = Table.AlignBottom /*>*/
            )
          } else {
            Table.Cell(content)
          }
        }
        val table = Sample.BaseTable.copy(
          columns = List(
            Table.Column("Name", Sample.renderNameId), /*<*/
            Table.Column("Level", renderLevel), /*>*/
            Sample.BaseTable.columns(3),
            Sample.BaseTable.columns(4)
          ),
          rows = Sample.BaseTable.rows.take(3), /*<*/
          align = Table.AlignTop /*>*/
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |
          |## Width
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          columns = List(
            Sample.BaseTable.columns.head, /*<*/
            Table.Column(
              head = "Name",
              render = member => Table.Cell(member.name),
              width = "50%"
            ), /*>*/
            Sample.BaseTable.columns(2),
            Sample.BaseTable.columns(3),
            Sample.BaseTable.columns(4)
          )
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |
          |## Sticky Head
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        val table = Sample.BaseTable.copy(
          headIsSticky = true
        )()
        <.div(table)
      }))(),
      Markdown(
        """
          |
          |# Sort
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          columns = List(
            Sample.BaseTable.columns.head, /*<*/
            Sample.BaseTable
              .columns(1)
              .copy(sortByString = Some(_.name)), /*>*/
            Sample.BaseTable
              .columns(2)
              .copy(sortByDouble = Some(_.level.toDouble)), /*<*/
            Sample.BaseTable.columns(3),
            Sample.BaseTable
              .columns(4)
              .copy(sortByDouble = Some(m => m.point * m.level)) /*>*/
          ),
          sortColumn = Some(1)
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |# Content
          |
          |## Span
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val render = (b: Boolean) => {
          val (color, text) =
            if (b) (Tag.ColorSuccess, "Pass")
            else (Tag.ColorDanger, "Fail")
          val tag = Tag(color = color)(text)
          <.div(Style.flexbox.flex.flexbox.justifyCenter, tag)
        } /*<*/
        val isSame = (m: Sample.Member) => m.testA == m.testB
        val renderTestA = (member: Sample.Member) => {
          val colSpan = if (isSame(member)) 2 else 1
          Table.Cell(render(member.testA), colSpan = colSpan)
        }
        val renderTestB = (member: Sample.Member) => {
          if (isSame(member)) Table.Cell(isNone = true)
          else Table.Cell(render(member.testB))
        } /*>*/
        val center = Style.textAlign.center
        val table = Sample.BaseTable.copy(
          columns = List(
            Sample.BaseTable.columns.head,
            Sample.BaseTable.columns(1), /*<*/
            Table.Column(<.p(center, "Test A"), renderTestA),
            Table.Column(<.p(center, "Test B"), renderTestB) /*>*/
          )
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |
          |## Footer
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          rows = Sample.BaseTable.rows.take(3), /*<*/
          footer = <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            Button(color = ButtonStyle.ColorPrimary)("Invite Member"),
            <.p(Style.margin.left16, "New member will see all history.")
          ) /*>*/
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |
        """.stripMargin
      )()
    )
  }
}
