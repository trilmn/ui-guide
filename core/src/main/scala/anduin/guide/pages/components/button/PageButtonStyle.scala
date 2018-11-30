package anduin.guide.pages.components.button

import anduin.guide.components._
import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.Icon
import anduin.component.icon.Icon.NameUpload
import anduin.component.menu.{Menu, MenuItem}
import anduin.component.portal.PositionBottomLeft
import anduin.component.modal.{Modal, ModalBody, ModalFooterWCancel}
import anduin.component.popover.Popover
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, React}

object PageButtonStyle {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "ButtonStyle", obj = Some(ButtonStyle))()
      ),
      Markdown(
        """
          |ButtonStyle is not a component, so it cannot be used on its own.
          |
          |Instead, you use ButtonStyle's values (e.g. ColorBlue or
          |StyleMinimal) to customize other components that support them (e.g.
          |Button or ButtonLink):
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.width.px256,
          Button(
            color = ButtonStyle.ColorBlue,
            size = ButtonStyle.Size40,
            isFullWidth = true
          )("Create Transaction")
        )
      }))(),
      Markdown(
        """
          |# Style
          |
          |The `style` prop controls a button's overall style. There are 3
          |options:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Button(style = ButtonStyle.StyleFull)("Full Style"), Style.margin.right16),
          <.div(Button(style = ButtonStyle.StyleMinimal)("Minimal Style"), Style.margin.right16),
          <.div(Button(style = ButtonStyle.StyleLink)("Link Style"), Style.margin.right16)
        )
      }))(),
      Markdown(
        """
          |**StyleFull** is the default value, which has strong visual hint.
          |Use StyleFull to make your button attractive and stand out from
          |other types of elements.
        """.stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          Button(
            color = ButtonStyle.ColorBlue
          )(
            Icon(name = NameUpload)(),
            <.span(Style.margin.left8, "Add Closing Book")
          ),
          <.p(
            Style.margin.left16,
            "After uploaded, it will be available to all investors"
          )
        )
      ),
      Markdown(
        """
          |**StyleMinimal** has little visual hint, so it should only be used
          |when the surrounding context already provided enough hint for
          |interaction.
          """.stripMargin
      )(), {
        val button = Button(size = ButtonStyle.SizeIcon, style = ButtonStyle.StyleMinimal)
        val sep = <.div(Style.margin.left8.padding.left8.border.left.borderWidth.px2.borderColor.gray4)
        ExampleSimple(
          """
            |Toolbar is a good use case of StyleMinimal: it is clear from the
            |arrangement that these icons are actions.
          """.stripMargin
        )(
          <.div(
            Style.flexbox.flex,
            button(Icon(name = Icon.NameAlignLeft)()),
            button(Icon(name = Icon.NameAlignCenter)()),
            button(Icon(name = Icon.NameAlignRight)()),
            button(Icon(name = Icon.NameAlignJustify)()),
            sep,
            button(Icon(name = Icon.NameBold)()),
            button(Icon(name = Icon.NameItalic)()),
            button(Icon(name = Icon.NameUnderline)()),
            button(Icon(name = Icon.NameStrikeThrough)()),
            sep,
            button(Icon(name = Icon.NameListBullet)()),
            button(Icon(name = Icon.NameListNumber)()),
            button(Icon(name = Icon.NameTable)())
          )
        )
      },
      Markdown(
        """
          |**StyleLink** makes the button looks exactly like a link. This should be used when your actions are part of a sentence or a paragraph (i.e. inline elements):
          """.stripMargin
      )(),
      ExampleSimple()(
        <.p(
          "John has not accepted your invitation yet. You can ",
          Button(
            onClick = Callback.alert("Reminded"),
            color = ButtonStyle.ColorBlue,
            style = ButtonStyle.StyleLink
          )("remind him"),
          " or ",
          Button(
            onClick = Callback.alert("Cancelled"),
            color = ButtonStyle.ColorRed,
            style = ButtonStyle.StyleLink
          )("cancel it"),
          "."
        )
      ),
      Markdown(
        """
          |# Color
          |
          |Use the `color` prop to communicate the intention or result of the
          |action. There are 5 options, with ColorNeutral/ColorWhite is the
          |default one:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.left16
        <.div(
          Style.flexbox.flex,
          <.div(Button()("Neutral")),
          <.div(Button(color = ButtonStyle.ColorBlue)("Blue"), margin),
          <.div(Button(color = ButtonStyle.ColorGreen)("Green"), margin),
          <.div(Button(color = ButtonStyle.ColorOrange)("Orange"), margin),
          <.div(Button(color = ButtonStyle.ColorRed)("Red"), margin)
        )
      }))(),
      Markdown(
        """
          |**There should only be one Colored StyleFull in each view.** 
          |Because the combination of StyleFull and a color (other than 
          |Neutral) provides the strongest visual hint, so multiple of them 
          |could lead to confusion.
        """.stripMargin
      )(),
      ExampleSimple("""
                      |**GOOD:** Users know which is the Blue action that should be
                      |taken
        """.stripMargin)(
        <.div(
          Style.flexbox.flex,
          <.div(Button(color = ButtonStyle.ColorBlue)("Create Transaction")),
          <.div(Button()("Create Organization"), Style.margin.left8)
        )
      ),
      ExampleSimple("""
                      |**NOT GOOD:** Users don't know which action should be taken
        """.stripMargin)(
        <.div(
          Style.flexbox.flex,
          <.div(Button(color = ButtonStyle.ColorBlue)("Create Transaction")),
          <.div(Button(color = ButtonStyle.ColorGreen)("Create Organization"), Style.margin.left8)
        )
      ),
      Markdown(
        """
          |**ColorRed + StyleFull should be saved for confirmation
          |action.** For example, the "Remove" button itself can be Neutral
          |since it is not the final destructive action.
          """.stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex.flexbox.justifyBetween.flexbox.itemsCenter,
          <.p("John (john.doe@yourco.com) was added 2 days ago."),
          Modal(
            title = "Confirm Removing",
            renderTarget = open => Button(onClick = open)("Remove"),
            renderContent = close =>
              React.Fragment(
                ModalBody()(
                  "After removed, John can not see any information about" +
                    "this transaction, including his past activity."
                ),
                ModalFooterWCancel(cancel = close) {
                  val onClick = Callback.log("John was removed") >> close
                  Button(onClick = onClick, color = ButtonStyle.ColorRed)("Remove John")
                }
            )
          )()
        )
      ),
      Markdown(
        """
          |**Color works with all types of Style:**
        """.stripMargin
      )(), {
        val margin = Style.margin.left8
        val sep = <.div(Style.margin.ver16.border.top.borderColor.gray2.borderWidth.px2)
        ExampleSimple()(
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.p(Style.flexbox.none.width.pc10, "Full"),
            <.div(Button()("Default"), margin),
            <.div(Button(color = ButtonStyle.ColorBlue)("Blue"), margin),
            <.div(Button(color = ButtonStyle.ColorGreen)("Green"), margin),
            <.div(Button(color = ButtonStyle.ColorOrange)("Orange"), margin),
            <.div(Button(color = ButtonStyle.ColorRed)("Red"), margin)
          ),
          sep,
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.p(Style.flexbox.none.width.pc10, "Minimal"),
            <.div(Button(style = ButtonStyle.StyleMinimal)("Default"), margin),
            <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorBlue)("Blue"), margin),
            <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorGreen)("Green"), margin),
            <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorOrange)("Orange"), margin),
            <.div(Button(style = ButtonStyle.StyleMinimal, color = ButtonStyle.ColorRed)("Red"), margin)
          ),
          sep,
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            <.p(Style.flexbox.none.width.pc10, "Link"),
            <.div(Button(style = ButtonStyle.StyleLink)("Default"), margin),
            <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorBlue)("Blue"), margin),
            <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorGreen)("Green"), margin),
            <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorOrange)("Orange"), margin),
            <.div(Button(style = ButtonStyle.StyleLink, color = ButtonStyle.ColorRed)("Red"), margin)
          )
        )
      },
      Markdown(
        """
          |# Size
          |
          |Default size is `Size32`, which should work most of the time. Use 
          |`Size40` to enlarge button in spacious context.
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.flexbox.flex,
            <.div(Button(size = ButtonStyle.Size24)("24"), Style.margin.right16),
            <.div(Button()("Medium"), Style.margin.right16),
            <.div(Button(size = ButtonStyle.Size40)("Large"))
          )
        )
      )(),
      Markdown(
        """
          |
          |There is also a `SizeIcon` value to make icon-only button looks
          |square, which is covered in [With icon](#with-icon) section below.
          |
          |# isFullWidth
          |
          |Although being a `block` element, the width of a ButtonStyle
          |element depends on its content size by default (`max-content` to
          |be exact).
          |
          |Set `isFullWidth = true` if you want the button's width to be 100%
          |of its parent. This comes in handy in some situations like a
          |vertical form.
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.width.px256,
            <.div(Button(isFullWidth = true, color = ButtonStyle.ColorBlue)("Submit form")),
            <.div(Button(isFullWidth = true)("Add more documents"), Style.margin.top8)
          )
        )
      )(),
      Markdown(
        """
          |Out of the box, the content of a full-width button will be horizontally centered.
          |
          |# isSelected
          |
          |Use the `isSelected` prop to have the `active` style always
          |visible. This is useful when the button is being used to toggle
          |something on and off, like a popover:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Popover(
          position = PositionBottomLeft,
          verticalOffset = 4,
          renderTarget = (open, isOpened) => Button(onClick = open, isSelected = isOpened)("More"),
          renderContent = _ => Menu()(MenuItem()("Copy"), MenuItem()("Paste"))
        )()
      }))(),
      Markdown(
        """
          |This works with both StyleFull and StyleMinimal, as well as all 
          |values of Color:
        """.stripMargin
      )(),
      ExampleSimple()({
        val margin = Style.margin.left16
        <.div(
          <.div(
            Style.flexbox.flex,
            <.div(Button(isSelected = true)("Full")),
            <.div(Button(isSelected = true, style = ButtonStyle.StyleMinimal)("Minimal"), margin)
          ),
          <.div(
            Style.flexbox.flex.margin.top16,
            <.div(Button(isSelected = true)("Neutral")),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorBlue)("Blue"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorGreen)("Green"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorOrange)("Orange"), margin),
            <.div(Button(isSelected = true, color = ButtonStyle.ColorRed)("Red"), margin)
          )
        )
      }),
      Markdown(
        """
          |That being said, isSelected does not work with `StyleLink` as it
          |would not make any sense (a link does not have "disabled" state in
          |the world of web).
          |
          |# Content
          |
          |## With icon
          |
          |It's common to have icon inside a button to add information. In
          |these cases, simply put the icon on left or right side of the
          |text, with proper spacing applied on either text or icon.
        """.stripMargin
      )(),
      ExampleRich(
        // format: off
        Source.annotate({
          /*>*/val icon = Icon(name = Icon.NameLightBolt)()
          val margin = Style.margin.right8
          <.div(Style.flexbox.flex,
            <.div(/*<*/Button()(<.span(margin, icon), "Text"/*>*/), Style.margin.right16),
            <.div(/*<*/Button()(<.span(margin, "Text"), icon)/*>*/))/*<*/
        })
        // format: on
      )(),
      Markdown(
        """
          |**Button can also be icon-only.** In these cases make sure the 
          |action can be clearly implied without text. Icon-only are often 
          |being used as a row, like in a toolbar, so their meaning can 
          |support each other.
          |
          |By default, button has unequal vertical and horizontal padding, 
          |which result in a rectangle for icon-only case. This might not 
          |look nice in some cases like toolbar. In such cases, use `size = 
          |ButtonStyle.SizeIcon` to make it square.
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val icon = Icon(name = Icon.NameLightBolt)()
          <.div(
            Style.flexbox.flex,
            <.div(Button()(icon), Style.margin.right16),
            <.div(Button(size = ButtonStyle.SizeIcon)(icon))
          )
        })
      )(),
      Markdown(
        """
          |## With isDisabled
          |
          |ButtonStyle observes the component's `disabled` state (usually set
          |via `isDisabled` prop) to provide correct "disabled" styling:
          |""".stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(Button(isDisabled = true)("Foo"))
        )
      )(),
      Markdown(
        """
          |ButtonStyle takes other props (e.g. Color, Style) into account 
          |when calculating the disabled style. For example, each Style value
          |has a different disabled appearance:
          |""".stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex,
          <.div(Button(isDisabled = true)("Default"), Style.margin.right16),
          <.div(Button(isDisabled = true, style = ButtonStyle.StyleMinimal)("Minimal")()),
          <.div(Button(isDisabled = true, style = ButtonStyle.StyleLink)("Link")())
        )
      ),
      Markdown(
        """
          |That being said, different options of Color intentionally share
          |the same disabled style:
          |""".stripMargin
      )(),
      ExampleSimple()(
        <.div(
          Style.flexbox.flex,
          <.div(Button(isDisabled = true)("Default"), Style.margin.right16),
          <.div(Button(isDisabled = true, color = ButtonStyle.ColorBlue)("Blue"), Style.margin.right16),
          <.div(Button(isDisabled = true, color = ButtonStyle.ColorGreen)("Green"), Style.margin.right16)
        )
      )
    )
  }
}
