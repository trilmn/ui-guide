#!/usr/bin/env bash

. ./scripts/_prepare.sh

echo "[info] Clearing linked JS ..."
rm -rf ${core_js}/style
rm -rf ${core_js}/component
rm -rf ${core_js}/scalajs

echo "[info] Clearing linked CSS ..."
rm -rf ${core_css}/tachyons
rm -f ${core_css}/_base.scss

echo "[info] Clearing generated: production ..."
rm -rf ${docs}

echo "[info] Clearing sbt (target) ..."
sbt clean >/dev/null 2>&1

echo "[success] Successfully cleaned"
