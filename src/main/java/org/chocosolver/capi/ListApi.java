package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

import java.util.List;

/**
 * C entrypoint API to manipulate Java Lists.
 * @author Dimitri Justeau-Allaire.
 */
public class ListApi {

    private static final String API_PREFIX = "ListApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "size")
    public static int getListSize(IsolateThread thread, ObjectHandle listHandle) {
        List list = globalHandles.get(listHandle);
        return list.size();
    }

    // Solution lists

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "solution_get")
    public static ObjectHandle solutionListGet(IsolateThread thread, ObjectHandle listHandle, int index) {
        List<Solution> solutions = globalHandles.get(listHandle);
        Solution s = solutions.get(index);
        ObjectHandle res = globalHandles.create(s);
        return res;
    }

}
