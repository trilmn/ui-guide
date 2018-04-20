#!/usr/bin/env bash

. ./scripts/_prepare.sh

echo "[info] Clearing linked JS: anduin.style ..."
rm -rf ${core_js_style}

echo "[info] Clearing linked JS: anduin.component ..."
rm -rf ${core_js_component}

echo "[info] Clearing linked CSS ..."
rm -rf ${core_css_tachyons}
rm -f ${core_css_base}

echo "[info] Clearing generated: development ..."
rm -f ${core_css_fastopt}

echo "[info] Clearing generated: production ..."
rm -rf ${docs}
mkdir ${docs}

echo "[info] Clearing sbt (target) ..."
sbt clean >/dev/null 2>&1

echo "[success] Successfully cleaned"