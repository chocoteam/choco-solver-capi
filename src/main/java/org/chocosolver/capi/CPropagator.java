package org.chocosolver.capi;

import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.PropagatorPriority;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CTypedef;

/**
 * A Choco propagator that delegates its propagation logic to an external
 * callback via a C function pointer bridge.
 *
 * <p>Any language that supports the C ABI (Python/ctypes, Rust/FFI, etc.)
 * can provide the callbacks. The bridge receives a propagator ID and a handle
 * to the variable array, then dispatches to the registered callback in the
 * C layer.</p>
 */
public class CPropagator extends Propagator<IntVar> {

    /**
     * C function pointer interface for the propagation bridge.
     * Signature: int bridge(IsolateThread thread, long propagatorId, ObjectHandle varsHandle)
     */
    @CTypedef(name = "propagate_fn_callback_t")
    interface PropagateFn extends CFunctionPointer {
        @InvokeCFunctionPointer
        int invoke(IsolateThread thread, long propagatorId, ObjectHandle varsHandle);
    }

    /**
     * C function pointer interface for the isEntailed bridge.
     * Returns 1 = TRUE, 0 = UNDEFINED, -1 (or any negative) = FALSE.
     * Signature: int bridge(IsolateThread thread, long isEntailedId)
     */
    @CTypedef(name = "is_entailed_fn_callback_t")
    interface IsEntailedFn extends CFunctionPointer {
        @InvokeCFunctionPointer
        int invoke(IsolateThread thread, long isEntailedId);
    }

    private final PropagateFn callback;
    private final long propagatorId;
    private final long isEntailedId;
    private final IsEntailedFn isEntailedCallback;
    private static final ObjectHandles globalHandles = ObjectHandles.getGlobal();

    /** Maps an integer priority value (1–7) to a {@link PropagatorPriority}, defaulting to LINEAR. */
    private static PropagatorPriority toPriority(int p) {
        try {
            return PropagatorPriority.get(p);
        } catch (Exception e) {
            return PropagatorPriority.LINEAR;
        }
    }

    /**
     * Creates a CPropagator.
     *
     * @param vars               the IntVar variables this propagator acts on
     * @param propagatorId       unique ID used by the C bridge to dispatch to the propagate callback
     * @param callback           C function pointer to the propagation bridge
     * @param isEntailedId       unique ID for the isEntailed callback, or -1 to use the default (ESat.TRUE)
     * @param isEntailedCallback C function pointer to the isEntailed bridge
     * @param priority           propagator priority (1=UNARY … 7=VERY_SLOW); out-of-range values default to LINEAR (4)
     */
    public CPropagator(IntVar[] vars, long propagatorId, PropagateFn callback,
                       long isEntailedId, IsEntailedFn isEntailedCallback, int priority) {
        super(vars, toPriority(priority), false);
        this.propagatorId = propagatorId;
        this.callback = callback;
        this.isEntailedId = isEntailedId;
        this.isEntailedCallback = isEntailedCallback;
    }

    @Override
    public void propagate(int evtmask) throws ContradictionException {
        ObjectHandle varsHandle = globalHandles.create(this.vars);
        try {
            int result = callback.invoke(CurrentIsolate.getCurrentThread(), propagatorId, varsHandle);
            if (result < 0) {
                this.fails();
            }
        } finally {
            globalHandles.destroy(varsHandle);
        }
    }

    @Override
    public ESat isEntailed() {
        if (isEntailedId < 0) {
            return ESat.TRUE;
        }
        int result = isEntailedCallback.invoke(CurrentIsolate.getCurrentThread(), isEntailedId);
        if (result > 0) return ESat.TRUE;
        if (result < 0) return ESat.FALSE;
        return ESat.UNDEFINED;
    }
}
