#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

$BASEDIR/keepalive.sh $BASEDIR/start_server_1.sh &
sleep 10
$BASEDIR/keepalive.sh $BASEDIR/start_server_2.sh &
sleep 10
$BASEDIR/keepalive.sh $BASEDIR/start_server_3.sh &
sleep 10

$BASEDIR/import.sh
