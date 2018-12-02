package anduin.guide.components

import anduin.guide.components.Heading.getId
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Toc(
  headings: Seq[Toc.RawHeading]
) {
  def apply(): VdomElement = Toc.component(this)
}

object Toc {

  private type Props = Toc
  private type RawHeading = (Int, String)
  private case class Heading(text: String, children: Seq[String])

  // Note in advance that we only care about the 1st and 2nd level
  private def groupHeadings(props: Props)(
    headings: Seq[Heading],
    rawHeading: RawHeading
  ): Seq[Heading] = {
    val (level, text) = rawHeading
    level match {
      // first level => new entry
      case 1 => headings :+ Heading(text = text, children = Seq.empty)
      // second level => add to last entry
      case 2 =>
        val lastOpt = headings.lastOption.map { heading =>
          val children = heading.children :+ text
          heading.copy(children = children)
        }
        lastOpt.fold(headings)(last => headings.init :+ last)
      case _ => headings
    }
  }

  private def renderLink(text: String): VdomElement = {
    <.a(Style.color.inherit, ^.href := s"#${getId(text)}", text)
  }

  private def renderChild(heading: Heading)(tuple: (String, Int)): VdomElement = {
    val (text, index) = tuple
    val sep = TagMod.when(index < heading.children.size - 1)(", ")
    <.span(^.key := text, renderLink(text), sep)
  }

  private def render(props: Props): VdomElement = {
    val headings = props.headings
      .foldLeft[Seq[Heading]](Vector.empty)(groupHeadings(props))
    <.div(
      headings.toVdomArray { heading =>
        <.div(
          ^.key := heading.text,
          <.span(Style.fontWeight.semiBold, renderLink(heading.text), ": "),
          heading.children.zipWithIndex.toVdomArray(renderChild(heading))
        )
      }
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
