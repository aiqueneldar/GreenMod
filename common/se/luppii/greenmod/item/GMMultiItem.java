package se.luppii.greenmod.item;

import java.util.List;

import se.luppii.greenmod.gui.GMCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GMMultiItem extends GMItem {
	
	protected String[] names;
	private Icon[] icons;

	public GMMultiItem(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(GMCreativeTab.tab);

	}
	
	protected void setNames(String... par1Str) {
		names = par1Str;
		icons = new Icon[names.length];
		
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
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damageValue) {
		return icons[Math.min(damageValue, icons.length - 1)];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[names.length];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i]);
		}
	}

}
