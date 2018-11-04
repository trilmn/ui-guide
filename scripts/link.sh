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

  reactvirtualized/ReactVirtualizedAutoSizer.scala
  reactvirtualized/ReactVirtualizedList.scala

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
  card/Card.scala
  date/DateTime.scala
  dropdown/Dropdown.scala
  dropdown/DropdownContent.scala
  dropdown/DropdownFilter.scala
  dropdown/DropdownMeasure.scala
  dropdown/DropdownOption.scala
  dropdown/DropdownStateReducer.scala
  dropdown/DropdownTarget.scala
  field/Field.scala
  icon/Icon.scala
  icon/Illus.scala
  icon/IllusFolder.scala
  input/Checkbox.scala
  input/FileButtonInput.scala
  input/Radio.scala
  input/RadioBox.scala
  input/textbox/TextBox.scala
  input/textbox/TextBoxMask.scala
  input/textbox/TextBoxStyle.scala
  menu/Menu.scala
  menu/MenuDivider.scala
  menu/MenuItem.scala
  menu/VerticalDivider.scala
  portal/modal/Modal.scala
  portal/modal/ModalBody.scala
  portal/modal/ModalFooter.scala
  portal/modal/ModalFooterWCancel.scala
  portal/modal/ModalHeader.scala
  portal/popover/Popover.scala
  portal/Portal.scala
  portal/PortalContent.scala
  portal/PortalPopper.scala
  portal/PortalUtils.scala
  portal/Position.scala
  portal/tooltip/Tooltip.scala
  stepper/Stepper.scala
  stepper/StepperHeader.scala
  tab/Tab.scala
  tab/TabFull.scala
  table/Table.scala
  table/TableBody.scala
  table/TableHead.scala
  table/TableSticky.scala
  tag/Tag.scala
  toggle/Toggle.scala
  truncate/TruncateMarkup.scala
  util/ComponentUtils.scala
  util/EventUtils.scala
  util/NodeListSeq.scala
  well/Well.scala
)
for name in ${components[*]}
do
    pkg="$(echo ${name} | rev | cut -d"/" -f2- | rev)"
    mkdir -p ${core_js}/component/${pkg}
    ln -sf ${sgz_js}/component/${name} ${core_js}/component/${name}
done

echo "[success] Successfully linked"
