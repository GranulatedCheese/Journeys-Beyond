package net.granny.journeysbeyond.common.util.item;

import net.minecraft.world.entity.player.Player;

public interface JBWeaponUtil {
    default boolean isShifting(Player player) {
        return player.isShiftKeyDown();
    }

    default boolean isSprinting(Player player) {
        return player.isSprinting();
    }

    default boolean onGround(Player player) {
        return player.onGround();
    }
}
