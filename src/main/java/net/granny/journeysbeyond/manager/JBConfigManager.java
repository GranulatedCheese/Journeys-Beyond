package net.granny.journeysbeyond.manager;

import net.granny.journeysbeyond.common.config.JBClientConfig;
import net.granny.journeysbeyond.common.config.JBCommonConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public final class JBConfigManager {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final JBCommonConfig COMMON;

    static {
        final Pair<JBCommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(JBCommonConfig::new);

        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }

    public static void registerConfigs() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC, "journeysbeyond-common.toml");
    }
}
