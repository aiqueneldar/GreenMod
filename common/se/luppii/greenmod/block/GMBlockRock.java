package se.luppii.greenmod.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.gui.GMCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

public class GMBlockRock extends GMMultiBlock {

	private static String[] names = new String[] { "basalt", "marble", "basaltcobblestone", "marblecobblestone", "basaltbrick", "marblebrick" };
	
	public GMBlockRock(int par1) {
		super(par1, Material.rock, names);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("greenmod.block.rock");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 1);
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1 < 2 ? par1 + 2 : par1;
	}

}
