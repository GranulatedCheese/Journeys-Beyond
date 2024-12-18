package net.granny.journeysbeyond.common.item.weapons;

import net.granny.journeysbeyond.common.item.base.SkilledSwordItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class AvariceSwordItem extends SkilledSwordItem {
    public AvariceSwordItem(Tier pTier, int pAttackDamageMod, float pAttackSpeedMod, Item.Properties pProperties) {
        super(pTier, pAttackDamageMod, pAttackSpeedMod, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        // Sprinting < !onGround
        if (isShifting(pPlayer)) {
            pullAndTakeEntity(pPlayer, pLevel);
            pPlayer.getCooldowns().addCooldown(itemstack.getItem(), 60);

            return InteractionResultHolder.success(itemstack);
        } else if (!onGround(pPlayer)) {
            swordSmash(pPlayer, pLevel);

            return InteractionResultHolder.success(itemstack);
        } else if (isSprinting(pPlayer)) {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1.2F);

            return InteractionResultHolder.success(itemstack);
        } else {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 0.3F);

            return InteractionResultHolder.success(itemstack);
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pEntity instanceof ServerPlayer && pIsSelected) {
            ((ServerPlayer) pEntity).addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 0, true, false, false));
        }
    }


    /*
    Plans
        Have particles connecting holder to targets
        Pulls targets toward holder
        Takes Health from Target and temporaily gives to holder
            Ex. Zombie begins with 20 Max HP and Holder begins with 20 Max HP,
            after use of avarice ability
            Zombie will have 16 Max HP and Player will Have 24 Max HP
     */
    public void pullAndTakeEntity(Player holder, Level level) {
        // Block Distance = Range + 2.0
        double range = 5.0;
        float damageAmplifier = getPlayerLevel(holder) > 4 ? (float) (Math.sqrt(getPlayerLevel(holder)) + 2) : 4;
        List<Entity> entities = iterateEntities(level, createAABB(holder.blockPosition().offset((int) (calculateXLook(holder) * 3), 1, (int) (calculateZLook(holder) * 3)), range));

        for (Entity entityBatch : entities) {
            if (entityBatch instanceof LivingEntity target) {
                if (target != holder && target.isAlive() && !holder.isAlliedTo(target)) {
                    target.hurt(target.damageSources().playerAttack(holder), damageAmplifier);

                    if (!holder.hasLineOfSight(target)) changeEntityDelta(target, holder, 1);
                    else changeEntityDelta(target, calculateXLook(holder), calculateZLook(holder), 1);

                    holder.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, (int) damageAmplifier * 20, 1), holder);
                    holder.playSound(SoundEvents.AMETHYST_BLOCK_PLACE);
                }
            }
        }
    }

    public void swordSmash(Player holder, Level level) {
        double range = 3.0;
        float damageAmplifier = getEnchantmentLevel(holder.getItemInHand(InteractionHand.MAIN_HAND), Enchantments.SHARPNESS);
        List<Entity> entities = iterateEntities(level, createAABB(holder.blockPosition().offset((int) (calculateXLook(holder) * 3), 1, (int) (calculateZLook(holder) * 3)), range));

        holder.setDeltaMovement(calculateXLook(holder) * 0.75, 1.25, calculateZLook(holder) * 0.75);


        for (Entity entityBatch : entities) {
            if (entityBatch instanceof  LivingEntity target) {
                if (target != holder && target.isAlive() && !holder.isAlliedTo(target) && holder.onGround()) {
                    target.hurt(target.damageSources().playerAttack(holder), 6 * damageAmplifier);

                    holder.playSound(SoundEvents.ANVIL_LAND);
                }
            }
        }
    }


}
