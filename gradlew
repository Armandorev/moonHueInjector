#!/bin/sh
##############################################################################
# Gradle start-up script for UN*X
##############################################################################
# Locate the Java runtime
if [ -n "$JAVA_HOME" ]; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA="java"
fi

# Determine directory of this script
DIR="$(cd "$(dirname "$0")" && pwd)"

# Execute the wrapper JAR using Java
exec "$JAVA" -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"