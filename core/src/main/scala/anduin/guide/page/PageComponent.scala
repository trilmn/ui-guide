package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageComponent {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(
          title = "Components",
          description =
            """
              |Components are generic, independent pieces of UI that helps you reuse styles and functionality.
            """.stripMargin
        )()
      ),
      Markdown(
        """
          |>::warning::**This guide refers to global components:** those that available platform-wide under `anduin.component` package.
          |>
          |>There are also module-level packages, like `anduin.module.entity.EntityLogo`. Although having some common behaviours, we don't cover these packages in this guide.
          |
          |## Components are global packages
          |
          |They should be available anywhere in our platform.
          |
          |```scala
          |import anduin.component.button.Button
          |import anduin.component.icon.IconAcl
          |
          |// Related components are grouped under the same package
          |import anduin.component.portal.{Modal, Tooltip, Popover}
          |```
          |
          |## Components are free from business logic.
          |
          |As mentioned above, components in this guide are global ones, in the mean that their functionality and styles should not be tied to a specific module or business feature. Components should be neutral and generic to be used anywhere.
          |
          |A good rule of thumb is that if your component needs to `import` something from other than `anduin.style` and `anduin.component`, then it should not be global components.
          |
          |## Components usually have `VdomNode*` as their children's type.
          |
          |`VdomNode` is the equivalent of React's `element` or `node`. This means only render-able contents (e.g: `VdomElement`, `VdomNode` such as text…) can be used as a component's children.
          |
          |We intentionally avoid using `TagMod` here to prevent the consumer from directly modifying classes or attributes of the component.
          |
          |```scala
          |Button(...)(
          |  IconAcl(...)(), // GOOD
          |  "Upload", // GOOD
          |  Style.margin.right8, // BAD
          |)
          |```
          |
          |Learn more:
          |
          |- [`​VdomNode` vs `TagMod​`](https://github.com/japgolly/scalajs-react/blob/master/doc/VDOM.md#types)
          |- [​React's `Components` vs `Elements​`](https://reactjs.org/blog/2015/12/18/react-components-elements-and-instances.html)
          |
          |## Components classes
          |
          |For example, to have
          |
          |<div(
          |    Style.flexbox.flex.flexbox.itemsCenter,
          |    <.span( /* wrapper of Icon */
          |      Style.margin.right2, /* space for Icon */
          |      Icon(name = Icon.NameUpload)()
          |    ),
          |    "Upload"
          |  )
          |})
          |​
          |
          |## Components are  block-level elements.
          |
          |Even those that used to be inline-level, like Button and Icon. This is to avoid the default partly vertical alignment of inline, which often leads engineers to mistakenly thought that it is good enough, which in fact is far from.
          |
          |The block display is intentional to
          |
          |The Why block article explains this in detail.
        """.stripMargin)()
    )
  }
}
