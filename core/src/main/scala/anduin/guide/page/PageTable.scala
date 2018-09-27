package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.container.Table
import anduin.component.text.Tag
import anduin.guide.Router
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

  def render(ctl: Router.Ctl): VdomElement = {
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
          |Table is a high level component, so you don't need to explicitly
          |render each Row or Cell. Instead, you provide the data and Table
          |will take care of the rendering for you.
          |
          |More specific, to have the simplest Table, you need to:
          |1. [Init Table](#init) with the type of your data
          |2. [Provide your data](#rows) via `rows` prop
          |3. [Describe their identity](#getkey) via `getKey` prop
          |4. [Describe their render](#columns) via `columns` prop
          |
          |## Init
          |
          |Table is a [generic component][gc], therefore you must first init it
          |with the type of your data (rows):
          |
          |[gc]: https://docs.scala-lang.org/tour/generic-classes.html
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
          |If you are not sure what is the type of your Table, just think
          |what is the list of thing that you want to render. In this case,
          |that is a list of "Member".
          |
          |**Note that this initialization should happen only once for each
          |type.** Therefore it is recommended to define your Table type as a
          |`private val` in your `object`.
          |
          |Defining it in a `def` (like your `render` method) should be
          |avoided as it will create a new component each time, thus React
          |will unmount the previous instance and lose all of your state.
          |
          |## Rows
          |
          |Next, provide your data via the `rows` prop. Your data should be a
          | List of your initialized type in the above step (e.g. `Member`).
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
          |Since these rows will be rendered dynamically, they need to have
          |keys in order for [React's reconciliation](https://reactjs
          |.org/docs/reconciliation.html#keys) to work properly.
          |
          |Therefore, you need to provide a `getKey` function that receives a
          | row data and returns a String to be used as key for that row:
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
          |Finally, you describe how should your data be rendered via the
          |`columns` prop, which should be a list of `Table.Column`.
          |
          |Each Table.Column represents a column in your table and requires
          |at least 2 attributes:
          |1. `head`: a VdomNode that will be used as column's head (`thead`,
          |to be exact).
          |2. `render`: a function that receives a row and return the
          |rendered content of that row for this column (`td`, technically).
          |This content must be wrapped inside `Table.Cell`.
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
              head = "Name",
              render = member => Table.Cell(member.name)
            ),
            Table.Column(
              head = "Email",
              render = member => Table.Cell(member.email)
            )
          )
        )()
        <.div(table)
      }))(),
      Markdown(
        """
          |More options of Table.Column and Table.Cell will be described
          |further below.
          |
          |# Appearance
          |
          |## Style
          |
          |At the moment Table supports 2 styles: `StyleFull` and
          |`StyleMinimal`.
          |
          |**`StyleFull` has outer border** and thus should be used when
          |there are other content around the table. This is the default value.
          |
          |**`StyleMinimal` does not have outer border,** so it should be
          |used when your table can be separated clearly from nearby content
          |already.
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
          |## (Vertical) Align
          |
          |Like in HTML, if the heights of cells in one row are not the same,
          |then the content will be vertically aligned in the middle of the
          |row:
          |""".stripMargin
      )(),
      ExampleSimple("Default behaviour. Equivalent to `align = AlignMiddle`.")(
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
          |You can also align them to top or bottom via `align = AlignTop` or
          |`align = AlignBottom`, respectively. This can be set at 2
          |levels: for the whole Table and for one Cell.
          |
          |For example, in the below table, the content is aligned to top,
          |with an exception that the level of Harry is aligned to bottom
          |(the red one):
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
          |## Width
          |
          |Behind the scene, Table uses the native `table` tag. Therefore, we
          |have the benefit of [`table-layout: auto`][1] in which the widths
          |of columns are adjusted to fit the content automatically.
          |
          |[1]: https://developer.mozilla.org/en-US/docs/Web/CSS/table-layout#Syntax
          |
          |However, you can still manually define the width of any Table
          |.Column via the `width` prop. This is equivalent to the CSS's
          |`width` property, so you can have pixel or percentage here:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          rows = Sample.BaseTable.rows.take(3),
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
          |It's worth to note that due to the auto layout, setting a `width`
          |does not guarantee that column will get the exact value. The
          |browser only try its best to have that. Detail can be found in
          |[this CSS Tricks's article][1].
          |
          |[1]: https://css-tricks.com/almanac/properties/t/table-layout/
          |
          |Also, the width of a Table is always 100% of its parent.
          |
          |### Text wrapping in Head
          |
          |In order for [Sticky Head](#sticky-head) to work, the title of
          |Table's column is not wrapped, even if there is not enough space.
          |This could result in a table that has width larger than its parent:
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          rows = Sample.BaseTable.rows.take(3),
          columns = List(
            Sample.BaseTable.columns.head, /*<*/
            Table.Column(
              head = "Long long long long long long long long long long long " +
                "long long name",
              render = member => Table.Cell(member.name)
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
          |Therefore, it's best to keep your titles short and provide
          |sufficient `width` for the column if necessary.
          |
          |## Sticky Head
          |
          |Simply set `headIsSticky = true` and your Table's head will be
          |[sticky positioned][2] (i.e. Stick to the top):
          |
          |[2]: https://developer.mozilla.org/en-US/docs/Web/CSS/position#Sticky_positioning
          |
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Sample.BaseTable.copy(headIsSticky = true)()
      }))(),
      Markdown(
        s"""
          |# Sort
          |
          |Table supports sorting, but you need to tell it how to sort each
          |column via the `sortByString` or `sortByDouble` prop. They are
          |similar to [Scala's `sortBy`][1] or [Lodash's `sortBy`][2]:
          |
          |
          |[1]: https://alvinalexander.com/scala/how-to-sort-map-in-scala-key-value-sortby-sortwith
          |[2]: https://lodash.com/docs/#sortBy
          |
          |```scala
          |Table.Column(
          |  head = "Email",
          |  render = _.email,
          |  sortByString = Option(_.email)
          |)
          |```
          |
          |These props expect a function that return a value from a row/item.
          |Table will use this value to sort its rows.
          |
          |**It's an implementation fault that we cannot support a simple
          |`sortBy` at the moment.** You must explicitly use `sortByString`
          |or `sortByDouble` depend on the type of returned values:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy(
          columns = List(
            Sample.BaseTable.columns.head, /*<*/
            Sample.BaseTable
              .columns(1)
              .copy(sortByString = Some(_.name)),
            Sample.BaseTable
              .columns(2)
              .copy(sortByDouble = Some(_.level.toDouble)), /*>*/
            Sample.BaseTable.columns(3), /*<*/
            Sample.BaseTable
              .columns(4)
              .copy(sortByDouble = Some(m => m.point * m.level)) /*>*/
          ), /*<*/
          sortColumn = Some(1) /*>*/
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |Even if you have defined `sortBy` for your columns, your Table will
          |not be sorted until user clicks on a header to sort. To have
          |Table sorted by a column by default, pass that column's index to
          |the `sortColumn` prop of the Table.
          |
          |# Span
          |
          |Table supports [`colspan`][1] via the `colSpan` prop of
          |`Table.Cell`. This should receive the number of column your cell
          |should extend.
          |
          |[1]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td#attr-colspan
          |
          |Like in HTML, beside setting `colSpan`, you also need to remove
          |the adjacent cell(s). In Table component this is done via setting
          |`isNone = true` in adjacent `Table.Cell`(s):
          |
          |```scala
          |Table.Column("First", _ => Table.Cell("Extended", colSpan = 3)),
          |Table.Column("Second", _ => Table.Cell(isNone = true))
          |Table.Column("Third", _ => Table.Cell(isNone = true))
          |```
          |
          |Full example:
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
        |# Custom Render
        |
        |## Row
        |
        |Table rows are separated by border and has background changed on
        |hover. This appearance, however, can be fully customized via the
        |`renderRow` prop, which expects a function that receives:
        |
        ||Param  |Type       |Description|
        ||-------|-----------|-----------|
        ||`style`|TagMod     |The default style of that row, from the [`style`](#style) prop|
        ||`key`  |String     |The [key][1] of that row, from the [`getKey`](#getkey) prop|
        ||`cells`|VdomArray  |Array of rendered cells (`td`) of that row|
        ||`row`  |A          |The data of that row (an item of the [`rows`](#rows) prop)|
        |
        |[1]: https://reactjs.org/docs/lists-and-keys.html
        |
        |and returns your customized `tr`.
        |
        |For example, let say we that we want to render the row of Ada a
        |little bit more prominent:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val table = Sample.BaseTable.copy( /*<*/
          renderRow = (
            _: TagMod,
            key: String,
            cells: VdomArray,
            member: Sample.Member
          ) => {
            <.tr(
              Style.border.all.borderColor.gray3,
              TagMod(
                Style.backgroundColor.gray1,
                ^.transform := "scale(1.05)",
                ^.boxShadow := "0 0 0 1px var(--color-gray-4)," +
                  "0 1px 8px #00000014"
              ).when(member.name == "Ada"),
              ^.key := key,
              cells
            )
          } /*>*/
        )()
        <.div(table) /*<*/
      }))(),
      Markdown(
        """
          |With this prop, you can also remove in between border to visually
          |group adjacent rows, or dim their background (e.g. to gray 1 or 2).
          |
          |For reference, below is the implementation of the default
          |`renderRow`, which could be used as a boilerplate for yours:
          |
          |```scala
          |def defaultRenderRow[A](
          |  style: TagMod,
          |  key: String,
          |  cells: VdomArray,
          |  row: A
          |): vdom.TagOf[html.TableRow] = {
          |  <.tr(
          |    Style.hover.backgroundGray1, // background changes on hover
          |    style,        // predefined style from `style` prop
          |    ^.key := key, // React's key
          |    cells         // rendered cells (`td`s)
          |  )
          |}
          |```
          |
          |>::warning::
          |>**Warning: Always provide `key`**
          |>
          |>Your `<.tr`s will be rendered as a VdomArray, which requires `key`
          |>to be defined in every `<.tr`. Without a valid key, your `<.tr`
          |>might be rendered more than enough and its children could lost
          |>their state or focus.
          |>
          |>The value of key is already provided as an argument in your
          |>`renderRow` function. You can prefix or suffix it, but make sure
          |>to always apply it to your `<.tr`.
          |
          |## Footer
          |
          |Table also has a `footer` prop, in which you can put any custom VdomNode:
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
      }))()
    )
  }
}
