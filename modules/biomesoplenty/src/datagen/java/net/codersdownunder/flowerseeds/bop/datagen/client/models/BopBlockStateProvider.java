package net.codersdownunder.flowerseeds.bop.datagen.client.models;

import biomesoplenty.api.block.BOPBlocks;

import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.codersdownunder.flowerseeds.data.FlowerSeedsBlockStates;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class BopBlockStateProvider extends FlowerSeedsBlockStates {
    public BopBlockStateProvider(PackOutput output, String MODID, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    public String getName() {
        return "Flower Seeds 2 Biomes O Plenty BlockState Provider";
    }

    
    @Override
    protected void registerStatesAndModels() {

        flowerModelCompat(FlowerSeedsBop.ROSE_SEED.get(), "block/rose", "rose", "biomesoplenty", BOPBlocks.ROSE.defaultBlockState().getBlock());
        //flowerModelCompat(FlowerSeedsBop.WILD_FLOWER_SEED.get(), "block/wildflower", "wildflower", "biomesoplenty", BOPBlocks.WILDFLOWER.defaultBlockState().getBlock());
        flowerModelCompat(FlowerSeedsBop.ORANGE_COSMOS_SEED.get(), "block/orange_cosmos", "orange_cosmos", "biomesoplenty", BOPBlocks.ORANGE_COSMOS.defaultBlockState().getBlock());
        flowerModelCompat(FlowerSeedsBop.PINK_DAFFODIL_SEED.get(), "block/pink_daffodil", "pink_daffodil", "biomesoplenty", BOPBlocks.PINK_DAFFODIL.defaultBlockState().getBlock());
        flowerModelCompat(FlowerSeedsBop.GLOW_FLOWER_SEED.get(), "block/glowflower", "glowflower", "biomesoplenty", BOPBlocks.GLOWFLOWER.defaultBlockState().getBlock());
        flowerModelCompat(FlowerSeedsBop.WILTED_LILY_SEED.get(), "block/wilted_lily", "wilted_lily", "biomesoplenty", BOPBlocks.WILTED_LILY.defaultBlockState().getBlock());
        flowerModelCompat(FlowerSeedsBop.BURNING_BLOSSOM_SEED.get(), "block/burning_blossom", "burning_blossom", "biomesoplenty", BOPBlocks.BURNING_BLOSSOM.defaultBlockState().getBlock());
    }



}
