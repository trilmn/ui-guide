package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object PageButton {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Button", Some(Button))(),
      Toc(
        headings = PageButtonType.getHeadings ++
          PageButtonStyle.getHeadings ++
          PageButtonDisabled.getHeadings
      )(),
      Markdown(
        """
          |Button lets users take action:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Button(
          onClick = Callback.alert("Hello")
        )("Say Hi")
      }))(),
      PageButtonType()(),
      PageButtonStyle(ctl)(),
      PageButtonDisabled()()
    )
  }
}
