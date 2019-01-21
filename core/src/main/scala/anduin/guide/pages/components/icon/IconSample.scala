// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.icon

import anduin.component.button.Button
import anduin.component.icon.{Icon, IconFile, IconNego, IconGlyph}
import org.scalajs.dom

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class IconSample(
  name: Icon.Name,
  size: Icon.Size,
  mod: TagMod
) {
  def apply(): VdomElement = IconSample.component(this)
}

object IconSample {

  private type Props = IconSample

  private def getName(props: Props): String =
    props.name.getClass.getSimpleName

  private def getNameDef(props: Props): String = {
    val parent = props.name match {
      case _: IconFile  => "File"
      case _: IconNego  => "Nego"
      case _: IconGlyph => "Glyph"
      case _            => "Name"
    }
    s"name = Icon.$parent.${getName(props)}"
  }

  private def getSize(props: Props): String = {
    props.size match {
      case Icon.Size.Px16 => ""
      case _              => s", size = Icon.Size.${props.size.getClass.getSimpleName}"
    }
  }

  private def copy(props: Props) = Callback {
    val str = s"Icon(${getNameDef(props)}${getSize(props)})()"
    dom.window.prompt("Copy yourself:", str)
  }

  private def render(props: Props): VdomElement = {
    Button(
      style = Button.Style.Minimal(height = Button.Height.Free),
      onClick = copy(props),
    )(<.div(props.mod, Icon(props.name, props.size)()))
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
