package colorwater;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class ExternalItems {

	public static ItemStack fuel = null;
	
	static void registerExternalItems()
	{
		LiquidStack liquidFuel = LiquidDictionary.getLiquid("Fuel", 0);
		if(liquidFuel != null){
			fuel = liquidFuel.asItemStack();
			System.out.println("Fuel found in LiquidDictionary.");
		}else{
			try{
				Item itemFuel = (Item)(Class.forName("buildcraft.BuildCraftEnergy").getField("fuel").get(null));
				fuel = new ItemStack(itemFuel, 1, 0);
				System.out.println("Fuel found in buildcraft.BuildCraftEnergy.");
			}catch(Exception e){
				System.out.println("Couldn't find fuel.");
			}
		}
	}
}
