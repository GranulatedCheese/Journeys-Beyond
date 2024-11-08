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
            new ForgeTier(5, 2550, 9.0F, 5.0F, 22,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(JBItems.CHARGED_ANCIENT_PRISM.get())),
                    JourneysBeyond.prefix("soulless"), List.of(Tiers.NETHERITE), List.of()
    );
    public static final Tier HALF_POWER = TierSortingRegistry.registerTier(
            new ForgeTier(6, 2805, 9.0F, 6.0F, 25,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(JBItems.EMPTY_SOUL_CATALYST.get())),
                    JourneysBeyond.prefix("half_power"), List.of(Tiers.NETHERITE), List.of()
    );
    public static final Tier FULL_POWER = TierSortingRegistry.registerTier(
            new ForgeTier(6, 3187, 9.0F, 7.0F, 28,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(JBItems.ACTIVE_RUBY.get())),
            JourneysBeyond.prefix("full_power"), List.of(Tiers.NETHERITE), List.of()
    );
}
