#!/bin/sh

mvn verify -Denv=$1 -Dspecs=$2
