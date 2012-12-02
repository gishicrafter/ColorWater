package colorwater;

import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemGeneric extends Item
{
	public HashMap<Integer, SubItemInfo> subItemList = new HashMap<Integer, SubItemInfo>();
	
	public ItemGeneric(int i) {
		super(i);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getIconFromDamage(int par1)
	{
		SubItemInfo subItem = subItemList.get(par1);
		if(subItem != null){
			return subItem.primaryTextureIndex;
		}
		return super.getIconFromDamage(par1);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		SubItemInfo subItem = subItemList.get(par1ItemStack.getItemDamage());
		if(subItem != null){
			return subItem.hasEffect;
		}
		return super.hasEffect(par1ItemStack);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	
	@Override
	public int getRenderPasses(int metadata)
	{
		SubItemInfo subItem = subItemList.get(metadata);
		if(subItem != null && subItem.renderMultipass){
			return 2;
		}
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getIconFromDamageForRenderPass(int par1, int par2)
	{
		SubItemInfo subItem = subItemList.get(par1);
		if(subItem != null && subItem.renderMultipass){
			if(par2 == 1) return subItem.secondaryTextureIndex;
			
			return subItem.primaryTextureIndex;
		}
		return super.getIconFromDamageForRenderPass(par1, par2);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		SubItemInfo subItem = subItemList.get(par1ItemStack.getItemDamage());
		if(subItem != null){
			if(par2 == 1)
				return subItem.secondaryMultiplyColor;
			
			return subItem.primaryMultiplyColor;
		}
		return super.getColorFromItemStack(par1ItemStack, par2);
	}

	
	@Override
	public float getSmeltingExperience(ItemStack item)
	{
		SubItemInfo subItem = subItemList.get(item.getItemDamage());
		if(subItem != null){
			return subItem.xpSmelting;
		}
		return super.getSmeltingExperience(item);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(SubItemInfo item : subItemList.values()){
			if(item.parentID == par1){
				par3List.add(item.getItemStack(1));
			}
		}
	}
	
	@Override
	public String getItemNameIS(ItemStack par1ItemStack)
	{
		if(par1ItemStack != null){
			SubItemInfo subItem = subItemList.get(par1ItemStack.getItemDamage());
			if(subItem != null && subItem.name != null && this.getItemName() != null){
				return this.getItemName() + "." + subItem.name;
			}
		}
		return super.getItemNameIS(par1ItemStack);
	}
}
