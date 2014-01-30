package se.luppii.greenmod.modhelper.mfr;

import java.util.Random;

import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;

public abstract class Fertilizable implements IFactoryFertilizable {

	protected final int fertilizableBlockID;
	protected final FertilizerType validFertilizer;
	
	public Fertilizable(int par1, FertilizerType fertilizerType) {
		this.fertilizableBlockID = par1;
		this.validFertilizer = fertilizerType;
	}
	
	@Override
	public int getFertilizableBlockId() {
		return this.fertilizableBlockID;
	}

	@Override
	public boolean canFertilizeBlock(World world, int x, int y, int z, FertilizerType fertilizerType) {
		return fertilizerType == this.validFertilizer && canFertilize(world.getBlockMetadata(x, y, z));
	}

	@Override
	public abstract boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType);

	protected abstract boolean canFertilize(int metadata);
	
}
