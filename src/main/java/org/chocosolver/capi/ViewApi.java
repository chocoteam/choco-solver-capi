package org.chocosolver.capi;

import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;


/**
 * C entrypoint API to create and manipulate views.
 * @author Dimitri Justeau-Allaire
 */
public class ViewApi {

    private static final String API_PREFIX = "ViewApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Boolean views

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "bool_not_view")
    public static ObjectHandle boolNotView(IsolateThread thread, ObjectHandle boolVarHandle) {
        BoolVar b = globalHandles.get(boolVarHandle);
        BoolVar view = b.getModel().boolNotView(b);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_bool_view")
    public static ObjectHandle setBoolView(IsolateThread thread, ObjectHandle setVarHandle, int v) {
        SetVar s = globalHandles.get(setVarHandle);
        BoolVar view = s.getModel().setBoolView(s, v);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_bools_view")
    public static ObjectHandle setBoolsView(IsolateThread thread, ObjectHandle setVarHandle, int size, int offset) {
        SetVar s = globalHandles.get(setVarHandle);
        BoolVar[] views = s.getModel().setBoolsView(s, size, offset);
        ObjectHandle res = globalHandles.create(views);
        return res;
    }

    // Integer views

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_offset_view")
    public static ObjectHandle intOffsetView(IsolateThread thread, ObjectHandle intVarHandle, int offset) {
        IntVar i = globalHandles.get(intVarHandle);
        IntVar view = i.getModel().intOffsetView(i, offset);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_minus_view")
    public static ObjectHandle intMinusView(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar i = globalHandles.get(intVarHandle);
        IntVar view = i.getModel().intMinusView(i);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_scale_view")
    public static ObjectHandle intScaleView(IsolateThread thread, ObjectHandle intVarHandle, int scale) {
        IntVar i = globalHandles.get(intVarHandle);
        IntVar view = i.getModel().intScaleView(i, scale);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_abs_view")
    public static ObjectHandle intAbsView(IsolateThread thread, ObjectHandle intVarHandle) {
        IntVar i = globalHandles.get(intVarHandle);
        IntVar view = i.getModel().intAbsView(i);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_affine_view")
    public static ObjectHandle intAffineView(IsolateThread thread, int a, ObjectHandle intVarHandle, int b) {
        IntVar i = globalHandles.get(intVarHandle);
        IntVar view = i.getModel().intAffineView(a, i, b);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_eq_view")
    public static ObjectHandle intEqView(IsolateThread thread, ObjectHandle intVarHandle, int value) {
        IntVar i = globalHandles.get(intVarHandle);
        BoolVar view = i.getModel().intEqView(i, value);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_ne_view")
    public static ObjectHandle intNeView(IsolateThread thread, ObjectHandle intVarHandle, int value) {
        IntVar i = globalHandles.get(intVarHandle);
        BoolVar view = i.getModel().intNeView(i, value);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_le_view")
    public static ObjectHandle intLeView(IsolateThread thread, ObjectHandle intVarHandle, int value) {
        IntVar i = globalHandles.get(intVarHandle);
        BoolVar view = i.getModel().intLeView(i, value);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "int_ge_view")
    public static ObjectHandle intGeView(IsolateThread thread, ObjectHandle intVarHandle, int value) {
        IntVar i = globalHandles.get(intVarHandle);
        BoolVar view = i.getModel().intGeView(i, value);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    // Set Variables

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "bools_set_view")
    public static ObjectHandle boolsSetView(IsolateThread thread, ObjectHandle boolVarsHandle, int offset) {
        BoolVar[] boolVars = globalHandles.get(boolVarsHandle);
        SetVar view = boolVars[0].getModel().boolsSetView(boolVars, offset);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "ints_set_view")
    public static ObjectHandle intsSetView(IsolateThread thread, ObjectHandle intVarsHandle, ObjectHandle vHandle,
                                           int offset) {
        IntVar[] intVars = globalHandles.get(intVarsHandle);
        int[] v = globalHandles.get(vHandle);
        SetVar view = intVars[0].getModel().intsSetView(intVars, v, offset);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_union_view")
    public static ObjectHandle setUnionView(IsolateThread thread, ObjectHandle setVarHandle) {
        SetVar[] setVars = globalHandles.get(setVarHandle);
        SetVar view = setVars[0].getModel().setUnionView(setVars);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_intersection_view")
    public static ObjectHandle setIntersectionView(IsolateThread thread, ObjectHandle setVarHandle) {
        SetVar[] setVars = globalHandles.get(setVarHandle);
        SetVar view = setVars[0].getModel().setIntersectionView(setVars);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_difference_view")
    public static ObjectHandle setDifferenceView(IsolateThread thread, ObjectHandle setVarHandle1,
                                                 ObjectHandle setVarHandle2) {
        SetVar setVar1 = globalHandles.get(setVarHandle1);
        SetVar setVar2 = globalHandles.get(setVarHandle2);
        SetVar view = setVar1.getModel().setDifferenceView(setVar1, setVar2);
        ObjectHandle res = globalHandles.create(view);
        return res;
    }
}
