package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.limits.*;
import org.chocosolver.util.criteria.Criterion;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Criterion objects.
 * @author Dimitri Justeau-Allaire.
 */
public class CriterionApi {

    private static final String API_PREFIX = "CriterionApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "time_counter")
    public static ObjectHandle timeCounter(IsolateThread thread, ObjectHandle modelHandle,
                                           long timeLimitMilliSeconds) {
        Model model = globalHandles.get(modelHandle);
        Criterion timeLimit = new TimeCounter(model, timeLimitMilliSeconds * 1000 * 1000);
        ObjectHandle res = globalHandles.create(timeLimit);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "solution_counter")
    public static ObjectHandle solutionCounter(IsolateThread thread, ObjectHandle modelHandle,
                                               long solutionLimit) {
        Model model = globalHandles.get(modelHandle);
        Criterion solutionCounter = new SolutionCounter(model, solutionLimit);
        ObjectHandle res = globalHandles.create(solutionCounter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "node_counter")
    public static ObjectHandle nodeCounter(IsolateThread thread, ObjectHandle modelHandle,
                                           long nodeLimit) {
        Model model = globalHandles.get(modelHandle);
        Criterion nodeCounter = new NodeCounter(model, nodeLimit);
        ObjectHandle res = globalHandles.create(nodeCounter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "fail_counter")
    public static ObjectHandle fail_counter(IsolateThread thread, ObjectHandle modelHandle,
                                            long failLimit) {
        Model model = globalHandles.get(modelHandle);
        Criterion failCounter = new FailCounter(model, failLimit);
        ObjectHandle res = globalHandles.create(failCounter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "restart_counter")
    public static ObjectHandle restart_counter(IsolateThread thread, ObjectHandle modelHandle,
                                               long restartLimit) {
        Model model = globalHandles.get(modelHandle);
        Criterion restartCounter = new RestartCounter(model, restartLimit);
        ObjectHandle res = globalHandles.create(restartCounter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "backtrack_counter")
    public static ObjectHandle backtrack_counter(IsolateThread thread, ObjectHandle modelHandle,
                                                 long restartLimit) {
        Model model = globalHandles.get(modelHandle);
        Criterion backtrackCounter = new BacktrackCounter(model, restartLimit);
        ObjectHandle res = globalHandles.create(backtrackCounter);
        return res;
    }
}
