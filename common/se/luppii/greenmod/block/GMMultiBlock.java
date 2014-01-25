package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.gui.GMCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

public class GMMultiBlock extends Block {

	protected String[] names;
	private Icon[] icons;
	
	public GMMultiBlock(int par1, Material par2Material, String[] par3String) {
		super(par1, par2Material);
		this.names = par3String;
		this.icons = new Icon[par3String.length];
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
	@Override
	public int getLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		return 0;
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
