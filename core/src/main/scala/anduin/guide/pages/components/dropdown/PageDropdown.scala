package anduin.guide.pages.components.dropdown

import anduin.guide.components._
import anduin.component.button.{Button, ButtonStyle}
import anduin.component.dropdown.Dropdown
import anduin.component.icon.Icon
import anduin.component.input.checkbox.Checkbox
import anduin.component.menu.VerticalDivider
import anduin.guide.app.main.Pages
import anduin.guide.components.SimpleState
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, React}

private case class Fruit(name: String)

private object Fruit {

  val options = List("Apple", "Banana", "Lemon", "Orange", "Pearl")
    .map(f => Dropdown.Opt(Fruit(f)))

  val FDropdown = (new Dropdown[Fruit])()
  val DropdownSample = FDropdown(
    value = Some(options.head.value),
    options = options,
    onChange = _ => Callback.empty,
    getValueString = _.name
  )

  val State = (new SimpleState[Fruit])()
  val StateSample = State(
    initialValue = options.head.value,
    render = (_, _) => EmptyVdom
  )
  val OptionState = (new SimpleState[Option[Fruit]])()

  val dropdownSample = StateSample.copy(render = (value, onChange) => {
    DropdownSample.copy(value = Some(value), onChange = onChange)()
  })
}

private case class Country(code: String, name: String, continent: String)

private object Country {
  val options = List(
    Country("us", "United States", "North America"),
    Country("cn", "China", "Asia"),
    Country("jp", "Japan", "Asia"),
    Country("de", "Germany", "Europe"),
    Country("gb", "United Kingdom", "Europe"),
    Country("in", "India", "Asia"),
    Country("fr", "France", "Europe"),
    Country("br", "Brazil", "South America"),
    Country("it", "Italy", "Europe"),
    Country("ca", "Canada", "North America"),
    Country("kr", "South Korea", "Asia"),
    Country("au", "Australia", "Australia"),
    Country("es", "Spain", "Europe"),
    Country("mx", "Mexico", "North America"),
    Country("id", "Indonesia", "Asia"),
    Country("nl", "Netherlands", "Europe"),
    Country("sa", "Saudi Arabia", "Asia"),
    Country("ch", "Switzerland", "Europe")
  ).map(c => Dropdown.Opt(c))

  val CDropdown = (new Dropdown[Country])()
  val DropdownSample = CDropdown(
    value = Some(options.head.value),
    options = options.take(5),
    onChange = _ => Callback.empty,
    getValueString = _.name,
    getFilterValue = Some(c => c.name + c.continent)
  )

  val State = (new SimpleState[Country])()
  val StateSample = State(
    initialValue = options.head.value,
    render = (_, _) => EmptyVdom
  )

  def getFlagSrc(code: String) = {
    s"https://lipis.github.io/flag-icon-css/flags/4x3/$code.svg"
  }
}

object PageDropdown {

