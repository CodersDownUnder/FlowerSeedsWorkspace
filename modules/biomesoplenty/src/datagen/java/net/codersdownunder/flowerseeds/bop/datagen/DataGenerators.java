package net.codersdownunder.flowerseeds.bop.datagen;

import com.mojang.logging.LogUtils;
import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.codersdownunder.flowerseeds.bop.datagen.client.lang.EN_US;
import net.codersdownunder.flowerseeds.bop.datagen.client.models.BopBlockStateProvider;
import net.codersdownunder.flowerseeds.bop.datagen.client.models.BopItemModelProvider;
import net.codersdownunder.flowerseeds.bop.datagen.server.compat.BopCompatDataProvider;
import net.codersdownunder.flowerseeds.bop.datagen.server.datamaps.CompostablesGen;
import net.codersdownunder.flowerseeds.bop.datagen.server.loot.BopLootTableProvider;
import net.codersdownunder.flowerseeds.bop.datagen.server.recipes.BopRecipeProvider;
import net.codersdownunder.flowerseeds.bop.datagen.server.tags.BopBlockTagProvider;
import net.codersdownunder.flowerseeds.bop.datagen.server.tags.BopItemTagProvider;
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

@Mod.EventBusSubscriber(modid = FlowerSeedsBop.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        String MODID = FlowerSeedsBop.MODID;

        LOGGER.debug("This is triggered");

        generator.addProvider(true, new PackMetadataGenerator(packOutput)
                .add(OverlayMetadataSection.TYPE, new OverlayMetadataSection(List.of(
                        new OverlayMetadataSection.OverlayEntry(new InclusiveRange<>(0, Integer.MAX_VALUE), "pack_overlays_test"))))
                .add(PackMetadataSection.TYPE, new PackMetadataSection(
                        Component.translatable("flowerseedsbop.packmeta.description"),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                        Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));

        generator.addProvider(event.includeClient(), new EN_US(packOutput, MODID));
        generator.addProvider(event.includeServer(), new BopRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), BopLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new BopBlockStateProvider(packOutput, MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new BopItemModelProvider(packOutput, MODID, existingFileHelper));

        BopBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new BopBlockTagProvider(packOutput, lookupProvider, existingFileHelper, MODID));
        generator.addProvider(event.includeServer(), new BopItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper, MODID));

        generator.addProvider(event.includeServer(), new BopCompatDataProvider(MODID, event));
        new BopCompatDataProvider(MODID, event);
        generator.addProvider(event.includeServer(), new CompostablesGen(packOutput, lookupProvider));


    }
}
