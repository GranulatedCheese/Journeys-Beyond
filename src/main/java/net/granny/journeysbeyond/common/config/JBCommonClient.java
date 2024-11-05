package net.granny.journeysbeyond.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JBCommonClient {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> ACTIVE_RUBY_DURABILITY;

    static {
        BUILDER.push("Config for Journey's Beyond");

        ACTIVE_RUBY_DURABILITY = BUILDER.comment("How much damage an active ruby can protect from before breaking. Corresponds with durability.\n Min = 0, Max = 192000")
                .defineInRange("durability", 192, 0, 192000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
