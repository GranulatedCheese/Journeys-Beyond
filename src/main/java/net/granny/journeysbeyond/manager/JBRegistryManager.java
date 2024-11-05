package net.granny.journeysbeyond.manager;

import net.granny.journeysbeyond.common.config.JBClientConfig;
import net.granny.journeysbeyond.common.config.JBCommonClient;
import net.granny.journeysbeyond.common.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public final class JBRegistryManager {

    protected static void registerRegistries(IEventBus modBus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, JBClientConfig.SPEC, "journeysbeyond-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JBCommonClient.SPEC, "journeysbeyond-common.toml");
        JBItems.ITEMS.register(modBus);
        JBBlocks.BLOCKS.register(modBus);
        JBCreativeTabs.CREATIVE_MODE_TABS.register(modBus);

    }
}
