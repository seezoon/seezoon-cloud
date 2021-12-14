#!/bin/bash
set -e

version=$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q)
name=$(mvn -Dexec.executable='echo' -Dexec.args='${project.artifactId}' --non-recursive exec:exec -q)
mvn clean package
docker build -t "${name}":"${version}" .
