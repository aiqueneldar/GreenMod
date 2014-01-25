package se.luppii.greenmod.item;

import java.util.List;

import se.luppii.greenmod.gui.GMCreativeTab;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GMItem extends Item {
	
	protected EnumToolMaterial toolMaterial;
	
	private int _metaMax = 0;

	public GMItem(int par1) {
		super(par1);
		this.setCreativeTab(GMCreativeTab.tab);

	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		super.setUnlocalizedName(par1Str);
		GameRegistry.registerItem(this, getUnlocalizedName());
		return this;
		
	}
	
	protected void setMetaMax(int par1) {
		_metaMax = par1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName());
	}
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i <= _metaMax; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
}
