package se.luppii.greenmod.item.weapon;

import se.luppii.greenmod.gui.GMCreativeTab;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class GMWeaponSword extends ItemSword {

	public GMWeaponSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		super.setUnlocalizedName(par1Str);
		GameRegistry.registerItem(this, getUnlocalizedName());
		return this;
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName());
	}
	
}