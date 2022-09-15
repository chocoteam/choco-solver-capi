# Make sure $JAVA_HOME is pointing to a GraalVM JDK with native image installed

mvn clean package

cd target

OS=`uname`

if [ "$OS" = "Linux" ]; then
    native-image --shared -H:Name=libchoco_capi -cp choco-solver-capi-1.0-SNAPSHOT.jar --no-fallback --link-at-build-time
elif [ "$OS" = "Darwin" ]; then
    native-image --shared -H:Name=libchoco_capi -cp choco-solver-capi-1.0-SNAPSHOT.jar --no-fallback --link-at-build-time
else
    native-image.cmd --shared -H:Name=libchoco_capi -cp choco-solver-capi-1.0-SNAPSHOT.jar --no-fallback --link-at-build-time
    mv libchoco_capi.dll choco_capi.dll
    mv libchoco_capi.lib choco_capi.lib
fi

cd ..

