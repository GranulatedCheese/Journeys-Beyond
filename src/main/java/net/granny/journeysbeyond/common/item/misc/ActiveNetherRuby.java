package net.granny.journeysbeyond.common.item.misc;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class ActiveNetherRuby extends Item {

    public ActiveNetherRuby(Item.Properties pProperties) {
        super(pProperties);
    }

    public ItemStack getDefaultInstance() { return PotionUtils.setPotion(super.getDefaultInstance(), Potions.STRONG_REGENERATION); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!pLevel.isClientSide) {
            if(!itemstack.getTag().getBoolean("ruby_enabled")) {
                itemstack.getOrCreateTag().putBoolean("ruby_enabled", !itemstack.getTag().getBoolean("ruby_enabled"));
            } else {
                itemstack.removeTagKey("ruby_enabled");
                return InteractionResultHolder.fail(itemstack);
            }
        }
        pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0.3F);
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlot, boolean pIsSelected) {
        if(pStack.getOrCreateTag().getBoolean("ruby_enabled") && pEntity instanceof ServerPlayer) {
            // Duration is per tick, i.e. 20 ticks = 1 sec // Amplifier starts from 0, each inc is 2 hearts
                ((ServerPlayer) pEntity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 4));
        }
    }
}
