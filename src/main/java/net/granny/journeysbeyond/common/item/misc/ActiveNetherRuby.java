package net.granny.journeysbeyond.common.item.misc;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class ActiveNetherRuby extends Item {

    public ActiveNetherRuby() {
        super(new Properties().stacksTo(1).rarity(Rarity.COMMON).durability(96));
    }

    public String rubyKey = "ruby_enabled";

    public ItemStack getDefaultInstance() { return PotionUtils.setPotion(super.getDefaultInstance(), Potions.STRONG_REGENERATION); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!pLevel.isClientSide) {

            if(!itemstack.getTag().getBoolean(rubyKey) && (!itemstack.hurt(0, pLevel.getRandom(), (ServerPlayer) pPlayer) || !itemstack.isDamaged())) {
                itemstack.getOrCreateTag().putBoolean(rubyKey, !itemstack.getTag().getBoolean(rubyKey));
            } else {
                itemstack.removeTagKey(rubyKey);
                return InteractionResultHolder.fail(itemstack);
            }
        }
        pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0.3F);
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlot, boolean pIsSelected) {
        if(pStack.getOrCreateTag().getBoolean(rubyKey) && pEntity instanceof ServerPlayer) {
            // Duration is per tick, i.e. 20 ticks = 1 sec // Amplifier starts from 0, each inc is 2 hearts
            rubyProtection((ServerPlayer) pEntity, pStack, pLevel);
            if(pStack.hurt(0, pLevel.getRandom(), (ServerPlayer) pEntity)) {
                pStack.removeTagKey(rubyKey);
            }
        } else if(!pStack.getOrCreateTag().getBoolean(rubyKey) && pEntity instanceof ServerPlayer) {
                repairRuby((ServerPlayer) pEntity, pStack);
        }
    }

    public static void rubyProtection(ServerPlayer pPlayer, ItemStack pStack, Level pLevel){
        float playerMaxHealth = pPlayer.getMaxHealth();
        float playerCurrentHealth = pPlayer.getHealth();
        float playerHealthDifference = playerMaxHealth - playerCurrentHealth;

        // Item Durability goes negative
        if(playerCurrentHealth < playerMaxHealth && !pStack.hurt((int)(playerHealthDifference) / 2, pLevel.getRandom(), pPlayer)) {
            pPlayer.setHealth(playerMaxHealth);
            pStack.hurt((int)(playerHealthDifference) / 2, pLevel.getRandom(), pPlayer);
        }
    }

    public void repairRuby(ServerPlayer pPlayer, ItemStack pStack) {
        if(pStack != null && pPlayer.totalExperience > 3) {
            pPlayer.giveExperiencePoints(-3);
            pStack.setDamageValue((pStack.getDamageValue() - 10));
        }
    }
}