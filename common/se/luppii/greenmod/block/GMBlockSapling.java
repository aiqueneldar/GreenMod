package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.gui.GMCreativeTab;
import se.luppii.greenmod.world.GMWorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GMBlockSapling extends BlockSapling {

	private String[] names = new String[] { "orange", "coconut" };
	private Icon[] icons = new Icon[names.length];
	
	public GMBlockSapling(int par1) {
		super(par1);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.setUnlocalizedName("greenmod.block.tree.sapling");
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return GreenMod.blockSapling.blockID;
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
	@Override
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
		
		if (par1World.isRemote) return;
		
		int meta = par1World.getBlockMetadata(par2, par3, par4) & 3;
		Object tree = null;
		par1World.setBlockToAir(par2, par3, par4);
		
		if (meta == 0) {
			tree = new GMWorldGenTrees(true);
			if (!((GMWorldGenTrees)tree).growOrangeTree(par1World, par5Random, par2, par3, par4)) {
				par1World.setBlock(par2, par3, par4, this.blockID, meta, 4);
			}
		}
		else if (meta == 1) {
			tree = new GMWorldGenTrees(true, 5, 1, 1, false);
			if (!((GMWorldGenTrees)tree).growPalmTree(par1World, par5Random, par2, par3, par4)) {
				par1World.setBlock(par2, par3, par4, this.blockID, meta, 4);
			}
		}
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
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i]);
		}
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[Math.min(par2, icons.length - 1)];
	}

}
