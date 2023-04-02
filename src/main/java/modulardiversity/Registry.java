package modulardiversity;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import modulardiversity.block.*;
import modulardiversity.components.component.*;
import modulardiversity.tile.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class Registry {
    private static ArrayList<Block> BLOCKS = new ArrayList<>();
    private static ArrayList<Item> ITEMS = new ArrayList<>();

    public static void preInit()
    {
        MinecraftForge.EVENT_BUS.register(Registry.class);
        registerBlocks();
        registerTileEntities();
    }

    public static void registerBlocks()
    {

        if(ModularDiversity.ImmersivePetroleumLoaded) {
            BlockJackHatch jackHatch = new BlockJackHatch();

            registerBlock("blockjackhatch",jackHatch, new ItemBlockMachineComponent(jackHatch));
        }

        if(ModularDiversity.BuildcraftLoaded) {
            BlockLaserHatch laserHatch = new BlockLaserHatch();

            registerBlock("blocklaserhatch",laserHatch, new ItemBlockMachineComponent(laserHatch));
        }


        if(ModularDiversity.BetterWithModsLoaded) {
            BlockMechInputHatch mechInputHatch = new BlockMechInputHatch();
            BlockMechCrankHatch crankInputHatch = new BlockMechCrankHatch();
            BlockMechOutputHatch mechOutputHatch = new BlockMechOutputHatch(1);
            BlockMechOutputHatch mechSteelOutputHatch = new BlockMechOutputHatch(50);

            registerBlock("blockmechcrankhatch",crankInputHatch, new ItemBlockMachineComponent(crankInputHatch));
            registerBlock("blockmechinputhatch",mechInputHatch, new ItemBlockMachineComponent(mechInputHatch));
            registerBlock("blockmechoutputhatch",mechOutputHatch, new ItemBlockMachineComponent(mechOutputHatch));
            registerBlock("blockmechsteeloutputhatch",mechSteelOutputHatch, new ItemBlockMachineComponent(mechSteelOutputHatch));
        }


        if (ModularDiversity.MekanismLoaded) {
            BlockMekLaserAcceptor mekLaserAcceptor = new BlockMekLaserAcceptor();
            BlockMekHeatInput mekHeatInput = new BlockMekHeatInput();
            BlockMekHeatOutput mekHeatOutput = new BlockMekHeatOutput();

            registerBlock("blockmeklaseracceptor", mekLaserAcceptor, new ItemBlockMachineComponent(mekLaserAcceptor));
            registerBlock("blockmekheatinput", mekHeatInput, new ItemBlockMachineComponent(mekHeatInput));
            registerBlock("blockmekheatoutput", mekHeatOutput, new ItemBlockMachineComponent(mekHeatOutput));
        }

        if(ModularDiversity.MysticalMechanicsLoaded) {
            BlockMysticalMechanicsInput mysticalMechanicsInput = new BlockMysticalMechanicsInput();
            BlockMysticalMechanicsOutput mysticalMechanicsOutput = new BlockMysticalMechanicsOutput();

            registerBlock("blockmystmechinput", mysticalMechanicsInput, new ItemBlockMachineComponent(mysticalMechanicsInput));
            registerBlock("blockmystmechoutput",mysticalMechanicsOutput, new ItemBlockMachineComponent(mysticalMechanicsOutput));
        }

    }

    public static void registerBlockModels()
    {
        for (Block block : BLOCKS) {
            ModularDiversity.proxy.registerBlockModel(block);
        }
    }

    public static void registerItemModels()
    {
        for (Item item : ITEMS) {
            ModularDiversity.proxy.registerItemModel(item);
        }
    }

    public static void registerBlock(String id,Block block, ItemBlock itemBlock)
    {
        block.setRegistryName(ModularDiversity.MODID,id);
        block.setTranslationKey(id);
        BLOCKS.add(block);
        registerItem(id,itemBlock);
    }

    public static void registerItem(String id,Item item)
    {
        item.setRegistryName(ModularDiversity.MODID,id);
        item.setTranslationKey(id);
        ITEMS.add(item);
    }

    public static void registerTileEntities() {

        if(ModularDiversity.ImmersivePetroleumLoaded) {
            registerTileEntity(TileJackHatch.class);
        }
        if(ModularDiversity.BetterWithModsLoaded) {
            registerTileEntity(TileMechInput.class);
            registerTileEntity(TileMechInputCrank.class);
            registerTileEntity(TileMechOutput.class);
        }
        if(ModularDiversity.BuildcraftLoaded) {
            registerTileEntity(TileLaserInput.class);
        }

        if (ModularDiversity.MekanismLoaded) {
            registerTileEntity(TileEntityMekLaserAcceptor.class);
            registerTileEntity(TileEntityMekHeatInput.class);
            registerTileEntity(TileEntityMekHeatOutput.class);
        }
        if (ModularDiversity.MysticalMechanicsLoaded) {
            registerTileEntity(TileMysticalMechanicsInput.class);
            registerTileEntity(TileMysticalMechanicsOutput.class);
        }
    }

    @SubscribeEvent
    public static void registerComponents(RegistryEvent.Register<ComponentType> event) {
        event.getRegistry().register(new ComponentMechanical());
        event.getRegistry().register(new ComponentLaser());
        event.getRegistry().register(new ComponentMekLaser());
        event.getRegistry().register(new ComponentMekHeat());
        event.getRegistry().register(new ComponentMysticalMechanics());
        event.getRegistry().register(new ComponentReservoir());
        event.getRegistry().register(new ComponentMineral());
        event.getRegistry().register(new ComponentModifier());
    }


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }

    private static void registerTileEntity(Class<? extends TileEntity> tile)
    {
        GameRegistry.registerTileEntity(tile,tile.getSimpleName().toLowerCase());
    }
}
