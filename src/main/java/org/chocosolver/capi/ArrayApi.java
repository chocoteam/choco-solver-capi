package org.chocosolver.capi;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.criteria.Criterion;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Java arrays.
 * @author Dimitri Justeau-Allaire.
 */
public class ArrayApi {

    private static final String API_PREFIX = "ArrayApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // int Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_create")
    public static ObjectHandle createIntArray(IsolateThread thread, int size) {
        int[] array = new int[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_set")
    public static void setIntArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                             int element, int index) {
        int[] array = globalHandles.get(arrayHandle);
        array[index] = element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_get")
    public static int getIntArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        int[] array = globalHandles.get(arrayHandle);
        int element = array[index];
        return element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_length")
    public static int getIntArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        int[] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // IntVar Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_create")
    public static ObjectHandle createIntVarArray(IsolateThread thread, int size) {
        IntVar[] array = new IntVar[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_set")
    public static void setIntVarArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                     ObjectHandle intVarHandle, int index) {
        IntVar[] array = globalHandles.get(arrayHandle);
        IntVar var = globalHandles.get(intVarHandle);
        array[index] = var;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_get")
    public static ObjectHandle getIntVarArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        IntVar[] array = globalHandles.get(arrayHandle);
        IntVar var = array[index];
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_length")
    public static int getIntVarArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        IntVar[] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // Criterion Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "criterion_create")
    public static ObjectHandle createCriterionArray(IsolateThread thread, int size) {
        Criterion[] array = new Criterion[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "criterion_set")
    public static void setCriterionArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                ObjectHandle criterionHandle, int index) {
        Criterion[] array = globalHandles.get(arrayHandle);
        Criterion criterion = globalHandles.get(criterionHandle);
        array[index] = criterion;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "criterion_get")
    public static ObjectHandle getCriterionArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        Criterion[] array = globalHandles.get(arrayHandle);
        Criterion criterion = array[index];
        ObjectHandle res = globalHandles.create(criterion);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "length")
    public static int getArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        Object[] array = globalHandles.get(arrayHandle);
        return array.length;
    }

}
