package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate constraints.
 * @author Dimitri Justeau-Allaire
 */
public class ConstraintApi {

    private static final String API_PREFIX = "ConstraintApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // --------------- //
    // Factory methods //
    // --------------- //

    // INT CONSTRAINTS

    // arithm

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "arithm_iv_cst")
    public static ObjectHandle arithm_iv_cst(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle,
                                             CCharPointer operator, int constant) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        String op = CTypeConversion.toJavaString(operator);
        Constraint arithm = model.arithm(intVar, op, constant);
        ObjectHandle res = globalHandles.create(arithm);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "arithm_iv_iv")
    public static ObjectHandle arithm_iv_iv(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                            CCharPointer operator, ObjectHandle intVarHandle2) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        String op = CTypeConversion.toJavaString(operator);
        Constraint arithm = model.arithm(intVar1, op, intVar2);
        ObjectHandle res = globalHandles.create(arithm);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "arithm_iv_iv_cst")
    public static ObjectHandle arithm_iv_iv_cst(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle intVarHandle1, CCharPointer operator1,
                                                ObjectHandle intVarHandle2, CCharPointer operator2, int constant) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        String op1 = CTypeConversion.toJavaString(operator1);
        String op2 = CTypeConversion.toJavaString(operator2);
        Constraint arithm = model.arithm(intVar1, op1, intVar2, op2, constant);
        ObjectHandle res = globalHandles.create(arithm);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "arithm_iv_iv_iv")
    public static ObjectHandle arithm_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle,
                                               ObjectHandle intVarHandle1, CCharPointer operator1,
                                               ObjectHandle intVarHandle2, CCharPointer operator2,
                                               ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        String op1 = CTypeConversion.toJavaString(operator1);
        String op2 = CTypeConversion.toJavaString(operator2);
        Constraint arithm = model.arithm(intVar1, op1, intVar2, op2, intVar3);
        ObjectHandle res = globalHandles.create(arithm);
        return res;
    }

    // member

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "member_iv_iarray")
    public static ObjectHandle member_iv_iarray(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle,
                                                ObjectHandle tableHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        int[] table = globalHandles.get(tableHandle);
        Constraint member = model.member(intVar, table);
        ObjectHandle res = globalHandles.create(member);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "member_iv_i_i")
    public static ObjectHandle member_iv_i_i(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle,
                                            int lb, int ub) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        Constraint member = model.member(intVar, lb, ub);
        ObjectHandle res = globalHandles.create(member);
        return res;
    }

    // allDifferent TODO add other variants

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "allDifferent")
    public static ObjectHandle allDifferent(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] vars = globalHandles.get(intVarArrayHandle);
        Constraint allDiff = model.allDifferent(vars);
        ObjectHandle res = globalHandles.create(allDiff);
        return res;
    }

    // mod

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "mod_iv_i_i")
    public static ObjectHandle mod_iv_i_i(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle,
                                          int mod, int result) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        Constraint modConstraint = model.mod(intVar, mod, result);
        ObjectHandle res = globalHandles.create(modConstraint);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "mod_iv_i_iv")
    public static ObjectHandle mod_iv_i_i(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                          int mod, ObjectHandle intVarHandle2) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        Constraint modConstraint = model.mod(intVar1, mod, intVar2);
        ObjectHandle res = globalHandles.create(modConstraint);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "mod_iv_iv_iv")
    public static ObjectHandle mod_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                            ObjectHandle intVarHandle2, ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        Constraint modConstraint = model.mod(intVar1, intVar2, intVar3);
        ObjectHandle res = globalHandles.create(modConstraint);
        return res;
    }

    // not

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "not")
    public static ObjectHandle not(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle constraintHandle) {
        Model model = globalHandles.get(modelHandle);
        Constraint constraint = globalHandles.get(constraintHandle);
        Constraint not = model.not(constraint);
        ObjectHandle res = globalHandles.create(not);
        return res;
    }

    // notMember

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "not_member_iv_iarray")
    public static ObjectHandle not_member_iv_iarray(IsolateThread thread, ObjectHandle modelHandle,
                                                    ObjectHandle intVarHandle, ObjectHandle tableHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        int[] table = globalHandles.get(tableHandle);
        Constraint member = model.notMember(intVar, table);
        ObjectHandle res = globalHandles.create(member);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "not_member_iv_i_i")
    public static ObjectHandle not_member_iv_i_i(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle intVarHandle, int lb, int ub) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        Constraint member = model.notMember(intVar, lb, ub);
        ObjectHandle res = globalHandles.create(member);
        return res;
    }

    // absolute

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "absolute")
    public static ObjectHandle absolute(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                        ObjectHandle intVarHandle2) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        Constraint absolute = model.absolute(intVar1, intVar2);
        ObjectHandle res = globalHandles.create(absolute);
        return res;
    }

    // distance

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "distance_iv_iv_i")
    public static ObjectHandle distance_iv_iv_i(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle intVarHandle1, ObjectHandle intVarHandle2,
                                                CCharPointer op, int constant) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        String operator = CTypeConversion.toJavaString(op);
        Constraint distance = model.distance(intVar1, intVar2, operator, constant);
        ObjectHandle res = globalHandles.create(distance);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "distance_iv_iv_iv")
    public static ObjectHandle distance_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle intVarHandle1, ObjectHandle intVarHandle2,
                                                 CCharPointer op, ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        String operator = CTypeConversion.toJavaString(op);
        Constraint distance = model.distance(intVar1, intVar2, operator, intVar3);
        ObjectHandle res = globalHandles.create(distance);
        return res;
    }

    // element

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "element_iv_iarray_iv_i")
    public static ObjectHandle element_iv_iarray_iv_i(IsolateThread thread, ObjectHandle modelHandle,
                                                      ObjectHandle intVarHandle1, ObjectHandle tableHandle,
                                                      ObjectHandle intVarHandle2, int offset) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        int[] table = globalHandles.get(tableHandle);
        Constraint element = model.element(intVar1, table, intVar2, offset);
        ObjectHandle res = globalHandles.create(element);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "element_iv_ivarray_iv_i")
    public static ObjectHandle element_iv_ivarray_iv_i(IsolateThread thread, ObjectHandle modelHandle,
                                                      ObjectHandle intVarHandle1, ObjectHandle tableHandle,
                                                      ObjectHandle intVarHandle2, int offset) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar[] table = globalHandles.get(tableHandle);
        Constraint element = model.element(intVar1, table, intVar2, offset);
        ObjectHandle res = globalHandles.create(element);
        return res;
    }

    // square

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "square")
    public static ObjectHandle square(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                      ObjectHandle intVarHandle2) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        Constraint square = model.square(intVar1, intVar2);
        ObjectHandle res = globalHandles.create(square);
        return res;
    }

    // table TODO

    // times

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "times_iv_i_iv")
    public static ObjectHandle times_iv_i_iv(IsolateThread thread, ObjectHandle modelHandle,
                                             ObjectHandle intVarHandle1, int Y, ObjectHandle intVarHandle2) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        Constraint times = model.times(intVar1, Y, intVar2);
        ObjectHandle res = globalHandles.create(times);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "times_iv_iv_i")
    public static ObjectHandle times_iv_iv_i(IsolateThread thread, ObjectHandle modelHandle,
                                             ObjectHandle intVarHandle1, ObjectHandle intVarHandle2, int Z) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        Constraint times = model.times(intVar1, intVar2, Z);
        ObjectHandle res = globalHandles.create(times);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "times_iv_iv_iv")
    public static ObjectHandle times_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle,
                                              ObjectHandle intVarHandle1, ObjectHandle intVarHandle2,
                                              ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        Constraint times = model.times(intVar1, intVar2, intVar3);
        ObjectHandle res = globalHandles.create(times);
        return res;
    }

    // pow TODO seems absent in Choco

    // div

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "div")
    public static ObjectHandle div(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                   ObjectHandle intVarHandle2, ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        Constraint div = model.div(intVar1, intVar2, intVar3);
        ObjectHandle res = globalHandles.create(div);
        return res;
    }

    // max

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "max_iv_iv_iv")
    public static ObjectHandle max_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                            ObjectHandle intVarHandle2, ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        Constraint max = model.max(intVar1, intVar2, intVar3);
        ObjectHandle res = globalHandles.create(max);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "max_iv_ivarray")
    public static ObjectHandle max_iv_ivarray(IsolateThread thread, ObjectHandle modelHandle,
                                              ObjectHandle intVarHandle1, ObjectHandle intVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar[] intVarArray = globalHandles.get(intVarArrayHandle);
        Constraint max = model.max(intVar1, intVarArray);
        ObjectHandle res = globalHandles.create(max);
        return res;
    }

    // min

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "min_iv_iv_iv")
    public static ObjectHandle min_iv_iv_iv(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle intVarHandle1,
                                            ObjectHandle intVarHandle2, ObjectHandle intVarHandle3) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar intVar2 = globalHandles.get(intVarHandle2);
        IntVar intVar3 = globalHandles.get(intVarHandle3);
        Constraint min = model.min(intVar1, intVar2, intVar3);
        ObjectHandle res = globalHandles.create(min);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "min_iv_ivarray")
    public static ObjectHandle min_iv_ivarray(IsolateThread thread, ObjectHandle modelHandle,
                                              ObjectHandle intVarHandle1, ObjectHandle intVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar1 = globalHandles.get(intVarHandle1);
        IntVar[] intVarArray = globalHandles.get(intVarArrayHandle);
        Constraint min = model.min(intVar1, intVarArray);
        ObjectHandle res = globalHandles.create(min);
        return res;
    }

    // allEqual TODO

    // notAllEqual TODO

    // among TODO

    // and TODO

    // atLeastNValues TODO

    // atMostNValues TODO

    // binPacking TODO

    // boolsIntChanneling TODO

    // bitsIntChanneling TODO

    // clausesIntChanneling TODO

    // circuit TODO

    // costRegular TODO

    // count TODO

    // cumulative TODO

    // decreasing TODO

    // diffN TODO

    // element TODO

    // globalCardinality TODO

    // increasing TODO

    // inverseChanneling TODO

    // intValuePrecedeChain TODO

    // knapsack TODO

    // keySort TODO

    // lexChainLess TODO

    // lexChainLessEq TODO

    // lexLess TODO

    // lexLessEq TODO

    // argmax TODO

    // argmin TODO

    // mddc TODO

    // multiCostRegular TODO

    // nValues TODO

    // or TODO

    // path TODO

    // regular TODO

    // scalar TODO

    // sort TODO

    // subCircuit TODO

    // subPath TODO

    // sum TODO

    // tree TODO

    // -------------- //
    // Object methods //
    // -------------- //

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "getName")
    public static CCharPointer getName(IsolateThread thread, ObjectHandle constraintHandle) {
        Constraint constraint = globalHandles.get(constraintHandle);
        String name = constraint.getName();
        return CTypeConversion.toCString(name).get();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "post")
    public static void post(IsolateThread thread, ObjectHandle constraintHandle) {
        Constraint constraint = globalHandles.get(constraintHandle);
        constraint.post();
    }

}
