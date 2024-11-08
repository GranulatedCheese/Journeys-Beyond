package net.granny.journeysbeyond.common.item.base;

import net.granny.journeysbeyond.common.util.item.JBWeaponUtil;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public abstract class SkilledSwordItem extends SwordItem implements JBWeaponUtil {
    public SkilledSwordItem(Tier pTier, int pAttackDamageMod, float pAttackSpeedMod, Properties pProperties) {
        super(pTier, pAttackDamageMod, pAttackSpeedMod, pProperties.fireResistant());
    }
}
