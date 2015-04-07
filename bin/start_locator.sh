#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

cd $BASEDIR/../output
gfsh start locator --name=locator1
