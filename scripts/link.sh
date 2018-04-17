#!/usr/bin/env bash

source ./scripts/_prepare.sh

echo "Please enter the absolute path to stargazer on your local:"
read sgz_src

# === Link CSS

sgz_css=${sgz_src}/gondor/webResources/src/main/assets/stylesheets

echo "[info] Linking CSS: Tachyons ..."
ln -sf ${sgz_css}/tachyons ${core_css}/

echo "[info] Linking CSS: Base ..."
ln -sf ${sgz_css}/base/_base.scss ${core_css}/

# === Link JS (Style and Component)

sgz_js=${sgz_src}/platform/stargazerJs/src/main/scala/anduin

# Style
echo "[info] Linking JS: anduin.style ..."
ln -sf ${sgz_js}/style ${core_js}/

# Component
echo "[info] Linking JS: anduin.component ..."
components=(
  button/Button.scala
  icon/IconAcl.scala
  util/ComponentUtils.scala
)
for name in ${components[*]}
do
    pkg="$(echo ${name} | rev | cut -d"/" -f2- | rev)"
    mkdir -p ${core_js}/component/${pkg}
    ln -sf ${sgz_js}/component/${name} ${core_js}/component/${name}
done

echo -e "[\e[32msuccess\e[0m] Successfully linked"