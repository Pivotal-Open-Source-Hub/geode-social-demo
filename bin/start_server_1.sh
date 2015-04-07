#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

LIBDIR=`pwd`/$BASEDIR/../workspace/happy-gemfire-server/build/install/happy-gemfire-server/lib
RESOURCE_DIR=`pwd`/$BASEDIR/../workspace/happy-gemfire-server/src/main/resources

CLASSPATH=$LIBDIR/antlr-2.7.7.jar:\
$LIBDIR/aopalliance-1.0.jar:\
$LIBDIR/aspectjweaver-1.8.2.jar:\
$LIBDIR/happy-common-0.1.0.jar:\
$LIBDIR/happy-gemfire-server-0.1.0.jar:\
$LIBDIR/jackson-core-asl-1.9.12.jar:\
$LIBDIR/jackson-mapper-asl-1.9.12.jar:\
$LIBDIR/jcl-over-slf4j-1.7.11.jar:\
$LIBDIR/jul-to-slf4j-1.7.11.jar:\
$LIBDIR/log4j-over-slf4j-1.7.11.jar:\
$LIBDIR/logback-classic-1.1.3.jar:\
$LIBDIR/logback-core-1.1.3.jar:\
$LIBDIR/slf4j-api-1.7.11.jar:\
$LIBDIR/snakeyaml-1.14.jar:\
$LIBDIR/spring-aop-4.1.6.RELEASE.jar:\
$LIBDIR/spring-beans-4.1.6.RELEASE.jar:\
$LIBDIR/spring-boot-1.2.3.RELEASE.jar:\
$LIBDIR/spring-boot-autoconfigure-1.2.3.RELEASE.jar:\
$LIBDIR/spring-boot-starter-1.2.3.RELEASE.jar:\
$LIBDIR/spring-boot-starter-data-gemfire-1.2.3.RELEASE.jar:\
$LIBDIR/spring-boot-starter-logging-1.2.3.RELEASE.jar:\
$LIBDIR/spring-context-4.1.6.RELEASE.jar:\
$LIBDIR/spring-context-support-4.1.6.RELEASE.jar:\
$LIBDIR/spring-core-4.1.6.RELEASE.jar:\
$LIBDIR/spring-data-commons-1.9.2.RELEASE.jar:\
$LIBDIR/spring-data-gemfire-1.5.2.RELEASE.jar:\
$LIBDIR/spring-expression-4.1.6.RELEASE.jar:\
$LIBDIR/spring-tx-4.1.6.RELEASE.jar


echo $CLASSPATH

mkdir "$BASEDIR/../output/server1"
gfsh start server --name=server1 --server-port=0 --spring-xml-location="/cache-context.xml" --classpath="$CLASSPATH" --dir="$BASEDIR/../output/server1" --locators="localhost[10334]"
