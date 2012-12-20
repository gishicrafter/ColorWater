package colorwater;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;


public class ModItems
{
	public static BlockGeneric blockGel;
	public static SubBlockInfo[] blockGelSub = new SubBlockInfo[16];
	
	public static ItemGeneric bucketGeneric;
	public static ItemGeneric liquidGeneric;
	public static ItemGeneric bucketGel;
	public static ItemGeneric itemGel;

	public static SubItemInfo[] bucketSub = new SubItemInfo[32];
	public static SubItemInfo[] liquidSub = new SubItemInfo[32];
	public static SubItemInfo[] bucketGelSub = new SubItemInfo[16];
	public static SubItemInfo[] itemGelSub = new SubItemInfo[16];
	
	public static void registerItems()
	{
		if(ModConfiguration.idBlockGel > 0){
			(blockGel = new BlockGel(ModConfiguration.idBlockGel, 16, Material.water))
				.setHardness(0.15F)
				.setResistance(5.0F)
				.setStepSound(Block.soundSandFootstep)
				.setLightOpacity(3)
				.setBlockName("colorwater.gel")
				.setTextureFile(CommonProxy.BLOCKS_PNG);
			
			LanguageRegistry.addName(blockGel, "Generic Gel");
			GameRegistry.registerBlock(blockGel, ItemBlockGeneric.class, "gel");
		}
		
		(bucketGeneric = new ItemGeneric(ModConfiguration.idBucketGeneric))
			.setMaxStackSize(1)
			.setIconCoord(0, 1)
			.setContainerItem(Item.bucketEmpty)
			.setCreativeTab(CreativeTabs.tabMisc)
			.setItemName("colorwater.bucket")
			.setTextureFile(CommonProxy.ITEMS_PNG);
		
		LanguageRegistry.addName(bucketGeneric, "Color Liquid Bucket");
		
		(liquidGeneric = new ItemGeneric(ModConfiguration.idLiquid))
			.setMaxStackSize(1)
			.setIconCoord(0, 2)
			.setItemName("colorwater.liquid")
			.setTextureFile(CommonProxy.ITEMS_PNG);
		
		LanguageRegistry.addName(liquidGeneric, "Color Liquid");
		
		(bucketGel = new ItemGenericUseable(ModConfiguration.idBucketGel))
			.setMaxStackSize(1)
			.setIconCoord(0, 1)
			.setContainerItem(Item.bucketEmpty)
			.setCreativeTab(CreativeTabs.tabMisc)
			.setItemName("colorwater.bucket.gel")
			.setTextureFile(CommonProxy.ITEMS_PNG);
		
		LanguageRegistry.addName(bucketGel, "Color Gel Bucket");
		
		(itemGel = new ItemGeneric(ModConfiguration.idItemGel))
			.setMaxStackSize(64)
			.setIconCoord(8, 1)
			.setCreativeTab(CreativeTabs.tabMaterials)
			.setItemName("colorwater.gel")
			.setTextureFile(CommonProxy.ITEMS_PNG);
		
		LanguageRegistry.addName(itemGel, "Color Gel Bucket");
		
		int[] fuelBurning = {60000, 60000, 60000, 60000, 120000, 120000, 120000, 120000, 300000, 300000, 300000, 300000, 600000, 600000, 600000, 600000};
		float[] fuelPower = new float[16];
		int i;
		for(i=0; i< 16; ++i){
			fuelPower[i] = 600000F/fuelBurning[i];
		}
		for(i=0; i<16; ++i){
			Color color = Color.values()[i];
			bucketSub[i] = new SubItemInfo(bucketGeneric, i)
				.setIconColor(0xFFFFFF)
				.setIconCoord(0, 1)
				.setSecondaryIconColor(color.colorcode)
				.setSecondaryIconCoord(1, 1)
				.setName("water." + color.colorName());
			
			LanguageRegistry.addName(bucketSub[i].getItemStack(1), color.name() + " Water Bucket");
			
			liquidSub[i] = new SubItemInfo(liquidGeneric, i)
				.setIconCoord(i, 2)
				.setName("water." + color.colorName());
			
			LanguageRegistry.addName(liquidSub[i].getItemStack(1), color.name() + " Water");
			
			LiquidContainerRegistry.registerLiquid(
					new LiquidContainerData(
							LiquidDictionary.getOrCreateLiquid(
									"water" + color.name(),
									new LiquidStack(
											liquidSub[i].parentID,
											LiquidContainerRegistry.BUCKET_VOLUME,
											liquidSub[i].meta
											)
									),
							bucketSub[i].getItemStack(1),
							LiquidContainerRegistry.EMPTY_BUCKET));
			
			bucketSub[16+i] = new SubItemInfo(bucketGeneric, 16+i)
				.setIconColor(0xFFFFFF)
				.setIconCoord(0, 1)
				.setSecondaryIconColor(color.colorcode)
				.setSecondaryIconCoord(1, 1)
				.setName("oil." + color.colorName());
			
			LanguageRegistry.addName(bucketSub[16+i].getItemStack(1), color.name() + " Oil Bucket");
			
			liquidSub[16+i] = new SubItemInfo(liquidGeneric, 16+i)
				.setIconCoord(i, 3)
				.setName("oil." + color.colorName());
			
			LanguageRegistry.addName(liquidSub[16+i].getItemStack(1), color.name() + " Oil");
			
			LiquidContainerRegistry.registerLiquid(
					new LiquidContainerData(
							LiquidDictionary.getOrCreateLiquid(
									"oil" + color.name(),
									new LiquidStack(
											liquidSub[16+i].parentID,
											LiquidContainerRegistry.BUCKET_VOLUME,
											liquidSub[16+i].meta
											)
									),
							bucketSub[16+i].getItemStack(1),
							LiquidContainerRegistry.EMPTY_BUCKET));
			
			IronEngineFuel.fuels.add(
					new IronEngineFuel(
							LiquidDictionary.getLiquid("oil" + color.name(), LiquidContainerRegistry.BUCKET_VOLUME),
							fuelPower[i],
							fuelBurning[i]));
			
			itemGelSub[i] = new SubItemInfo(itemGel, i)
				.setIconColor(color.colorcode)
				.setIconCoord(8, 1)
				.setSecondaryIconColor(0xF0FFF0)
				.setSecondaryIconCoord(9, 1)
				.setName(color.colorName());
			
			LanguageRegistry.addName(itemGelSub[i].getItemStack(1), color.name() + " Gel");
			
			if(ModConfiguration.idBlockGel > 0){
				blockGelSub[i] = new SubBlockInfo(blockGel, i)
				.setBlockColor(0xFFFFFF)
				.setTextureIndex(16+i)
				.setDropItem(itemGelSub[i].parentID, 4, itemGelSub[i].meta)
				.setName(color.colorName());
				
				LanguageRegistry.addName(blockGelSub[i].getItemStack(1), color.name() + "Gel");
			}
			
			bucketGelSub[i] = new SubItemInfo(bucketGel, i)
				.setIconColor(0xFFFFFF)
				.setIconCoord(0, 1)
				.setSecondaryIconColor(color.colorcode)
				.setSecondaryIconCoord(1, 1)
				.setName(color.colorName());

			((ItemGenericUseable)bucketGel).useItemHandlerList.put(i,
					new UseGelBucketHandler(
							bucketGelSub[i],
							ModConfiguration.idBlockGel,
							i));
			
			LanguageRegistry.addName(bucketGelSub[i].getItemStack(1), color.name() + " Gel Bucket");
			
			LiquidContainerRegistry.registerLiquid(
					new LiquidContainerData(
							LiquidDictionary.getOrCreateLiquid(
									"gel" + color.name(),
									new LiquidStack(
											ModConfiguration.idBlockGel,
											LiquidContainerRegistry.BUCKET_VOLUME,
											i
											)
									),
							bucketGelSub[i].getItemStack(1),
							LiquidContainerRegistry.EMPTY_BUCKET));
		}
	}
}