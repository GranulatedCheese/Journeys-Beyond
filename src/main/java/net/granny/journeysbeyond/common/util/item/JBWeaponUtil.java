package net.granny.journeysbeyond.common.util.item;

import net.granny.journeysbeyond.common.registry.JBAttributes;
import net.granny.journeysbeyond.common.registry.JBDamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public interface JBWeaponUtil {
    default boolean isShifting(Player player) { return player.isShiftKeyDown(); }
    default boolean isSprinting(Player player) { return player.isSprinting(); }
    default boolean onGround(Player player) { return player.onGround(); }

    default float calculateAttributeDependentDamage(LivingEntity holder, ItemStack stack, float attackAttributeMultiplier) {
        float holderAttribute = (float) holder.getAttributeValue(Attributes.ATTACK_DAMAGE) + EnchantmentHelper.getDamageBonus(holder.getMainHandItem(), MobType.UNDEFINED);
        float holderAttackDamage = (holderAttribute - this.getDamageOfItem(holder.getMainHandItem()) + this.getDamageOfItem(stack) * attackAttributeMultiplier);

        return holderAttackDamage;
    }

    default void attributeDependentAttack(LivingEntity holder, LivingEntity target, ItemStack stack, float attackAttributeModifier) {
        this.initiateAbilityAttack(holder, target, this.calculateAttributeDependentDamage(holder, stack, attackAttributeModifier));
    }

    private float getDamageOfItem(ItemStack stack) {
        return (float) (
                stack.getAttributeModifiers(EquipmentSlot.MAINHAND).
                        get(Attributes.ATTACK_DAMAGE).
                        stream().
                        mapToDouble(AttributeModifier::getAmount).
                        sum() + EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED)
        );
    }

    default void initiateAbilityAttack(LivingEntity holder, LivingEntity target, float damage, DamageSource damageSource) {
        if (damage == 0) return;
        JBDamageSources source = new JBDamageSources(target.level().registryAccess());
        DamageSource basicDamage = source.basicPlayerATK(holder);
        DamageSource finalDamageSource = null;
        if(damageSource != null) {
            finalDamageSource = damageSource;
        }
        double finalDamage = damage * holder.getAttributeValue(JBAttributes.activated_damage.get()) / target.getAttributeValue(JBAttributes.activated_damage_reduction.get());
        attack(holder, target, (float) finalDamage, finalDamageSource);
    }

    default void initiateAbilityAttack(LivingEntity holder, LivingEntity target, float damage) {
        initiateAbilityAttack(holder, target, damage, null);
    }

    private void attack(LivingEntity holder, LivingEntity target, float damage, DamageSource damageSource) {
        target.hurt(damageSource, damage);
    }
}
