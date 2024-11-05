package net.granny.journeysbeyond.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JBClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Journey's Beyond");

        // DEFINE CONFIGS

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
