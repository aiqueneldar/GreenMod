package se.luppii.greenmod.modhelper.mfr;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.block.GMBlockSapling;

public class FertilizablePlant extends Fertilizable {

	public FertilizablePlant(int par1) {
		super(par1, FertilizerType.GrowPlant);
	}
	
	@Override
	protected boolean canFertilize(int metadata) {
		return true;
	}

	@Override
	public boolean fertilize(World world, Random rand, int x, int y, int z,	FertilizerType fertilizerType) {
		((GMBlockSapling)GreenMod.blockSapling).growTree(world, x, y, z, world.rand);
		return true;
	}
}
