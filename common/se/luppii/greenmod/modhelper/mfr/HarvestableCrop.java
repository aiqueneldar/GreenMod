package se.luppii.greenmod.modhelper.mfr;

import java.util.Map;

import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;

public class HarvestableCrop extends Harvestable {

	private int meta;
	
	// par1 == blockID; par2 == blockMeta
	public HarvestableCrop(int par1, int par2) {
				
		super(par1, HarvestType.Normal);
		this.meta = par2;
	}
	
	@Override
	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		
		return world.getBlockMetadata(x, y, z) >= this.meta;
	}

}
