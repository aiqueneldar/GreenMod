package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.gui.GMCreativeTab;
import se.luppii.greenmod.lib.GMConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GMBlock extends Block {

	private int _metaMax = 0;
	
	public GMBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public Block setUnlocalizedName(String par1Str) {
		super.setUnlocalizedName(par1Str);
		GameRegistry.registerBlock(this, getUnlocalizedName());
		return this;
		
	}
	
	protected void setMetaMax(int par1) {
		_metaMax = par1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName());
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i <= _metaMax; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}	
	
}
