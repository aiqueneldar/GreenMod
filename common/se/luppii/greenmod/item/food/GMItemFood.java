package se.luppii.greenmod.item.food;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.gui.GMCreativeTab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class GMItemFood extends ItemFood {

	public GMItemFood(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);
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
