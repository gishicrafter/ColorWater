package colorwater;

import buildcraft.api.recipes.RefineryRecipe;
import net.minecraft.src.Item;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name="ColorWater", version="0.0.0", modid = "ColorWater")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ColorWater {

	@Instance("ColorWater")
	public static ColorWater instance;
	
	@SidedProxy(clientSide="colorwater.client.ClientProxy", serverSide="colorwater.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ModConfiguration.loadConfiguration(event.getSuggestedConfigurationFile());
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		ModItems.registerItems();
		proxy.registerRenderers();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		ExternalItems.registerExternalItems();
		registerCraftingRecipe();
		registerRefineryRecipe();
	}
	
	private void registerCraftingRecipe()
	{
		int i;
		for(i = 0; i < 16; ++i){
			GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.bucketSub[i].getItemStack(1) ,new Object[]{"dye" + Color.values()[i].name(), Item.bucketEmpty, Item.bucketWater}));
		}
	}
	
	private void registerRefineryRecipe()
	{
		int i;
		LiquidStack ingredient1;
		LiquidStack ingredient2;
		LiquidStack result;
		for(i = 0; i < 16; ++i){
			if(Color.mixing[i].length == 2){
				RefineryRecipe.registerRefineryRecipe(
						new RefineryRecipe(
								LiquidDictionary.getLiquid("water" + Color.mixing[i][0].name(), 1),
								LiquidDictionary.getLiquid("water" + Color.mixing[i][1].name(), 1),
								LiquidDictionary.getLiquid("water" + Color.values()[i].name(), 2),
								1, 1
								)
						);
				
				RefineryRecipe.registerRefineryRecipe(
						new RefineryRecipe(
								LiquidDictionary.getLiquid("oil" + Color.mixing[i][0].name(), 1),
								LiquidDictionary.getLiquid("oil" + Color.mixing[i][1].name(), 1),
								LiquidDictionary.getLiquid("oil" + Color.values()[i].name(), 2),
								1, 1
								)
						);
			}
			
			if(ExternalItems.fuel != null){
				RefineryRecipe.registerRefineryRecipe(
						new RefineryRecipe(
								new LiquidStack(ExternalItems.fuel.itemID, 1, ExternalItems.fuel.getItemDamage()),
								LiquidDictionary.getLiquid("water" + Color.values()[i].name(), 1),
								LiquidDictionary.getLiquid("oil" + Color.values()[i].name(), 2),
								1, 1
								)
						);
			}
		}
	}
}
