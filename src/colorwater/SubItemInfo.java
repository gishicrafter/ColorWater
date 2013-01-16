package colorwater;

import net.minecraft.item.ItemStack;

public class SubItemInfo {

	public int parentID;
	public int meta;
	public int primaryTextureIndex;
	public int primaryMultiplyColor;
	public int secondaryTextureIndex;
	public int secondaryMultiplyColor;
	public boolean renderMultipass;
	public boolean hasEffect;
	public String name;
	public float xpSmelting;
	
	public SubItemInfo(ItemGeneric parent, int meta)
	{
		if(meta < 0) throw new IllegalArgumentException("Parameter 'meta' should be >= 0");
		parentID = parent.itemID;
		this.meta = meta;
		
		if(parent.subItemList.containsKey(meta)){
			System.out.println("CONFLICT @ " + meta + " item slot already occupied by " + parent.subItemList.get(meta) + " while adding " + this);
		}
		
		parent.subItemList.put(meta, this);
		
		this.renderMultipass = false;
		this.primaryTextureIndex = 0;
		this.primaryMultiplyColor = 0xFFFFFF;
		this.secondaryTextureIndex = 0;
		this.secondaryMultiplyColor = 0xFFFFFF;
		this.hasEffect = false;
		this.xpSmelting = -1;
	}
	
	public SubItemInfo setIconIndex(int index)
	{
		this.primaryTextureIndex = index;
		return this;
	}
	
	public SubItemInfo setIconCoord(int x, int y)
	{
		return this.setIconIndex(x + y*16);
	}
	
	public SubItemInfo setIconColor(int color)
	{
		this.primaryMultiplyColor = color;
		return this;
	}
	
	public SubItemInfo setSecondaryIconIndex(int index)
	{
		this.secondaryTextureIndex = index;
		this.renderMultipass = true;
		return this;
	}
	
	public SubItemInfo setSecondaryIconCoord(int x, int y)
	{
		return this.setSecondaryIconIndex(x + y*16);
	}
	
	public SubItemInfo setSecondaryIconColor(int color)
	{
		this.secondaryMultiplyColor = color;
		return this;
	}
	
	public SubItemInfo setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public SubItemInfo setEffect(boolean enable)
	{
		this.hasEffect = enable;
		return this;
	}
	
	public SubItemInfo setXpSmelting(float xpSmelting)
	{
		this.xpSmelting = xpSmelting;
		return this;
	}
	
	public ItemStack getItemStack(int quantity)
	{
		return new ItemStack(parentID, quantity, meta);
	}
}
