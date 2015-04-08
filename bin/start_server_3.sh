#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

LIBDIR=/home/dsmith/data/apachecon/talk/mydemo/project/happy-gemfire-server/build/install/happy-gemfire-server/lib

CLASSPATH=$LIBDIR/../happy-gemfire-server-0.1.0.jar:\
$LIBDIR/happy-common-0.1.0.jar:\
$LIBDIR/spring-core-4.1.6.RELEASE.jar:\
$LIBDIR/aopalliance-1.0.jar:\
$LIBDIR/commons-logging-1.2.jar:\
$LIBDIR/spring-aop-4.1.6.RELEASE.jar:\
$LIBDIR/spring-beans-4.1.6.RELEASE.jar


echo $CLASSPATH

mkdir -p "$BASEDIR/../output/server3"
gfsh start server --name=server3 --server-port=0 --spring-xml-location="/cache-context.xml" --classpath="$CLASSPATH" --dir="$BASEDIR/../output/server3" --locators="localhost[10334]" --mcast-port=0
