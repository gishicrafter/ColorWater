package colorwater;

import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGenericUseable extends ItemGeneric {
	public HashMap<Integer, IUseItemHandler> useItemHandlerList = new HashMap<Integer, IUseItemHandler>();
	
	public ItemGenericUseable(int id)
	{
		super(id);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10)
	{
		if(useItemHandlerList.containsKey(par1ItemStack.getItemDamage())){
			return useItemHandlerList.get(par1ItemStack.getItemDamage()).onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5,
				par6, par7, par8, par9, par10);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer)
	{
		if(useItemHandlerList.containsKey(par1ItemStack.getItemDamage())){
			MovingObjectPosition movingObjectPosition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
			return useItemHandlerList.get(par1ItemStack.getItemDamage()).onItemRightClick(par1ItemStack, par2World, par3EntityPlayer, movingObjectPosition);
		}
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving)
	{
		if(useItemHandlerList.containsKey(par1ItemStack.getItemDamage())){
			return useItemHandlerList.get(par1ItemStack.getItemDamage()).itemInteractionForEntity(par1ItemStack, par2EntityLiving);
		}
		return super.itemInteractionForEntity(par1ItemStack, par2EntityLiving);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ)
	{
		if(useItemHandlerList.containsKey(stack.getItemDamage())){
			return useItemHandlerList.get(stack.getItemDamage()).onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		}
		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY,
				hitZ);
	}
}
