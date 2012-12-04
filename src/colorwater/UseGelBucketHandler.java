package colorwater;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public class UseGelBucketHandler extends AbstractUseItemHandler {

	private int blockID;
	private int blockMeta;
	public UseGelBucketHandler(SubItemInfo subItem, int blockID, int blockMeta) {
		super(subItem);
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, MovingObjectPosition par4MovingObjectPosition) {
		if(ModItems.blockGel == null)
			return par1ItemStack;
		
		if(par4MovingObjectPosition == null){
			return par1ItemStack;
		}else{
			if(par4MovingObjectPosition.typeOfHit == EnumMovingObjectType.TILE){
				int tileX = par4MovingObjectPosition.blockX;
				int tileY = par4MovingObjectPosition.blockY;
				int tileZ = par4MovingObjectPosition.blockZ;
				
				if (!par2World.canMineBlock(par3EntityPlayer, tileX, tileY, tileZ)){
					return par1ItemStack;
				}
				
				switch(par4MovingObjectPosition.sideHit){
				case 0:
					--tileY;
					break;
				case 1:
					++tileY;
					break;
				case 2:
					--tileZ;
					break;
				case 3:
					++tileZ;
					break;
				case 4:
					--tileX;
					break;
				case 5:
					++tileX;
					break;
				}
				
				if (!par3EntityPlayer.canPlayerEdit(tileX, tileY, tileZ, par4MovingObjectPosition.sideHit, par1ItemStack)){
					return par1ItemStack;
				}
				
				if(!par2World.isAirBlock(tileX, tileY, tileZ) && par2World.getBlockMaterial(tileX, tileY, tileZ).isSolid()){
					return par1ItemStack;
				}
				
				par2World.setBlockAndMetadataWithNotify(tileX, tileY, tileZ, blockID, blockMeta);
				
				if(par3EntityPlayer.capabilities.isCreativeMode){
					return par1ItemStack;
				}
				
				return new ItemStack(Item.bucketEmpty);
			}
		}
		
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer, par4MovingObjectPosition);
	}

}
