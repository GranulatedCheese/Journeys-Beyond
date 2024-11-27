package net.granny.journeysbeyond.common.item.weapons;

import com.google.common.collect.ImmutableList;
import net.granny.journeysbeyond.common.attack.Avarice.AvariceWideAttack;
import net.granny.journeysbeyond.common.attack.WeaponAttackInstance;
import net.granny.journeysbeyond.common.item.base.SkilledSwordItem;
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
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class AvariceSwordItem extends SkilledSwordItem {
    public AvariceSwordItem(Tier pTier, int pAttackDamageMod, float pAttackSpeedMod, Item.Properties pProperties) {
        super(pTier, pAttackDamageMod, pAttackSpeedMod, pProperties);
    }

    @Override
    public ImmutableList<WeaponAttackInstance> getPossibleAttacks(Player player, ItemStack stack, int useDuration) {
        return ImmutableList.of(
                new AvariceWideAttack(player, stack, useDuration)
        );
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pEntity instanceof ServerPlayer && pIsSelected) {
            ((ServerPlayer) pEntity).addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 0, true, false, false));
        }
    }
}
