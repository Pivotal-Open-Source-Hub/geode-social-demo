#!/bin/bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
PROJECT_HOME="$(dirname "$SCRIPT_DIR")"

export GEMFIRE_HOME=$PROJECT_HOME/incubator-geode/gemfire-assembly/build/install/apache-geode/
export PATH=$PATH:$GEMFIRE_HOME/bin

# Setup your java home if necessary
#export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
#export PATH=$JAVA_HOME/bin:$PATH
