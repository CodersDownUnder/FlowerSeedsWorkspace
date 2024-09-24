package net.codersdownunder.flowerseeds.cyclic;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
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
@Mod(FlowerSeedsCyclic.MODID)
public class FlowerSeedsCyclic
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "flowerseedscyclic";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> CYAN_ROSE_SEED = registerBlock("cyan_rose_seed",
            () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP), SeedColour.CYAN));

    public static final DeferredBlock<Block> PURPLE_TULIP_SEED = registerBlock("purple_tulip_seed",
            () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP), SeedColour.PURPLE));

    public static final DeferredBlock<Block> LIME_CARNATION_SEED = registerBlock("lime_carnation_seed",
            () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP), SeedColour.LIME));

    public static final DeferredBlock<Block> ABSALON_TULIP_SEED = registerBlock("absalon_tulip_seed",
            () -> new CustomCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).sound(SoundType.CROP), SeedColour.BROWN));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new ItemNameBlockItem(block.get(), new Item.Properties()));
    }

    public FlowerSeedsCyclic(IEventBus modEventBus)
    {

        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative);

        //NeoForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
//        event.enqueueWork(() -> {
//            for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
//                FlowerSeeds.compostable(block.get());
//            }
//        });

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
                CustomCropBlock item = (CustomCropBlock) block.get();
                event.register(item.getColour().get(), item.asItem());
            }
        }
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeTabInit.FLOWER_SEEDS_TAB.getKey()) {
            for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                event.accept(block.get().asItem());
            }
        }
    }


}
