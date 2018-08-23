#!/usr/bin/env bash

. ./scripts/_prepare.sh


# Resources
echo "[info] Copying resources ..."
cp ${core_res}/404.html ${docs}/
cp ${core_res}/index-opt.html ${docs}/index.html
cp ${core_res}/icon.png ${docs}/
cp ${core_res}/CNAME ${docs}/

# CSS
echo "[info] Compiling CSS ..."
postcss ${core_css_main} --config ${postcss_config} --output ${docs}/app.css

# JS
echo "[info] Compiling JS (fullOpt) ..."
sbt fullOptJS
echo "[info] Copying JS ..."
cp ${core_target}/core-opt.js ${docs}/app.js
cp ${core_target}/core-jsdeps.min.js ${docs}/dep.js

echo "[success] Successfully built"