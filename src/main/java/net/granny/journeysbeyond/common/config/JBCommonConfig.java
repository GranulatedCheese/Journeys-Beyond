package net.granny.journeysbeyond.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JBCommonConfig {
    public final ForgeConfigSpec.ConfigValue<Integer> ACTIVE_RUBY_DURABILITY;

    public JBCommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Config for Journey's Beyond");

        ACTIVE_RUBY_DURABILITY = builder.comment("How much damage an active ruby can protect from before breaking.")
                .define("durability", 128);

        builder.pop();
    }
}
