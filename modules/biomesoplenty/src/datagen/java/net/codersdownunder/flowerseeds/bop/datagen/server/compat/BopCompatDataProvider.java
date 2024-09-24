package net.codersdownunder.flowerseeds.bop.datagen.server.compat;

import biomesoplenty.api.block.BOPBlocks;
import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.codersdownunder.flowerseeds.data.FlowerSeedsCompat;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BopCompatDataProvider extends FlowerSeedsCompat {

    public BopCompatDataProvider(String modid, GatherDataEvent event) {
        super(modid, event);
    }

    @Override
    protected @NotNull CompletableFuture<?> generate() {

        basicFlowerProcessing(FlowerSeedsBop.ROSE_SEED.get(), BOPBlocks.ROSE.asItem());
        //basicFlowerProcessing(FlowerSeedsBop.WILD_FLOWER_SEED.get(), BOPBlocks.WILDFLOWER.asItem());
        basicFlowerProcessing(FlowerSeedsBop.ORANGE_COSMOS_SEED.get(), BOPBlocks.ORANGE_COSMOS.asItem());
        basicFlowerProcessing(FlowerSeedsBop.PINK_DAFFODIL_SEED.get(), BOPBlocks.PINK_DAFFODIL.asItem());
        basicFlowerProcessing(FlowerSeedsBop.GLOW_FLOWER_SEED.get(), BOPBlocks.GLOWFLOWER.asItem());
        basicFlowerProcessing(FlowerSeedsBop.WILTED_LILY_SEED.get(), BOPBlocks.WILTED_LILY.asItem());

        basicNetherFlowerProcessing(FlowerSeedsBop.BURNING_BLOSSOM_SEED.get(), BOPBlocks.BURNING_BLOSSOM.asItem());

       //This is here to force it to generate all the files, otherwise the last few files don't get generated for some reason or other.
        //TODO: fix this in future (somehow?)
//        MEKANISM_CRUSHING.builder(toName(Blocks.AIR), Ingredient.of(Blocks.AIR.asItem()), new ResourceLocation("minecraft:air"), 0);
//
//        THERMAL_INSOLATING.builder(toName(Items.AIR))
//                .addInput(Ingredient.of(Items.CACTUS))
//                .addOutputItem(Items.AIR, 2, 4f)
//                .addOutputItem(Items.AIR, 1, 2f).build();

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public String getName() {
        return "Flower Seeds 2 Bop Compat data Provider";
    }

}
