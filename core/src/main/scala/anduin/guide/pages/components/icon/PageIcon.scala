package anduin.guide.pages.components.icon

import anduin.component.icon.Icon
import anduin.guide.app.main.Pages
import anduin.guide.components._
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIcon {

  private def renderSetSize(name: Icon.Name)(size: Icon.Size): VdomElement = {
    <.div(^.key := size.getClass.getSimpleName, Style.margin.right4, Icon(name, size)())
  }

  private def renderSetName(sizes: Seq[Icon.Size])(name: Icon.Name): VdomElement = {
    <.div(
      ^.key := name.getClass.getSimpleName,
      Style.flexbox.flex.flexbox.itemsEnd.margin.right20,
      sizes.toVdomArray(renderSetSize(name))
    )
  }

  private def renderSet(sizes: Seq[Icon.Size], names: Icon.Name*): VdomElement = {
    ExampleSimple()(
      <.div(
        Style.flexbox.flex.color.gray7,
        names.toVdomArray(renderSetName(sizes))
      )
    )
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Icon", Some(Icon))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Icons symbolizes common actions and objects:
          |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(name = Icon.Glyph.Download)()
      }))(),
      Markdown(
        s"""
           |# Name
           |
           |```scala
           |name: Icon.Name
           |```
           |
           |The `name` prop of an Icon defines what will be rendered. There
           |are 3 sets of Icon's names:
           |
           |### [`Icon.Glyph`](${ctl.urlFor(Pages.IconGlyph()).value})
           |""".stripMargin
      )(), {
        import Icon.Glyph._
        renderSet(Vector(Icon.Size.Px16), Check, Cross, Download, LightBolt, Reply, Star, DataRoom, Vault, Question, Anduin)
      },
      Markdown(
        s"""
           |`Icon.Glyph` is the main set. It contains many generic icons that
           |should be used primarily around the platform. Learn more at the
           |[Glyph Icons][l] page.
           |
           |[l]: ${ctl.urlFor(Pages.IconGlyph()).value}
           |
           |### [`Icon.Nego`](${ctl.urlFor(Pages.IconNego()).value})
        """.stripMargin
      )(), {
        import Icon.Nego._
        renderSet(Vector(Icon.Size.Px24), RequestDocs, UploadDoc, ReviewDoc, MarkAsFinal, RequestToSign, Sign, ShareBankInfo, ConfirmFundReceived)
      },
      Markdown(
        s"""
           |`Icon.Nego` contains enhanced versions of negotiation action icons.
           |They work best at 24 pixels. Learn more at the [Negotiation
           |Icons][l] page.
           |
           |[l]: ${ctl.urlFor(Pages.IconNego()).value}
           |
           |### [`Icon.File`](${ctl.urlFor(Pages.IconFile()).value})
        """.stripMargin
      )(), {
        import Icon.Size._
        import Icon.File._
        renderSet(Vector(Px32, Px24, Px16), Word, Categorized, Final, Folder)
      },
      Markdown(
        s"""
           |`Icon.File` contains enhanced versions of file and folder icons.
           |They are colorful and usually have different designs for each size.
           |Learn more at the [File Icons][l] page.
           |
           |[l]: ${ctl.urlFor(Pages.IconFile()).value}
           |
           |""".stripMargin
      )(),
      Markdown(
        s"""
           |# Size
           |
           |```scala
           |size: Icon.Size = Icon.Size.Px16
           |```
           |
           |The `size` prop controls the size of an Icon. There are:
           |
           |- Fixed values: `Px16` (default), `Px24`, `Px32`
           |- and a custom one: `Custom(px: Int)`
           |
           |Depend on each [name](#name), the Icon might have a design defined
           |at selected size, or will use a scaled one from another size.
           |For example, [File Icons] usually have specific designs at each
           |size:
           |
           |[File Icons]: ${ctl.urlFor(Pages.IconFile()).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex.flexbox.itemsEnd,
          <.div(Icon(name = Icon.File.Signed, size = Icon.Size.Px32)(), Style.margin.right8),
          <.div(Icon(name = Icon.File.Signed, size = Icon.Size.Px24)(), Style.margin.right8),
          <.div(Icon(name = Icon.File.Signed, size = Icon.Size.Px16)(), Style.margin.right8)
        )
      }))(),
      Markdown(
        s"""
          |[Negotiation Icons], on the other hand, only have designs at 24
          |pixels, which will be scaled to 16 or 32 pixels when used:
          |
          |[Negotiation Icons]: ${ctl.urlFor(Pages.IconNego()).value}
          |""".stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex.flexbox.itemsEnd,
          <.div(Icon(name = Icon.Nego.Redline, size = Icon.Size.Px32)(), Style.margin.right8),
          <.div(Icon(name = Icon.Nego.Redline, size = Icon.Size.Px24)(), Style.margin.right8),
          <.div(Icon(name = Icon.Nego.Redline, size = Icon.Size.Px16)(), Style.margin.right8)
        )
      ),
      Markdown(
        """
          |Detail about which designs are defined at which size, as well as
          |their previews, can be found at each Icon set page.
        """.stripMargin
      )()
    )
  }

}
