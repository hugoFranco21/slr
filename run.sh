#!/bin/sh

if [ "$#" -ne 1 ] || ! [ -f "$1" ]; then
  echo "Usage: sh run.sh FILENAME" >&2
  exit 1
fi
find -name "*.java" > sources.txt
rm -r build > /dev/null 2>&1
mkdir build
javac -d build/ @sources.txt
java -classpath build/ project.Main $1
mkdir output > /dev/null 2>&1
mv *.html output/.
