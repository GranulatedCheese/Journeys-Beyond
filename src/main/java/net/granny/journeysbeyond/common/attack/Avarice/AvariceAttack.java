package net.granny.journeysbeyond.common.attack.Avarice;

import net.granny.journeysbeyond.common.attack.WeaponAttackInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class AvariceAttack extends WeaponAttackInstance {
    public AvariceAttack(Player player, ItemStack stack, int heldDuration) {
        super(player, stack, heldDuration);
    }
}
