package org.chocosolver.capi;

import org.chocosolver.solver.constraints.extension.Tuples;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Choco Tuples.
 * @author Dimitri Justeau-Allaire.
 */
public class TuplesApi {

    private static final String API_PREFIX = "TuplesApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructors

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "tuples_create")
    public static ObjectHandle createTuples(IsolateThread thread, boolean feasible) {
        Tuples tuples = new Tuples(feasible);
        ObjectHandle res = globalHandles.create(tuples);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "tuples_create_with_values")
    public static ObjectHandle createTuples(IsolateThread thread, ObjectHandle tuplesHandle, boolean feasible) {
        int[][] tuplesValue = globalHandles.get(tuplesHandle);
        Tuples tuples = new Tuples(tuplesValue, feasible);
        ObjectHandle res = globalHandles.create(tuples);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_tuple")
    public static void addTuple(IsolateThread thread, ObjectHandle tuplesHandle, ObjectHandle toAdd) {
        Tuples tuples = globalHandles.get(tuplesHandle);
        int[] tuple = globalHandles.get(toAdd);
        tuples.add(tuple);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_tuples")
    public static void addTuples(IsolateThread thread, ObjectHandle tuplesHandle, ObjectHandle toAdd) {
        Tuples tuples = globalHandles.get(tuplesHandle);
        int[][] tuplesToAdd = globalHandles.get(toAdd);
        tuples.add(tuplesToAdd);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "nb_tuples")
    public static int nbTuples(IsolateThread thread, ObjectHandle tuplesHandle) {
        Tuples tuples = globalHandles.get(tuplesHandle);
        return tuples.nbTuples();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get")
    public static ObjectHandle get(IsolateThread thread, ObjectHandle tuplesHandle, int idx) {
        Tuples tuples = globalHandles.get(tuplesHandle);
        int[] tuple = tuples.get(idx);
        ObjectHandle res = globalHandles.create(tuple);
        return res;
    }
}
