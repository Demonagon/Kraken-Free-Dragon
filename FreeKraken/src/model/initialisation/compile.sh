#!/bin/sh

./javacc-6.0/bin/javacc grammaire1.jj
echo "Compiling..."
javac *.java
echo "Done."
