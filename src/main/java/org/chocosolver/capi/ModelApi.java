package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to manipulate Model objects.
 * @author Dimitri Justeau-Allaire.
 */
public class ModelApi {

    private static final String API_PREFIX = "ModelApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Create and access model

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "createModel")
    public static ObjectHandle createModel(IsolateThread thread, CCharPointer name) {
        String jName = CTypeConversion.toJavaString(name);
        Model model = new Model(jName);
        ObjectHandle res = globalHandles.create(model);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getName")
    public static CCharPointer getName(IsolateThread thread, ObjectHandle modelHandle) {
        Model model = globalHandles.get(modelHandle);
        String name = model.getName();
        CCharPointer res = CTypeConversion.toCString(name).get();
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getSolver")
    public static ObjectHandle getSolver(IsolateThread thread, ObjectHandle modelHandle) {
        Model model = globalHandles.get(modelHandle);
        Solver solver = model.getSolver();
        ObjectHandle res = globalHandles.create(solver);
        return res;
    }
}
