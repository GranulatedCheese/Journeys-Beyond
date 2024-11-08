package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;

public class JBCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JourneysBeyond.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JOURNEYSBEYOND = CREATIVE_MODE_TABS.register("journeysbeyond_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(JBItems.ANCIENT_COIN.get()))
                    .title(Component.translatable("creativetab.journeysbeyond_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        acceptItemRegistry(pOutput, JBItems.ITEMS.getEntries());
                        acceptBlockRegistry(pOutput, JBBlocks.BLOCKS.getEntries());
                    }).build()
    );

    public static void acceptItemRegistry(CreativeModeTab.Output output, Collection<RegistryObject<Item>> registry) {
        for(RegistryObject<Item> item : registry) {
            if(!getBlackList().contains(item)) {
                output.accept(item.get());
            }
        }
    }

    public static void acceptBlockRegistry(CreativeModeTab.Output output, Collection<RegistryObject<Block>> registry) {
        for(RegistryObject<Block> block : registry) {
            if(!getBlackList().contains(block)) {
                output.accept(block.get());
            }
        }
    }

    public static List<RegistryObject<? extends ItemLike>> getBlackList() {
        return List.of(
                JBItems.ANCIENT_COIN
        );
    }
}
