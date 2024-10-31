package net.granny.journeysbeyond.manager;

import net.minecraftforge.eventbus.api.IEventBus;

public class JBModManager {

    public static void registerAll(IEventBus modbus, IEventBus forgeBus) {
        JBRegistryManager.registerRegistries(modbus);
    }
}
