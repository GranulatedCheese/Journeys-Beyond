package net.granny.journeysbeyond.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class JBDamageSources {
    private final Registry<DamageType> damageTypes;

    public JBDamageSources(RegistryAccess pRegistry) {
        this.damageTypes = pRegistry.registryOrThrow(Registries.DAMAGE_TYPE);
    }

    public static JBDamageSources getInstance(Level level) {
        return new JBDamageSources(level.registryAccess());
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey));
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pEntity);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pCausingEntity, @Nullable Entity pDirectEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pCausingEntity, pDirectEntity);
    }

    public DamageSource basicPlayerATK(LivingEntity pPlayer) {
        return this.source(JBDamageTypes.BASIC_PLAYER_ATK, pPlayer);
    }

    public DamageSource basicPlayerATKwithoutKB(LivingEntity pPlayer) {
        return this.source(JBDamageTypes.BASIC_PLAYER_ATK_NOKB, pPlayer);
    }
}
