package net.granny.journeysbeyond.datagen;

import net.granny.journeysbeyond.JourneysBeyond;
import net.granny.journeysbeyond.common.registry.JBItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class JBItemModelProvider extends ItemModelProvider {
    public JBItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JourneysBeyond.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(JBItems.SOULLESS_GREED_SWORD);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(JourneysBeyond.MOD_ID, "item/" + item.getId().getPath()));
    }
}
