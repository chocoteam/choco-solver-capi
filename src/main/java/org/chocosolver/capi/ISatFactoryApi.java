package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.variables.BoolVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * API entrypoint to SAT constraints
 */
public class ISatFactoryApi {

    private static final String API_PREFIX = "ISatFactoryApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_logop")
    public static boolean addClauses(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle TREE) {
        Model model = globalHandles.get(modelHandle);
        LogOp ltree = globalHandles.get(TREE);
        return model.addClauses(ltree);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses")
    public static boolean addClauses(IsolateThread thread, ObjectHandle modelHandle,
                                     ObjectHandle POSLITS, ObjectHandle NEGLITS) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bposlits = globalHandles.get(POSLITS);
        BoolVar[] bneglits = globalHandles.get(NEGLITS);
        return model.addClauses(bposlits, bneglits);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clause_true")
    public static boolean addClauseTrue(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle BOOLVAR) {
        Model model = globalHandles.get(modelHandle);
        BoolVar b = globalHandles.get(BOOLVAR);
        return model.addClauseTrue(b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clause_false")
    public static boolean addClauseFalse(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle BOOLVAR) {
        Model model = globalHandles.get(modelHandle);
        BoolVar b = globalHandles.get(BOOLVAR);
        return model.addClauseFalse(b);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_eq")
    public static boolean addClausesBoolEq(IsolateThread thread, ObjectHandle modelHandle,
                                           ObjectHandle LEFT, ObjectHandle RIGHT) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        return model.addClausesBoolEq(bleft, bright);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_le")
    public static boolean addClausesBoolLe(IsolateThread thread, ObjectHandle modelHandle,
                                           ObjectHandle LEFT, ObjectHandle RIGHT) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        return model.addClausesBoolLe(bleft, bright);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_lt")
    public static boolean addClausesBoolLt(IsolateThread thread, ObjectHandle modelHandle,
                                           ObjectHandle LEFT, ObjectHandle RIGHT) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        return model.addClausesBoolLt(bleft, bright);

    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_not")
    public static boolean addClausesBoolNot(IsolateThread thread, ObjectHandle modelHandle,
                                            ObjectHandle LEFT, ObjectHandle RIGHT) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        return model.addClausesBoolNot(bleft, bright);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_or_array_eq_var")
    public static boolean addClausesBoolOrArrayEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                     ObjectHandle BOOLVARS, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolOrArrayEqVar(bvars, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_and_array_eq_var")
    public static boolean addClausesBoolAndArrayEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                      ObjectHandle BOOLVARS, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolAndArrayEqVar(bvars, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_or_eq_var")
    public static boolean addClausesBoolOrEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolOrEqVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_and_eq_var")
    public static boolean addClausesBoolAndEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolAndEqVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_xor_eq_var")
    public static boolean addClausesBoolXorEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolXorEqVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_is_eq_var")
    public static boolean addClausesBoolIsEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolIsEqVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_is_neq_var")
    public static boolean addClausesBoolIsNeqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                 ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolIsNeqVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_is_le_var")
    public static boolean addClausesBoolIsLeVar(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolIsLeVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_is_lt_var")
    public static boolean addClausesBoolIsLtVar(IsolateThread thread, ObjectHandle modelHandle,
                                                ObjectHandle LEFT, ObjectHandle RIGHT, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar bleft = globalHandles.get(LEFT);
        BoolVar bright = globalHandles.get(RIGHT);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesBoolIsLtVar(bleft, bright, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_or_array_equal_true")
    public static boolean addClausesBoolOrArrayEqualTrue(IsolateThread thread, ObjectHandle modelHandle,
                                                         ObjectHandle BOOLVARS) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        return model.addClausesBoolOrArrayEqualTrue(bvars);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_bool_and_array_equal_false")
    public static boolean addClausesBoolAndArrayEqualFalse(IsolateThread thread, ObjectHandle modelHandle,
                                                           ObjectHandle BOOLVARS) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        return model.addClausesBoolAndArrayEqualFalse(bvars);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_at_most_one")
    public static boolean addClausesAtMostOne(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle BOOLVARS) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        return model.addClausesAtMostOne(bvars);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_at_most_nminus_one")
    public static boolean addClausesAtMostNMinusOne(IsolateThread thread, ObjectHandle modelHandle,
                                                    ObjectHandle BOOLVARS) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        return model.addClausesAtMostNMinusOne(bvars);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_sum_bool_array_greater_eq_var")
    public static boolean addClausesSumBoolArrayGreaterEqVar(IsolateThread thread, ObjectHandle modelHandle,
                                                             ObjectHandle BOOLVARS, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesSumBoolArrayGreaterEqVar(bvars, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_max_bool_array_less_eq_var")
    public static boolean addClausesMaxBoolArrayLessEqVar(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle BOOLVARS, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesMaxBoolArrayLessEqVar(bvars, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_clauses_sum_bool_array_less_eq_kvar")
    public static boolean addClausesSumBoolArrayLessEqKVar(IsolateThread thread, ObjectHandle modelHandle,
                                                           ObjectHandle BOOLVARS, ObjectHandle TARGET) {
        Model model = globalHandles.get(modelHandle);
        BoolVar[] bvars = globalHandles.get(BOOLVARS);
        BoolVar btarget = globalHandles.get(TARGET);
        return model.addClausesSumBoolArrayLessEqKVar(bvars, btarget);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_constructive_disjunction")
    public static boolean addConstructiveDisjunction(IsolateThread thread, ObjectHandle modelHandle, ObjectHandle cstrs) {
        Model model = globalHandles.get(modelHandle);
        Constraint[] constraints = globalHandles.get(cstrs);
        return model.addConstructiveDisjunction(constraints);
    }
}
