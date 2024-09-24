package net.codersdownunder.flowerseeds.aether.datagen.server.compat;

import com.aetherteam.aether.block.AetherBlocks;
import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.codersdownunder.flowerseeds.data.FlowerSeedsCompat;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AetherCompatDataProvider extends FlowerSeedsCompat {

    public AetherCompatDataProvider(String modid, GatherDataEvent event) {
        super(modid, event);
    }

    @Override
    protected @NotNull CompletableFuture<?> generate() {

        basicFlowerProcessing(FlowerSeedsAether.PURPLE_FLOWER_SEED.get(), AetherBlocks.PURPLE_FLOWER.asItem());
        basicFlowerProcessing(FlowerSeedsAether.WHITE_FLOWER_SEED.get(), AetherBlocks.WHITE_FLOWER.asItem());

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
        return "Flower Seeds 2 Aether Compat data Provider";
    }

}
