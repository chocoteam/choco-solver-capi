package org.chocosolver.capi;

import org.chocosolver.memory.IStateBool;
import org.chocosolver.memory.IStateInt;
import org.chocosolver.solver.Model;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entry-point API for Choco backtrackable state objects (IStateInt, IStateBool).
 *
 * <p>These objects are tied to the model's environment: their value is automatically
 * restored on backtrack, making them useful inside custom propagators and search
 * strategies that need to maintain state across the search tree.
 */
public class StateApi {

    private static final String API_PREFIX = "StateApi_";
    private static final ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // -------------------------------------------------------------------------
    // IStateInt
    // -------------------------------------------------------------------------

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "make_state_int")
    public static ObjectHandle makeStateInt(IsolateThread thread,
                                            ObjectHandle modelHandle,
                                            int initialValue) {
        Model model = globalHandles.get(modelHandle);
        IStateInt state = model.getEnvironment().makeInt(initialValue);
        return globalHandles.create(state);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "state_int_get")
    public static int stateIntGet(IsolateThread thread, ObjectHandle handle) {
        IStateInt state = globalHandles.get(handle);
        return state.get();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "state_int_set")
    public static void stateIntSet(IsolateThread thread, ObjectHandle handle, int value) {
        IStateInt state = globalHandles.get(handle);
        state.set(value);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "state_int_add")
    public static int stateIntAdd(IsolateThread thread, ObjectHandle handle, int delta) {
        IStateInt state = globalHandles.get(handle);
        return state.add(delta);
    }

    // -------------------------------------------------------------------------
    // IStateBool
    // -------------------------------------------------------------------------

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "make_state_bool")
    public static ObjectHandle makeStateBool(IsolateThread thread,
                                             ObjectHandle modelHandle,
                                             boolean initialValue) {
        Model model = globalHandles.get(modelHandle);
        IStateBool state = model.getEnvironment().makeBool(initialValue);
        return globalHandles.create(state);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "state_bool_get")
    public static boolean stateBoolGet(IsolateThread thread, ObjectHandle handle) {
        IStateBool state = globalHandles.get(handle);
        return state.get();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "state_bool_set")
    public static void stateBoolSet(IsolateThread thread, ObjectHandle handle, boolean value) {
        IStateBool state = globalHandles.get(handle);
        state.set(value);
    }
}
