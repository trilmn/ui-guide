package anduin.guide.app.main
import japgolly.scalajs.react.extra.router.RouterCtl

object Pages {
  sealed trait Page
  // entry
  case object Welcome extends Page

  // style
  sealed trait StyleT extends Page
  case class Logo(hash: String = "") extends StyleT
  case class Style(hash: String = "") extends StyleT
  case class Space(hash: String = "") extends StyleT
  case class Flexbox(hash: String = "") extends StyleT
  case class Color(hash: String = "") extends StyleT
  sealed trait TypographyT extends StyleT
  case class Typography(hash: String = "") extends TypographyT
  case class FixedLineHeight(hash: String = "") extends TypographyT

  // component
  sealed trait ComponentT extends Page
  case class Component(hash: String = "") extends ComponentT

  case class Avatar(hash: String = "") extends ComponentT
  sealed trait ButtonT extends ComponentT
  case class Button(hash: String = "") extends ButtonT
  case class ButtonBox(hash: String = "") extends ButtonT
  case class Card(hash: String = "") extends ComponentT
  case class Checkbox(hash: String = "") extends ComponentT
  case class DateTime(hash: String = "") extends ComponentT
  sealed trait DropdownT extends ComponentT
  case class Dropdown(hash: String = "") extends DropdownT
  case class DropdownMulti(hash: String = "") extends DropdownT
  case class Field(hash: String = "") extends ComponentT
  case class Filter(hash: String = "") extends ComponentT
  sealed trait IconT extends ComponentT
  case class Icon(hash: String = "") extends IconT
  case class IconFile(hash: String = "") extends IconT
  case class IconFolder(hash: String = "") extends IconT
  case class IconGlyph(hash: String = "") extends IconT
  case class IconNego(hash: String = "") extends IconT
  case class Menu(hash: String = "") extends ComponentT
  case class Modal(hash: String = "") extends ComponentT
  case class Popover(hash: String = "") extends ComponentT
  case class ProgressIndicator(hash: String = "") extends ComponentT
  sealed trait RadioT extends ComponentT
  case class Radio(hash: String = "") extends RadioT
  case class RadioBox(hash: String = "") extends RadioT
  case class Stepper(hash: String = "") extends ComponentT
  sealed trait SuggestT extends ComponentT
  case class Suggest(hash: String = "") extends SuggestT
  case class SuggestMulti(hash: String = "") extends SuggestT
  case class Tab(hash: String = "") extends ComponentT
  case class Table(hash: String = "") extends ComponentT
  case class Tag(hash: String = "") extends ComponentT
  case class TextBox(hash: String = "") extends ComponentT
  case class Toggle(hash: String = "") extends ComponentT
  case class Tooltip(hash: String = "") extends ComponentT
  case class Tree(hash: String = "") extends ComponentT
  case class Well(hash: String = "") extends ComponentT

  // Copy
  case class Copy(hash: String = "") extends Page

  // Others
  case class Resources(hash: String = "") extends Page
  case class Careers(hash: String = "") extends Page

  type Ctl = RouterCtl[Pages.Page]
}
