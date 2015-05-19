#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

mkdir -p $BASEDIR/../output
cd $BASEDIR/../output
gfsh start locator --name=locator1
