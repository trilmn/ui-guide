package anduin.guide

object Pages {
  sealed trait Page {}
  // entry
  case object Welcome extends Page
  case class WIP(hash: String = "") extends Page
  // style
  case class Style(hash: String = "") extends Page
  case class Space(hash: String = "") extends Page
  case class Flexbox(hash: String = "") extends Page
  case class Color(hash: String = "") extends Page
  case class Typography(hash: String = "") extends Page
  case class FixedLineHeight(hash: String = "") extends Page
  // component
  case class Component(hash: String = "") extends Page
  // button
  case class Button(hash: String = "") extends Page
  case class ButtonStyle(hash: String = "") extends Page
  case class ButtonLink(hash: String = "") extends Page
  // container
  case class Card(hash: String = "") extends Page
  case class Collapse(hash: String = "") extends Page
  case class Tab(hash: String = "") extends Page
  case class Table(hash: String = "") extends Page
  case class Well(hash: String = "") extends Page
  // icon
  case class Icon(hash: String = "") extends Page
  case class Illus(hash: String = "") extends Page
  case class IllusFolder(hash: String = "") extends Page
  // input
  case class Checkbox(hash: String = "") extends Page
  // portal
  case class Modal(hash: String = "") extends Page
  case class Tooltip(hash: String = "") extends Page
}
