package net.codersdownunder.flowerseeds.aether.datagen.server.tags;

import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.codersdownunder.flowerseeds.data.tags.FlowerSeedsBlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class AetherBlockTagProvider extends FlowerSeedsBlockTagsProvider {
    public AetherBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper, String modid) {
        super(output, lookupProvider, modid, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Flower Seeds 2 Aether Block Tag data Provider";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        ArrayList<Block> blocks = new ArrayList<Block>();

        for (DeferredHolder<Block, ? extends Block> block : FlowerSeedsAether.BLOCKS.getEntries()) {
            blocks.add(block.get());
        }

        tagSet(blocks.toArray(new Block[blocks.size()]));
    }

}
