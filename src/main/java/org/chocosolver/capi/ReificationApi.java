package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API for IReificationFactory.
 * @author Dimitri Justeau-Allaire
 */
public class ReificationApi {

    private static final String API_PREFIX = "ReificationApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then")
    public static void ifThen(IsolateThread thread, ObjectHandle modelHandle,
                              ObjectHandle constraintHandleIf, ObjectHandle constraintHandleThen) {

        Model model = globalHandles.get(modelHandle);
        Constraint constraintIf = globalHandles.get(constraintHandleIf);
        Constraint constraintThen = globalHandles.get(constraintHandleThen);
        model.ifThen(constraintIf, constraintThen);
    }
}
