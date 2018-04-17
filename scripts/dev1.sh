#!/usr/bin/env bash

source ./scripts/_prepare.sh

echo -e "[\e[33mwarn\e[0m] This one compiles CSS and start ~fastOptJS."
echo -e "[\e[33mwarn\e[0m] Please have another tab for \"dev-2\"."

# CSS
echo "[info] Compiling CSS ..."
sass ${core_css_main} ${core_css_fastopt}

# JS
echo "[info] Starting sbt shell with ~fastOptJS ..."
echo "~fastOptJS" | sbt