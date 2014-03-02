package se.luppii.greenmod.modhelper.mfr;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class Harvestable implements IFactoryHarvestable {

	private int blockID;
	private HarvestType harvestType;
	
	public Harvestable(int par1, HarvestType par2HarvestType) {
		this.blockID = par1;
		this.harvestType = par2HarvestType;
	}
	
	@Override
	public int getPlantId() {
		return this.blockID;
	}

	@Override
	public HarvestType getHarvestType() {
		return this.harvestType;
	}

	@Override
	public boolean breakBlock() {
		return true;
	}

	@Override
	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return true;
	}

	@Override
	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
		return Block.blocksList[this.blockID].getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	}

	@Override
	public void preHarvest(World world, int x, int y, int z) {
		
	}

	@Override
	public void postHarvest(World world, int x, int y, int z) {
		
	}

}
