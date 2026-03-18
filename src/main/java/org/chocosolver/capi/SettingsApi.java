package org.chocosolver.capi;

import org.chocosolver.solver.Settings;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;

/**
 * C entrypoint API to manipulate Settings instance.
 *
 * @author Charles Prud'homme.
 */

public class SettingsApi {

    private static final String API_PREFIX = "SettingsApi_";

    private static ObjectHandles globalHandles = ObjectHandles.getGlobal();

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "init")
    public static ObjectHandle init(IsolateThread thread) {
        Settings settings = Settings.init();
        ObjectHandle res = globalHandles.create(settings);
        return res;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setLCG")
    public static ObjectHandle setLCG(IsolateThread thread, ObjectHandle settingsHandle, boolean lcg) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setLCG(lcg);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setWarnUser")
    public static ObjectHandle setWarnUser(IsolateThread thread, ObjectHandle settingsHandle, boolean warnUser) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setWarnUser(warnUser);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setCheckDeclaredConstraints")
    public static ObjectHandle setCheckDeclaredConstraints(IsolateThread thread, ObjectHandle settingsHandle, boolean checkDeclaredConstraints) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setCheckDeclaredConstraints(checkDeclaredConstraints);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setCheckDeclaredViews")
    public static ObjectHandle setCheckDeclaredViews(IsolateThread thread, ObjectHandle settingsHandle, boolean checkDeclaredViews) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setCheckDeclaredViews(checkDeclaredViews);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setCheckDeclaredMonitors")
    public static ObjectHandle setCheckDeclaredMonitors(IsolateThread thread, ObjectHandle settingsHandle, boolean checkDeclaredMonitors) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setCheckDeclaredMonitors(checkDeclaredMonitors);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setMaxDomSizeForEnumerated")
    public static ObjectHandle setMaxDomSizeForEnumerated(IsolateThread thread, ObjectHandle settingsHandle, int maxDomSizeForEnumerated) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setMaxDomSizeForEnumerated(maxDomSizeForEnumerated);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setMinCardinalityForSumDecomposition")
    public static ObjectHandle setMinCardinalityForSumDecomposition(IsolateThread thread, ObjectHandle settingsHandle, int minCardinalityForSumDecomposition) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setMinCardinalityForSumDecomposition(minCardinalityForSumDecomposition);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setEnableTableSubstitution")
    public static ObjectHandle setEnableTableSubstitution(IsolateThread thread, ObjectHandle settingsHandle, boolean enableTableSubstitution) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setEnableTableSubstitution(enableTableSubstitution);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setMaxTupleSizeForSubstitution")
    public static ObjectHandle setMaxTupleSizeForSubstitution(IsolateThread thread, ObjectHandle settingsHandle, int maxTupleSizeForSubstitution) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setMaxTupleSizeForSubstitution(maxTupleSizeForSubstitution);
        return settingsHandle;
    }


    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setMaxSizeInMBToUseCompactTable")
    public static ObjectHandle setMaxSizeInMBToUseCompactTable(IsolateThread thread, ObjectHandle settingsHandle, int maxSizeInMBToUseCompactTable) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setMaxSizeInMBToUseCompactTable(maxSizeInMBToUseCompactTable);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setEnableSAT")
    public static ObjectHandle setEnableSAT(IsolateThread thread, ObjectHandle settingsHandle, boolean enableSAT) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setEnableSAT(enableSAT);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setSwapOnPassivate")
    public static ObjectHandle setSwapOnPassivate(IsolateThread thread, ObjectHandle settingsHandle, boolean swapOnPassivate) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setSwapOnPassivate(swapOnPassivate);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setPrintAllUndeclaredConstraints")
    public static ObjectHandle setPrintAllUndeclaredConstraints(IsolateThread thread, ObjectHandle settingsHandle, boolean printAllUndeclaredConstraints) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setPrintAllUndeclaredConstraints(printAllUndeclaredConstraints);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "setNbMaxLearntClauses")
    public static ObjectHandle setNbMaxLearntClauses(IsolateThread thread, ObjectHandle settingsHandle, int n) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.setNbMaxLearntClauses(n);
        return settingsHandle;
    }

    @CEntryPoint(name = Constants.METHOD_PREFIX + API_PREFIX + "set")
    public static ObjectHandle set(IsolateThread thread, ObjectHandle settingsHandle, String key , ObjectHandle value) {
        Settings settings = globalHandles.get(settingsHandle);
        settings.set(key, value);
        return settingsHandle;
    }
}
