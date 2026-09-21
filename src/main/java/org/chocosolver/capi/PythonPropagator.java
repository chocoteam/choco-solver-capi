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

/**
 * A Choco propagator that delegates its propagation logic to a Python callback
 * via a C function pointer bridge.
 *
 * <p>The bridge function receives a propagator ID and a handle to the variable
 * array, then dispatches to the appropriate Python callable registered in the
 * C layer.</p>
 */
public class PythonPropagator extends Propagator<IntVar> {

    /**
     * C function pointer interface for the propagation bridge.
     * Signature: int bridge(IsolateThread thread, long propagatorId, ObjectHandle varsHandle)
     */
    interface PropagateFn extends CFunctionPointer {
        @InvokeCFunctionPointer
        int invoke(IsolateThread thread, long propagatorId, ObjectHandle varsHandle);
    }

    private final PropagateFn callback;
    private final long propagatorId;
    private static final ObjectHandles globalHandles = ObjectHandles.getGlobal();

    /**
     * Creates a PythonPropagator.
     *
     * @param vars         the IntVar variables this propagator acts on
     * @param propagatorId unique ID used by the C bridge to look up the Python callable
     * @param callback     C function pointer to the static bridge in backend.c
     */
    public PythonPropagator(IntVar[] vars, long propagatorId, PropagateFn callback) {
        super(vars, PropagatorPriority.LINEAR, false);
        this.propagatorId = propagatorId;
        this.callback = callback;
    }

    @Override
    public void propagate(int evtmask) throws ContradictionException {
        ObjectHandle varsHandle = globalHandles.create(this.vars);
        int result = callback.invoke(CurrentIsolate.getCurrentThread(), propagatorId, varsHandle);
        globalHandles.destroy(varsHandle);
        if (result < 0) {
            this.fails();
        }
    }

    @Override
    public ESat isEntailed() {
        return ESat.UNDEFINED;
    }
}
