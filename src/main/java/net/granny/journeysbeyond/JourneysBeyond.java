package net.granny.journeysbeyond;

import com.mojang.logging.LogUtils;
import net.granny.journeysbeyond.common.registry.JBItems;
import net.granny.journeysbeyond.manager.JBModManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(JourneysBeyond.MOD_ID)
public class JourneysBeyond {
    public static final String MOD_ID = "journeysbeyond";
    private static final Logger LOGGER = LogUtils.getLogger();

    public JourneysBeyond() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        if (modEventBus != null && forgeEventBus != null) JBModManager.registerAll(modEventBus, forgeEventBus);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MOD_ID, path.toLowerCase(Locale.ROOT));
    }
}
