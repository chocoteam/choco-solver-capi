# Make sure $JAVA_HOME is pointing to an Oracle GraalVM JDK (25 or later): profile-guided
# optimizations are not available in GraalVM Community Edition. The script checks it and stops,
# before running Maven, if native-image is missing, older than 25 or not from Oracle GraalVM
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

if [ "$OS" = "Linux" ] || [ "$OS" = "Darwin" ]; then
    NATIVE_IMAGE=native-image
else
    NATIVE_IMAGE=native-image.cmd
fi

# Fail early, before compiling anything, if the toolchain is not Oracle GraalVM 25 or later:
# the first line of "native-image --version" is "native-image <version> <date>"
REQUIRED_GRAALVM=25
graalvm_error() {
    echo "Error: $1" >&2
    echo "This build requires Oracle GraalVM $REQUIRED_GRAALVM or later (profile-guided optimizations are not available in GraalVM Community Edition)." >&2
    echo "Make sure \$JAVA_HOME points to such a JDK (current value: ${JAVA_HOME:-<unset>})." >&2
    exit 1
}
if ! command -v "$NATIVE_IMAGE" >/dev/null 2>&1; then
    graalvm_error "$NATIVE_IMAGE not found."
fi
if ! NATIVE_IMAGE_VERSION=`"$NATIVE_IMAGE" --version 2>&1`; then
    graalvm_error "\"$NATIVE_IMAGE --version\" failed: $NATIVE_IMAGE_VERSION"
fi
GRAALVM_VERSION=`echo "$NATIVE_IMAGE_VERSION" | sed -n '1s/^native-image \([0-9][0-9.]*\).*/\1/p'`
GRAALVM_MAJOR=${GRAALVM_VERSION%%.*}
case "$GRAALVM_MAJOR" in
    ''|*[!0-9]*) graalvm_error "cannot read the GraalVM version from \"$NATIVE_IMAGE --version\": `echo "$NATIVE_IMAGE_VERSION" | head -n 1`";;
esac
if [ "$GRAALVM_MAJOR" -lt "$REQUIRED_GRAALVM" ]; then
    graalvm_error "found GraalVM $GRAALVM_VERSION ($NATIVE_IMAGE), version $REQUIRED_GRAALVM or later is required."
fi
if ! echo "$NATIVE_IMAGE_VERSION" | grep -q "Oracle GraalVM"; then
    graalvm_error "found GraalVM $GRAALVM_VERSION ($NATIVE_IMAGE), but not Oracle GraalVM: `echo "$NATIVE_IMAGE_VERSION" | sed -n 2p`"
fi

mvn clean package

cd target

"$NATIVE_IMAGE" --shared -o libchoco_capi -cp choco-solver-capi.jar --no-fallback --link-at-build-time $OPTIMIZATION

if [ "$OS" != "Linux" ] && [ "$OS" != "Darwin" ]; then
    mv libchoco_capi.dll choco_capi.dll
    mv libchoco_capi.lib choco_capi.lib
fi

cd ..
