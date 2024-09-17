package org.chocosolver.capi;

import org.chocosolver.solver.constraints.extension.hybrid.ISupportable;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

import static org.chocosolver.solver.constraints.extension.hybrid.HybridTuples.*;

/**
 * API to create and manipulate ISupportable expressions for the hybrid table constraint
 */
public class ISupportableApi {

    private static final String API_PREFIX = "ISupportableApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "any")
    public static ObjectHandle expAny(IsolateThread thread) {
        ISupportable c = any();
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "col")
    public static ObjectHandle expCol(IsolateThread thread, int idx) {
        ISupportable c = col(idx);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "eq")
    public static ObjectHandle expEq(IsolateThread thread, int val) {
        ISupportable c = eq(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ne")
    public static ObjectHandle expNe(IsolateThread thread, int val) {
        ISupportable c = eq(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ge")
    public static ObjectHandle expGe(IsolateThread thread, int val) {
        ISupportable c = ge(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "gt")
    public static ObjectHandle expGt(IsolateThread thread, int val) {
        ISupportable c = gt(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "le")
    public static ObjectHandle expLe(IsolateThread thread, int val) {
        ISupportable c = le(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "lt")
    public static ObjectHandle expLt(IsolateThread thread, int val) {
        ISupportable c = lt(val);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "in")
    public static ObjectHandle expIn(IsolateThread thread, ObjectHandle valuesHandle) {
        int[] values = globalHandles.get(valuesHandle);
        ISupportable c = in(values);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "nin")
    public static ObjectHandle expNin(IsolateThread thread, ObjectHandle valuesHandle) {
        int[] values = globalHandles.get(valuesHandle);
        ISupportable c = nin(values);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "eq_col")
    public static ObjectHandle expEqCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = eq(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ne_col")
    public static ObjectHandle expNeCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = ne(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ge_col")
    public static ObjectHandle expGeCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = ge(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "gt_col")
    public static ObjectHandle expGtCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = gt(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "le_col")
    public static ObjectHandle expLeCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = le(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "lt_col")
    public static ObjectHandle expLtCol(IsolateThread thread, ObjectHandle colHandle, int inc) {
        ISupportable.UnCol col = globalHandles.get(colHandle);
        ISupportable c = lt(col, inc);
        ObjectHandle res = globalHandles.create(c);
        return res;
    }
}
