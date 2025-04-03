#!/bin/bash
javac --module-path /Library/Java/JavaFX/lib --add-modules javafx.controls,javafx.graphics LightsOut.java
java --module-path /Library/Java/JavaFX/lib --add-modules javafx.controls,javafx.graphics LightsOut 