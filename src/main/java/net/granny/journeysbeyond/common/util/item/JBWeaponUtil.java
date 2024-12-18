package net.granny.journeysbeyond.common.util.item;

import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface JBWeaponUtil {
    default boolean isShifting(Player player) { return player.isShiftKeyDown(); }

    default boolean isSprinting(Player player) { return player.isSprinting(); }

    default boolean onGround(Player player) { return player.onGround(); }

    default double getEntityMaxHealth(LivingEntity livingEntity) { return livingEntity.getMaxHealth(); }

    default double getEntityCurrentHealth(LivingEntity livingEntity) { return livingEntity.getHealth(); }

    default double getPlayerLevel(Player player) { return player.experienceLevel; }


    default AABB createAABB(Vec3i pos, double range) {
        return createAABB(pos.getX(), pos.getY(), pos.getZ(), range);
    }

    default AABB createAABB(Vec3i pos, double xzRange, double yRange) {
        return createAABB(pos.getX(), pos.getY(), pos.getZ(),xzRange, yRange);
    }

    default AABB createAABB(double x, double y, double z, double range) {
        return createAABB(x, y, z, range, range);
    }

    default AABB createAABB(double x, double y, double z, double xzRange, double yRange) {
        return new AABB(x + xzRange, y + yRange, z + xzRange, x - xzRange, y - yRange, z - xzRange);
    }

    default double calculateXLook(LivingEntity player) {
        return player.getLookAngle().x();
    }

    default double calculateYLook(LivingEntity player, double yMult) {
        double lookY = player.getLookAngle().y();

        if (lookY > 0) return lookY * yMult;
        else return lookY * 0.5;
    }

    default double calculateYLook(LivingEntity player) {
        return player.getLookAngle().y();
    }

    default double calculateZLook(LivingEntity player) {
        return player.getLookAngle().z();
    }

    default List<Entity> iterateEntities(Level level, AABB aabb) {
        return level.getEntitiesOfClass(Entity.class, aabb);
    }

    default void changeEntityDelta(Entity entity, double eX, double eZ, double eY, double eScale) {
        Vec3 vec3 = entity.getDeltaMovement();
        Vec3 vec31 = (new Vec3(eX, eY, eZ)).normalize().scale(eScale);

        entity.setDeltaMovement(vec3.x / 2.0D - vec31.x, entity.onGround() ? Math.min(0.4D, vec3.y / 2.0D + eScale) : vec3.y / 2.0D - vec31.y, vec3.z / 2.0D - vec31.z);
    }

    default void changeEntityDelta(Entity entity, double eX, double eZ, double eScale) {
        changeEntityDelta(entity, eX, eZ, 0.0D, eScale);
    }

    default void changeEntityDelta(Entity target, Entity player, double eScale) {
        changeEntityDelta(target, player.getLookAngle().x * -1, player.getLookAngle().z * -1, eScale);
    }
}
