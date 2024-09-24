package net.codersdownunder.flowerseeds.aether.datagen;

import com.mojang.logging.LogUtils;
import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.codersdownunder.flowerseeds.aether.datagen.client.lang.EN_US;
import net.codersdownunder.flowerseeds.aether.datagen.client.models.AetherBlockStateProvider;
import net.codersdownunder.flowerseeds.aether.datagen.client.models.AetherItemModelProvider;
import net.codersdownunder.flowerseeds.aether.datagen.server.compat.AetherCompatDataProvider;
import net.codersdownunder.flowerseeds.aether.datagen.server.datamaps.CompostablesGen;
import net.codersdownunder.flowerseeds.aether.datagen.server.loot.AetherLootTableProvider;
import net.codersdownunder.flowerseeds.aether.datagen.server.recipes.AetherRecipeProvider;
import net.codersdownunder.flowerseeds.aether.datagen.server.tags.AetherBlockTagProvider;
import net.codersdownunder.flowerseeds.aether.datagen.server.tags.AetherItemTagProvider;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.OverlayMetadataSection;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FlowerSeedsAether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        LOGGER.debug("This is triggered");

        generator.addProvider(true, new PackMetadataGenerator(packOutput)
                .add(OverlayMetadataSection.TYPE, new OverlayMetadataSection(List.of(
                        new OverlayMetadataSection.OverlayEntry(new InclusiveRange<>(0, Integer.MAX_VALUE), "pack_overlays_test"))))
                .add(PackMetadataSection.TYPE, new PackMetadataSection(
                        Component.translatable("flowerseedsaether.packmeta.description"),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                        Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));

        generator.addProvider(event.includeClient(), new EN_US(packOutput, FlowerSeedsAether.MODID));
        generator.addProvider(event.includeServer(), new AetherRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), AetherLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new AetherBlockStateProvider(packOutput, FlowerSeedsAether.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new AetherItemModelProvider(packOutput, FlowerSeedsAether.MODID, existingFileHelper));

        AetherBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new AetherBlockTagProvider(packOutput, lookupProvider, existingFileHelper, FlowerSeedsAether.MODID));
        generator.addProvider(event.includeServer(), new AetherItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper, FlowerSeedsAether.MODID));

        generator.addProvider(event.includeServer(), new AetherCompatDataProvider(FlowerSeedsAether.MODID, event));
        new AetherCompatDataProvider(FlowerSeedsAether.MODID, event);
        generator.addProvider(event.includeServer(), new CompostablesGen(packOutput, lookupProvider));


    }
}
