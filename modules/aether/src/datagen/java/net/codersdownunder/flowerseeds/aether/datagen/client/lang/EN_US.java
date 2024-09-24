package net.codersdownunder.flowerseeds.aether.datagen.client.lang;

import net.codersdownunder.flowerseeds.aether.FlowerSeedsAether;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EN_US extends LanguageProvider
    {
        public EN_US(PackOutput gen, String modid)
        {
            super(gen, modid, "en_us");
        }

        @Override
        public String getName() {
            return "Flower Seeds 2 Aether EN_US Lang Provider";
        }

        @Override
        protected void addTranslations() {

            addBlockTranslation(FlowerSeedsAether.PURPLE_FLOWER_SEED.get(), "Purple Flower");
            addBlockTranslation(FlowerSeedsAether.WHITE_FLOWER_SEED.get(), "White Flower");

            add("flowerseedsaether.packmeta.description", "Flower Seeds 2 Biomes o Plenty Resource And Datapacks");
        }

        protected void addBlockTranslation(Block block, String name) {
            add(block, name);
            add(block.asItem(), name + " Seeds");
        }
}
