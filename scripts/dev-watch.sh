#!/usr/bin/env bash

. ./scripts/_prepare.sh

# CSS
echo "[info] Compiling CSS ..."
postcss ${core_css_main} --config ${postcss_config} --output ${core_target}/core-fastopt.css

# JS
echo "[info] Starting sbt shell with ~fastOptJS ..."
echo "~fastOptJS" | sbt