package se.luppii.greenmod.world;

import java.util.Random;
import java.util.logging.Level;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GMWorldGenRock implements IWorldGenerator {
	
	private int blockId;
	private int meta;
	
	public GMWorldGenRock(Block par1Block, boolean par2Boolean, boolean par3Boolean) {
		this.blockId = par1Block.blockID;
		//this.meta = par2;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId) {
			case -1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 0:
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		}
	}
	
	private void generateNether(World world, Random random, int x, int z) {
		
	}
	
	private void generateSurface(World world, Random rand, int x, int z) {
		for (int i = 0; i < 2; i++) {
			if (rand.nextInt(100) < 25) {
				int rockX = x + rand.nextInt(16);
				int rockY = rand.nextInt(80);
				int rockZ = z + rand.nextInt(16);
				
				(new WorldGenMinable(blockId, i, 40 + rand.nextInt(30), Block.stone.blockID)).generate(world, rand, rockX, rockY, rockZ);
			}
		}
	}
		
	private void generateEnd(World world, Random random, int x, int z) {
		
	}
}
