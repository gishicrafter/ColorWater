package colorwater;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;


public class ModItems
{
	public static ItemGeneric bucketGeneric;
	public static ItemGeneric liquidGeneric;

	public static SubItemInfo[] bucketSub = new SubItemInfo[32];
	public static SubItemInfo[] liquidSub = new SubItemInfo[32];
	
	public static void registerItems()
	{
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
		}
	}
}