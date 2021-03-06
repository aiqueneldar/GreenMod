package se.luppii.greenmod.world;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GMWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		
		switch(world.provider.dimensionId) {
		
			case -1:
				generateNether(world, random, x, z);
				break;
			case 0:
				generateSurface(world, random, x, z);
				break;
			case 1:
				generateEnd(world, random, x, z);
				break;
		}
	}

	private void generateEnd(World world, Random random, int x, int z) {
		
	}
	
	private void generateNether(World world, Random random, int x, int z) {
		
	}

	private void generateSurface(World world, Random random, int x, int z) {

		// Generate Fruit Trees
		if (GreenMod.generateFruitTree) {
			
			String biome = world.getBiomeGenForCoords(x, z).biomeName;
			int rand = random.nextInt(100);
			
			if (rand < 5 && generateInBiome(biome)) {
				
				if ((biome == "Plains" && rand == 1) || biome != "Plains") {
					
					new GMWorldGenTrees(true).generate(world, random, x, 1 + random.nextInt(2), z);
				}
			}
			if (rand > 5 && rand < 11) {
				
				if ((biome == "Plains" && rand == 10) || biome != "Plains") {
					
					new GMWorldGenTrees(true, 5, 1, 1, false).generate(world, random, x, 1 + random.nextInt(2), z);
				}
			}
		}
		
		// Generate Marble and Basalt
		if (GreenMod.generateBasalt || GreenMod.generateMarble) {
			
			int blockId = GreenMod.blockRock.blockID;
			int blockToReplace = Block.stone.blockID;
			
			for (int i = 0; i < 2; i++) {
				
				if (random.nextInt(100) < 25) {
					
					int rockX = x + random.nextInt(16);
					int rockY = random.nextInt(80);
					int rockZ = z + random.nextInt(16);
					
					if (i == 0 && GreenMod.generateBasalt) {
						
						(new WorldGenMinable(blockId, i, 40 + random.nextInt(30), blockToReplace)).generate(world, random, rockX, rockY, rockZ);
					}
					else if (i == 1 && GreenMod.generateMarble) {
						
						(new WorldGenMinable(blockId, i, 40 + random.nextInt(30), blockToReplace)).generate(world, random, rockX, rockY, rockZ);
					}
				}
			}
		}
		
		// Generate Ruby and Sapphire
		if (GreenMod.generateOre) {
			
			int blockId = GreenMod.oreGemBlock.blockID;
			int blockToReplace = Block.stone.blockID;
			int[] rarity = GreenMod.oreGemRarity;
			
			for (int i = 0; i < 2; i++) {
				
				for (int k = 0; k < rarity[0]; k++) {
					
					int oreGemX = x + random.nextInt(16);
					int oreGemY = 8 + random.nextInt(37);
					int oreGemZ = z + random.nextInt(16);
					
					(new WorldGenMinable(blockId, i, rarity[1] + random.nextInt(rarity[2]), blockToReplace)).generate(world, random, oreGemX, oreGemY, oreGemZ);
				}
			}
		}
	}
	
	private static boolean generateInBiome(String str) {
		
		String[] disallowedBiomes = { "Swampland" };
		return (!Arrays.asList(disallowedBiomes).contains(str));
	}
}
