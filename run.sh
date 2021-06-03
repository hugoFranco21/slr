#!/bin/sh

rm -r build > /dev/null 2>&1
mkdir build
javac -d build/ src/project/*.java
java build/project.Main
