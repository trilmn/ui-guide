#!/usr/bin/env bash

. ./scripts/_prepare.sh

# Clean
echo "[info] Clearing previous generation ..."
rm -rf ${docs}
mkdir ${docs}

# Resources
echo "[info] Copying resources (public folder content) ..."
cp -R ${core_res}/public/. ${docs}/

# CSS
echo "[info] Compiling CSS ..."
postcss ${core_css_main} --config ${postcss_config} --output ${docs}/app.css

# JS
echo "[info] Compiling JS (fullOpt) ..."
sbt fullOptJS::webpack
echo "[info] Copying JS ..."
cp ${core_target}/core-opt-bundle.js ${docs}/app.js

echo "[success] Successfully built"