package org.chocosolver.capi;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.ParallelPortfolio;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C API entrypoint to parallel portfolio for parallel search
 */
public class ParallelPortfolioApi {

    private static final String API_PREFIX = "ParallelPortfolio_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    // Constructor

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "create_parallel_porfolio")
    public static ObjectHandle createParallelPortfolio(IsolateThread thread, boolean searchAutoConf) {
        ParallelPortfolio pf = new ParallelPortfolio(searchAutoConf);
        ObjectHandle res = globalHandles.create(pf);
        return res;
    }

    // Methods

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "steal_nogoods_on_restarts")
    public static void stealNoGoodsOnRestarts(IsolateThread thread, ObjectHandle pfHandle) {
        ParallelPortfolio pf = globalHandles.get(pfHandle);
        pf.stealNogoodsOnRestarts();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "add_model")
    public static void addModel(IsolateThread thread, ObjectHandle pfHandle, ObjectHandle modelHandle, boolean reliable) {
        ParallelPortfolio pf = globalHandles.get(pfHandle);
        Model model = globalHandles.get(modelHandle);
        pf.addModel(model, reliable);
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "solve")
    public static boolean solve(IsolateThread thread, ObjectHandle pfHandle) {
        ParallelPortfolio pf = globalHandles.get(pfHandle);
        return pf.solve();
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "get_best_model")
    public static ObjectHandle getBestModel(IsolateThread thread, ObjectHandle pfHandle) {
        ParallelPortfolio pf = globalHandles.get(pfHandle);
        Model bestModel = pf.getBestModel();
        ObjectHandle res = globalHandles.create(bestModel);
        return res;
    }
}
