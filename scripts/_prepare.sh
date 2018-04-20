#!/usr/bin/env bash

core_src=./core/src/main

core_res=${core_src}/resources
core_css=${core_res}/stylesheets
core_css_tachyons=${core_css}/tachyons
core_css_base=${core_css}/_base.scss
core_css_main=${core_css}/main.scss
core_css_fastopt=${core_css}/core-fastopt.css

core_js=${core_src}/scala/anduin
core_js_style=${core_js}/style
core_js_component=${core_js}/component

core_target=./core/target/scala-2.12

docs=./docs