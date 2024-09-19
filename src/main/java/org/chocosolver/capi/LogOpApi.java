package org.chocosolver.capi;

import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.variables.BoolVar;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * API entrypoint to Logic operators
 */
public class LogOpApi {

    private static final String API_PREFIX = "LogOpApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "and")
    public static ObjectHandle and(IsolateThread thread, ObjectHandle op) {
        LogOp[] ops = globalHandles.get(op);
        LogOp rlogop = LogOp.and(ops);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_only_if")
    public static ObjectHandle ifOnlyIf(IsolateThread thread, ObjectHandle a, ObjectHandle b) {
        LogOp opA = globalHandles.get(a);
        LogOp opB = globalHandles.get(b);
        LogOp rlogop = LogOp.ifOnlyIf(opA, opB);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "if_then_else")
    public static ObjectHandle ifThenElse(IsolateThread thread, ObjectHandle a, ObjectHandle b, ObjectHandle c) {
        LogOp opA = globalHandles.get(a);
        LogOp opB = globalHandles.get(b);
        LogOp opC = globalHandles.get(c);
        LogOp rlogop = LogOp.ifThenElse(opA, opB, opC);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "implies")
    public static ObjectHandle implies(IsolateThread thread, ObjectHandle a, ObjectHandle b) {
        LogOp opA = globalHandles.get(a);
        LogOp opB = globalHandles.get(b);
        LogOp rlogop = LogOp.implies(opA, opB);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "reified")
    public static ObjectHandle reified(IsolateThread thread, ObjectHandle b, ObjectHandle tree) {
        BoolVar boolv = globalHandles.get(b);
        LogOp ltree = globalHandles.get(tree);
        LogOp rlogop = LogOp.reified(boolv, ltree);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "or")
    public static ObjectHandle or(IsolateThread thread, ObjectHandle op) {
        LogOp[] ops = globalHandles.get(op);
        LogOp rlogop = LogOp.or(ops);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "nand")
    public static ObjectHandle nand(IsolateThread thread, ObjectHandle op) {
        LogOp[] ops = globalHandles.get(op);
        LogOp rlogop = LogOp.nand(ops);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "nor")
    public static ObjectHandle nor(IsolateThread thread, ObjectHandle op) {
        LogOp[] ops = globalHandles.get(op);
        LogOp rlogop = LogOp.nor(ops);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "xor")
    public static ObjectHandle xor(IsolateThread thread, ObjectHandle a, ObjectHandle b) {
        LogOp opA = globalHandles.get(a);
        LogOp opB = globalHandles.get(b);
        LogOp rlogop = LogOp.xor(opA, opB);
        ObjectHandle res = globalHandles.create(rlogop);
        return res;
    }
}
