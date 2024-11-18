package net.granny.journeysbeyond.common.registry;

import net.granny.journeysbeyond.JourneysBeyond;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JBAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, JourneysBeyond.MOD_ID);

    public static final RegistryObject<Attribute> activated_damage = createDefaultRangedAttribute("activated_damage", 1);

    public static final RegistryObject<Attribute> activated_damage_reduction = createDefaultRangedAttribute("activated_damage_reduction", 1);

    public static RegistryObject<Attribute> createDefaultRangedAttribute(String id, double defaultValue) {
        return ATTRIBUTES.register(id, () -> new RangedAttribute("attribute.journeysbeyond" + id, defaultValue, -1024.0 , 1024.0));
    }
}
