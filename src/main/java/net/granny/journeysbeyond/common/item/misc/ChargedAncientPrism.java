package net.granny.journeysbeyond.common.item.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;

public class ChargedAncientPrism extends ItemEntity {
    public ChargedAncientPrism(EntityType<? extends ItemEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void EntityStruckByLightningEvent(Entity entity, LightningBolt lightningBolt) {

    }
}
