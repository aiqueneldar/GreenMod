package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.gui.GMCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GMBlockLeaves extends BlockLeaves {

	private String[] names = new String[] { "orange", "coconut" };
	private Icon[] iconLeavesOpaque = new Icon[names.length];
	private Icon[] iconLeavesTransparent = new Icon[names.length];
	
	public GMBlockLeaves(int par1) {
		
		super(par1);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundGrassFootstep);
		this.setUnlocalizedName("greenmod.block.tree.leaves");
		this.setCreativeTab(GMCreativeTab.tab);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		
		return GreenMod.blockSapling.blockID;
	}
	
	@Override
	public int damageDropped(int par1) {
		
		return par1 & 3;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		
		final int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		return getRenderColor(metadata);
	}
	
	@Override
	public int getRenderColor(int metadata) {
		
		return 0xffffff;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		
		if (!par1World.isRemote) {
			
			// Modifier for sapling drop.
			int modifier = 20;
			int meta = par5 & 3;
			
			// Support for fortune enchant.
			if (par7 > 0) {
				
				modifier -= 2 << par7;
                if (modifier < 10) {
                	
                	modifier = 10;
                }
            }
			
			// Sapling drop.
			if (par1World.rand.nextInt(modifier) == 0) {
				
				int id = this.idDropped(par5, par1World.rand, par7);
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(id, 1, this.damageDropped(meta)));
			}
			
			// Modifier for fruit drop.
			modifier = 75;
			
			if (par7 > 0) {
				
				modifier -= 10 << par7;
				if (modifier < 20) {
					
					modifier = 20;
				}
			}
			
			// Fruit drop.
			if (par1World.rand.nextInt(modifier) == 0) {
				
				int id = GreenMod.itemMisc.itemID;
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(id, 1, meta + 1));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		
		int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3;
		return Block.leaves.graphicsLevel ? iconLeavesTransparent[meta] : iconLeavesOpaque[meta];
	}
	
	@Override
	public boolean isOpaqueCube() {
		
		return !Block.leaves.graphicsLevel;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		
		return Block.leaves.graphicsLevel ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		
		for (int i = 0; i < names.length; i++) {
			
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
		for (int i = 0; i < iconLeavesOpaque.length; i++) {
			
			iconLeavesOpaque[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i] + ".opaque");
			iconLeavesTransparent[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i] + ".transparent");
		}
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		
		int leavesIcon = par2 & 3;
		return Block.leaves.graphicsLevel ? iconLeavesTransparent[leavesIcon] : iconLeavesOpaque[leavesIcon]; 
	}

}
