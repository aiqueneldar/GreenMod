package se.luppii.greenmod.block;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GMMultiItemBlock extends ItemBlock {

	protected String[] names;
	
	public GMMultiItemBlock(int par1) {
		super(par1);
	}
	
	protected void setNames(String[] par1String) {
		names = par1String;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return getUnlocalizedName() + "." + names[Math.min(par1ItemStack.getItemDamage(), names.length -1)];
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return Block.blocksList[getBlockID()].getIcon(2, par1);
	}
	
	public void getSubItems(int par1, List<ItemStack> par2List) {
		for (int i = 0; i < names.length; i++) {
			par2List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		this.getSubItems(par1, par3List);
	}

}
