#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh


gfsh -e "connect" -e "shutdown --include-locators=true"
