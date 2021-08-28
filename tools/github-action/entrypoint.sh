#!/bin/sh

mvn clean verify -Denv=$1 -Dspecs=$2
