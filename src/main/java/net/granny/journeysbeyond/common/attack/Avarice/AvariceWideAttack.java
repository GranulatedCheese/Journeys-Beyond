package net.granny.journeysbeyond.common.attack.Avarice;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AvariceWideAttack extends AvariceAttack{
    public AvariceWideAttack(Player player, ItemStack stack, int heldDuration) {
        super(player, stack, heldDuration);
    }

    @Override
    public int getCooldown() {
        return 15;
    }

    @Override
    public int getAttackStopTime() {
        return 15;
    }

    @Override
    public boolean getCondition() {
        return !isSprinting(player) && !isShifting(player) && onGround(player);
    }

    @Override
    public void startUsing() {
        super.startUsing();

        useAndDamageItem(stack, level, player, 1);
    }

    @Override
    public void tickAttack() {
        double range = 6.0;
        List<Entity> entities = iterateEntities(level, createAABB(player.blockPosition().offset((int) (calculateXLook(player) * 3), 1, (int) (calculateZLook(player) * 3)), range));

        for (Entity entityBatch : entities) {
            if(entityBatch instanceof LivingEntity target) {
                if(target != player && target.isAlive() && !player.isAlliedTo(target)) {
                    this.attributeDependentAttack(player, target, stack, 1.3F);
                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    public void stopUsing() {

    }
}
