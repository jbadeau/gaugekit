#!/bin/sh

mvn test-compile
mvn gauge:execute -Denv=$1 -Dspecs=$2
