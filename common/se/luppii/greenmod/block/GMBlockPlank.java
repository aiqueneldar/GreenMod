package se.luppii.greenmod.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

public class GMBlockPlank extends GMMultiBlock {

	private static String[] names = new String[] { "orange", "coconut" };
	
	public GMBlockPlank(int par1) {
		
		super(par1, Material.wood, names);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("greenmod.block.tree.plank");
	}

}
