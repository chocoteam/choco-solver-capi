package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.SetVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate SetVars.
 * @author Dimitri Justeau-Allaire
 */
public class SetVarApi {

    private static final String API_PREFIX = "SetVarApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructors

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_setVar_named")
    public static ObjectHandle createSetVarNamed(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                            ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        int[] lb = globalHandles.get(lbHandle);
        int[] ub = globalHandles.get(ubHandle);
        SetVar var = model.setVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_setVar")
    public static ObjectHandle createSetVar(IsolateThread thread, ObjectHandle modelHandle,
                                            ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        int[] lb = globalHandles.get(lbHandle);
        int[] ub = globalHandles.get(ubHandle);
        SetVar var = model.setVar(lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_setVar_cst_named")
    public static ObjectHandle createSetVarCstNamed(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                               ObjectHandle valueHandle) {
        Model model = globalHandles.get(modelHandle);
        int[] value = globalHandles.get(valueHandle);
        SetVar var = model.setVar(CTypeConversion.toJavaString(name), value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_setVar_cst")
    public static ObjectHandle createSetVarCst(IsolateThread thread, ObjectHandle modelHandle,
                                               ObjectHandle valueHandle) {
        Model model = globalHandles.get(modelHandle);
        int[] value = globalHandles.get(valueHandle);
        SetVar var = model.setVar(value);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getLB")
    public static ObjectHandle getLB(IsolateThread thread, ObjectHandle setVarHandle) {
        SetVar var = globalHandles.get(setVarHandle);
        int[] lb = var.getLB().toArray();
        ObjectHandle res = globalHandles.create(lb);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getUB")
    public static ObjectHandle getUB(IsolateThread thread, ObjectHandle setVarHandle) {
        SetVar var = globalHandles.get(setVarHandle);
        int[] ub = var.getUB().toArray();
        ObjectHandle res = globalHandles.create(ub);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getValue")
    public static ObjectHandle getValue(IsolateThread thread, ObjectHandle setVarHandle) {
        SetVar var = globalHandles.get(setVarHandle);
        int[] value = var.getValue().toArray();
        ObjectHandle res = globalHandles.create(value);
        return res;
    }
}
