package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class JBDamageTypes {
    public static ResourceKey<DamageType> HEALTH_STEAL_ATTACK = ResourceKey.create(Registries.DAMAGE_TYPE, JourneysBeyond.prefix("health_steal_attack"));
}
