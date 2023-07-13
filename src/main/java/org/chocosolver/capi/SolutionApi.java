package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.GraphVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;
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

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getSetVal")
    public static ObjectHandle getSetVal(IsolateThread thread, ObjectHandle solutionHandler, ObjectHandle setVarHandle) {
        Solution solution = globalHandles.get(solutionHandler);
        SetVar setVar = globalHandles.get(setVarHandle);
        int[] setVal = solution.getSetVal(setVar);
        ObjectHandle handle = globalHandles.create(setVal);
        return handle;
    }

}
