#!/bin/sh

mvn verify -Denv=$1 -DspecsDir=$2 -Dtags=$3 -DinParallel=$4 -Dnodes=$5