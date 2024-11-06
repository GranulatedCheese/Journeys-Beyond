package net.granny.journeysbeyond.manager;

import net.granny.journeysbeyond.common.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;

public final class JBRegistryManager {

    protected static void registerRegistries(IEventBus modBus) {
        JBItems.ITEMS.register(modBus);
        JBBlocks.BLOCKS.register(modBus);
        JBCreativeTabs.CREATIVE_MODE_TABS.register(modBus);
    }
}
