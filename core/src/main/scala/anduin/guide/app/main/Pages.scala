package anduin.guide.app.main
import japgolly.scalajs.react.extra.router.RouterCtl

object Pages {
  sealed trait Page
  // entry
  case object Welcome extends Page
  case object WIP extends Page
  // brand
  sealed trait BrandT extends Page
  case class Logo(hash: String = "") extends BrandT
  // style
  sealed trait StyleT extends Page
  case class Style(hash: String = "") extends StyleT
  case class Space(hash: String = "") extends StyleT
  case class Flexbox(hash: String = "") extends StyleT
  case class Color(hash: String = "") extends StyleT
  sealed trait TypographyT extends StyleT
  case class Typography(hash: String = "") extends TypographyT
  case class FixedLineHeight(hash: String = "") extends TypographyT
  // component
  sealed trait ComponentT extends Page
  case class Button(hash: String = "") extends ComponentT
  case class Card(hash: String = "") extends ComponentT
  case class Checkbox(hash: String = "") extends ComponentT
  case class Component(hash: String = "") extends ComponentT
  case class Dropdown(hash: String = "") extends ComponentT
  case class Field(hash: String = "") extends ComponentT
  case class Icon(hash: String = "") extends ComponentT
  case class Illus(hash: String = "") extends ComponentT
  case class IllusFolder(hash: String = "") extends ComponentT
  case class Modal(hash: String = "") extends ComponentT
  case class MultiDropdown(hash: String = "") extends ComponentT
  case class MultiSuggest(hash: String = "") extends ComponentT
  case class Popover(hash: String = "") extends ComponentT
  case class Radio(hash: String = "") extends ComponentT
  case class Stepper(hash: String = "") extends ComponentT
  case class Suggest(hash: String = "") extends ComponentT
  case class Tab(hash: String = "") extends ComponentT
  case class Table(hash: String = "") extends ComponentT
  case class TextBox(hash: String = "") extends ComponentT
  case class Toggle(hash: String = "") extends ComponentT
  case class Well(hash: String = "") extends ComponentT
  type Ctl = RouterCtl[Pages.Page]
}
