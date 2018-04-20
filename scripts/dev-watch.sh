#!/usr/bin/env bash

. ./scripts/_prepare.sh

# CSS
echo "[info] Compiling CSS ..."
sass ${core_css_main} ${core_css_fastopt}

# JS
echo "[info] Starting sbt shell with ~fastOptJS ..."
echo "~fastOptJS" | sbt