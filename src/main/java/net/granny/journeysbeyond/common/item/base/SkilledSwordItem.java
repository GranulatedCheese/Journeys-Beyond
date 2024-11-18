package net.granny.journeysbeyond.common.item.base;

import net.granny.journeysbeyond.common.registry.JBItemTiers;
import net.granny.journeysbeyond.common.util.item.JBWeaponUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public abstract class SkilledSwordItem extends SwordItem implements JBWeaponUtil {
    Tier itemTier;
    public SkilledSwordItem(Tier pTier, int pAttackDamageMod, float pAttackSpeedMod, Properties pProperties) {
        super(pTier, pAttackDamageMod, pAttackSpeedMod, pProperties.fireResistant());
        this.itemTier = pTier;
    }

    public int getTierMultiplier () {
        if (itemTier.equals(JBItemTiers.HALF_POWER)) {
            return 2;
        } else if (itemTier.equals(JBItemTiers.FULL_POWER)) {
            return 3;
        } else {
            return 1;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        // Sprinting < !onGround
        if (isShifting(pPlayer)) {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0.7F);
            return InteractionResultHolder.success(itemstack);
        } else if (!onGround(pPlayer)) {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1.0F);
            return InteractionResultHolder.success(itemstack);
        } else if (isSprinting(pPlayer)) {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1.2F);
            return InteractionResultHolder.success(itemstack);
        } else {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0.3F);
            return InteractionResultHolder.success(itemstack);
        }
    }
}
