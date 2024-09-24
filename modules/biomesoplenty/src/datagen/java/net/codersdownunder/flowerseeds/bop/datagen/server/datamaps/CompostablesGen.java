package net.codersdownunder.flowerseeds.bop.datagen.server.datamaps;

import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class CompostablesGen extends DataMapProvider {

    public CompostablesGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        var compostables = builder(NeoForgeDataMaps.COMPOSTABLES);
        // Always give entities that drop any item in the minecraft:fox_food tag 12 hearts
        for (DeferredHolder<Block, ? extends Block> block: FlowerSeedsBop.BLOCKS.getEntries()) {
            compostables.add(block.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false);
        }
        // Have a 10% chance of healing entities that drop an acacia boat by one point

    }
}
