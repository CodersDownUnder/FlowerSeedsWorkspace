package net.codersdownunder.flowerseeds.aether.datagen.server.recipes;

import com.aetherteam.aether.block.AetherBlocks;
import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.codersdownunder.flowerseeds.data.FlowerSeedsRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;


public class AetherRecipeProvider extends FlowerSeedsRecipes implements IConditionBuilder {

    public AetherRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

//    @Override
//    public String getName() {
//        return "Flower Seeds 2 Roses Compat data Provider";
//    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {

        flowerSeedRecipe(FlowerSeedsAether.WHITE_FLOWER_SEED.get().asItem(), AetherBlocks.WHITE_FLOWER.asItem(), pWriter, "aether");
        flowerSeedRecipe(FlowerSeedsAether.PURPLE_FLOWER_SEED.get().asItem(), AetherBlocks.PURPLE_FLOWER.asItem(), pWriter, "aether");

    }

}
