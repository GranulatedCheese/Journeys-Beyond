package net.granny.journeysbeyond.common.item.base;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SkilledSwordItem extends SwordItem {
    public SkilledSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


}
