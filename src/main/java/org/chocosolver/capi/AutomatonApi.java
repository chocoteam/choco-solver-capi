package org.chocosolver.capi;

import org.chocosolver.solver.constraints.nary.automata.FA.CostAutomaton;
import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.constraints.nary.automata.FA.IAutomaton;
import org.chocosolver.solver.constraints.nary.automata.FA.utils.Counter;
import org.chocosolver.solver.constraints.nary.automata.FA.utils.CounterState;
import org.chocosolver.solver.constraints.nary.automata.FA.utils.ICounter;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import java.util.List;

import static org.chocosolver.solver.constraints.nary.automata.FA.CostAutomaton.makeSingleResource;

/**
 * C entrypoint API to manipulate Java arrays.
 * @author Dimitri Justeau-Allaire.
 */
public class AutomatonApi {

    private static final String API_PREFIX = "AutomatonApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructors

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_fa")
    public static ObjectHandle createFA(IsolateThread thread) {
        FiniteAutomaton fa = new FiniteAutomaton();
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_fa_regexp_min_max")
    public static ObjectHandle createFARegexpMinMax(IsolateThread thread, CCharPointer regexp, int min, int max) {
        String jRegexp = CTypeConversion.toJavaString(regexp);
        FiniteAutomaton fa = new FiniteAutomaton(jRegexp, min, max);
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_fa_regexp")
    public static ObjectHandle createFARegexp(IsolateThread thread, CCharPointer regexp) {
        String jRegexp = CTypeConversion.toJavaString(regexp);
        FiniteAutomaton fa = new FiniteAutomaton(jRegexp);
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_cost_fa")
    public static ObjectHandle createCostFA(IsolateThread thread) {
        CostAutomaton fa = new CostAutomaton();
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_cost_fa_from_automaton")
    public static ObjectHandle createCostFAFromAutomation(IsolateThread thread, ObjectHandle automationHandle) {
        IAutomaton automaton = globalHandles.get(automationHandle);
        CostAutomaton fa = new CostAutomaton(automaton);
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_cost_fa_from_automaton_counters")
    public static ObjectHandle createCostFAFromAutomation(IsolateThread thread, ObjectHandle automationHandle,
                                                          ObjectHandle counterListHandle) {
        IAutomaton automaton = globalHandles.get(automationHandle);
        List<ICounter> counters = globalHandles.get(counterListHandle);
        CostAutomaton fa = new CostAutomaton(automaton, counters);
        ObjectHandle res = globalHandles.create(fa);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_nb_states")
    public static int getNbStates(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.getNbStates();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_nb_symbols")
    public static int getNbSymbols(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.getNbSymbols();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_state")
    public static int addState(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.addState();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "remove_symbol")
    public static void removeSymbol(IsolateThread thread, ObjectHandle faHandle, int symbol) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.removeSymbolFromAutomaton(symbol);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_transition")
    public static void addTransition(IsolateThread thread, ObjectHandle faHandle, int source, int destination,
                                     ObjectHandle symbolsHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        int[] symbols = globalHandles.get(symbolsHandle);
        fa.addTransition(source, destination, symbols);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "delete_transition")
    public static void deleteTransition(IsolateThread thread, ObjectHandle faHandle, int source, int destination,
                                        int symbol) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.deleteTransition(source, destination, symbol);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "delta")
    public static int delta(IsolateThread thread, ObjectHandle faHandle, int source, int symbol) throws IAutomaton.NonDeterministicOperationException {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.delta(source, symbol);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_to_alphabet")
    public static void addToAlphabet(IsolateThread thread, ObjectHandle faHandle, int a) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.addToAlphabet(a);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "remove_from_alphabet")
    public static void removeFromAlphabet(IsolateThread thread, ObjectHandle faHandle, int a) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.removeFromAlphabet(a);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_initial_state")
    public static int getInitialState(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.getInitialState();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "is_final")
    public static boolean isFinal(IsolateThread thread, ObjectHandle faHandle, int state) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        return fa.isFinal(state);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_initial_state")
    public static void setInitialState(IsolateThread thread, ObjectHandle faHandle, int state) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.setInitialState(state);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_final")
    public static void setFinal(IsolateThread thread, ObjectHandle faHandle, ObjectHandle statesHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        int[] states = globalHandles.get(statesHandle);
        fa.setFinal(states);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set_non_final")
    public static void setNonFinal(IsolateThread thread, ObjectHandle faHandle, ObjectHandle statesHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        int[] states = globalHandles.get(statesHandle);
        fa.setNonFInal(states);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "cost_fa_add_counter")
    public static void costFaAddCounter(IsolateThread thread, ObjectHandle costFaHandle,
                                        ObjectHandle counterHandle) {
        CostAutomaton costFa = globalHandles.get(costFaHandle);
        ICounter counter = globalHandles.get(counterHandle);
        costFa.addCounter(counter);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "union")
    public static ObjectHandle union(IsolateThread thread, ObjectHandle faHandle, ObjectHandle otherFaHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        FiniteAutomaton other = globalHandles.get(otherFaHandle);
        FiniteAutomaton union = fa.union(other);
        ObjectHandle res = globalHandles.create(union);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "minimize")
    public static void minimize(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        fa.minimize();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "complement")
    public static ObjectHandle complement(IsolateThread thread, ObjectHandle faHandle) {
        FiniteAutomaton fa = globalHandles.get(faHandle);
        FiniteAutomaton compl = fa.complement();
        ObjectHandle res = globalHandles.create(compl);
        return res;
    }

    // Counters

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_counter_min_max")
    public static ObjectHandle createCounterMinMax(IsolateThread thread, ObjectHandle layerValueHandle, int min, int max) {
        int[][] layerValue = globalHandles.get(layerValueHandle);
        Counter counter = new Counter(layerValue, min, max);
        ObjectHandle res = globalHandles.create(counter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_counter")
    public static ObjectHandle createCounter(IsolateThread thread, ObjectHandle layerValueStateHandle) {
        int[][][] layerValueState = globalHandles.get(layerValueStateHandle);
        Counter counter = new Counter(layerValueState);
        ObjectHandle res = globalHandles.create(counter);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_counter_state")
    public static ObjectHandle createCounterState(IsolateThread thread, ObjectHandle layerValueStateHandle,
                                                  int min, int max) {
        int[][][] layerValueState = globalHandles.get(layerValueStateHandle);
        CounterState counter = new CounterState(layerValueState, min, max);
        ObjectHandle res = globalHandles.create(counter);
        return res;
    }

    // Make resources

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "make_single_resource_iii")
    public static ObjectHandle makeSingleResourceIii(IsolateThread thread, ObjectHandle automatonHandle,
                                                     ObjectHandle costsHandle, int inf, int sup) {
        int[][][] costs = globalHandles.get(costsHandle);
        IAutomaton auto = globalHandles.get(automatonHandle);
        CostAutomaton costAuto = (CostAutomaton) makeSingleResource(auto, costs, inf, sup);
        ObjectHandle res = globalHandles.create(costAuto);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "make_single_resource_ii")
    public static ObjectHandle makeSingleResourceIi(IsolateThread thread, ObjectHandle automatonHandle,
                                                    ObjectHandle costsHandle, int inf, int sup) {
        int[][] costs = globalHandles.get(costsHandle);
        IAutomaton auto = globalHandles.get(automatonHandle);
        CostAutomaton costAuto = (CostAutomaton) makeSingleResource(auto, costs, inf, sup);
        ObjectHandle res = globalHandles.create(costAuto);
        return res;
    }

}
