package se.luppii.greenmod.world;

import java.util.Random;
import java.util.logging.Level;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.RotationHelper;

public class GMWorldGenTrees extends WorldGenerator {

	/** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;
    
    public GMWorldGenTrees(boolean par1) {
    	this(par1, 5, 0, 0, false);
    }
    
	public GMWorldGenTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
		super(par1);
		this.minTreeHeight = par2;
		this.metaWood = par3;
		this.metaLeaves = par4;
		this.vinesGrow = par5;
    }
	
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		
		if (world.getBiomeGenForCoords(i, k).getFloatTemperature() < 0.8F ||
					world.getBiomeGenForCoords(i, k).getFloatTemperature() >= 2.0F) {
			return false;
		}
		
		int tries = j;
		
		for (int l = 0; l <= tries; l++) {
			int y = world.getActualHeight() - 1;
			while (world.isAirBlock(i, y, k) && y > 0) {
				y--;
			}
			
			if (!growOrangeTree(world, random, i, y + 1, k)) {
				tries--;
			}
			
			i += random.nextInt(16) - 8;
			k += random.nextInt(16) - 8;
		}
		return true;
	}
	
	public boolean growOrangeTree(World world, Random random, int x, int y, int z) {
		
		int treeHeight = this.minTreeHeight + random.nextInt(2);
		int worldHeight = world.getHeight();
		Block block;
		
		if (y >= 1 && y + treeHeight + 1 <= worldHeight) {
			
			int blockId = world.getBlockId(x, y - 1, z);
			block = Block.blocksList[blockId];
			
			int xOffset;
			int yOffset;
			int zOffset;
			
			if (block != null && block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, ((BlockSapling)GreenMod.blockSapling)) &&
					y < worldHeight - treeHeight - 1) {
				
				for (yOffset = y; yOffset <= y + treeHeight + 1; ++yOffset) {
					
					byte radius = 1;
					
					if (yOffset == y) {
						radius = 0;
					}
					
					if (yOffset >= y + treeHeight + 1 - 2) {
						radius = 2;
					}
					
					if (yOffset >= 0 && yOffset < worldHeight) {
						
						for (xOffset = x - radius; xOffset <= x + radius; ++xOffset) {
							
							for (zOffset = z - radius; zOffset <= z + radius; ++zOffset) {
								
								blockId = world.getBlockId(xOffset, yOffset, zOffset);
								block = Block.blocksList[blockId];
								
								if (block != null && !block.isLeaves(world, xOffset, yOffset, zOffset) &&
										!block.isAirBlock(world, xOffset, yOffset, zOffset) &&
										!block.canBeReplacedByLeaves(world, xOffset, yOffset, zOffset)) {
									
									return false;
								}
							}
						}
					}
					else {
						return false;
					}
				}
				
				blockId = world.getBlockId(x, y - 1, z);
				block = Block.blocksList[blockId];
				
				if (block == null) {
					return false;
				}
				block.onPlantGrow(world, x, y - 1, z, x, y, z);
				
				for (yOffset = y - 3 + treeHeight; yOffset <= y + treeHeight; ++yOffset) {
					int i = yOffset - (y + treeHeight);
					int center = 1 - i / 2;
					
					for (xOffset = x - center; xOffset <= x + center; ++xOffset) {
						
						int xPos = xOffset - x;
						
						for (zOffset = z - center; zOffset <= z + center; ++zOffset) {
							
							int zPos = zOffset - z;
							
							blockId = world.getBlockId(xOffset, yOffset, zOffset);
							block = Block.blocksList[blockId];
							
							if ((Math.abs(xPos) != center || Math.abs(zPos) != center || random.nextInt(2) != 0 && i != 0) &&
									(block == null || block.isLeaves(world, xOffset, yOffset, zOffset) ||
									block.isAirBlock(world, xOffset, yOffset, zOffset) ||
									block.canBeReplacedByLeaves(world, xOffset, yOffset, zOffset))) {
								
								this.setBlockAndMetadata(world, xOffset, yOffset, zOffset, GreenMod.blockLeaves.blockID, this.metaLeaves);
							}
						}
					}
				}
				
				for (yOffset = 0; yOffset < treeHeight; ++yOffset) {
					
					blockId = world.getBlockId(x, y + yOffset, z);
					block = Block.blocksList[blockId];
					
					if (block == null || block.isAirBlock(world, x, y + yOffset, z) ||
							block.isLeaves(world, x, y + yOffset, z) || 
							block.isBlockReplaceable(world, x, y + yOffset, z)) {
						
						this.setBlockAndMetadata(world, x, y + yOffset, z, GreenMod.blockWood.blockID, this.metaLeaves);
					}
				}
				
				return true;
			}
		}
		return false;
	}
	
	public boolean growPalmTree(World world, Random random, int x, int y, int z) {
		
		int treeHeight = this.minTreeHeight + random.nextInt(2);
		int worldHeight = world.getHeight();
		Block block;
		
		if (y >= 1 && y + treeHeight + 1 <= worldHeight) {
			
			int blockId = world.getBlockId(x, y - 1, z);
			block = Block.blocksList[blockId];
			
			int xOffset;
			int yOffset;
			int zOffset;

			if (block != null && block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, ((BlockSapling)GreenMod.blockSapling)) &&
					y < worldHeight - treeHeight - 1) {
				
				for (yOffset = y; yOffset <= y + treeHeight + 1; ++yOffset) {
					
					byte radius = 1;
						
					if (yOffset == y) {
						radius = 0;
					}
					
					if (yOffset >= y + treeHeight + 1 - 2) {
						radius = 5;
					}
				
					if (yOffset >= 0 && yOffset < worldHeight) {
					
						for (xOffset = x - radius; xOffset <= x + radius; ++xOffset) {
						
							for (zOffset = z - radius; zOffset <= z + radius; ++zOffset) {
							
								blockId = world.getBlockId(xOffset, yOffset, zOffset);
								block = Block.blocksList[blockId];
							
								if (block != null && !block.isLeaves(world, xOffset, yOffset, zOffset) &&
										!block.isAirBlock(world, xOffset, yOffset, zOffset) &&
										!block.canBeReplacedByLeaves(world, xOffset, yOffset, zOffset)) {
								
									return false;
								}
							}
						}
					}
					else {
						return false;
					}
				}
				
				blockId = world.getBlockId(x, y - 1, z);
				block = Block.blocksList[blockId];
				
				if (block == null) {
					return false;
				}
				block.onPlantGrow(world, x, y - 1, z, x, y, z);
				
				for (yOffset = y - 2 + treeHeight; yOffset <= y + treeHeight; ++yOffset) {
					int i = yOffset - (y + treeHeight);
					int center = 4 - i / 2;
					
					for (xOffset = x - center; xOffset <= x + center; ++xOffset) {
						
						int xPos = xOffset - x;
						
						for (zOffset = z - center; zOffset <= z + center; ++zOffset) {
							
							int zPos = zOffset - z;
							
							blockId = world.getBlockId(xOffset, yOffset, zOffset);
							block = Block.blocksList[blockId];
							
							
							if (block == null || block.isLeaves(world, xOffset, yOffset, zOffset) ||
									block.isAirBlock(world, xOffset, yOffset, zOffset) ||
									block.canBeReplacedByLeaves(world, xOffset, yOffset, zOffset)) {
								
								// First layer
								if (i == -2 && (Math.abs(xPos) < center - 4 || Math.abs(xPos) > center + 4 || Math.abs(zPos) < center - 4 || Math.abs(zPos) > center + 4)) {
									
									if ((Math.abs(xPos) < -2 || Math.abs(xPos) > 2) || (Math.abs(zPos) < -2 || Math.abs(zPos) > 2)) {
										
										this.setBlockAndMetadata(world, xOffset, yOffset, zOffset, GreenMod.blockLeaves.blockID, this.metaLeaves);
									}
								}
								
								// Second layer
								if (i == -1 && (Math.abs(xPos) < center - 2 || Math.abs(xPos) > center + 2 || Math.abs(zPos) < center - 2 || Math.abs(zPos) > center + 2)) {
									
									if (!((Math.abs(xPos) == -4 || Math.abs(xPos) == 4) && (Math.abs(zPos) == -1 || Math.abs(zPos) == 1)) &&
											 !((Math.abs(xPos) == -1 || Math.abs(xPos) == 1) && (Math.abs(zPos) == -4 || Math.abs(zPos) == 4))) {
										this.setBlockAndMetadata(world, xOffset, yOffset, zOffset, GreenMod.blockLeaves.blockID, this.metaLeaves);
									}
								}
								
								// Last layer
								if (i == 0 && (Math.abs(xPos) < center - 3 || Math.abs(xPos) > center + 3 || Math.abs(zPos) < center - 3 || Math.abs(zPos) > center + 3)) {
									
									if (!((Math.abs(xPos) < -2 || Math.abs(xPos) > 2) || (Math.abs(zPos) < -2 || Math.abs(zPos) > 2))) {
										this.setBlockAndMetadata(world, xOffset, yOffset, zOffset, GreenMod.blockLeaves.blockID, this.metaLeaves);
									}
								}
							}
						}
					}
				}
				for (yOffset = 0; yOffset < treeHeight; ++yOffset) {
					
					blockId = world.getBlockId(x, y + yOffset, z);
					block = Block.blocksList[blockId];
					
					if (block == null || block.isAirBlock(world, x, y + yOffset, z) ||
							block.isLeaves(world, x, y + yOffset, z) || 
							block.isBlockReplaceable(world, x, y + yOffset, z)) {
						
						this.setBlockAndMetadata(world, x, y + yOffset, z, GreenMod.blockWood.blockID, this.metaLeaves);
						
						if (yOffset == treeHeight - 1) {
							
							this.placeWoodWithDirection(world, x - 1, y + yOffset, z, GreenMod.blockWood.blockID, this.metaLeaves, ForgeDirection.SOUTH);
							this.placeWoodWithDirection(world, x + 1, y + yOffset, z, GreenMod.blockWood.blockID, this.metaLeaves, ForgeDirection.NORTH);
							this.placeWoodWithDirection(world, x, y + yOffset, z - 1, GreenMod.blockWood.blockID, this.metaLeaves, ForgeDirection.EAST);
							this.placeWoodWithDirection(world, x, y + yOffset, z + 1, GreenMod.blockWood.blockID, this.metaLeaves, ForgeDirection.WEST);
						}
					}
				}
				
				return true;
			}		
		}
		return false;
	}
	
	private void placeWoodWithDirection(World world, int x, int y, int z, int blockId, int blockMeta, ForgeDirection dir) {
		
		Block block = Block.blocksList[blockId];
		
		this.setBlockAndMetadata(world, x, y, z, blockId, blockMeta);
		RotationHelper.rotateVanillaBlock(block, world, x, y, z, dir);
	}
}
