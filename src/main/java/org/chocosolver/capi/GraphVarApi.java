package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.DirectedGraphVar;
import org.chocosolver.solver.variables.GraphVar;
import org.chocosolver.solver.variables.UndirectedGraphVar;
import org.chocosolver.util.objects.graphs.DirectedGraph;
import org.chocosolver.util.objects.graphs.IGraph;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

/**
 * C entrypoint API to create and manipulate GraphVars.
 * @author Dimitri Justeau-Allaire
 */
public class GraphVarApi {

    private static final String API_PREFIX = "GraphVarApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructors

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_graphvar")
    public static ObjectHandle createGraphVar(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                              ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        UndirectedGraph lb = globalHandles.get(lbHandle);
        UndirectedGraph ub = globalHandles.get(ubHandle);
        UndirectedGraphVar var = model.graphVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_digraphvar")
    public static ObjectHandle createDigraphVar(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                                ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        DirectedGraph lb = globalHandles.get(lbHandle);
        DirectedGraph ub = globalHandles.get(ubHandle);
        DirectedGraphVar var = model.digraphVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_node_induced_graphar")
    public static ObjectHandle createNodeInducedGraphVar(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                                         ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        UndirectedGraph lb = globalHandles.get(lbHandle);
        UndirectedGraph ub = globalHandles.get(ubHandle);
        UndirectedGraphVar var = model.nodeInducedGraphVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_node_induced_digraphvar")
    public static ObjectHandle createNodeInducedDigraphVarNamed(IsolateThread thread, ObjectHandle modelHandle, CCharPointer name,
                                                                ObjectHandle lbHandle, ObjectHandle ubHandle) {
        Model model = globalHandles.get(modelHandle);
        DirectedGraph lb = globalHandles.get(lbHandle);
        DirectedGraph ub = globalHandles.get(ubHandle);
        DirectedGraphVar var = model.nodeInducedDigraphVar(CTypeConversion.toJavaString(name), lb, ub);
        ObjectHandle res = globalHandles.create(var);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_graphvar_lb")
    public static ObjectHandle getLB(IsolateThread thread, ObjectHandle graphVarHandle) {
        GraphVar g = globalHandles.get(graphVarHandle);
        IGraph lb = g.getLB();
        ObjectHandle res = globalHandles.create(lb);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_graphvar_ub")
    public static ObjectHandle getUB(IsolateThread thread, ObjectHandle graphVarHandle) {
        GraphVar g = globalHandles.get(graphVarHandle);
        IGraph ub = g.getLB();
        ObjectHandle res = globalHandles.create(ub);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_graphvar_value")
    public static ObjectHandle getValue(IsolateThread thread, ObjectHandle graphVarHandle) {
        GraphVar g = globalHandles.get(graphVarHandle);
        IGraph ub = g.getValue();
        ObjectHandle res = globalHandles.create(ub);
        return res;
    }
}
