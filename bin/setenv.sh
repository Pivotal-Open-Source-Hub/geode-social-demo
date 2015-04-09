#!/bin/bash

#export JAVA_HOME=/home/dan/software/jdk1.8.0_40
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

#export PROJECT_HOME=/home/dan/MyStuff/Code/geode-demo
export PROJECT_HOME=/home/dsmith/data/apachecon/talk/mydemo/project


export GEMFIRE_HOME=$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/
export PATH=$JAVA_HOME/bin:$PATH
export PATH=$PATH:$GEMFIRE_HOME/bin
