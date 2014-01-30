package se.luppii.greenmod.modhelper.mfr;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;

public class FertilizableCrop extends Fertilizable {

	private final int meta;
	
	public FertilizableCrop(int par1, int par2) {
		super(par1, FertilizerType.GrowPlant);
		this.meta = par2;
	}
	
	@Override
	protected boolean canFertilize(int metadata) {
		return metadata < this.meta;
	}

	@Override
	public boolean fertilize(World world, Random rand, int x, int y, int z,	FertilizerType fertilizerType) {
		((BlockCrops)Block.crops).fertilize(world, x, y, z);
		return true;
	}
}
