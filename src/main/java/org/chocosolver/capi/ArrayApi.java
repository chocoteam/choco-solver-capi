package org.chocosolver.capi;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;
import org.chocosolver.solver.variables.Task;
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

    // int[][]

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_2d_array_create")
    public static ObjectHandle createInt2DArray(IsolateThread thread, int size) {
        int[][] array = new int[size][];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_2d_array_set")
    public static void setInt2DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                             ObjectHandle elementHandle, int index) {
        int[][] array = globalHandles.get(arrayHandle);
        int[] element = globalHandles.get(elementHandle);
        array[index] = element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_2d_array_get")
    public static ObjectHandle getInt2DArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        int[][] array = globalHandles.get(arrayHandle);
        int[] element = array[index];
        ObjectHandle res = globalHandles.create(element);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_2d_array_length")
    public static int getInt2DArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        int[][] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // int[][][]

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_3d_array_create")
    public static ObjectHandle createInt3DArray(IsolateThread thread, int size) {
        int[][][] array = new int[size][][];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_3d_array_set")
    public static void setInt3DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                    ObjectHandle elementHandle, int index) {
        int[][][] array = globalHandles.get(arrayHandle);
        int[][] element = globalHandles.get(elementHandle);
        array[index] = element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_3d_array_get")
    public static ObjectHandle getInt3DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                            int index) {
        int[][][] array = globalHandles.get(arrayHandle);
        int[][] element = array[index];
        ObjectHandle res = globalHandles.create(element);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_3d_array_length")
    public static int getInt3DArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        int[][][] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // int[][][][]

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_4d_array_create")
    public static ObjectHandle createInt4DArray(IsolateThread thread, int size) {
        int[][][][] array = new int[size][][][];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_4d_array_set")
    public static void setInt4DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                    ObjectHandle elementHandle, int index) {
        int[][][][] array = globalHandles.get(arrayHandle);
        int[][][] element = globalHandles.get(elementHandle);
        array[index] = element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_4d_array_get")
    public static ObjectHandle getInt4DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                    int index) {
        int[][][][] array = globalHandles.get(arrayHandle);
        int[][][] element = array[index];
        ObjectHandle res = globalHandles.create(element);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_4d_array_length")
    public static int getInt4DArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        int[][][][] array = globalHandles.get(arrayHandle);
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

    // IntVar array of array

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_2d_array_create")
    public static ObjectHandle createIntVar2DArray(IsolateThread thread, int size) {
        IntVar[][] array = new IntVar[size][];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_2d_array_set")
    public static void setIntVar2DArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                  ObjectHandle intVarArrayHandle, int index) {
        IntVar[][] array = globalHandles.get(arrayHandle);
        IntVar[] element = globalHandles.get(intVarArrayHandle);
        array[index] = element;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_2d_array_get")
    public static ObjectHandle getIntVar2DArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        IntVar[][] array = globalHandles.get(arrayHandle);
        IntVar[] var = array[index];
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "intVar_2d_array_length")
    public static int getIntVar2DArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        IntVar[][] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // Tasks Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "task_create")
    public static ObjectHandle createTaskArray(IsolateThread thread, int size) {
        Task[] array = new Task[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "task_set")
    public static void setTaskArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                           ObjectHandle taskHandle, int index) {
        Task[] array = globalHandles.get(arrayHandle);
        Task task = globalHandles.get(taskHandle);
        array[index] = task;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "task_get")
    public static ObjectHandle getTaskArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        Task[] array = globalHandles.get(arrayHandle);
        Task task = array[index];
        ObjectHandle res = globalHandles.create(task);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "task_length")
    public static int getTaskArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        Task[] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // BoolVar Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar_create")
    public static ObjectHandle createBoolVarArray(IsolateThread thread, int size) {
        BoolVar[] array = new BoolVar[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolVar_set")
    public static void setBoolVarArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                              ObjectHandle boolVarHandle, int index) {
        BoolVar[] array = globalHandles.get(arrayHandle);
        BoolVar var = globalHandles.get(boolVarHandle);
        array[index] = var;
    }

    // SetVar Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setVar_create")
    public static ObjectHandle createSetVarArray(IsolateThread thread, int size) {
        SetVar[] array = new SetVar[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setVar_set")
    public static void setSetVarArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                             ObjectHandle setVarHandle, int index) {
        SetVar[] array = globalHandles.get(arrayHandle);
        SetVar var = globalHandles.get(setVarHandle);
        array[index] = var;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setVar_get")
    public static ObjectHandle getSetVarArrayElement(IsolateThread thread, ObjectHandle arrayHandle, int index) {
        SetVar[] array = globalHandles.get(arrayHandle);
        SetVar var = array[index];
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setVar_length")
    public static int getSetVarArrayLength(IsolateThread thread, ObjectHandle arrayHandle) {
        SetVar[] array = globalHandles.get(arrayHandle);
        return array.length;
    }

    // Constraint Arrays

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "constraint_create")
    public static ObjectHandle createConstraintArray(IsolateThread thread, int size) {
        Constraint[] array = new Constraint[size];
        ObjectHandle res = globalHandles.create(array);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "constraint_set")
    public static void setConstraintArrayElement(IsolateThread thread, ObjectHandle arrayHandle,
                                                 ObjectHandle constraintHandle, int index) {
        Constraint[] array = globalHandles.get(arrayHandle);
        Constraint cons = globalHandles.get(constraintHandle);
        array[index] = cons;
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
