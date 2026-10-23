# Make sure $JAVA_HOME is pointing to an Oracle GraalVM JDK (25 or later): profile-guided
# optimizations are not available in GraalVM Community Edition
#
# sh build.sh             optimized library, guided by the profile pgo/choco.iprof.gz
# sh build.sh instrument  instrumented library, to record a new profile (see pgo/README.md)

set -e

# native-image is not necessarily on the PATH: take the one of $JAVA_HOME
if [ -n "$JAVA_HOME" ]; then
    PATH="$JAVA_HOME/bin:$PATH"
fi

OS=`uname`

if [ "$1" = "instrument" ]; then
    case "$OS" in Linux|Darwin) ;; *) echo "instrument is only supported on Linux and macOS"; exit 1;; esac
    # A shared library never dumps its profile by itself: ProfilingApi.exit runs the shutdown hooks
    OPTIMIZATION="--pgo-instrument -H:+UnlockExperimentalVMOptions -H:+ProfilingEnableProfileDumpHooks -R:ProfilesDumpFile=$(pwd)/target/choco.iprof"
elif [ -n "$1" ]; then
    echo "Unknown option $1"
    exit 1
else
    OPTIMIZATION="-O3 --pgo=../pgo/choco.iprof.gz"
fi

mvn clean package

cd target

if [ "$OS" = "Linux" ] || [ "$OS" = "Darwin" ]; then
    native-image --shared -o libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time $OPTIMIZATION
else
    native-image.cmd --shared -o libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time $OPTIMIZATION
    mv libchoco_capi.dll choco_capi.dll
    mv libchoco_capi.lib choco_capi.lib
fi

cd ..
