package net.granny.journeysbeyond.common.util.item;

import net.minecraft.core.Vec3i;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public interface JBWeaponUtil {
    default boolean isShifting(Player player) { return player.isShiftKeyDown(); }

    default boolean isSprinting(Player player) { return player.isSprinting(); }

    default boolean onGround(Player player) { return player.onGround(); }

    default float calculateAttributeDependentDamage(LivingEntity holder, ItemStack stack, float attackAttributeMultiplier) {
        float holderAttribute = (float) holder.getAttributeValue(Attributes.ATTACK_DAMAGE) + EnchantmentHelper.getDamageBonus(holder.getMainHandItem(), MobType.UNDEFINED);
        float holderAttackDamage = (holderAttribute - this.getDamageofItem(holder.getMainHandItem()) + this.getDamageofItem(stack)) * attackAttributeMultiplier;

        return holderAttackDamage;
    }

    default void attributeDependentAttack(LivingEntity holder, LivingEntity target, ItemStack stack, float attackTierMultiplier) {
        this.initiateAbilityAttack(holder, target, this.calculateAttributeDependentDamage(holder, stack, attackTierMultiplier));
    }

    private float getDamageofItem(ItemStack stack) {
        return (float) (stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE).stream().mapToDouble(AttributeModifier::getAmount).sum() + EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED));
    }

    default void initiateAbilityAttack(LivingEntity holder, LivingEntity target, float damage, DamageSource damageSource) {
        if (damage == 0) return;

        DamageSource finalDamageSource = null;
        if (damageSource != null) {
            finalDamageSource = damageSource;
        }

        double finalDamage = damage * holder.getAttributeValue(Attributes.ATTACK_DAMAGE);
        attack(holder, target, (float) finalDamage, finalDamageSource);
    }

    default void initiateAbilityAttack(LivingEntity holder, LivingEntity target, float damage) {
        initiateAbilityAttack(holder, target, damage, null);
    }

    private void attack(LivingEntity holder, LivingEntity target, float damage, DamageSource damageSource) {
        target.hurt(damageSource, damage);
    }

    default AABB createAABB(Vec3i pos, double range) {
        return createAABB(pos.getX(), pos.getY(), pos.getZ(), range);
    }
    default AABB createAABB(Vec3i pos, double xzRange, double yRange) {
        return createAABB(pos.getX(), pos.getY(), pos.getZ(), xzRange, yRange);
    }

    default AABB createAABB(double x, double y, double z, double range) {
        return createAABB(x, y, z, range);
    }

    default AABB createAABB(double x, double y, double z, double xzRange, double yRange) {
        return new AABB(x + xzRange, y +yRange, z + xzRange, x - xzRange, y - yRange, x - xzRange);
    }

    default List<Entity> iterateEntities(Level level, AABB aabb) {
        return level.getEntitiesOfClass(Entity.class, aabb);
    }

    default double calculateXLook(LivingEntity player) {
        return player.getLookAngle().x();
    }

    default double calculateYLook(LivingEntity player, double yMult) {
        double lookY = player.getLookAngle().y();

        if(lookY > 0) return lookY * yMult;
        else return lookY * 0.5;
    }

    default double calculateYLook(LivingEntity player) {
        return player.getLookAngle().y();
    }

    default double calculateZLook(LivingEntity player) {
        return player.getLookAngle().z();
    }

    default void useAndDamageItem (ItemStack stack, Level level, LivingEntity targetOwnerEnitity, int damageAmount) {
        if (!level.isClientSide) {
            stack.hurtAndBreak(damageAmount, targetOwnerEnitity, (ownerEntity) -> {
                if (targetOwnerEnitity.getMainHandItem() == stack) {
                    ownerEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                } else if (targetOwnerEnitity.getOffhandItem() == stack) {
                    ownerEntity.broadcastBreakEvent(InteractionHand.OFF_HAND);
                }
            });
        }
        if (targetOwnerEnitity instanceof Player ownerPlayer) {
            ownerPlayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
    }
}
