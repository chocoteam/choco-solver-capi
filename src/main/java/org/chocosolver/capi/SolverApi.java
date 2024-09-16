package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.criteria.Criterion;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import java.util.List;

/**
 * C entrypoint API to manipulate Solver objects.
 * @author Dimitri Justeau-Allaire.
 */
public class SolverApi {

    private static final String API_PREFIX = "SolverApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "solve")
    public static boolean solve(IsolateThread thread, ObjectHandle solverHandler,
                                ObjectHandle stopArrayHandle) {
        Solver solver = globalHandles.get(solverHandler);
        Criterion[] stop = globalHandles.get(stopArrayHandle);
        solver.addStopCriterion(stop);
        boolean res = solver.solve();
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "find_solution")
    public static ObjectHandle findSolution(IsolateThread thread, ObjectHandle solverHandler,
                                            ObjectHandle stopArrayHandle) {
        Solver solver = globalHandles.get(solverHandler);
        Criterion[] stop = globalHandles.get(stopArrayHandle);
        Solution solution = solver.findSolution(stop);
        ObjectHandle res = globalHandles.create(solution);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "find_all_solutions")
    public static ObjectHandle findAllSolutions(IsolateThread thread, ObjectHandle solverHandler,
                                                ObjectHandle stopArrayHandle) {
        Solver solver = globalHandles.get(solverHandler);
        Criterion[] stop = globalHandles.get(stopArrayHandle);
        List<Solution> solutions = solver.findAllSolutions(stop);
        ObjectHandle res = globalHandles.create(solutions);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "find_optimal_solution")
    public static ObjectHandle findOptimalSolution(IsolateThread thread, ObjectHandle solverHandler,
                                                   ObjectHandle objectiveVariableHandler, boolean maximize,
                                                   ObjectHandle stopArrayHandle) {
        Solver solver = globalHandles.get(solverHandler);
        IntVar objective = globalHandles.get(objectiveVariableHandler);
        Criterion[] stop = globalHandles.get(stopArrayHandle);
        Solution solution = solver.findOptimalSolution(objective, maximize, stop);
        ObjectHandle res = globalHandles.create(solution);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "find_all_optimal_solutions")
    public static ObjectHandle findAllOptimalSolutions(IsolateThread thread, ObjectHandle solverHandler,
                                                       ObjectHandle objectiveVariableHandler, boolean maximize,
                                                       ObjectHandle stopArrayHandle) {
        Solver solver = globalHandles.get(solverHandler);
        IntVar objective = globalHandles.get(objectiveVariableHandler);
        Criterion[] stop = globalHandles.get(stopArrayHandle);
        List<Solution> solutions = solver.findAllOptimalSolutions(objective, maximize, stop);
        ObjectHandle res = globalHandles.create(solutions);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "show_statistics")
    public static void showStatistics(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.showStatistics();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "show_short_statistics")
    public static void showShortStatistics(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.showShortStatistics();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "show_restarts")
    public static void showRestarts(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.showRestarts();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_solution_count")
    public static long getSolutionCount(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        return solver.getSolutionCount();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "limit_time")
    public static void limitTime(IsolateThread thread, ObjectHandle solverHandler, CCharPointer limit) {
        Solver solver = globalHandles.get(solverHandler);
        String timeLimit = CTypeConversion.toJavaString(limit);
        solver.limitTime(timeLimit);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "propagate")
    public static boolean propagate(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        try{
            solver.propagate();
            return true;
        } catch(ContradictionException e){
            return false;
        }
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "push_state")
    public static void pushState(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.getEnvironment().worldPush();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "pop_state")
    public static void popState(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.getEnvironment().worldPop();
    }
}
