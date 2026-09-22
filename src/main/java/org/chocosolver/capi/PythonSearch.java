package org.chocosolver.capi;

import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;

/**
 * Python-backed variable and value selectors for custom search strategies.
 *
 * <p>Each selector is given a unique ID at registration time. The C bridge dispatches
 * calls from the GraalVM thread to the appropriate Python callback.
 *
 * <p>Variable selector bridge signature:
 *   {@code int bridge(IsolateThread, long selectorId) -> index in vars[] or -1}
 *
 * <p>Value selector bridge signature:
 *   {@code int bridge(IsolateThread, long selectorId, int varIdx) -> chosen value}
 */
public class PythonSearch {

    /**
     * C function pointer for the variable selector bridge.
     * Returns the index of the chosen variable in the {@code vars[]} array, or -1 if none.
     */
    interface VarSelectorFn extends CFunctionPointer {
        @InvokeCFunctionPointer
        int invoke(IsolateThread thread, long selectorId);
    }

    /**
     * C function pointer for the value selector bridge.
     * Receives the index of the target variable in the stored {@code vars[]} array.
     * Returns the value to assign.
     */
    interface ValSelectorFn extends CFunctionPointer {
        @InvokeCFunctionPointer
        int invoke(IsolateThread thread, long selectorId, int varIdx);
    }

    /** Variable selector that delegates to a Python callback via the C bridge. */
    static class PythonVariableSelector implements VariableSelector<IntVar> {
        private final IntVar[] registeredVars;
        private final long selectorId;
        private final VarSelectorFn callback;

        PythonVariableSelector(IntVar[] registeredVars, long selectorId, VarSelectorFn callback) {
            this.registeredVars = registeredVars;
            this.selectorId = selectorId;
            this.callback = callback;
        }

        @Override
        public IntVar getVariable(IntVar[] variables) {
            // Choco may pass a filtered (uninstantiated-only) subset of the original vars,
            // so we index into our own copy and return the chosen variable directly.
            int idx = callback.invoke(CurrentIsolate.getCurrentThread(), selectorId);
            if (idx < 0 || idx >= registeredVars.length) return null;
            IntVar chosen = registeredVars[idx];
            // Skip already-instantiated variables (safety check in case the Python
            // selector returns a variable that is already fixed at this search node).
            return chosen.isInstantiated() ? null : chosen;
        }
    }

    /**
     * Value selector that delegates to a Python callback via the C bridge.
     *
     * <p>The stored {@code vars[]} array is the original array registered at construction.
     * When {@code selectValue} is called by the solver, the target variable is identified by
     * scanning {@code storedVars} for a reference match, and the index is forwarded to Python.
     */
    static class PythonValueSelector implements IntValueSelector {
        private final IntVar[] storedVars;
        private final long selectorId;
        private final ValSelectorFn callback;

        PythonValueSelector(IntVar[] storedVars, long selectorId, ValSelectorFn callback) {
            this.storedVars = storedVars;
            this.selectorId = selectorId;
            this.callback = callback;
        }

        @Override
        public int selectValue(IntVar var) {
            for (int i = 0; i < storedVars.length; i++) {
                if (storedVars[i] == var) {
                    return callback.invoke(CurrentIsolate.getCurrentThread(), selectorId, i);
                }
            }
            // Fallback: should not happen in normal usage
            return var.getLB();
        }
    }
}
