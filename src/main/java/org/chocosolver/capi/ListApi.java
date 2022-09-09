package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.search.limits.ICounter;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

import java.util.ArrayList;
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

    // Automaton ICounter lists

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "fa_counter_create")
    public static ObjectHandle faCounterListCreate(IsolateThread thread) {
        List<ICounter> list = new ArrayList<>();
        ObjectHandle res = globalHandles.create(list);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "fa_counter_add")
    public static void faCounterListAdd(IsolateThread thread, ObjectHandle listHandle,
                                                ObjectHandle counterHandle) {
        List<ICounter> list = globalHandles.get(listHandle);
        ICounter counter = globalHandles.get(counterHandle);
        list.add(counter);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "fa_counter_get")
    public static ObjectHandle faCounterListGet(IsolateThread thread, ObjectHandle listHandle,
                                                int index) {
        List<ICounter> list = globalHandles.get(listHandle);
        ICounter counter = list.get(index);
        ObjectHandle res = globalHandles.create(counter);
        return res;
    }
}
