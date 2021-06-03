#!/bin/sh

if [ "$#" -ne 1 ] || ! [ -f "$1" ]; then
  echo "Usage: sh run.sh FILENAME" >&2
  exit 1
fi
rm -r build > /dev/null 2>&1
mkdir build
javac -d build/ src/project/*.java
java -classpath build/ project.Main $1
