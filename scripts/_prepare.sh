#!/usr/bin/env bash

core_src=./core/src/main

core_res=${core_src}/resources
core_css=${core_res}/stylesheets
core_css_main=${core_css}/main.scss

core_js=${core_src}/scala/anduin

core_target=./core/target/scala-2.12/scalajs-bundler/main

docs=./docs

postcss_config=./scripts/postcss.config.js