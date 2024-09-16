package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.objects.setDataStructures.iterable.IntIterableRangeSet;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API for IReificationFactory.
 * @author Dimitri Justeau-Allaire
 */
public class ReificationApi {

    private static final String API_PREFIX = "ReificationApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then_else")
    public static void ifThenElse(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle constraintHandleIf, ObjectHandle constraintHandleThen,
                                  ObjectHandle constraintHandleElse) {

        Model model = globalHandles.get(modelHandle);
        Constraint constraintIf = globalHandles.get(constraintHandleIf);
        Constraint constraintThen = globalHandles.get(constraintHandleThen);
        Constraint constraintElse = globalHandles.get(constraintHandleElse);
        model.ifThenElse(constraintIf, constraintThen, constraintElse);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then_else_bool")
    public static void ifThenElseBool(IsolateThread thread, ObjectHandle modelHandle,
                                      ObjectHandle boolVarHandle, ObjectHandle constraintHandleThen,
                                      ObjectHandle constraintHandleElse) {

        Model model = globalHandles.get(modelHandle);
        BoolVar boolVarIf = globalHandles.get(boolVarHandle);
        Constraint constraintThen = globalHandles.get(constraintHandleThen);
        Constraint constraintElse = globalHandles.get(constraintHandleElse);
        model.ifThenElse(boolVarIf, constraintThen, constraintElse);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then")
    public static void ifThen(IsolateThread thread, ObjectHandle modelHandle,
                              ObjectHandle constraintHandleIf, ObjectHandle constraintHandleThen) {

        Model model = globalHandles.get(modelHandle);
        Constraint constraintIf = globalHandles.get(constraintHandleIf);
        Constraint constraintThen = globalHandles.get(constraintHandleThen);
        model.ifThen(constraintIf, constraintThen);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then_bool")
    public static void ifThenBool(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle boolVarHandle, ObjectHandle constraintHandleThen) {

        Model model = globalHandles.get(modelHandle);
        BoolVar boolVarIf = globalHandles.get(boolVarHandle);
        Constraint constraintThen = globalHandles.get(constraintHandleThen);
        model.ifThen(boolVarIf, constraintThen);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_only_if")
    public static void ifOnlyIf(IsolateThread thread, ObjectHandle modelHandle,
                                ObjectHandle cstr1Handle, ObjectHandle cstr2Handle) {

        Model model = globalHandles.get(modelHandle);
        Constraint cstr1 = globalHandles.get(cstr1Handle);
        Constraint cstr2 = globalHandles.get(cstr2Handle);
        model.ifOnlyIf(cstr1, cstr2);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reification")
    public static void reification(IsolateThread thread, ObjectHandle modelHandle,
                                   ObjectHandle boolVarHandle, ObjectHandle cstrHandle) {

        Model model = globalHandles.get(modelHandle);
        BoolVar boolVar = globalHandles.get(boolVarHandle);
        Constraint cstr = globalHandles.get(cstrHandle);
        model.reification(boolVar, cstr);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_eq_c")
    public static void reifyXeqC(IsolateThread thread, ObjectHandle modelHandle,
                                ObjectHandle xHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXeqC(x, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_ne_c")
    public static void reifyXneC(IsolateThread thread, ObjectHandle modelHandle,
                                ObjectHandle xHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXneC(x, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_eq_y")
    public static void reifyXeqY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXeqY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_ne_y")
    public static void reifyXneY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXneY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_eq_yc")
    public static void reifyXeqYC(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXeqYC(x, y, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_ne_yc")
    public static void reifyXneYC(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle xHandle, ObjectHandle yHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXneYC(x, y, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_lt_c")
    public static void reifyXltC(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle xHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXltC(x, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_gt_c")
    public static void reifyXgtC(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXgtC(x, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_lt_y")
    public static void reifyXltY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXltY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_gt_y")
    public static void reifyXgtY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXgtY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_le_y")
    public static void reifyXleY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXleY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_ge_y")
    public static void reifyXgeY(IsolateThread thread, ObjectHandle modelHandle,
                                 ObjectHandle xHandle, ObjectHandle yHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXgeY(x, y, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_lt_yc")
    public static void reifyXltYC(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle xHandle, ObjectHandle yHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXltYC(x, y, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_gt_yc")
    public static void reifyXgtYC(IsolateThread thread, ObjectHandle modelHandle,
                                  ObjectHandle xHandle, ObjectHandle yHandle, int c, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);
        IntVar x = globalHandles.get(xHandle);
        IntVar y = globalHandles.get(yHandle);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXgtYC(x, y, c, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_in_s")
    public static void reify_x_in_s(IsolateThread thread, ObjectHandle modelHandle,
                                    ObjectHandle xHandle, ObjectHandle sArrayHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);

        IntVar x = globalHandles.get(xHandle);
        int[] sArray = globalHandles.get(sArrayHandle);
        IntIterableRangeSet s = new IntIterableRangeSet(sArray);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXinS(x, s, b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify_x_not_in_s")
    public static void reify_x_not_in_s(IsolateThread thread, ObjectHandle modelHandle,
                                        ObjectHandle xHandle, ObjectHandle sArrayHandle, ObjectHandle bHandle) {

        Model model = globalHandles.get(modelHandle);

        IntVar x = globalHandles.get(xHandle);
        int[] sArray = globalHandles.get(sArrayHandle);
        IntIterableRangeSet s = new IntIterableRangeSet(sArray);
        BoolVar b = globalHandles.get(bHandle);
        model.reifyXnotinS(x, s, b);
    }
}
