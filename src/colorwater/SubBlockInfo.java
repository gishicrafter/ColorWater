package colorwater;

import net.minecraft.item.ItemStack;

public class SubBlockInfo {
	public int parentID;
	public int meta;
	public int textureIndex;
	public int multiplyColor;
	public String name;
	public int idDropItem;
	public int quantityDropItem;
	public int damageDropItem;
	public int xpMin;
	public int xpMax;

	public SubBlockInfo(BlockGeneric parent, int meta)
	{
		if(parent.subBlockList[meta] != null){
			throw new IllegalArgumentException("Slot " + meta + " is already occupied by " + parent.subBlockList[meta] + " when adding " + this);
		}else{
			parent.subBlockList[meta] = this;
			this.parentID = parent.blockID;
			this.meta = meta;
			this.textureIndex = parent.blockIndexInTexture;
			this.multiplyColor = 16777215;
			this.idDropItem = parent.blockID;
			this.quantityDropItem = 1;
			this.damageDropItem = meta;
			this.xpMin = 0;
			this.xpMax = 0;
		}
	}
	
	public SubBlockInfo setTextureIndex(int textureIndex)
	{
		this.textureIndex = textureIndex;
		return this;
	}
	
	public SubBlockInfo setBlockColor(int multiplyColor)
	{
		this.multiplyColor = multiplyColor;
		return this;
	}
	
	public SubBlockInfo setName(String  name)
	{
		this.name = name;
		return this;
	}
	
	public SubBlockInfo setDropItem(int idDropItem, int quantityDropItem, int damageDropItem)
	{
		this.idDropItem = idDropItem;
		this.quantityDropItem = quantityDropItem;
		this.damageDropItem = damageDropItem;
		return this;
	}
	
	public SubBlockInfo setXpMine(int xpMin, int xpMax)
	{
		this.xpMin = xpMin;
		this.xpMax = xpMax;
		return this;
	}
	
	public ItemStack getItemStack(int quantity)
	{
		return new ItemStack(parentID, quantity, meta);
	}
}
