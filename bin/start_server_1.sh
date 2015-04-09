#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

LIBDIR=$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib

CLASSPATH=$LIBDIR/../happy-gemfire-server-0.1.0.jar:\
$LIBDIR/happy-common-0.1.0.jar:\
$LIBDIR/spring-core-4.1.6.RELEASE.jar:\
$LIBDIR/aopalliance-1.0.jar:\
$LIBDIR/commons-logging-1.2.jar:\
$LIBDIR/spring-aop-4.1.6.RELEASE.jar:\
$LIBDIR/spring-beans-4.1.6.RELEASE.jar


mkdir -p "$BASEDIR/../output/server1"
#gfsh start server --name=server1 --server-port=0 --spring-xml-location="/cache-context.xml" --classpath="$CLASSPATH" --dir="$BASEDIR/../output/server1" --locators="localhost[10334]" --mcast-port=0

#Start the server in the foregound
cd $BASEDIR/../output/server1
java -server -classpath $PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/gemfire-core-1.0.0.0-SNAPSHOT.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/../happy-gemfire-server-0.1.0.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/happy-common-0.1.0.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/spring-core-4.1.6.RELEASE.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/aopalliance-1.0.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/commons-logging-1.2.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/spring-aop-4.1.6.RELEASE.jar:$PROJECT_HOME/happy-gemfire-server/build/install/happy-gemfire-server/lib/spring-beans-4.1.6.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/gemfire-core-dependencies.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-context-support-3.2.12.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-expression-3.2.12.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-aop-3.2.12.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-context-3.2.12.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-data-commons-1.9.1.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-tx-3.2.12.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-data-gemfire-1.5.1.RELEASE.jar:$PROJECT_HOME/geode/gemfire-assembly/build/install/geode/lib/spring-beans-3.2.12.RELEASE.jar -Dgemfire.mcast-port=0 -Dgemfire.locators=localhost[10334] -Dgemfire.use-cluster-configuration=true -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806 com.gemstone.gemfire.distributed.ServerLauncher start server1 --redirect-output --server-port=0 --spring-xml-location=/cache-context.xml