  private val StringDropdown = (new Dropdown[String])()

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header("Dropdown", obj = Some(Dropdown))()
      ),
      Markdown(
        """
          |Dropdown let users select a value from a list of options:
          |""".stripMargin
      )(),
      ExampleSimple()(
        Fruit.State(
          initialValue = Fruit("Apple"),
          render = (value, onChange) => {
            Fruit.DropdownSample
              .copy(value = Some(value), onChange = onChange)()
          }
        )()
      ),
      Markdown(s"""
                  |**Dropdown allows only one value selected at a time.** For selecting
                  |multiple values, consider [MultiDropdown][md] or a list of
                  |[Checkboxes][cb].
                  |
                  |[md]: ${ctl.urlFor(Pages.MultiDropdown()).value}
                  |[cb]: ${ctl.urlFor(Pages.Checkbox()).value}
                  |
                  |**Dropdown's value can only come from the option list.** If users
                  |should be able to freely input any value (like a text field),
                  |consider [Suggest][ss] or [MultiSuggest][ms] component.
                  |
                  |[ss]: ${ctl.urlFor(Pages.Suggest()).value}
                  |[ms]: ${ctl.urlFor(Pages.MultiSuggest()).value}
                  |
                  |**Dropdown's options are hidden in a pop-up menu.** This saves
                  |space but require user's interaction to view more. If your options
                  |should always be visible, consider a list of [Radio buttons][rb].
                  |
                  |[rb]: ${ctl.urlFor(Pages.Radio()).value}
                  |
                  |# Basic Usage
                  |
                  |## Initialize
                  |
                  |Dropdown is a [generic component][gc], so first you need to
                  |initialize it with the type of your options's value:
                  |
                  |[gc]: https://docs.scala-lang.org/tour/generic-classes.html
                  |
                  |```scala
                  |object YourComponent {
                  |
                  |  case class Fruit(name: String)
                  |  val FruitDropdown = (new Dropdown[Fruit])()
                  |
                  |  def render(props: Props) {
                  |    /* use your FruitDropdown here */
                  |  }
                  |
                  |}
                  |```
                  |
                  |**In practice, your option's type is usually available already,
                  |as the id of something,** like `EntityId` or `UserId`. It does not
                  |need to include all information that are necessary to render (like
                  |`EntityModel` or `UserModel`), just enough to differentiate one
                  |option from another.
                  |
                  |## Props
                  |
                  |After initialized, you can pass props to your `FruitDropdown` to
                  |render it like any other components. Dropdown's props mimic closely
                  |the HTML's [`select`][ms] element, so the following must be defined:
                  |
                  |[ms]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select
                  |
                  |1. **`value:`**`scala::Option[A]`：The currently selected option.
                  |Can be `None` to indicate no default value. Learn more in the
                  |[Default value](#default-value) section.
                  |
                  |2. **`options:`**`scala::List[Dropdown.Opt[A]]`：The list of options
                  |that users can select from. Each option should have the following
                  |interface:
                  |
                  |    ```scala
                  |    case class Dropdown.Opt[A](
                  |      value: A,
                  |      isDisabled: Boolean = false
                  |    )
                  |    ```
                  |
                  |3. **`onChange`**`scala::(selectedValue: A => Callback)`：A
                  |function to be called when user selects a new option. It is called
                  |with the value of the new option that was just selected.
                  |
                  |Moreover, since Dropdown is a generic component, it also requires:
                  |
                  |4. **`getValueString:`**`scala::(value: A => String)`：which should
                  |return a string that represent your option's value. By default this
                  |string will be used to [identify][rk], [render](#render-value) and
                  |[filter](#search) your options.
                  |
                  |[rk]: https://reactjs.org/docs/lists-and-keys.html
                  |
                  |Putting together, these 4 props let you have a basic instance of
                  |the Dropdown component. Although simple, it should be enough for
                  |most use cases:
                  |
        """.stripMargin)(),
      ExampleRich(Source.annotate({
        /*>*/
        Fruit.State(
          initialValue = Fruit("Apple"), /*<*/
          render = (value, onChange) => {
            Fruit.FDropdown(
              value = Some(value),
              options = List(
                Dropdown.Opt(Fruit("Apple")),
                Dropdown.Opt(Fruit("Banana")),
                Dropdown.Opt(Fruit("Lemon")),
                Dropdown.Opt(Fruit("Orange")),
                Dropdown.Opt(Fruit("Pearl"))
              ),
              onChange = onChange,
              getValueString = _.name
            )()
          } /*>*/
        )()
      }))(),
      Markdown(
        """
          |# Behaviour
          |
          |## Value
          |
          |**Dropdown is a [controlled component][cc].** The current value
          |should be passed by the consumer via the `value` prop. This prop
          |accepts an `Option`, with `Some(X)` means that X is selected, and
          |`None` means no value is currently selected.
          |
          |[cc]: https://reactjs.org/docs/forms.html#controlled-components
          |
          |The `None` value is useful when being used as the initial value for
          |Dropdown components that should not have pre-selected option. In
          |these cases, the `placeholder` prop will be used to render the
          |content of Dropdown's button:
          |
          |```scala
          |placeholder: VdomNode = "Select…"
          |```
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Fruit.OptionState(
          initialValue = None,
          render = (value, onChange) => {
            Fruit.DropdownSample.copy(
              value = value,
              placeholder = "Fruit?",
              onChange = item => onChange(Some(item))
            )()
          }
        )()
      }))(),
      Markdown(
        """
          |
          |## Disabled
          |
          |Like HTML's [`select` element][se], Dropdown also accepts an
          |`isDisabled` prop to disable all interaction to the component:
          |
          |[se]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Fruit.StateSample.copy(render = (value, onChange) => {
          Fruit.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            isDisabled = true /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown(
        """
          |
          |## Search
          |
          |**A search box will be available when the Dropdown has more than 10
          |options.** The search box is placed on top of option list and
          |automatically focused to help users quickly find their target
          |options.
          |
          |When search box is available, the result of `getValueString` will
          |be used to compare options with user's input. This can be overriden
          |by defining a `getFilterValue`:
          |
          |```scala
          |// A is type of option.value
          |getFilterValue: Option[A => String] = None
          |```
          |
          |…which should return the value to be used for comparing. For
          |example, in the Dropdown below you can search by not only
          |countries's name but also their continent (try "Asia"):
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange,
            options = Country.options, /*<*/
            getFilterValue = Some(c => c.name + c.continent) /*>*/
          )()
        })()
      }))(),
      Markdown(
        """
          |
          |# Appearance
          |
          |## Width
          |
          |Like native `select`, the width of Dropdown's button is the width
          |of its longest option by default:
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Country.State(
          initialValue = Country.options(1).value,
          render = (value, onChange) => {
            Country.DropdownSample.copy(
              value = Some(value),
              onChange = onChange
            )()
          }
        )()
      }))(),
      Markdown(
        """
          |
          |**To control the width of Dropdown's button,** set `isFullWidth =
          |true`. The width will then be fixed as 100% of its parent width:
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        val dropdown = Fruit.StateSample.copy(render = (value, onChange) => {
          Fruit.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            isFullWidth = true /*>*/
          )()
        })() /*<*/
        <.div(Style.width.px256, dropdown)
      }))(),
      Markdown(
        """
          |
          |This is particularly useful in building form layout, where several
          |fields should share a common width for undistracted scanning.
          |
          |## Style
          |
          |Dropdown comes with 2 styles: `StyleFull` and `StyleMinimal`:
          |
          |```scala
          |style: Style = StyleFull
          |
          |case object StyleFull extends Style
          |case object StyleMinimal extends Style
          |```
          |
          |`StyleFull` is the default one with strong visual hint. `StyleFull`
          |should be used for most cases to make your Dropdown stand out
          |clearly from other content:
          |
        """.stripMargin
      )(),
      ExampleSimple()({
        val dropdown = Fruit.StateSample.copy(render = (value, onChange) => {
          Fruit.DropdownSample.copy(
            value = Some(value),
            onChange = onChange,
            isFullWidth = true
          )()
        })()
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Style.width.px128, dropdown),
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter.color.gray6.margin.left16,
            Icon(name = Icon.NameInfo)(),
            <.p(Style.margin.left8, "Additional information")
          )
        )
      }),
      Markdown(
        """
          |
          |`StyleMinimal` has limited visual hint and thus should only be
          |used when the surrounding context already provided enough hint for
          |interaction:
          |
        """.stripMargin
      )(),
      ExampleSimple(
        """
          |One common example is the Toolbar. Several control components
          |are placed next to each other is enough to call out the interaction.
        """.stripMargin
      )({
        val dropdown = SimpleState.Str(
          initialValue = "Arial",
          render = (value, onChange) => {
            StringDropdown(
              value = Some(value),
              onChange = onChange,
              options = List("Arial", "Tahoma", "Verdana").map(v => Dropdown.Opt(v)),
              getValueString = _.toString,
              style = Dropdown.StyleMinimal,
              isFullWidth = true
            )()
          }
        )()
        val button = Button(size = ButtonStyle.SizeIcon, style = ButtonStyle.StyleMinimal)
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Style.width.px128, dropdown),
          VerticalDivider()(),
          button(Icon(name = Icon.NameBold)()),
          button(Icon(name = Icon.NameItalic)()),
          button(Icon(name = Icon.NameUnderline)()),
          button(Icon(name = Icon.NameStrikeThrough)()),
        )
      }),
      Markdown(
        """
          |
          |Note that these styles only affect Dropdown's button. At the moment,
          |Dropdown's menu is the same for all styles.
          |
          |## Header and Footer
          |
          |Dropdown's menu can also have an header and/or footer to provide
          |additional information for the option list if necessary:
          |
          |```scala
          |header: Option[VdomNode] = None
          |footer: Option[VdomNode] = None
          |```
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        /*>*/
        Fruit.StateSample.copy(render = (value, onChange) => {
          Fruit.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            header = Some("Fruit is good"),
            footer = Some("Fruit is fun") /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown(
        """
          |
          |The menu's header and footer will always be visible at the top and
          |bottom of the menu. If the option list is long and requires
          |scroll overflow, they will be outside of the scroll area:
          |
        """.stripMargin
      )(),
      ExampleSimple()({
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange,
            options = Country.options,
            header = Some("Sample header"),
            footer = Some("Sample footer") /*>*/
          )()
        })()
      }),
      Markdown(
        """
          |
          |Note that both header and footer come with pre-defined styles
          |(including padding, background and border). This is not customizable
          |at the moment.
          |
          |# Advanced
          |
          |## Render value
          |
          |By default, Dropdown will use `getValueString` to render the value
          |of currently selected option in the Dropdown's button. This can be
          |overridden by the `renderValue` prop:
          |
          |```scala
          |// A is the type of option's value
          |renderValue: Option[A => VdomNode] = None
          |```
          |
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderValue = (country: Country) => {
          val flag = <.img(
            Style.margin.right8,
            ^.src := Country.getFlagSrc(country.code),
            TagMod(^.width := "16px", ^.height := "16px")
          )
          React.Fragment(flag, country.name)
        } /*>*/
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            renderValue = Some(renderValue) /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown(
        s"""
           |
           |## Render option
           |
           |By default, Dropdown will use `getValueString` to render the values
           |of available options in the Dropdown's menu. This can be
           |overridden by the `renderOption` prop:
           |
           |```scala
           |// A is the type of option's value
           |renderOption: Option[A => VdomNode] = None,
           |```
           |
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        val renderOption: Country => VdomNode = (country: Country) => {
          <.span(
            <.span(country.name),
            <.span(Style.color.gray6, s" - ${country.continent}")
          )
        } /*>*/
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            renderOption = Some(renderOption) /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown(
        """
          |
          |This, however, can not control some UI elements like the hover
          |effect or the tick icon. These are essential for interaction and
          |are not customizable at the moment.
          |
          |## Performance
          |
          |**Under the hood, Dropdown did some expensive calculations in
          |initial render** to have necessary measurements for [some layout
          |features](#width). It is carefully optimized so that in most cases
          |there would be no significant performance penalty. In fact, this
          |even makes subsequent renders faster.
          |
          |However, in rare cases where `renderValue` is defined and/or the
          |list of options is very long, these calculations could
          |significantly slow down the initial render. **In these cases, it
          |is suggested that the engineer should provide these measurements
          |themselves.**
          |
          |These necessary measurements should be provided via the
          |`staticMeasurement` prop. Upon provided, the static measurement
          |will be used in all renders and no expensive calculation will be
          |done at all.
          |
          |```scala
          |staticMeasurement: Option[Measurement] = None
          |
          |case class Measurement[A](
          |  // The dropdown option that should have the
          |  // biggest width after render
          |  biggestWidthOption: Option[Dropdown.Opt[A]],
          |  // The height of each option
          |  optionHeight: Option[Int]
          |)
          |```
          |
          |For example, let's compare the mounting time of the following
          |dropdown with "Use static measurement" checked and unchecked:
          |
        """.stripMargin
      )(),
      ExampleSimple()({
        val m = Dropdown.Measurement(
          biggestWidthOption = Some(Dropdown.Opt("10000")),
          optionHeight = Some(32)
        )
        val dropdown = (isStatic: Boolean) =>
          SimpleState.Str(
            initialValue = "0",
            render = (value, onChange) => {
              StringDropdown(
                value = Some(value),
                onChange = onChange,
                options = 0.to(10000).map(i => Dropdown.Opt(i.toString)).toList,
                getValueString = identity[String],
                staticMeasurement = if (isStatic) Some(m) else None
              )()
            }
          )()
        SimpleState.BoolF.copy(render = (isMounted, setIsMounted) => {
          SimpleState.BoolF.copy(render = (isStaticMeasurement, setIsStaticMeasurement) => {
            <.div(
              Checkbox(isStaticMeasurement, onChange = setIsStaticMeasurement)("Use static measurement"),
              <.div(Style.height.px8),
              Button(onClick = setIsMounted(!isMounted))(if (isMounted) "Unmount" else "Mount"),
              <.div(Style.height.px8),
              if (isMounted) { dropdown(isStaticMeasurement) } else EmptyVdom
            )
          })()
        })()
      }),
    )
  }
}
