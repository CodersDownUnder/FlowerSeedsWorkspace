package net.codersdownunder.flowerseeds.aether.datagen.client.models;

import com.aetherteam.aether.block.AetherBlocks;
import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.codersdownunder.flowerseeds.data.FlowerSeedsBlockStates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class AetherBlockStateProvider extends FlowerSeedsBlockStates {
    public AetherBlockStateProvider(PackOutput output, String MODID, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    public String getName() {
        return "Flower Seeds 2 Aether BlockState Provider";
    }

    
    @Override
    protected void registerStatesAndModels() {

        flowerModelCompat(FlowerSeedsAether.PURPLE_FLOWER_SEED.get(), "block/purple_flower", "purple_flower", "aether", AetherBlocks.PURPLE_FLOWER.get());
        flowerModelCompat(FlowerSeedsAether.WHITE_FLOWER_SEED.get(), "block/white_flower", "white_flower", "aether", AetherBlocks.WHITE_FLOWER.get());
    }


    public void flowerModelCompat(Block block, String texture, String flower, String modid, Block compatBlock) {

        getVariantBuilder(block)
                .partialState()
                .with(CropBlock.AGE, 0)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 0, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 1)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 0, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 2)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 1, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 3)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 1, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 4)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 1, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 5)
                .modelForState()
                .modelFile(stageCompat(new ResourceLocation("aether:block/natural/" + flower), 2, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 6)
                .modelForState()
                .modelFile(fullyGrownCompat(blockTexture(compatBlock), texture, flower, modid))
                .addModel()
                .partialState()
                .with(CropBlock.AGE, 7)
                .modelForState()
                .modelFile(fullyGrownCompat(blockTexture(compatBlock), texture, flower, modid))
                .addModel();

    }


}
