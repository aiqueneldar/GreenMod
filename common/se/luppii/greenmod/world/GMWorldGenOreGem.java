package se.luppii.greenmod.world;

import java.util.Random;

import se.luppii.greenmod.GreenMod;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GMWorldGenOreGem implements IWorldGenerator {
	
	private static int[] oreGemRarity = GreenMod.oreGemRarity;

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
			for (int k = 0; k < oreGemRarity[0]; k++) {
				int oreGemX = x + rand.nextInt(16);
				int oreGemY = 8 + rand.nextInt(37);
				int oreGemZ = z + rand.nextInt(16);
				
				(new WorldGenMinable(GreenMod.oreGemBlock.blockID, i, oreGemRarity[1] + rand.nextInt(oreGemRarity[2]), Block.stone.blockID)).generate(world, rand, oreGemX, oreGemY, oreGemZ);
			}
		}
	}
		
	private void generateEnd(World world, Random random, int x, int z) {
		
	}

}
