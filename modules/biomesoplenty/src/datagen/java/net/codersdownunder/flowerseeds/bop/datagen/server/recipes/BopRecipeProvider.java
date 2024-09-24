package net.codersdownunder.flowerseeds.bop.datagen.server.recipes;

import biomesoplenty.api.block.BOPBlocks;
import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
import net.codersdownunder.flowerseeds.data.FlowerSeedsRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;

public class BopRecipeProvider extends FlowerSeedsRecipes  {

    public BopRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

//    @Override
//    public String getName() {
//        return "Flower Seeds 2 Roses Compat data Provider";
//    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {

        flowerSeedRecipe(FlowerSeedsBop.ROSE_SEED.get().asItem(), BOPBlocks.ROSE.asItem(), pWriter, "biomesoplenty");
        //flowerSeedRecipe(FlowerSeedsBop.WILD_FLOWER_SEED.get().asItem(), BOPBlocks.WILDFLOWER.asItem(), pWriter, "biomesoplenty");
        flowerSeedRecipe(FlowerSeedsBop.ORANGE_COSMOS_SEED.get().asItem(), BOPBlocks.ORANGE_COSMOS.asItem(), pWriter, "biomesoplenty");
        flowerSeedRecipe(FlowerSeedsBop.PINK_DAFFODIL_SEED.get().asItem(), BOPBlocks.PINK_DAFFODIL.asItem(), pWriter, "biomesoplenty");
        flowerSeedRecipe(FlowerSeedsBop.GLOW_FLOWER_SEED.get().asItem(), BOPBlocks.GLOWFLOWER.asItem(), pWriter, "biomesoplenty");
        flowerSeedRecipe(FlowerSeedsBop.WILTED_LILY_SEED.get().asItem(), BOPBlocks.WILTED_LILY.asItem(), pWriter, "biomesoplenty");
        flowerSeedRecipe(FlowerSeedsBop.BURNING_BLOSSOM_SEED.get().asItem(), BOPBlocks.BURNING_BLOSSOM.asItem(), pWriter, "biomesoplenty");

    }

}
