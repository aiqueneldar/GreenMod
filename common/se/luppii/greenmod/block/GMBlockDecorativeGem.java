package se.luppii.greenmod.block;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.gui.GMCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

public class GMBlockDecorativeGem extends GMMultiBlock {
	
	private static String[] names = new String[] { "ruby", "sapphire" };
	
	public GMBlockDecorativeGem(int par1) {
		
		super(par1, Material.iron, names);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName("greenmod.block.decorative.gem");
		MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
	}
	
}
