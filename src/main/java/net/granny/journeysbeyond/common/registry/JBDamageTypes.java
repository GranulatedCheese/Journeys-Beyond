package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class JBDamageTypes {
    public static ResourceKey<DamageType> BASIC_PLAYER_ATK = ResourceKey.create(Registries.DAMAGE_TYPE, JourneysBeyond.prefix("basic_player_attack"));
    public static ResourceKey<DamageType> BASIC_PLAYER_ATK_NOKB = ResourceKey.create(Registries.DAMAGE_TYPE, JourneysBeyond.prefix("basic_player_attack_nokb"));
}
