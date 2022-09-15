# Make sure $JAVA_HOME is pointing to a GraalVM JDK with native image installed

mvn clean package

cd target
 
native-image --shared -H:Name=libchoco_capi -cp choco-solver-capi-1.0-SNAPSHOT.jar --no-fallback --link-at-build-time

cd ..

