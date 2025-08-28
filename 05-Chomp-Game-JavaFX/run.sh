#!/bin/bash

# Compile the program
javac --module-path /Library/Java/JavaFX/lib --add-modules javafx.controls,javafx.graphics Chomp.java module-info.java

# Run the program with JavaFX modules
java --module-path /Library/Java/JavaFX/lib --add-modules javafx.controls,javafx.graphics Chomp 