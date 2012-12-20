package colorwater;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockGel extends BlockGeneric {
	
	public BlockGel(int id, int defaultIcon, Material material)
	{
		super(id, defaultIcon, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5)
	{
		int var6 = par1iBlockAccess.getBlockId(par2, par3, par4);
		int var7 = 1 - par5;
		return var6 == this.blockID ? false : super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, var7);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return ForgeHooks.canHarvestBlock(Block.blockSnow, player, 0);
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
	{
		return 0;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if(subBlockList[meta] != null && (subBlockList[meta].idDropItem >= Block.blocksList.length || Block.blocksList[subBlockList[meta].idDropItem] == null)){
			return 1 + random.nextInt(subBlockList[meta].quantityDropItem);
		}else{
			return 1;
		}
	}
	
}
