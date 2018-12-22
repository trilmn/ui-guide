//package anduin.guide.pages.components.button
//
//import anduin.component.icon.Icon
//import anduin.component.menu.MenuDivider
//import anduin.guide.app.main.Pages
//import anduin.guide.components._
//import anduin.mcro.Source
//import anduin.style.Style
//import anduin.test.component.button.Button
//import japgolly.scalajs.react.vdom.html_<^._
//import japgolly.scalajs.react.{Callback, React}
//
//object PageButtonTest {
//
//  private case class Opt(value: Boolean, set: Callback)
//
//  private val margin = Style.margin.right16
//
//  private def renderColorRow(Base: Button, getStyle: Button.Color => Button.Style): VdomElement = {
//    import Button.Color._
//    val colors: Seq[Button.Color] = Vector(White, Black, Blue, Red, Green)
//    val buttons = colors.toTagMod { color =>
//      val button = Base.copy(style = getStyle(color))(color.getClass.getSimpleName)
//      <.div(Style.padding.all8, button)
//    }
//    <.div(Style.flexbox.flex, buttons)
//  }
//
//  private def renderColorView(isDark: Opt, isSelected: Opt, isDisabled: Opt): VdomElement = {
//    val Base = Button(isDisabled = isDisabled.value)
//    <.div(
//      if (isDark.value) Style.backgroundColor.gray8 else Style.backgroundColor.white,
//      ^.margin := "-16px",
//      Style.padding.all16,
//      <.div(
//        Style.flexbox.flex,
//        <.div(margin, Button(onClick = isDark.set)("Toggle Bg Color")),
//        <.div(margin, Button(onClick = isSelected.set)("Toggle isSelected")),
//        <.div(margin, Button(onClick = isDisabled.set)("Toggle isDisabled"))
//      ),
//      <.div(Style.padding.ver8, MenuDivider()()),
//      <.div(
//        ^.margin := "-8px",
//        renderColorRow(Base, Button.Style.Full(_, isSelected = isSelected.value)),
//        renderColorRow(Base, Button.Style.Ghost(_, isSelected = isSelected.value)),
//        renderColorRow(Base, Button.Style.Minimal(_, isSelected = isSelected.value)),
//        renderColorRow(Base, Button.Style.Link),
//      )
//    )
//  }
//
//  private def renderColor(): VdomElement = {
//    SimpleState.Bool(
//      initialValue = false,
//      render = (isDark, setIsDark) => {
//        SimpleState.Bool(
//          initialValue = false,
//          render = (isSelected, setIsSelected) => {
//            SimpleState.Bool(
//              initialValue = false,
//              render = (isDisabled, setIsDisabled) => {
//                renderColorView(
//                  Opt(isDark, setIsDark(!isDark)),
//                  Opt(isSelected, setIsSelected(!isSelected)),
//                  Opt(isDisabled, setIsDisabled(!isDisabled))
//                )
//              }
//            )()
//          }
//        )()
//      }
//    )()
//  }
//
//  private def renderSize(): VdomElement = {
//    import Button.Size._
//    val sizes = Vector(Fix24, Fix32, Fix40).toTagMod { size =>
//      val icon = Some(Icon.NameLightBolt)
//      val styles = Vector(
//        Button.Style.Full(size = size, icon = icon),
//        Button.Style.Minimal(size = size, icon = icon),
//        Button.Style.Minimal(size = size, icon = icon, color = Button.Color.Blue)
//      ).toTagMod { style =>
//        React.Fragment(
//          <.div(Style.padding.all8, Button(style = style)()),
//          <.div(Style.padding.all8, Button(style = style)("Text"))
//        )
//      }
//      <.div(Style.flexbox.flex, styles)
//    }
//    <.div(^.margin := "-8px", sizes)
//  }
//
//  private def renderIsLoadingRow(getStyle: Button.Color => Button.Style): VdomElement = {
//    import Button.Color._
//    val buttons = Vector(White, Black, Blue).toTagMod { color =>
//      <.div(Style.padding.all8, Button(style = getStyle(color))("Text"))
//    }
//    <.div(Style.flexbox.flex, buttons)
//  }
//
//  private def renderIsLoading(): VdomElement = {
//    SimpleState.Bool(
//      initialValue = false,
//      render = (isLoading, setIsLoading) => {
//        val full = Button.Style.Full(isLoading = isLoading)
//        val icon = Some(Icon.NameLightBolt)
//        <.div(
//          <.div(
//            Button(onClick = setIsLoading(!isLoading))("Toggle isLoading"),
//          ),
//          <.div(Style.padding.ver8, MenuDivider()()),
//          <.div(
//            ^.margin := "-8px",
//            <.div(
//              Style.flexbox.flex,
//              renderIsLoadingRow(Button.Style.Full(_, isLoading = isLoading)),
//              renderIsLoadingRow(Button.Style.Minimal(_, isLoading = isLoading))
//            ),
//            <.div(
//              Style.flexbox.flex,
//              <.div(Style.padding.all8, Button(style = full.copy(icon = icon, size = Button.Size.Fix24))("Text")),
//              <.div(Style.padding.all8, Button(style = full.copy(icon = icon))("Text")),
//              <.div(Style.padding.all8, Button(style = full.copy(icon = icon))()),
//              <.div(Style.padding.all8, Button(style = full.copy(icon = icon, size = Button.Size.Fix40))("Text"))
//            )
//          )
//        )
//      }
//    )()
//  }
//
//  def render(ctl: Pages.Ctl): VdomElement = {
//    <.div(
//      Header("Button", Some(Button))(),
//      Toc(headings = Source.getTocHeadings)(),
//      ExampleSimple()(renderColor()),
//      ExampleSimple()(renderSize()),
//      ExampleSimple()(renderIsLoading())
//    )
//  }
//}
