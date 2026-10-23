# Make sure $JAVA_HOME is pointing to a GraalVM JDK with native image installed

set -e

# native-image is not necessarily on the PATH: take the one of $JAVA_HOME
if [ -n "$JAVA_HOME" ]; then
    PATH="$JAVA_HOME/bin:$PATH"
fi

mvn clean package

cd target

OS=`uname`

if [ "$OS" = "Linux" ]; then
    native-image --shared -H:Name=libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time
elif [ "$OS" = "Darwin" ]; then
    native-image --shared -H:Name=libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time
else
    native-image.cmd --shared -H:Name=libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time
    mv libchoco_capi.dll choco_capi.dll
    mv libchoco_capi.lib choco_capi.lib
fi

cd ..

