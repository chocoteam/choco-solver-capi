package org.chocosolver.capi;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate object handles.
 * Inspired from https://github.com/jgrapht/jgrapht-capi/.
 * @author Dimitri Justeau-Allaire.
 */
public class HandlesApi {

    private static final String API_PREFIX = "HandlesApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    /**
     * Destroy a handle
     *
     * @param thread the thread
     * @param handle the handle
     */
    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "destroy")
    public static void destroy(IsolateThread thread, ObjectHandle handle) {
        globalHandles.destroy(handle);
    }

}
