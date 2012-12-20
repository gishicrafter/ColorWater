package colorwater;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class AbstractUseItemHandler implements IUseItemHandler {
	
	protected SubItemInfo subItem;
	
	public AbstractUseItemHandler(SubItemInfo subItem)
	{
		this.subItem = subItem;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving) {
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, MovingObjectPosition par4MovingObjectPosition) {
		return par1ItemStack;
	}

}
