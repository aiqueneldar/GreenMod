package se.luppii.greenmod.gui;

import se.luppii.greenmod.GreenMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GMCreativeTab extends CreativeTabs {
	
	public static final GMCreativeTab tab = new GMCreativeTab("Luppii's Green Mod");
	
	public GMCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(GreenMod.rubyPruningSaw);
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}
	
}
