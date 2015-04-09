#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

$BASEDIR/locator.sh
$BASEDIR/servers.sh
