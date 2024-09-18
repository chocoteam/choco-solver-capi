package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.extension.hybrid.HybridTuples;
import org.chocosolver.solver.constraints.extension.hybrid.ISupportable;
import org.chocosolver.solver.variables.IntVar;

import static org.chocosolver.solver.constraints.extension.hybrid.HybridTuples.*;
import static org.chocosolver.solver.constraints.extension.hybrid.HybridTuples.col;

public class Test {
    public static void main(String[] args) {
        Model m = new Model();
        IntVar[] intVars = m.intVarArray(3, 0, 5);
        ISupportable[][] htuples = new ISupportable[][]{
                {eq(1), gt(2), le(col(0))},
                {eq(2), le(2), ne(col(1))}

        };
        HybridTuples ht = new HybridTuples();
        ht.add(htuples);
        m.table(intVars, ht).post();
        for (int i = 0; i < 7; i++) {
            m.getSolver().solve();
        }
    }
}
