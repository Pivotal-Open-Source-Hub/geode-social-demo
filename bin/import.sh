#!/bin/bash

BASEDIR=$(dirname $0)
. $BASEDIR/setenv.sh

gfsh -e "connect" -e "import data --file=../../data/sentimentWords.gfd --member=server1 --region=/sentimentWords"
