package org.chocosolver.capi;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Solver objects.
 * @author Dimitri Justeau-Allaire.
 */
public class SolverApi {

    private static final String API_PREFIX = "SolverApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "findSolution")
    public static ObjectHandle findSolution(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        Solution solution = solver.findSolution();
        ObjectHandle res = globalHandles.create(solution);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "showStatistics")
    public static void showStatistics(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.showStatistics();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "showShortStatistics")
    public static void showShortStatistics(IsolateThread thread, ObjectHandle solverHandler) {
        Solver solver = globalHandles.get(solverHandler);
        solver.showShortStatistics();
    }
}
