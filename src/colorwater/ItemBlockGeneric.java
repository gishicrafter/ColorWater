package colorwater;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {

	private BlockGeneric block;
	
	public ItemBlockGeneric(int par1) {
		super(par1);
		
		this.setHasSubtypes(true);
		if(Block.blocksList[this.getBlockID()] instanceof BlockGeneric){
			block = (BlockGeneric) Block.blocksList[this.getBlockID()];
		}
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		if(par1ItemStack != null){
			SubBlockInfo subBlock = block.subBlockList[par1ItemStack.getItemDamage()];
			if(this.getItemName() != null && subBlock != null && subBlock.name != null){
				return this.getItemName() + "." + subBlock.name;
			}
		}
		return super.getItemNameIS(par1ItemStack);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
