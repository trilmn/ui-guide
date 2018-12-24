#!/usr/bin/env bash

. ./scripts/_prepare.sh

# === Find out where is stargazer
sgz_src_config=sgz_src.txt
if [[ ! -f ${sgz_src_config} ]]; then
  echo "Please enter the absolute path to stargazer on your local:"
  read sgz_src
  echo ${sgz_src} > ${sgz_src_config}
else
  read -r sgz_src < ${sgz_src_config}
  echo "Found stargazer at ${sgz_src}."
fi

# === Link Config
echo "[info] Linking .scalafmt.conf..."
ln -sf ${sgz_src}/.scalafmt.conf ./

# === Link CSS
sgz_css=${sgz_src}/gondor/webResources/src/main/assets/stylesheets

echo "[info] Linking tachyons..."
ln -sf ${sgz_css}/tachyons ${core_css}/

echo "[info] Linking _base.scss..."
ln -sf ${sgz_css}/base/_base.scss ${core_css}/

# === Link JS
sgz_js=${sgz_src}/platform/stargazerJs/src/main/scala/anduin
sgz_js_dep=${sgz_src}/platform/stargazerJSDependency/src/main/scala/anduin/scalajs

# Style
echo "[info] Linking anduin.style..."
ln -sf ${sgz_js}/style ${core_js}/

# Facades
echo "[info] Linking anduin.scalajs..."
. ./scripts/link/link_paths.sh \
  ${sgz_js_dep} ${core_js}/scalajs facade_paths

# Components
echo "[info] Linking anduin.component ..."
. ./scripts/link/link_paths.sh \
  ${sgz_js}/component ${core_js}/component component_paths

echo "[success] Successfully linked"
