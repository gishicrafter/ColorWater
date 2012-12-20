package colorwater;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGeneric extends Block {
	public SubBlockInfo[] subBlockList = new SubBlockInfo[16];
	
	public BlockGeneric(int id, int defaultIcon, Material material)
	{
		super(id, defaultIcon, material);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		if(subBlockList[par1] != null ){
			return subBlockList[par1].idDropItem;
		}else{
			return this.blockID;
		}
	}
	
	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
	{
		int result = par2Random.nextInt(par1 + 2) - 1;
		if(result < 0) result = 0;
		return result;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if(subBlockList[meta] != null && (subBlockList[meta].idDropItem >= Block.blocksList.length || Block.blocksList[subBlockList[meta].idDropItem] == null)){
			return subBlockList[meta].quantityDropItem * (1 + quantityDroppedWithBonus(fortune, random));
		}else{
			return 1;
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		
		if(subBlockList[par5] != null){
			int xp = MathHelper.getRandomIntegerInRange(par1World.rand, subBlockList[par5].xpMin, subBlockList[par5].xpMax);
			this.dropXpOnBlockBreak(par1World, par2, par3, par4, xp);
		}
	}
	
	@Override
	public int damageDropped(int par1)
	{
		if(subBlockList[par1] != null){
			return subBlockList[par1].damageDropItem;
		}else{
			return par1;
		}
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		if(subBlockList[par2] != null){
			return subBlockList[par2].textureIndex;
		}else{
			return this.getBlockTextureFromSide(par1);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderColor(int par1)
	{
		if(subBlockList[par1] != null){
			return subBlockList[par1].multiplyColor;
		}else{
			return getBlockColor();
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		return getRenderColor(var5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for(int i=0; i<16; ++i){
			if(this.subBlockList[i] != null){
				par3List.add(this.subBlockList[i].getItemStack(1));
			}
		}
	}
}
