package se.luppii.greenmod.lib;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class GMConfig {
	
	// Block
	public static int blockCropCottonID;
	public static int blockOreGemID;
	public static int blockDecorativeGemID;
	public static int blockRockID;
	public static int blockTreeLeavesID;
	public static int blockTreeSaplingID;
	public static int blockTreeWoodID;
	
	// General
	public static boolean generateBasalt;
	public static boolean generateMarble;
	public static boolean generateOre;
	public static boolean generateFruitTrees;
	public static boolean enableRecipeGemTool;
	public static boolean enableSheepDrop;
	public static boolean enableVanillaGemTool;
	public static int gemDurability;
	public static int[] OreGemRarity;
	public static int[] diamondPruningSawAoe;
	public static int[] gemPruningSawAoe;
	public static int[] ironPruningSawAoe;
	
	// Item
	public static int itemFoodMiscID;
	public static int itemFoodMuttonID;
	public static int itemGemID;
	public static int itemMiscID;
	public static int itemSawBladeID;
	public static int itemSeedID;
	public static int itemIronSawBladeID;
	public static int itemDiamondSawBladeID;
	
	// Item // Tool // Axe
	public static int itemRubyAxeID;
	public static int itemSapphireAxeID;
	
	// Item // Tool // Hoe
	public static int itemRubyHoeID;
	public static int itemSapphireHoeID;
	
	// Item // Tool // Pickaxe
	public static int itemRubyPickaxeID;
	public static int itemSapphirePickaxeID;
	
	// Item // Tool // Pruning Saw
	public static int itemDiamondPruningSawID;
	public static int itemIronPruningSawID;
	public static int itemRubyPruningSawID;
	public static int itemSapphirePruningSawID;
	
	// Item // Tool // Shovel
	public static int itemRubyShovelID;
	public static int itemSapphireShovelID;
	
	// Item // Weapon // Sword
	public static int itemRubySwordID;
	public static int itemSapphireSwordID;
	
	public static void loadConfig(FMLPreInitializationEvent e) {
		
		Configuration config = new Configuration(e.getSuggestedConfigurationFile()); // Gets config file
		try {
			config.load();
			
			// Block
			blockCropCottonID = config.getBlock("ID.Crop.Cotton", 704).getInt();
			blockDecorativeGemID = config.getBlock("ID.Decorative.Gem", 702).getInt();
			blockOreGemID = config.getBlock("ID.Ore.Gem", 701).getInt();
			blockRockID = config.getBlock("ID.Rock", 703).getInt();
			blockTreeLeavesID = config.getBlock("ID.Tree.Leaves", 706).getInt();
			blockTreeSaplingID = config.getBlock("ID.Tree.Sapling", 707).getInt();
			blockTreeWoodID = config.getBlock("ID.Tree.Log", 705).getInt();
			
			// General // Durability
			gemDurability = config.get(config.CATEGORY_GENERAL, "Ore.Gem.Durability", 384).getInt();
			
			// General // World gen
			generateOre = config.get(config.CATEGORY_GENERAL, "Generate gem ore deposits", true).getBoolean(true);
			generateMarble = config.get(config.CATEGORY_GENERAL, "Generate marble deposits", true).getBoolean(true);
			generateBasalt = config.get(config.CATEGORY_GENERAL, "Generate basalt deposits", true).getBoolean(true);
			generateFruitTrees = config.get(config.CATEGORY_GENERAL, "Generate fruit trees", true).getBoolean(true);
			enableRecipeGemTool = config.get(config.CATEGORY_GENERAL, "Enable vanilla gem tool recipes", true).getBoolean(true);
			enableSheepDrop = config.get(config.CATEGORY_GENERAL, "Enable sheep mutton drop", true).getBoolean(true);
			enableVanillaGemTool = config.get(config.CATEGORY_GENERAL, "Enable vanilla gem tool", true).getBoolean(true);
			config.get(config.CATEGORY_GENERAL, "Enable vanilla gem tool", true)
				.comment = "Game will complain about missing ID's if enabled once before disabling.";
			
			OreGemRarity = config.get(config.CATEGORY_GENERAL, "Ore.Gem.Rarity", new int[] { 4, 3, 3 }).getIntList();
			config.get(config.CATEGORY_GENERAL, "Ore.Gem.Rarity", new int[] { 4, 3, 3 })
				.comment = "No. of veins in a chunk. Lowest no. of ores in a vein. RNG for more ores, 3 gives 0-2 extra.";
			
			// General // Tool AoE
			diamondPruningSawAoe = config.get(config.CATEGORY_GENERAL, "Tool.Saw.Diamond.Aoe", new int[] {1, 2, 1}).getIntList();
			config.get(config.CATEGORY_GENERAL, "Tool.Saw.Diamond.Aoe", new int[] {1, 2, 1})
				.comment = "Array with aoe in X-Y-Z axis.";
			gemPruningSawAoe = config.get(config.CATEGORY_GENERAL, "Tool.Saw.Gem.Aoe", new int[] {1, 2, 1}).getIntList();
			ironPruningSawAoe = config.get(config.CATEGORY_GENERAL, "Tool.Saw.Iron.Aoe", new int[] {1, 1, 1}).getIntList();
			
			// Item
			itemFoodMiscID = config.getItem("ID.Item.Food.Misc", 7325).getInt();
			itemFoodMuttonID = config.getItem("ID.Item.Food.Mutton", 7324).getInt();
			itemGemID = config.getItem("ID.Item.Gem", 7320).getInt();
			itemMiscID = config.getItem("ID.Item.Misc", 7323).getInt();
			itemSawBladeID = config.getItem("ID.Item.Sawblade", 7321).getInt();
			itemSeedID = config.getItem("ID.Item.Seed", 7322).getInt();
			
			// Item // Tool // Axe
			itemRubyAxeID = config.getItem("ID.Tool.Axe.Ruby", 7330).getInt();
			itemSapphireAxeID = config.getItem("ID.Tool.Axe.Sapphire", 7331).getInt();
			
			// Item // Tool // Hoe
			itemRubyHoeID = config.getItem("ID.Tool.Hoe.Ruby", 7332).getInt();
			itemSapphireHoeID = config.getItem("ID.Tool.Hoe.Sapphire", 7333).getInt();
			
			// Item // Tool // Pickaxe
			itemRubyPickaxeID = config.getItem("ID.Tool.Pickaxe.Ruby", 7334).getInt();
			itemSapphirePickaxeID = config.getItem("ID.Tool.Pickaxe.Sapphire", 7335).getInt();
			
			// Item // Tool // Pruning Saw
			itemDiamondPruningSawID = config.getItem("ID.Tool.Saw.Diamond", 7336).getInt();
			itemIronPruningSawID = config.getItem("ID.Tool.Saw.Iron", 7337).getInt();
			itemRubyPruningSawID = config.getItem("ID.Tool.Saw.Ruby", 7338).getInt();
			itemSapphirePruningSawID = config.getItem("ID.Tool.Saw.Sapphire", 7339).getInt();
			
			// Item // Tool // Shovel
			itemRubyShovelID = config.getItem("ID.Tool.Shovel.Ruby", 7340).getInt();
			itemSapphireShovelID = config.getItem("ID.Tool.Shovel.Sapphire", 7341).getInt();
			
			// Item // Weapon // Sword
			itemRubySwordID = config.getItem("ID.Weapon.Sword.Ruby", 7342).getInt();
			itemSapphireSwordID = config.getItem("ID.Weapon.Sword.Sapphire", 7343).getInt();
			
			config.save();
		}
		catch (Exception err) {
			err.printStackTrace();
		}
		
	}
	
}
