package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate BoolVars.
 * @author Dimitri Justeau-Allaire
 */
public class BoolVarApi {

    private static final String API_PREFIX = "BoolVarApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // --------------- //
    // Factory methods //
    // --------------- //

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar_s")
    public static ObjectHandle boolVar_s(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name) {
        Model model = globalHandles.get(modelHandle);
        BoolVar var = model.boolVar(CTypeConversion.toJavaString(name));
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar")
    public static ObjectHandle boolVar(IsolateThread thread, ObjectHandle modelHandle) {
        Model model = globalHandles.get(modelHandle);
        BoolVar var = model.boolVar();
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar_b")
    public static ObjectHandle boolVar_b(IsolateThread thread, ObjectHandle modelHandle, boolean value) {
        Model model = globalHandles.get(modelHandle);
        BoolVar var = model.boolVar(value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar_sb")
    public static ObjectHandle boolVar_sb(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name, boolean value) {
        Model model = globalHandles.get(modelHandle);
        BoolVar var = model.boolVar(CTypeConversion.toJavaString(name), value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

}


