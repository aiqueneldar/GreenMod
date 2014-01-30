package se.luppii.greenmod.modhelper.mfr;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PlantableCrop extends Plantable {

	public PlantableCrop(int par1, int par2) {
		super(par1, par2);
	}
	
	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
		int groundID = world.getBlockId(x, y - 1, z);
			if(!world.isAirBlock(x, y, z)) {
				return false;
			}
			return
					(groundID == Block.dirt.blockID ||
					groundID == Block.grass.blockID ||
					groundID == Block.tilledField.blockID ||
					(Block.blocksList[this.plantBlockID] instanceof IPlantable && Block.blocksList[groundID] != null &&
					Block.blocksList[groundID].canSustainPlant(world, x, y, z, ForgeDirection.UP, ((IPlantable)Block.blocksList[this.plantBlockID]))));
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) {
		int groundID = world.getBlockId(x, y - 1, z);
			if(groundID == Block.dirt.blockID || groundID == Block.grass.blockID) {
				world.setBlock(x, y - 1, z, Block.tilledField.blockID);
			}
	}

}
