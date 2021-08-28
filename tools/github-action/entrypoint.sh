#!/bin/sh

mvn $1 -Denv=$2 -DspecsDir=$3 -Dtags=$4 -DinParallel=$5 -Dnodes=$6