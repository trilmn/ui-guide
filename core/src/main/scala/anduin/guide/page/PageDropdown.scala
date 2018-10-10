package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, React}

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.dropdown
import anduin.component.dropdown.Dropdown
import anduin.component.icon.Icon
import anduin.component.menu.VerticalDivider
import anduin.guide.component.SimpleState
import anduin.guide.{Pages, Router}
import anduin.mcro.Source
import anduin.style.Style

private case class Fruit(name: String)

private object Fruit {

  val options = List("Apple", "Banana", "Lemon", "Orange", "Pearl")
    .map(f => dropdown.Dropdown.Opt(Fruit(f)))

  val Dropdown = (new Dropdown[Fruit])()
  val DropdownSample = Dropdown(
    value = Some(options.head.value),
    options = options,
    onChange = _ => Callback.empty,
    renderValue = _.name
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
  ).map(c => dropdown.Dropdown.Opt(c))

  val Dropdown = (new Dropdown[Country])()
  val DropdownSample = Dropdown(
    value = Some(options.head.value),
    options = options.take(5),
    onChange = _ => Callback.empty,
    renderValue = _.name,
    getFilterValue = c => c.name + c.continent
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

  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header("Dropdown", cls = Some(Dropdown.getClass))()
      ),
//      ExampleSimple()(
//        // xxxxxxxxxxxxx
//        SimpleState.Str(
//          initialValue = "0",
//          render = (value, onChange) => {
//            StringDropdown(
//              value = Some(value),
//              onChange = onChange,
//              options = 0.to(1000).map(i => Dropdown.Opt(i.toString)).toList,
//              renderValue = a => a
//            )()
//          }
//        )()
//      ),
      Markdown(
        """
          |Dropdown let users select a value from a list of options:
        """.stripMargin
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
          |initialize it with the type of your options:
          |
          |[gc]: https://docs.scala-lang.org/tour/generic-classes.html
          |
          |```scala
          |object YourComponent {
          |  case class Fruit(name: String)
          |  val FruitDropdown = (new Dropdown[Fruit])()
          |
          |  def render(props: Props) {
          |    /* use your FruitDropdown here */
          |  }
          |}
          |```
          |
          |**In practice, your option's type is usually available already.**
          |It can be anything that can represent your option, from an id (like
          |`EntityId`) to a whole model (like `EntityModel`).
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
          |Moreover, since Dropdown is a generic component, it requires:
          |
          |4. **`renderValue:`**`scala::(value: A => VdomNode)`：How your
          |value should be rendered. Learn more in the
          |[Render value](#render-value) section.
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
            Fruit.Dropdown(
              value = Some(value),
              options = List(
                Dropdown.Opt(Fruit("Apple")),
                Dropdown.Opt(Fruit("Banana")),
                Dropdown.Opt(Fruit("Lemon")),
                Dropdown.Opt(Fruit("Orange")),
                Dropdown.Opt(Fruit("Pearl"))
              ),
              onChange = onChange,
              renderValue = _.name
            )()
          } /*>*/
        )()
      }))(),
      Markdown("""
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
        """.stripMargin)(),
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
      Markdown("""
          |
          |## Disabled
          |
          |Like HTML's [`select` element][se], Dropdown also accepts an
          |`isDisabled` prop to disable all interaction to the component:
          |
          |[se]: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select
          |
        """.stripMargin)(),
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
      Markdown("""
          |
          |## Search
          |
          |**A search box will be available when the Dropdown has more than 10
          |options.** The search box is placed on top of option list and
          |automatically focused to help users quickly find their target
          |options.
          |
          |When search box is available, the `getFilterValue` prop will be
          |used to stringify options to compare with user's input:
          |
          |```scala
          |// A is type of option.value
          |getFilterValue: A => String = _.toString
          |```
          |
          |For example, in the Dropdown below you can search by not only
          |countries's name but also their continent (try "Asia"):
          |
        """.stripMargin)(),
      ExampleRich(Source.annotate({
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            options = Country.options,
            getFilterValue = c => c.name + c.continent /*>*/
          )()
        })()
      }))(),
      Markdown("""
          |
          |# Appearance
          |
          |## Render value
          |
          |Because Dropdown is a generic component, a `renderValue` prop is
          |required to describe how your option's value should be rendered.
          |The render result will be used in both Dropdown's button and the
          |list of options:
          |
        """.stripMargin)(),
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
            renderValue = renderValue /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown(s"""
          |
          |This, however, does not control the whole render of an option (e.g.
          |the hover effect and the tick icon). If you need complete
          |customization, please see the [Render option](#render-option)
          |section.
          |
          |## Width
          |
          |Like native `select`, the width of Dropdown's button is the width
          |of its longest option by default:
          |
        """.stripMargin)(),
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
      Markdown("""
          |
          |This is particularly useful in building form layout, where several
          |fields should share a common width for easy scanning.
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
        """.stripMargin)(),
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
      Markdown("""
          |
          |`StyleMinimal` has limited visual hint and thus should only be
          |used when the surrounding context already provided enough hint for
          |interaction:
          |
        """.stripMargin)(),
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
              renderValue = _.toString,
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
      Markdown("""
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
        """.stripMargin)(),
      ExampleRich(Source.annotate({
        /*>*/
        val boxStyles = Style.backgroundColor.gray1.borderColor.gray3.padding.ver8.padding.hor12
        Fruit.StateSample.copy(render = (value, onChange) => {
          Fruit.DropdownSample.copy(
            value = Some(value),
            onChange = onChange, /*<*/
            header = Some(<.div("Fruit is good", Style.border.bottom, boxStyles)),
            footer = Some(<.div("Fruit is fun", Style.border.top, boxStyles)) /*>*/
          )()
        })() /*<*/
      }))(),
      Markdown("""
          |
          |The menu's header and footer will always be visible at the top and
          |bottom of the menu. If the option list is long and requires
          |scroll overflow, they will be outside of the scroll area:
          |
        """.stripMargin)(),
      ExampleSimple()({
        val boxStyles = Style.backgroundColor.gray1.borderColor.gray3.padding.ver8.padding.hor12
        Country.StateSample.copy(render = (value, onChange) => {
          Country.DropdownSample.copy(
            value = Some(value),
            onChange = onChange,
            options = Country.options,
            header = Some(<.div("Sample header", Style.border.bottom, boxStyles)),
            footer = Some(<.div("Sample footer", Style.border.top, boxStyles)) /*>*/
          )()
        })()
      }),
      Markdown("""
          |
          |Note that both header and footer don't have any pre-defined styles
          |(like padding or border). Therefore, you are free to define
          |anything to separate them from the option list as you want.
          |
          |# Advanced
          |
          |## Render option
          |
          |**Most of the time [`renderValue`](rendervalue) should be enough for
          |custom rendering, even the complex ones.** `renderOption` should
          |only be used in the rare cases where you truly need to override
          |the essential visual elements like background color change on hover
          |or the checkmark icon for selected item.
          |
          |>::warning::These visual elements are essential for user's
          |understanding of what is happening. Ensure that your replacements
          |provide as much visual hint as the default ones.
          |
          |To override the default render of an option completely, pass a
          |function to the `renderOption` prop which should return your
          |desired render:
          |
          |```scala
          |// See the implementation of `defaultRender` below
          |renderOption: RenderProps[A] => VdomNode = defaultRender[A]
          |```
          |
          |This function will receive an instance of `case class RenderProps`,
          |which has the following values and methods to help your rendering:
          |
          |1. **`option:`**`scala::Dropdown.Opt[A]`：The option to be rendered.
          |The interface of `Dropdown.Opt` can be found at the
          |[Props](#props) section.
          |
          |2. **`mods:`**`scala::TagMod`：The pre-defined `TagMod` that should
          |be applied to the target element of your render. This includes the
          |computed key, interaction (e.g. `onClick`) and accessibility
          |attributes for the element. This does not cover any appearance
          |definitions.
          |
          |3. **`renderValue:`**`scala::A => VdomNode`：the [same name
          |prop](#render-value) that was passed to Dropdown. Can be called
          |with `option.value` to get the string that should represent this
          |option.
          |
          |4. **`isSelected:`**`scala::Boolean`：whether this option is
          |currently selected.
          |
          |5. **`isHighlighted:`**`scala::Boolean`：whether this option is
          |currently highlighted (e.g. mouse hover or keyboard navigation via
          |up and down arrow).
          |
          |For reference purpose, below is the implementation of the default
          |option render. You might want to use it as the starting point for
          |your own render to ensure you won't miss any aspects.
          |
          |```scala
          |private val highlightedStyles = Style.backgroundColor.gray2
          |private val disabledStyles = Style.color.gray6
          |private val void = <.span(Style.width.px16) // visual balance
          |private val iconStyles = TagMod(Style.margin.right8, ^.marginLeft := "-4px")
          |
          |def defaultRender[A](props: RenderProps[A]): VdomElement = {
          |  val value = props.renderValue(props.option.value)
          |  val styles: TagMod = TagMod(
          |    TagMod.when(props.option.isDisabled)(disabledStyles),
          |    TagMod.when(props.isHighlighted)(highlightedStyles)
          |  )
          |  val iconName = if (props.isSelected) Icon.NameCheck else Icon.NameBlank
          |  val icon = <.span(iconStyles, Icon(iconName, Icon.SizeDynamic("12"))())
          |  <.button(MenuItem.buttonStyles, styles, props.mods)(icon, value, void)
          |}
          |```
          |
        """.stripMargin)()
    )
  }
}
