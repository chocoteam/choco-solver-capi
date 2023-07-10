package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate IntVars.
 * @author Dimitri Justeau-Allaire
 */
public class IntVarApi {

    private static final String API_PREFIX = "IntVarApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // --------------- //
    // Factory methods //
    // --------------- //

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_sii")
    public static ObjectHandle intVar_sii(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name, int lb, int ub) {
        Model model = globalHandles.get(modelHandle);
        IntVar var = model.intVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_ii")
    public static ObjectHandle intVar_ii(IsolateThread thread, ObjectHandle modelHandle, int lb, int ub) {
        Model model = globalHandles.get(modelHandle);
        IntVar var = model.intVar(lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_s_arr")
    public static ObjectHandle intVar_s_arr(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name, ObjectHandle values) {
        Model model = globalHandles.get(modelHandle);
        int[] vals = globalHandles.get(values);
        IntVar var = model.intVar(CTypeConversion.toJavaString(name), vals);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_arr")
    public static ObjectHandle intVar_arr(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle values) {
        Model model = globalHandles.get(modelHandle);
        int[] vals = globalHandles.get(values);
        IntVar var = model.intVar(vals);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }


    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_i")
    public static ObjectHandle intVar_i(IsolateThread thread, ObjectHandle modelHandle, int value) {
        Model model = globalHandles.get(modelHandle);
        IntVar var = model.intVar(value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_si")
    public static ObjectHandle intVar_i(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name, int value) {
        Model model = globalHandles.get(modelHandle);
        IntVar var = model.intVar(CTypeConversion.toJavaString(name), value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    // -------------- //
    // Object methods //
    // -------------- //

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getName")
    public static CCharPointer getName(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar var = globalHandles.get(intVarHandle);
        String name = var.getName();
        return CTypeConversion.toCString(name).get();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getLB")
    public static int getLB(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar var = globalHandles.get(intVarHandle);
        return var.getLB();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getUB")
    public static int getUB(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar var = globalHandles.get(intVarHandle);
        return var.getUB();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getValue")
    public static int getValue(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar var = globalHandles.get(intVarHandle);
        return var.getValue();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "hasEnumeratedDomain")
    public static boolean hasEnumeratedDomain(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar var = globalHandles.get(intVarHandle);
        return var.hasEnumeratedDomain();
    }

}


