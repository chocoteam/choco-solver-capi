package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Solution objects.
 * @author Dimitri Justeau-Allaire.
 */
public class SolutionApi {

    private static final String API_PREFIX = "SolutionApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Create and access model

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getIntVal")
    public static int getIntVal(IsolateThread thread, ObjectHandle solutionHandler, ObjectHandle intVarHandle) {
        Solution solution = globalHandles.get(solutionHandler);
        IntVar intVar = globalHandles.get(intVarHandle);
        return solution.getIntVal(intVar);
    }

}
