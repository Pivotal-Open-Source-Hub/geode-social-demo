#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

$BASEDIR/start_server_1.sh
$BASEDIR/start_server_2.sh
$BASEDIR/start_server_3.sh

$BASEDIR/import.sh
