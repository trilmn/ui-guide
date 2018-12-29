#!/usr/bin/env bash

# ${sgz_js_dep}
# ${sgz_js}/component
source=$1
# ${core_js}/scalajs
# ${core_js}/component
target=$2
# (button, dropdown, ...)
path_name=$3

. ./scripts/link/${path_name}.sh

mkdir -p ${target}
for path in ${link_paths[*]}
do
  # create intermediate directory if necessary
  if [[ ${path} == *"/"* ]]; then
    parent_path="$(echo ${path} | rev | cut -d"/" -f2- | rev)"
    mkdir -p ${target}/${parent_path}
  fi

  # suffix
  if [[ ${path} == *".scala" ]]; then
    ln -sf ${source}/${path} ${target}/${path}
  elif [[ ${path} == *"/"* ]]; then
    parent_path="$(echo ${path} | rev | cut -d"/" -f2- | rev)"
    ln -sf ${source}/${path} ${target}/${parent_path}/
  else
    ln -sf ${source}/${path} ${target}/
  fi
done
