package net.codersdownunder.flowerseeds.aether.datagen.client.models;

import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AetherItemModelProvider extends ItemModelProvider {

    private static String MODID = "";

    public AetherItemModelProvider(PackOutput output, String MODID, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
        AetherItemModelProvider.MODID = MODID;
    }

    @Override
    public String getName() {
        return "Flower Seeds 2" + MODID + " Item Model Provider";
    }

    @Override
    protected void registerModels() {

        for (Holder<Item> item : FlowerSeedsAether.ITEMS.getEntries()) {
            oneLayerItem(item.value());
        }
    }

    private void oneLayerItem(final Item item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.asItem()).getPath(), "item/generated")
                .texture("layer0", new ResourceLocation("flowerseeds","item/seed"));
    }

    private ItemModelBuilder simpleItem(DeferredHolder<?,?> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MODID,"item/" + item.getId().getPath()));
    }
}
