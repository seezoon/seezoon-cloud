#!/bin/bash
set -e

ROOT=$(
  cd $(dirname $0)/..
  pwd
)
cd "${ROOT}"
case $1 in
"start")
  source bin/start.sh
  ;;
"stop")
  source bin/stop.sh
  ;;
"restart")
  source bin/restart.sh
  ;;
*)
  exec "$@"
  ;;
esac
