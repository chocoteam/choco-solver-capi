package org.chocosolver.capi;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate Variables.
 * @author Dimitri Justeau-Allaire
 */
public class VariableApi {

    private static final String API_PREFIX = "VariableApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "isInstantiated")
    public static boolean isInstantiated(IsolateThread thread, ObjectHandle varHandle) {
        Variable var = globalHandles.get(varHandle);
        return var.isInstantiated();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getName")
    public static CCharPointer getName(IsolateThread thread, ObjectHandle varHandle) {
        IntVar var = globalHandles.get(varHandle);
        String name = var.getName();
        return CTypeConversion.toCString(name).get();
    }
}


