package colorwater;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public interface IUseItemHandler {
	boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ);
	boolean itemInteractionForEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving);
	boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10);
	ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, MovingObjectPosition par4MovingObjectPosition);
}
