package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageWelcome {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Anduin UI Guide",
          description = "How we think User Interface should be built, at Anduin."
        )()
      ),
      <.div(
        Style.padding.ver32,
        <.img(
          Style.display.block.margin.horAuto,
          ^.src := "https://cdn2.hubspot.net/hubfs/2735615/Anduin_Transact_April2018/Image/anduin-deals-illustration.svg?t=1524989666671",
          TagMod(^.width := "394", ^.height := "130")
        )
      ),
      Markdown(
        """
          |>To become the de-facto standard software to execute financial transactions in [primary](https://www.investopedia.com/terms/p/primarymarket.asp) and [secondary](https://www.investopedia.com/terms/s/secondarymarket.asp) markets
          |>
          |>— Goals of Anduin Transactions
          |
          |**At Anduin, we are solving huge, complex, and segmented problems.** The complete solutions for these problems are unknown. Therefore, our strategy is to follow fast execution cycle to get feedback at the earliest. With that in mind, we build our UI libraries and guides around these principles:
          |""".stripMargin
      )(),
      <.div(
        renderGoal(
          "1. Changing",
          "eliminateErrors",
          "Human changes, so does the UI Engineering. Our elements are designed so that changes are expected, effortless, and usually automated."
        ),
        renderGoal(
          "2. Scaling",
          "generateDoc",
          "As a platform, one component could easily have hundreds of instances, while a style could have thousands. Scalability is therefore our first priority."
        ),
        renderGoal(
          "3. Delighting",
          "saveTimeAndMoney",
          "Using the library should feel like a privilege to the engineers. The library should save engineering time by solving complex UI issues via simple APIs."
        ),
        renderGoal(
          "4. Empowering",
          "alignDeal",
          "Knowing \"How to\" is only the surface. Understanding \"Why\" and become an expert in UI Engineering yourself is our ultimate goal in this guide."
        )
      )
    )
  }

  private def renderGoal(title: String, img: String, description: String): VdomElement = {
    <.div(
      Style.flexbox.flex.padding.ver32,
      <.img(
        Style.flexbox.none.margin.right32,
        ^.src := s"https://cdn2.hubspot.net/hubfs/2735615/Anduin_Transact_April2018/Image/$img.svg",
        TagMod(^.width := "104px", ^.height := "104px")
      ),
      <.div(
        Style.flexbox.fixed,
        <.h3(Style.margin.bottom8, title),
        <.p(description)
      )
    )
  }
}
