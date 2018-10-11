#!/usr/bin/env bash

. ./scripts/_prepare.sh

# === Find out where is stargazer
sgz_src_config=sgz_src.txt
if [ ! -f ${sgz_src_config} ]; then
  echo "Please enter the absolute path to stargazer on your local:"
  read sgz_src
  echo ${sgz_src} > ${sgz_src_config}
else
  read -r sgz_src < ${sgz_src_config}
  echo "Found stargazer at ${sgz_src}."
fi

# === Link Config
echo "[info] Linking Config: .scalafmt.conf"
ln -sf ${sgz_src}/.scalafmt.conf ./

# === Link CSS

sgz_css=${sgz_src}/gondor/webResources/src/main/assets/stylesheets

echo "[info] Linking CSS: Tachyons ..."
ln -sf ${sgz_css}/tachyons ${core_css}/

echo "[info] Linking CSS: Base ..."
ln -sf ${sgz_css}/base/_base.scss ${core_css}/

# === Link JS (Style and Component)

sgz_js=${sgz_src}/platform/stargazerJs/src/main/scala/anduin

# Style
echo "[info] Linking JS: styles at anduin.style ..."
ln -sf ${sgz_js}/style ${core_js}/

# JS Dependency (For Component)
echo "[info] Linking JS: facades at anduin.scalajs ..."
sgz_js_dep=${sgz_src}/platform/stargazerJSDependency/src/main/scala/anduin/scalajs
deps=(
  popper/Popper.scala
  popper/PopperPlacement.scala
  popper/PopperModifiers.scala

  downshift/Downshift.scala
  downshift/DownshiftRender.scala
  downshift/DownshiftState.scala

  textmask/TextMask.scala
  textmask/TextMaskAddons.scala
  textmask/ReactTextMask.scala

  reacttruncatemarkup/ReactTruncateMarkup.scala
  focusvisible/FocusVisible.scala

  util/Util.scala
)
# @TODO: Ask Keimoon or Tue how to reuse the below func
for name in ${deps[*]}
do
    pkg="$(echo ${name} | rev | cut -d"/" -f2- | rev)"
    mkdir -p ${core_js}/scalajs/${pkg}
    ln -sf ${sgz_js_dep}/${name} ${core_js}/scalajs/${name}
done

# Component
echo "[info] Linking JS: components at anduin.component ..."
components=(
  button/Button.scala
  button/ButtonLink.scala
  button/ButtonStyle.scala

  dropdown/Dropdown.scala
  dropdown/DropdownContent.scala
  dropdown/DropdownOption.scala
  dropdown/DropdownFilter.scala
  dropdown/DropdownTarget.scala
  dropdown/DropdownMeasure.scala
  dropdown/DropdownInnerProps.scala

  container/Card.scala
  container/Collapse.scala
  container/Tab.scala
  container/TabFull.scala
  container/Well.scala

  container/Table.scala
  container/TableBody.scala
  container/TableHead.scala
  container/TableSticky.scala

  icon/Icon.scala
  icon/Illus.scala
  icon/IllusFolder.scala

  input/Checkbox.scala
  input/CustomDropdown.scala
  input/NativeDropdown.scala
  input/FileButtonInput.scala
  input/Radio.scala
  input/RadioBox.scala

  input/textbox/TextBox.scala
  input/textbox/TextBoxStyle.scala
  input/textbox/TextBoxMask.scala

  menu/Menu.scala
  menu/MenuDivider.scala
  menu/MenuItem.scala
  menu/VerticalDivider.scala

  portal/Modal.scala
  portal/ModalBody.scala
  portal/ModalFooter.scala
  portal/ModalFooterWCancel.scala
  portal/ModalHeader.scala
  portal/Popover.scala
  portal/Portal.scala
  portal/PortalContent.scala
  portal/PortalPopper.scala
  portal/PortalUtils.scala
  portal/Position.scala
  portal/Tooltip.scala

  text/Tag.scala
  text/DateTime.scala

  truncate/TruncateMarkup.scala

  util/EventUtils.scala
  util/NodeListSeq.scala
  util/ComponentUtils.scala
)
for name in ${components[*]}
do
    pkg="$(echo ${name} | rev | cut -d"/" -f2- | rev)"
    mkdir -p ${core_js}/component/${pkg}
    ln -sf ${sgz_js}/component/${name} ${core_js}/component/${name}
done

echo "[success] Successfully linked"
