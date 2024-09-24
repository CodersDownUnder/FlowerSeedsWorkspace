package net.codersdownunder.flowerseeds.bop.datagen.client.lang;

import net.codersdownunder.flowerseeds.bop.FlowerSeedsBop;
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
            return "Flower Seeds 2 Biomes O Plenty EN_US Lang Provider";
        }

        @Override
        protected void addTranslations() {

            addBlockTranslation(FlowerSeedsBop.ROSE_SEED.get(), "Rose");
            //addBlockTranslation(FlowerSeedsBop.WILD_FLOWER_SEED.get(), "Wild Flower");
            addBlockTranslation(FlowerSeedsBop.ORANGE_COSMOS_SEED.get(), "Orange Cosmos");
            addBlockTranslation(FlowerSeedsBop.PINK_DAFFODIL_SEED.get(), "Pink Daffodil");
            addBlockTranslation(FlowerSeedsBop.GLOW_FLOWER_SEED.get(), "Glow Flower");
            addBlockTranslation(FlowerSeedsBop.WILTED_LILY_SEED.get(), "Wilted Lily");
            addBlockTranslation(FlowerSeedsBop.BURNING_BLOSSOM_SEED.get(), "Burning Blossom");

            add("flowerseedsbop.packmeta.description", "Flower Seeds 2 Biomes o Plenty Resource And Datapacks");
        }

        protected void addBlockTranslation(Block block, String name) {
            add(block, name);
            add(block.asItem(), name + " Seeds");
        }
}
