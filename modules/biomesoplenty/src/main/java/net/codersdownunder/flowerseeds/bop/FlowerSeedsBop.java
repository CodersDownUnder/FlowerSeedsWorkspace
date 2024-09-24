package net.codersdownunder.flowerseeds.bop;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.codersdownunder.flowerseeds.blocks.CustomBurningCropBlock;
import net.codersdownunder.flowerseeds.blocks.CustomCropBlock;
import net.codersdownunder.flowerseeds.events.SeedColour;
import net.codersdownunder.flowerseeds.events.VillagerTradesEventHandler;
import net.codersdownunder.flowerseeds.init.CreativeTabInit;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FlowerSeedsBop.MODID)
public class FlowerSeedsBop {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "flowerseedsbop";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    /*
    Flowers to add
    Rose -> Red
    WildFlower -> Magenta
    Orange Cosmos -> Orange
    Pink Daffodil -> Pink
    GlowFlower -> Cyan
    Wilted Lily -> Grey
    Burning Blossom -> Orange
     */
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MODID);

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> ROSE_SEED = registerBlock("rose_seed",
                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP),
                        SeedColour.RED));

//    public static final DeferredBlock<Block> WILD_FLOWER_SEED = registerBlock("wild_flower_seed",
//                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP),
//                        SeedColour.MAGENTA));

    public static final DeferredBlock<Block> ORANGE_COSMOS_SEED = registerBlock("orange_cosmos_seed",
                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP),
                        SeedColour.ORANGE));

    public static final DeferredBlock<Block> PINK_DAFFODIL_SEED = registerBlock("pink_daffodil_seed",
                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP),
                        SeedColour.PINK));

    public static final DeferredBlock<Block> GLOW_FLOWER_SEED = registerBlock("glow_flower_seed",
                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP).lightLevel((state) -> {return 9;}),
                        SeedColour.CYAN));

    public static final DeferredBlock<Block> WILTED_LILY_SEED = registerBlock("wilted_lily_seed",
                () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP),
                        SeedColour.GREY));
    
    public static final DeferredBlock<Block> BURNING_BLOSSOM_SEED = registerBlock("burning_blossom_seed",
                () -> new CustomBurningCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP).lightLevel((state) -> {return 7;}),
                        SeedColour.ORANGE));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new ItemNameBlockItem(block.get(), new Item.Properties()));
    }

    public FlowerSeedsBop(IEventBus modEventBus) {

        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative);



    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class VillagerEventHandler {
        @SubscribeEvent
        public static void onVillagerTrades(VillagerTradesEvent pEvent) {
            if (pEvent.getType().equals(VillagerProfession.FARMER)) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = pEvent.getTrades();
                int villagerLevel = 1;

                for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                    trades.get(villagerLevel).add((trader, rand) -> VillagerTradesEventHandler.addTrade(block.get()));
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
            for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                if (block.get() instanceof CustomBurningCropBlock) {
                    CustomBurningCropBlock item = (CustomBurningCropBlock) block.get();
                    event.register(item.getColour().get(), item.asItem());
                    continue;
                }

                CustomCropBlock item = (CustomCropBlock) block.get();
                event.register(item.getColour().get(), item.asItem());

            }
        }
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeTabInit.FLOWER_SEEDS_TAB.getKey()) {
            for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                event.accept(block.get().asItem());
            }

        }
    }

}
