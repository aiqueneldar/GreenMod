package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.gui.GMCreativeTab;
import se.luppii.greenmod.lib.GMLogger;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GMBlockWood extends BlockLog {
	
	private String[] names = new String[] { "orange", "coconut" };
	private Icon[] iconWoodTop = new Icon[names.length];
	private Icon[] iconWoodSide = new Icon[names.length];
	
	public GMBlockWood(int par1) {
		super(par1);
		this.setHardness(2.0F);
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("greenmod.block.tree.log");
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int par1) {
		
		return iconWoodSide[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int par1) {
		
		return iconWoodTop[par1];
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
		
		for (int i = 0; i < iconWoodTop.length; i++) {
			iconWoodTop[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i] + ".top");
			iconWoodSide[i] = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName() + "." + names[i] + ".side");
		}
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		
		int logDirection = par2 & 12;
		int logIcon = par2 & 3;
		return logDirection == 0 && (par1 == 1 || par1 == 0) ? this.getEndIcon(logIcon) : (logDirection == 4 && (par1 == 5 || par1 == 4) ? this.getEndIcon(logIcon) : (logDirection == 8 && (par1 == 2 || par1 == 3) ? this.getEndIcon(logIcon) : this.getSideIcon(logIcon)));
	}

}
