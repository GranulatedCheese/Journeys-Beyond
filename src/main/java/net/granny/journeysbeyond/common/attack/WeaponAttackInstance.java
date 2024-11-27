package net.granny.journeysbeyond.common.attack;

import net.granny.journeysbeyond.common.util.item.JBWeaponUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class WeaponAttackInstance implements JBWeaponUtil {
    public final Player player;
    public final Level level;
    public final ItemStack stack;
    public final int heldDuration;

    public WeaponAttackInstance(Player player, ItemStack stack, int heldDuration) {
        this.player = player;
        this.level = player.level();
        this.stack = stack;
        this.heldDuration = heldDuration;
    }

    public abstract int getCooldown();

    public abstract int getAttackStopTime();

    public abstract boolean getCondition();

    public void startUsing() {
    }

    public abstract void tickAttack();

    public abstract void stopUsing();

    public void baseStart() {
        startUsing();
    }

    public void baseStop() {
        stopUsing();
        player.stopUsingItem();
    }

    public Level getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getHeldDuration() {
        return heldDuration;
    }
}
