package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Task;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Tasks.
 * @author Dimitri Justeau-Allaire.
 */
public class TaskApi {

    private static final String API_PREFIX = "TaskApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructors

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_iiiii")
    public static ObjectHandle createTask_iiiii(IsolateThread thread, ObjectHandle modelHandle,
                                                int est, int lst, int d, int ect, int lct) {
        Model model = globalHandles.get(modelHandle);
        Task task = new Task(model, est, lst, d, ect, lct);
        ObjectHandle res = globalHandles.create(task);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_iv_i")
    public static ObjectHandle createTask_iv_i(IsolateThread thread, ObjectHandle intVarHandle, int d) {
        IntVar s = globalHandles.get(intVarHandle);
        Task task = new Task(s, d);
        ObjectHandle res = globalHandles.create(task);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_iv_i_iv")
    public static ObjectHandle createTask_iv_i_iv(IsolateThread thread, ObjectHandle intVarHandle, int d,
                                                  ObjectHandle intVarHandle2) {
        IntVar s = globalHandles.get(intVarHandle);
        IntVar e = globalHandles.get(intVarHandle2);
        Task task = new Task(s, d, e);
        ObjectHandle res = globalHandles.create(task);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_iv_i_iv")
    public static ObjectHandle createTask_iv_i_iv(IsolateThread thread, ObjectHandle intVarHandle,
                                                  ObjectHandle intVarHandle2, ObjectHandle intVarHandle3) {
        IntVar s = globalHandles.get(intVarHandle);
        IntVar d = globalHandles.get(intVarHandle2);
        IntVar e = globalHandles.get(intVarHandle3);
        Task task = new Task(s, d, e);
        ObjectHandle res = globalHandles.create(task);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ensure_bound_consistency")
    public static void ensureBoundConsistency(IsolateThread thread, ObjectHandle taskHandle) throws ContradictionException {
        Task t = globalHandles.get(taskHandle);
        t.ensureBoundConsistency();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_start")
    public static ObjectHandle getStart(IsolateThread thread, ObjectHandle taskHandle) {
        Task t = globalHandles.get(taskHandle);
        IntVar v = t.getStart();
        ObjectHandle res = globalHandles.create(v);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_end")
    public static ObjectHandle getEnd(IsolateThread thread, ObjectHandle taskHandle) {
        Task t = globalHandles.get(taskHandle);
        IntVar v = t.getEnd();
        ObjectHandle res = globalHandles.create(v);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_duration")
    public static ObjectHandle getDuration(IsolateThread thread, ObjectHandle taskHandle) {
        Task t = globalHandles.get(taskHandle);
        IntVar v = t.getDuration();
        ObjectHandle res = globalHandles.create(v);
        return res;
    }
}
