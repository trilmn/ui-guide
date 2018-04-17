#!/usr/bin/env bash

source ./scripts/_prepare.sh

echo -e "[\e[33mwarn\e[0m] This one starts a local server."
echo -e "[\e[33mwarn\e[0m] Please have another tab for \"dev-1\"."

# Server
echo "[info] Starting python3 http.server ..."
sudo python3 -m http.server 80