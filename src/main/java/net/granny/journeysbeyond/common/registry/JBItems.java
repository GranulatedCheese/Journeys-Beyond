package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.granny.journeysbeyond.common.item.base.SkilledSwordItem;
import net.granny.journeysbeyond.common.item.misc.ActiveNetherRuby;
import net.granny.journeysbeyond.common.item.weapons.AvariceSwordItem;
import net.granny.journeysbeyond.common.item.weapons.PraxiSwordItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JourneysBeyond.MOD_ID);

    public static final RegistryObject<Item> ANCIENT_COIN = ITEMS.register("ancient_coin", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ANCIENT_PRISM = ITEMS.register("ancient_prism", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHARGED_ANCIENT_PRISM = ITEMS.register("charged_ancient_prism", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ROUGH_RUBY = ITEMS.register("rough_ruby", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> DORMANT_RUBY = ITEMS.register("dormant_ruby", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHARGED_BLAZE_ROD = ITEMS.register("charged_blaze_rod", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> PRAXI_SHARD = ITEMS.register("praxi_shard", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CONJURERS_STONE = ITEMS.register("conjurers_stone", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> EMPTY_SOUL_CATALYST = ITEMS.register("empty_soul_catalyst", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ACTIVE_RUBY = ITEMS.register("active_ruby", ActiveNetherRuby::new);

    // --- WEAPONS ---//
    public static final RegistryObject<Item> SOULLESS_GREED_SWORD = ITEMS.register("soulless_greed_sword",
            () -> new SwordItem(JBItemTiers.SOULLESS, 7, 1.9F - 4, (new Item.Properties()).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AVARICE_SWORD = ITEMS.register("avarice_sword",
            () -> new AvariceSwordItem(JBItemTiers.FULL_POWER, 12, 1.9F - 4, (new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON))));
    public static final RegistryObject<Item> PRAXI_SPEAR = ITEMS.register("praxi_spear",
            () -> new PraxiSwordItem(JBItemTiers.FULL_POWER, 12, 1.3F - 4, (new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON))));
}
