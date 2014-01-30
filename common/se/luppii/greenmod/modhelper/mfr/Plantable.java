package se.luppii.greenmod.modhelper.mfr;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class Plantable implements IFactoryPlantable {

	protected int seedID;
	protected int plantBlockID;
	
	public Plantable(int par1, int par2) {
		this.seedID = par1;
		this.plantBlockID = par2;	
	}
	
	@Override
	public int getSeedId() {
		return this.seedID;
	}

	@Override
	public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) {
		return stack.itemID != this.seedID ? -1 : this.plantBlockID;
	}

	@Override
	public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) {
		return stack.getItemDamage();
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
		
		int groundID = world.getBlockId(x, y - 1, z);
		if (!world.isAirBlock(x, y, z)) {
			return false;
		}
		return
                (Block.blocksList[this.plantBlockID].canPlaceBlockAt(world, x, y, z) && Block.blocksList[this.plantBlockID].canBlockStay(world, x, y, z)) ||
                (Block.blocksList[this.plantBlockID] instanceof IPlantable && Block.blocksList[groundID] != null &&
                Block.blocksList[groundID].canSustainPlant(world, x, y, z, ForgeDirection.UP, ((IPlantable)Block.blocksList[this.plantBlockID])));
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) {
		return;
		
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack) {
		return;
		
	}

}
