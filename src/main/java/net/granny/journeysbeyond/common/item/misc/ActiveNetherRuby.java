package net.granny.journeysbeyond.common.item.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ActiveNetherRuby extends Item {

    public ActiveNetherRuby() {
        super(new Properties().stacksTo(1).rarity(Rarity.COMMON).durability(48));
    }

    public String rubyKey = "ruby_enabled";

    public ItemStack getDefaultInstance() { return PotionUtils.setPotion(super.getDefaultInstance(), Potions.STRONG_REGENERATION); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!pLevel.isClientSide) {
            if(!itemstack.getTag().getBoolean(rubyKey) && (!itemstack.hurt(0, pLevel.getRandom(), (ServerPlayer) pPlayer) || !itemstack.isDamaged())) {
                itemstack.getOrCreateTag().putBoolean(rubyKey, !itemstack.getTag().getBoolean(rubyKey));
                pPlayer.getCooldowns().addCooldown(this, 20);
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.journeysbeyond.active_ruby.tooltip").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.getOrCreateTag().getBoolean(rubyKey);
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
        int randomVal = 1 + (int)(Math.random() * ((6 - 1) + 1)); // [1, 6]
        if(pStack != null && pPlayer.totalExperience > randomVal) {
            pPlayer.giveExperiencePoints(randomVal * -1);
            pStack.setDamageValue((pStack.getDamageValue() - randomVal));
        }
    }
}