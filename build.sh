# Make sure $JAVA_HOME is pointing to a GraalVM JDK with native image installed

mvn -Pnative -DskipTests package
