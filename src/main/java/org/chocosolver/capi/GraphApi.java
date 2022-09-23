package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.util.objects.graphs.DirectedGraph;
import org.chocosolver.util.objects.graphs.IGraph;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import static org.chocosolver.util.objects.setDataStructures.SetType.*;

/**
 * C entrypoint API to create and manipulate Graphs.
 * @author Dimitri Justeau-Allaire
 */
public class GraphApi {

    private static final String API_PREFIX = "GraphApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    public static SetType getSetType(CCharPointer setType) {
        String jSetType = CTypeConversion.toJavaString(setType);
        switch (jSetType) {
            case "BIPARTITE_SET":
                return BIPARTITESET;
            case "SMALL_BIPARTITE_SET":
                return SMALLBIPARTITESET;
            case "RANGE_SET":
                return RANGESET;
            case "LINKED_LIST":
                return LINKED_LIST;
            default:
                return BITSET;

        }
    }

    public static CCharPointer getSetTypeCChar(SetType setType) {
        switch (setType) {
            case BIPARTITESET:
                return CTypeConversion.toCString("BIPARTITE_SET").get();
            case SMALLBIPARTITESET:
                return CTypeConversion.toCString("SMALL_BIPARTITE_SET").get();
            case RANGESET:
                return CTypeConversion.toCString("RANGE_SET").get();
            case LINKED_LIST:
                return CTypeConversion.toCString("LINKED_LIST").get();
            default:
                return CTypeConversion.toCString("BITSET").get();

        }
    }

    // Undirected graphs

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_graph")
    public static ObjectHandle createGraph(IsolateThread thread, ObjectHandle modelHandle, int n,
                                           CCharPointer nodeSetType, CCharPointer edgeSetType, boolean allNodes) {
        Model model = globalHandles.get(modelHandle);
        SetType nodeType = getSetType(nodeSetType);
        SetType edgeType = getSetType(edgeSetType);
        UndirectedGraph g = new UndirectedGraph(model, n, nodeType, edgeType, allNodes);
        ObjectHandle res = globalHandles.create(g);
        return res;
    }

    // Directed graphs

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_digraph")
    public static ObjectHandle createDigraph(IsolateThread thread, ObjectHandle modelHandle, int n,
                                             CCharPointer nodeSetType, CCharPointer edgeSetType, boolean allNodes) {
        Model model = globalHandles.get(modelHandle);
        SetType nodeType = getSetType(nodeSetType);
        SetType edgeType = getSetType(edgeSetType);
        DirectedGraph g = new DirectedGraph(model, n, nodeType, edgeType, allNodes);
        ObjectHandle res = globalHandles.create(g);
        return res;
    }

    // Generic graph methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_nodes")
    public static ObjectHandle getNodes(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        int[] nodes = g.getNodes().toArray();
        ObjectHandle res = globalHandles.create(nodes);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_node")
    public static boolean addNode(IsolateThread thread, ObjectHandle graphHandle, int x) {
        IGraph g = globalHandles.get(graphHandle);
        return g.addNode(x);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "remove_node")
    public static boolean removeNode(IsolateThread thread, ObjectHandle graphHandle, int x) {
        IGraph g = globalHandles.get(graphHandle);
        return g.removeNode(x);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_edge")
    public static boolean addEdge(IsolateThread thread, ObjectHandle graphHandle, int x, int y) {
        IGraph g = globalHandles.get(graphHandle);
        return g.addEdge(x, y);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "remove_edge")
    public static boolean removeEdge(IsolateThread thread, ObjectHandle graphHandle, int x, int y) {
        IGraph g = globalHandles.get(graphHandle);
        return g.removeEdge(x, y);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_nb_max_nodes")
    public static int getNbMaxNodes(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        return g.getNbMaxNodes();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_node_set_type")
    public static CCharPointer getNodeSetType(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        return getSetTypeCChar(g.getNodeSetType());
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_edge_set_type")
    public static CCharPointer getEdgeSetType(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        return getSetTypeCChar(g.getEdgeSetType());
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "contains_node")
    public static boolean containsNode(IsolateThread thread, ObjectHandle graphHandle, int x) {
        IGraph g = globalHandles.get(graphHandle);
        return g.containsNode(x);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "contains_edge")
    public static boolean containsEdge(IsolateThread thread, ObjectHandle graphHandle, int x, int y) {
        IGraph g = globalHandles.get(graphHandle);
        return g.containsEdge(x, y);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "is_directed")
    public static boolean isDirected(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        return g.isDirected();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_successors_of")
    public static ObjectHandle getSuccessorsOf(IsolateThread thread, ObjectHandle graphHandle, int x) {
        IGraph g = globalHandles.get(graphHandle);
        int[] successors = g.getSuccessorsOf(x).toArray();
        ObjectHandle res = globalHandles.create(successors);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_predecessors_of")
    public static ObjectHandle getPredecessorsOf(IsolateThread thread, ObjectHandle graphHandle, int x) {
        IGraph g = globalHandles.get(graphHandle);
        int[] predecessors = g.getPredecessorsOf(x).toArray();
        ObjectHandle res = globalHandles.create(predecessors);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "graphviz_export")
    public static CCharPointer graphviz_export(IsolateThread thread, ObjectHandle graphHandle) {
        IGraph g = globalHandles.get(graphHandle);
        CCharPointer graphviz = CTypeConversion.toCString(g.graphVizExport()).get();
        return graphviz;
    }
}
