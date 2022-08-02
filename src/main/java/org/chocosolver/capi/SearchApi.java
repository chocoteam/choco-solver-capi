package org.chocosolver.capi;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to select search strategies.
 * @author Dimitri Justeau-Allaire
 */
public class SearchApi {

    private static final String API_PREFIX = "SearchApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_random_search")
    public static void setRandomSearch(IsolateThread thread, ObjectHandle solverHandle,
                                      ObjectHandle intVarArrayHandle, long seed) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.randomSearch(vars, seed));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_dom_over_w_deg_search")
    public static void setDomOverWDegSearch(IsolateThread thread, ObjectHandle solverHandle,
                                            ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.domOverWDegSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_dom_over_w_deg_ref_search")
    public static void setDomOverWDegRefSearch(IsolateThread thread, ObjectHandle solverHandle,
                                               ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.domOverWDegRefSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_activity_based_search")
    public static void setActivityBasedSearch(IsolateThread thread, ObjectHandle solverHandle,
                                              ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.activityBasedSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_min_dom_lb_search")
    public static void setMinDomLBSearch(IsolateThread thread, ObjectHandle solverHandle,
                                              ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.minDomLBSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_min_dom_ub_search")
    public static void setMinDomUBSearch(IsolateThread thread, ObjectHandle solverHandle,
                                         ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.minDomUBSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_conflict_history_search")
    public static void setConflictHistorySearch(IsolateThread thread, ObjectHandle solverHandle,
                                                ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.conflictHistorySearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_default_search")
    public static void setDefaultSearch(IsolateThread thread, ObjectHandle solverHandle) {
        Solver solver = globalHandles.get(solverHandle);
        solver.setSearch(Search.defaultSearch(solver.getModel()));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_input_order_lb_search")
    public static void setInputOrderLBSearch(IsolateThread thread, ObjectHandle solverHandle,
                                             ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.inputOrderLBSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_input_order_ub_search")
    public static void setInputOrderUBSearch(IsolateThread thread, ObjectHandle solverHandle,
                                             ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.inputOrderUBSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_failure_length_based_search")
    public static void setFailureLengthBasedSearch(IsolateThread thread, ObjectHandle solverHandle,
                                                   ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.failureLengthBasedSearch(vars));
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_failure_rate_based_search")
    public static void setFailureRateBasedSearch(IsolateThread thread, ObjectHandle solverHandle,
                                                 ObjectHandle intVarArrayHandle) {
        Solver solver = globalHandles.get(solverHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        solver.setSearch(Search.failureRateBasedSearch(vars));
    }
}