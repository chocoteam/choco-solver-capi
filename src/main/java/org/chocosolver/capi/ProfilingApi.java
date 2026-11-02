package org.chocosolver.capi;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entry-point used to record the profile of profile-guided optimizations (see pgo/README.md).
 *
 * <p>An instrumented shared library dumps its profile in the shutdown hooks, which never run
 * when the host process simply exits: this entry point runs them and then terminates the
 * process. Do not call it outside a training run.
 */
public class ProfilingApi {

    private static final String API_PREFIX = "ProfilingApi_";

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "exit")
    public static void exit(IsolateThread thread) {
        System.exit(0);
    }
}
