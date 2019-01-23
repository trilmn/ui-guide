package anduin.guide.components

import anduin.guide.components.Heading.{getId, getTitle}
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Toc(
  headings: Seq[Toc.Heading]
) {
  def apply(): VdomElement = Toc.component(this)
}

object Toc {

  private type Props = Toc
  private type Heading = (Int, String)
  private case class Section(title: String, children: Seq[String])

  // Note in advance that we only care about the 1st and 2nd level
  private def groupHeadings(props: Props)(
    sections: Seq[Section],
    heading: Heading
  ): Seq[Section] = {
    val (level, title) = heading
    level match {
      // first level => new entry
      case 1 => sections :+ Section(title = title, children = Seq.empty)
      // second level => add to last entry
      case 2 =>
        val lastOpt = sections.lastOption.map { heading =>
          val children = heading.children :+ title
          heading.copy(children = children)
        }
        lastOpt.fold(sections)(last => sections.init :+ last)
      case _ => sections
    }
  }

  private val linkStyles = TagMod(
    Style.color.inherit.focus.outline.transition.allWithOutline,
    Style.border.bottom.borderWidth.px2.borderColor.transparent,
    Style.hover.underlineNone.hover.colorPrimary4.hover.borderPrimary3
  )

  private def renderLink(title: String): VdomElement = {
    <.a(linkStyles, ^.href := s"#${getId(title)}", getTitle(title))
  }

  private def renderChild(title: String): VdomElement = {
    <.li(^.key := title, renderLink(title))
  }

  private def renderSection(section: Section): VdomElement = {
    <.li(
      ^.key := section.title,
      <.p(renderLink(section.title)),
      <.ol(Style.listing.list, section.children.toVdomArray(renderChild))
    )
  }

  private val mainStyles = TagMod(
    Style.position.fixed.coordinate.top0.height.pc100,
    ^.left := "calc(50% + calc(576px / 2))",
    ^.paddingTop := "128px"
  )

  private val listStyles = TagMod(
    Style.fontSize.px15.lineHeight.px32,
    Style.color.gray4.hover.colorGray7.transition.all,
    Style.listing.list.height.pc100.overflow.autoY,
    ^.cls := "ad-scroll-hidden",
    ^.padding := "8px 32px 32px 108px"
  )

  private def render(props: Props): Option[VdomElement] = {
    val sections = props.headings
      .foldLeft[Seq[Section]](Vector.empty)(groupHeadings(props))
    if (sections.isEmpty) {
      None
    } else {
      val sectionsWTop = Section("Top", Vector.empty) +: sections
      val list = <.ol(listStyles, sectionsWTop.toVdomArray(renderSection))
      Some(<.div(mainStyles, list))
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
