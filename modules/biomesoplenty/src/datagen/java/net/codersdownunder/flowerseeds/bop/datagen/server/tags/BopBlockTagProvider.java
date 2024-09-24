package net.codersdownunder.flowerseeds.bop.datagen.server.tags;

import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.codersdownunder.flowerseeds.data.tags.FlowerSeedsBlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class BopBlockTagProvider extends FlowerSeedsBlockTagsProvider {
    public BopBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper, String modid) {
        super(output, lookupProvider, modid, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Flower Seeds 2 Biomes O Plenty Block Tag data Provider";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        ArrayList<Block> blocks = new ArrayList<Block>();

        for (DeferredHolder<Block, ? extends Block> block : FlowerSeedsBop.BLOCKS.getEntries()) {
            blocks.add(block.get());
        }

        tagSet(blocks.toArray(new Block[blocks.size()]));
    }

}
