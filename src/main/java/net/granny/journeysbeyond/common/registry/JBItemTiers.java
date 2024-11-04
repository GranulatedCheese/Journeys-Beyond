package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class JBItemTiers {
    public static final Tier SOULLESS = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2550, 9.0F, 5.0F, 25,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(JBItems.CHARGED_ANCIENT_PRISM.get())),
                    JourneysBeyond.prefix("soulless"), List.of(Tiers.NETHERITE), List.of()
    );
}