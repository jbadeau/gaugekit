#!/bin/sh

mvn gauge:execute -Denv=$1 -Dspecs=$2
