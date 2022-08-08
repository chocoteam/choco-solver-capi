package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.nary.circuit.CircuitConf;
import org.chocosolver.solver.variables.BoolVar;
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

    // table TODO implement TuplesApi

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

    // allEqual

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "all_equal")
    public static ObjectHandle all_equal(IsolateThread thread, ObjectHandle modelHandle,
                                         ObjectHandle intVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVarArray = globalHandles.get(intVarArrayHandle);
        Constraint allEqual = model.allEqual(intVarArray);
        ObjectHandle res = globalHandles.create(allEqual);
        return res;
    }

    // notAllEqual

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "not_all_equal")
    public static ObjectHandle not_all_equal(IsolateThread thread, ObjectHandle modelHandle,
                                             ObjectHandle intVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVarArray = globalHandles.get(intVarArrayHandle);
        Constraint notAllEqual = model.notAllEqual(intVarArray);
        ObjectHandle res = globalHandles.create(notAllEqual);
        return res;
    }

    // among

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "among")
    public static ObjectHandle among(IsolateThread thread, ObjectHandle modelHandle,
                                             ObjectHandle nbVarHandle, ObjectHandle intVarArrayHandle,
                                             ObjectHandle valuesHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar nbVar = globalHandles.get(nbVarHandle);
        IntVar[] intVarArray = globalHandles.get(intVarArrayHandle);
        int[] value = globalHandles.get(valuesHandle);
        Constraint among = model.among(nbVar, intVarArray, value);
        ObjectHandle res = globalHandles.create(among);
        return res;
    }

    // and

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "and_bv_bv")
    public static ObjectHandle and_bv_bv(IsolateThread thread, ObjectHandle modelHandle,
                                   ObjectHandle boolVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] boolVars = globalHandles.get(boolVarArrayHandle);
        Constraint and = model.and(boolVars);
        ObjectHandle res = globalHandles.create(and);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "and_cs_cs")
    public static ObjectHandle and_cs_cs(IsolateThread thread, ObjectHandle modelHandle,
                                         ObjectHandle constraintArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        Constraint[] constraints = globalHandles.get(constraintArrayHandle);
        Constraint and = model.and(constraints);
        ObjectHandle res = globalHandles.create(and);
        return res;
    }

    // atLeastNValues

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "atLeastNValues")
    public static ObjectHandle atLeastNValues(IsolateThread thread, ObjectHandle modelHandle,
                                              ObjectHandle intVarArrayHandle, ObjectHandle nValuesHandle, boolean AC) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intvars = globalHandles.get(intVarArrayHandle);
        IntVar nValues = globalHandles.get(nValuesHandle);
        Constraint atLeastNValues = model.atLeastNValues(intvars, nValues, AC);
        ObjectHandle res = globalHandles.create(atLeastNValues);
        return res;
    }

    // atMostNValues

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "atMostNValues")
    public static ObjectHandle atMostNValues(IsolateThread thread, ObjectHandle modelHandle,
                                              ObjectHandle intVarArrayHandle, ObjectHandle nValuesHandle,
                                             boolean STRONG) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intvars = globalHandles.get(intVarArrayHandle);
        IntVar nValues = globalHandles.get(nValuesHandle);
        Constraint atMostNValues = model.atMostNValues(intvars, nValues, STRONG);
        ObjectHandle res = globalHandles.create(atMostNValues);
        return res;
    }

    // binPacking

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "binPacking")
    public static ObjectHandle binPacking(IsolateThread thread, ObjectHandle modelHandle,
                                          ObjectHandle itemBinHandle, ObjectHandle itemSizeHandle,
                                          ObjectHandle binLoadHandle, int offset) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] itemBin = globalHandles.get(itemBinHandle);
        int[] itemSize = globalHandles.get(itemSizeHandle);
        IntVar[] binLoad = globalHandles.get(binLoadHandle);
        Constraint binPacking = model.binPacking(itemBin, itemSize, binLoad, offset);
        ObjectHandle res = globalHandles.create(binPacking);
        return res;
    }

    // boolsIntChanneling

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "boolsIntChanneling")
    public static ObjectHandle boolsIntChanneling(IsolateThread thread, ObjectHandle modelHandle,
                                                  ObjectHandle boolVarsHandle, ObjectHandle intVarHandle,
                                                  int offset) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] boolVars = globalHandles.get(boolVarsHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        Constraint boolsIntChanneling = model.boolsIntChanneling(boolVars, intVar, offset);
        ObjectHandle res = globalHandles.create(boolsIntChanneling);
        return res;
    }

    // bitsIntChanneling

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "bitsIntChanneling")
    public static ObjectHandle bitsIntChanneling(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle boolVarsHandle, ObjectHandle intVarHandle) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] boolVars = globalHandles.get(boolVarsHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        Constraint bitsIntChanneling = model.bitsIntChanneling(boolVars, intVar);
        ObjectHandle res = globalHandles.create(bitsIntChanneling);
        return res;
    }

    // clausesIntChanneling

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "clausesIntChanneling")
    public static ObjectHandle clausesIntChanneling(IsolateThread thread, ObjectHandle modelHandle,
                                                    ObjectHandle intVarHandle, ObjectHandle eVarsHandle,
                                                    ObjectHandle lVarsHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar intVar = globalHandles.get(intVarHandle);
        BoolVar[] eVars = globalHandles.get(eVarsHandle);
        BoolVar[] lVars = globalHandles.get(lVarsHandle);
        Constraint clausesIntChanneling = model.clausesIntChanneling(intVar, eVars, lVars);
        ObjectHandle res = globalHandles.create(clausesIntChanneling);
        return res;
    }

    // circuit

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "circuit")
    public static ObjectHandle circuit(IsolateThread thread, ObjectHandle modelHandle,
                                       ObjectHandle intVarArrayHandle, int offset,
                                       CCharPointer conf) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVars = globalHandles.get(intVarArrayHandle);
        String jConf = CTypeConversion.toJavaString(conf);
        CircuitConf cConf;
        switch (jConf) {
            case "LIGHT":
                cConf = CircuitConf.LIGHT;
                break;
            case "FIRST":
                cConf = CircuitConf.FIRST;
                break;
            case "ALL":
                cConf = CircuitConf.ALL;
                break;
            default:
                cConf = CircuitConf.RD;
                break;
        }
        Constraint circuit = model.circuit(intVars, offset, cConf);
        ObjectHandle res = globalHandles.create(circuit);
        return res;
    }

    // costRegular TODO ADD AUTOMATON API

    // count

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "count_i")
    public static ObjectHandle count_i(IsolateThread thread, ObjectHandle modelHandle,
                                       int value, ObjectHandle intVarArrayHandle,
                                       ObjectHandle limitHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVars = globalHandles.get(intVarArrayHandle);
        IntVar limit = globalHandles.get(limitHandle);
        Constraint count = model.count(value, intVars, limit);
        ObjectHandle res = globalHandles.create(count);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "count_iv")
    public static ObjectHandle count_iv(IsolateThread thread, ObjectHandle modelHandle,
                                        ObjectHandle valueHandle, ObjectHandle intVarArrayHandle,
                                        ObjectHandle limitHandle) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVars = globalHandles.get(intVarArrayHandle);
        IntVar value = globalHandles.get(valueHandle);
        IntVar limit = globalHandles.get(limitHandle);
        Constraint count = model.count(value, intVars, limit);
        ObjectHandle res = globalHandles.create(count);
        return res;
    }

    // cumulative TODO Implement task API

    // decreasing TODO Seems absent in Choco

    // diffN

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "diffN")
    public static ObjectHandle diffN(IsolateThread thread, ObjectHandle modelHandle,
                                     ObjectHandle XHandle, ObjectHandle YHandle,
                                     ObjectHandle widthHandle, ObjectHandle heightHandle,
                                     boolean addCumulativeReasoning) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] X = globalHandles.get(XHandle);
        IntVar[] Y = globalHandles.get(YHandle);
        IntVar[] width = globalHandles.get(widthHandle);
        IntVar[] height = globalHandles.get(heightHandle);
        Constraint diffN = model.diffN(X, Y, width, height, addCumulativeReasoning);
        ObjectHandle res = globalHandles.create(diffN);
        return res;
    }

    // globalCardinality

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "globalCardinality")
    public static ObjectHandle globalCardinality(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle intVarArrayHandle, ObjectHandle valuesHandle,
                                                 ObjectHandle occurrencesHandle, boolean closed) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVars = globalHandles.get(intVarArrayHandle);
        IntVar[] occurrences = globalHandles.get(occurrencesHandle);
        int[] values = globalHandles.get(valuesHandle);
        Constraint gcc = model.globalCardinality(intVars, values, occurrences, closed);
        ObjectHandle res = globalHandles.create(gcc);
        return res;
    }

    // increasing TODO Seems absent in Choco

    // inverseChanneling

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "inverseChanneling")
    public static ObjectHandle inverseChanneling(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle intVarArrayHandle1, ObjectHandle intVarArrayHandle2,
                                                 int offset1, int offset2, boolean ac) {
        Model model = globalHandles.get(modelHandle);
        IntVar[] intVars1 = globalHandles.get(intVarArrayHandle1);
        IntVar[] intVars2 = globalHandles.get(intVarArrayHandle2);
        Constraint inverseChanneling = model.inverseChanneling(intVars1, intVars2, offset1, offset2, ac);
        ObjectHandle res = globalHandles.create(inverseChanneling);
        return res;
    }

    // intValuePrecedeChain TODO

    // knapsack TODO

    // keySort TODO

    // lexChainLess TODO

    // lexChainLessEq TODO

    // lexLess TODO

    // lexLessEq TODO

    // argmax TODO

    // argmin TODO

    // mddc TODO Implement MDD Api

    // multiCostRegular TODO Implement Automaton Api

    // nValues TODO

    // or

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "or_bv_bv")
    public static ObjectHandle or_bv_bv(IsolateThread thread, ObjectHandle modelHandle,
                                        ObjectHandle boolVarArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] boolVars = globalHandles.get(boolVarArrayHandle);
        Constraint or = model.or(boolVars);
        ObjectHandle res = globalHandles.create(or);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "or_cs_cs")
    public static ObjectHandle or_cs_cs(IsolateThread thread, ObjectHandle modelHandle,
                                         ObjectHandle constraintArrayHandle) {
        Model model = globalHandles.get(modelHandle);
        Constraint[] constraints = globalHandles.get(constraintArrayHandle);
        Constraint or = model.or(constraints);
        ObjectHandle res = globalHandles.create(or);
        return res;
    }

    // path TODO

    // regular TODO Implement Automaton Api

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

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reify")
    public static ObjectHandle reify(IsolateThread thread, ObjectHandle constraintHandle) {
        Constraint constraint = globalHandles.get(constraintHandle);
        BoolVar b = constraint.reify();
        ObjectHandle res = globalHandles.create(b);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "is_satisfied")
    public static int isSatisfied(IsolateThread thread, ObjectHandle constraintHandle) {
        Constraint constraint = globalHandles.get(constraintHandle);
        switch (constraint.isSatisfied()) {
            case FALSE:
                return 0;
            case TRUE:
                return 1;
            case UNDEFINED:
                return 2;
        }
        return 2;
    }
}
