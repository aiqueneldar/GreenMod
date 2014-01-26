package se.luppii.greenmod.block;

import java.util.Random;
import java.util.logging.Level;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class GMBlockCropCotton extends BlockCrops {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public GMBlockCropCotton(int par1) {
		super(par1);
		this.setUnlocalizedName("greenmod.block.crop.cotton");
	}
	
	@Override
	protected int getSeedItem() {
		// Seed item ID.
		return GreenMod.itemSeed.itemID;
	}
	
	@Override
    protected int getCropItem() {
		// The item to drop when harvesting.
        return GreenMod.itemMisc.itemID;
    }
	
	@Override
	public int damageDropped(int par1) {
		return 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if (par2 < 0 || par2 > 7) {
			par2 = 7;
		}
		return this.icons[par2];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons = new Icon[8];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "_stage_" + i);
		}
	}
	
}
