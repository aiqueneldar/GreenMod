package se.luppii.greenmod;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.SidedProxy;
import se.luppii.greenmod.block.GMBlockCropCotton;
import se.luppii.greenmod.block.GMBlockDecorativeGem;
import se.luppii.greenmod.block.GMBlockLeaves;
import se.luppii.greenmod.block.GMBlockOreGem;
import se.luppii.greenmod.block.GMBlockPlank;
import se.luppii.greenmod.block.GMBlockRock;
import se.luppii.greenmod.block.GMBlockSapling;
import se.luppii.greenmod.block.GMBlockWood;
import se.luppii.greenmod.block.GMMultiItemBlockDecorativeGem;
import se.luppii.greenmod.block.GMMultiItemBlockLeaves;
import se.luppii.greenmod.block.GMMultiItemBlockOreGem;
import se.luppii.greenmod.block.GMMultiItemBlockPlank;
import se.luppii.greenmod.block.GMMultiItemBlockRock;
import se.luppii.greenmod.block.GMMultiItemBlockSapling;
import se.luppii.greenmod.block.GMMultiItemBlockWood;
import se.luppii.greenmod.event.GMBonemealEvent;
import se.luppii.greenmod.event.GMSheepDropEvent;
import se.luppii.greenmod.event.GMSquidDropEvent;
import se.luppii.greenmod.handler.GMFoodCraftingHandler;
import se.luppii.greenmod.item.GMItemGem;
import se.luppii.greenmod.item.GMItemMisc;
import se.luppii.greenmod.item.GMItemSawBlade;
import se.luppii.greenmod.item.GMItemSeed;
import se.luppii.greenmod.item.food.GMItemFoodMisc;
import se.luppii.greenmod.item.food.GMItemFoodMutton;
import se.luppii.greenmod.item.tool.GMToolAxe;
import se.luppii.greenmod.item.tool.GMToolHoe;
import se.luppii.greenmod.item.tool.GMToolPickaxe;
import se.luppii.greenmod.item.tool.GMToolSaw;
import se.luppii.greenmod.item.tool.GMToolShovel;
import se.luppii.greenmod.item.weapon.GMWeaponSword;
import se.luppii.greenmod.lib.GMConfig;
import se.luppii.greenmod.lib.GMLogger;
import se.luppii.greenmod.lib.GMReferences;
import se.luppii.greenmod.proxy.CommonProxy;
import se.luppii.greenmod.world.GMWorldGen;
import se.luppii.greenmod.world.GMWorldGenOreGem;
import se.luppii.greenmod.world.GMWorldGenRock;

@Mod(modid=GMReferences.MOD_ID, name=GMReferences.MOD_NAME, version=GMReferences.MOD_VERSION)
@NetworkMod(clientSideRequired=true)
public class GreenMod {
	
	@Instance(value = "GreenMod")
	public static GreenMod instance;
	public static final String modString = GMReferences.MOD_STRING;
	
	// Block
	public static Block cropCottonBlock;
	public static Block oreGemBlock;
	public static Block decorativeGemBlock;
	public static Block blockRock;
	public static Block blockLeaves;
	public static Block blockPlank;
	public static Block blockSapling;
	public static Block blockWood;
	
	// General
	public static boolean enableSheepDrop;
	public static boolean enableSquidDrop;
	public static boolean generateBasalt;
	public static boolean generateFruitTree;
	public static boolean generateMarble;
	public static boolean generateOre;
	public static int[] oreGemRarity;
	public static int[] diamondPruningSawAoe;
	public static int[] gemPruningSawAoe;
	public static int[] ironPruningSawAoe;
	
	// Item
	public static Item itemBlockRock;
	public static Item itemFoodMisc;
	public static Item itemFoodMutton;
	public static Item itemMisc;
	public static Item multiGem;
	public static Item multiSawBlade;
	
	// Item // Seed
	public static Item itemSeed;
	
	// Item // Tool // Axe
	public static Item rubyAxe;
	public static Item sapphireAxe;
	
	// Item // Tool // Hoe
	public static Item rubyHoe;
	public static Item sapphireHoe;
	
	// Item // Tool // Pickaxe
	public static Item rubyPickaxe;
	public static Item sapphirePickaxe;
	
	// Item // Tool // Pruning Saw
	public static Item diamondPruningSaw;
	public static Item ironPruningSaw;
	public static Item rubyPruningSaw;
	public static Item sapphirePruningSaw;
	
	// Item // Tool // Shovel
	public static Item rubyShovel;
	public static Item sapphireShovel;
	
	// Item // Weapon // Sword
	public static Item rubySword;
	public static Item sapphireSword;
	
	// Register new material type.
	public EnumToolMaterial RUBY;
	
	// Tell forge where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide=GMReferences.CLIENT_PROXY_LOCATION, serverSide=GMReferences.COMMON_PROXY_LOCATION)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		// Initialize logger class.
		GMLogger.init();
		
		// Load config.
		GMConfig conf = new GMConfig();
		conf.loadConfig(e);
		
		// Block
		cropCottonBlock = new GMBlockCropCotton(conf.blockCropCottonID);
		decorativeGemBlock = new GMBlockDecorativeGem(conf.blockDecorativeGemID);
		oreGemBlock = new GMBlockOreGem(conf.blockOreGemID);
		blockRock = new GMBlockRock(conf.blockRockID);
		blockLeaves = new GMBlockLeaves(conf.blockTreeLeavesID);
		blockPlank = new GMBlockPlank(conf.blockTreePlankID);
		blockSapling = new GMBlockSapling(conf.blockTreeSaplingID);
		blockWood = new GMBlockWood(conf.blockTreeWoodID);
		
		// General // Durability
		RUBY = new EnumHelper().addToolMaterial("RUBY", 3, conf.gemDurability, 8.0F, 3.0F, 10);
		
		// World gen
		enableSheepDrop = conf.enableSheepDrop;
		enableSquidDrop = conf.enableSquidDrop;
		generateBasalt = conf.generateBasalt;
		generateMarble = conf.generateMarble;
		generateOre = conf.generateOre;
		generateFruitTree = conf.generateFruitTree;
		oreGemRarity = conf.OreGemRarity;
		
		// Tool AoE
		diamondPruningSawAoe = conf.diamondPruningSawAoe;
		gemPruningSawAoe = conf.gemPruningSawAoe;
		ironPruningSawAoe = conf.ironPruningSawAoe;

		// Item
		itemFoodMisc = new GMItemFoodMisc(conf.itemFoodMiscID, false)
		.setUnlocalizedName(modString + "food.misc");
		itemFoodMutton = new GMItemFoodMutton(conf.itemFoodMuttonID, true)
			.setUnlocalizedName(modString + "food.mutton");
		multiGem = new GMItemGem(conf.itemGemID)
			.setUnlocalizedName(modString + "gem");
		itemMisc = new GMItemMisc(conf.itemMiscID)
			.setUnlocalizedName(modString + "misc");
		multiSawBlade = new GMItemSawBlade(conf.itemSawBladeID)
			.setUnlocalizedName(modString + "sawblade");
		itemSeed = new GMItemSeed(conf.itemSeedID, cropCottonBlock.blockID, Block.tilledField.blockID)
			.setUnlocalizedName(modString + "seed");
		
		// Only register if setting is enabled.
		if (conf.enableVanillaGemTool) {
			
			// Item // Tool // Axe
			rubyAxe = new GMToolAxe(conf.itemRubyAxeID, RUBY)
				.setUnlocalizedName(modString + "axe.ruby");
			sapphireAxe = new GMToolAxe(conf.itemSapphireAxeID, RUBY)
				.setUnlocalizedName(modString + "axe.sapphire");
			
			// Item // Tool // Hoe
			rubyHoe = new GMToolHoe(conf.itemRubyHoeID, RUBY)
				.setUnlocalizedName(modString + "hoe.ruby");
			sapphireHoe = new GMToolHoe(conf.itemSapphireHoeID, RUBY)
				.setUnlocalizedName(modString + "hoe.sapphire");
			
			// Item // Tool // Pickaxe
			rubyPickaxe = new GMToolPickaxe(conf.itemRubyPickaxeID, RUBY)
				.setUnlocalizedName(modString + "pickaxe.ruby");
			sapphirePickaxe = new GMToolPickaxe(conf.itemSapphirePickaxeID, RUBY)
				.setUnlocalizedName(modString + "pickaxe.sapphire");
			
			// Item // Tool // Shovel
			rubyShovel = new GMToolShovel(conf.itemRubyShovelID, RUBY)
				.setUnlocalizedName(modString + "shovel.ruby");
			sapphireShovel = new GMToolShovel(conf.itemSapphireShovelID, RUBY)
				.setUnlocalizedName(modString + "shovel.sapphire");
			
			// Item // Weapon // Sword
			rubySword = new GMWeaponSword(conf.itemRubySwordID, RUBY)
				.setUnlocalizedName(modString + "sword.ruby");
			sapphireSword = new GMWeaponSword(conf.itemSapphireSwordID, RUBY)
				.setUnlocalizedName(modString + "sword.sapphire");
		}
		else {
			
			GMLogger.log(Level.INFO, "Gem vanilla tools is disabled.");
		}

		// Item // Tool // Pruning Saw
		diamondPruningSaw = new GMToolSaw(conf.itemDiamondPruningSawID, EnumToolMaterial.EMERALD, diamondPruningSawAoe)
			.setUnlocalizedName(modString + "pruningsaw.diamond");
		ironPruningSaw = new GMToolSaw(conf.itemIronPruningSawID, EnumToolMaterial.IRON, ironPruningSawAoe)
			.setUnlocalizedName(modString + "pruningsaw.iron");
		rubyPruningSaw = new GMToolSaw(conf.itemRubyPruningSawID, RUBY, gemPruningSawAoe)
			.setUnlocalizedName(modString + "pruningsaw.ruby");
		sapphirePruningSaw = new GMToolSaw(conf.itemSapphirePruningSawID, RUBY, gemPruningSawAoe)
			.setUnlocalizedName(modString + "pruningsaw.sapphire");
		
	}
	
	@EventHandler
	public void load(FMLInitializationEvent e) {

		GameRegistry.registerCraftingHandler(new GMFoodCraftingHandler());
		oreDictRegistration();
		addRecipes();
		GameRegistry.registerWorldGenerator(new GMWorldGen());
		registerEvent(new GMSheepDropEvent(), enableSheepDrop);
		registerEvent(new GMSquidDropEvent(), enableSquidDrop);
		MinecraftForge.EVENT_BUS.register(new GMBonemealEvent());
		proxy.registerRenderers();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	private static void registerEvent(Object par1, boolean par2) {
		
		if (par2) {
			
			MinecraftForge.EVENT_BUS.register(par1);
		}
	}
	
	private static void addRecipes() {
		
		// Object contain material used for the craft.
		Object[] materials = { Item.diamond, Item.ingotIron, "gemRuby", "gemSapphire" };
		Object[] rocks = { "blockBasalt", "blockMarble" };
		Item[][] tools = { { rubyAxe, rubyHoe, rubyPickaxe, rubyShovel, rubySword }, { sapphireAxe, sapphireHoe, sapphirePickaxe, sapphireShovel, sapphireSword } };
		Item[] saws = { diamondPruningSaw, ironPruningSaw, rubyPruningSaw, sapphirePruningSaw };
		
		// Cotton boll --> Cotton seed
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(itemSeed, 1, 0), new ItemStack(itemMisc, 1, 0)));
		// Cotton boll --> String
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.silk), true, new Object[] {
			"MM ", "MM ", 'M', new ItemStack(itemMisc, 1, 0)}));
		
		// Recipes with Ruby/Sapphire
		for (int i = 0; i < materials.length; i++) {
			
			// Sawblade
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(multiSawBlade, 1, i), true, new Object[] {
				" IM", "IM ", 'I', Item.ingotIron, 'M', materials[i]}));
			// Pruning Saw - Recipe 1
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(saws[i]), true, new Object[] {
				" IM", "IM ", "S  ", 'I', Item.ingotIron, 'M', materials[i], 'S', "stickWood"}));
			// Pruning Saw - Recipe 2
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(saws[i]), true, new Object[] {
				" M", "S ", 'I', Item.ingotIron, 'M', new ItemStack(multiSawBlade, 1, i), 'S', "stickWood"}));
			if (i > 1) {
				
				// Gem --> Decorative Block
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(decorativeGemBlock, 1, i - 2), true, new Object[] {
					"MMM", "MMM", "MMM", 'M', materials[i]}));
				// Decorative Block --> Gem
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(multiGem, 9, i - 2), new ItemStack(decorativeGemBlock, 1, i -2)));
				// Axe
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tools[i - 2][0]), true, new Object[] {
					"MM ", "MS ", " S ", 'M', materials[i], 'S', "stickWood"}));
				// Hoe
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tools[i - 2][1]), true, new Object[] {
					"MM ", " S ", " S ", 'M', materials[i], 'S', "stickWood"}));				
				// Pickaxe
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tools[i - 2][2]), true, new Object[] {
					"MMM", " S ", " S ", 'M', materials[i], 'S', "stickWood"}));
				// Shovel
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tools[i - 2][3]), true, new Object[] {
					" M ", " S ", " S ", 'M', materials[i], 'S', "stickWood"}));
				// Sword
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tools[i - 2][4]), true, new Object[] {
					" M ", " M ", " S ", 'M', materials[i], 'S', "stickWood"}));
			}
		}
		
		// Recipes with Basalt/Marble
		for (int i = 0; i < 2; i++) {
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockRock, 4, i + 4), true, new Object[] {
				"MM ", "MM ", 'M', rocks[i]}));
			FurnaceRecipes.smelting().addSmelting(blockRock.blockID, i + 2, new ItemStack(blockRock.blockID, 1, i), 0.1f);
		}
		
		// Wood-stuff
		for (int i = 0; i < 2; i++) {
			
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockPlank.blockID, 4, i), new ItemStack(blockWood.blockID, 1, i)));
		}
		
		// Raw mutton --> Cooked mutton
		FurnaceRecipes.smelting().addSmelting(itemFoodMutton.itemID, 1, new ItemStack(itemFoodMutton, 1, 0), 0.1f);
		// Raw squid --> Calamari
		FurnaceRecipes.smelting().addSmelting(itemFoodMisc.itemID, 2, new ItemStack(itemFoodMisc, 3, 3), 0.1f);
		
		// Fruit stuff - not quite working
		Item[] axes = { Item.axeDiamond, Item.axeGold, Item.axeIron, Item.axeStone, Item.axeWood, rubyAxe, sapphireAxe };
		for (int i = 0; i < axes.length; i++) {
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemMisc, 1, 3), new ItemStack(axes[i], 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemMisc, 1, 2));
			GameRegistry.addShapelessRecipe(new ItemStack(itemFoodMisc, 1, 1), new ItemStack(axes[i], 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemMisc, 1, 3));
		}
		
		GameRegistry.addShapelessRecipe(new ItemStack(blockSapling, 1, 1), new ItemStack(itemMisc, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(itemFoodMisc, 4, 0), new ItemStack(itemMisc, 1, 1));
	}
	
	private static void oreDictRegistration() {
		
		MinecraftForge.addGrassSeed(new ItemStack(itemSeed, 1, 0), 5);
		
		GameRegistry.registerBlock(decorativeGemBlock, GMMultiItemBlockDecorativeGem.class, decorativeGemBlock.getUnlocalizedName());
		GameRegistry.registerBlock(oreGemBlock, GMMultiItemBlockOreGem.class, oreGemBlock.getUnlocalizedName());
		GameRegistry.registerBlock(blockRock, GMMultiItemBlockRock.class, blockRock.getUnlocalizedName());
		GameRegistry.registerBlock(blockLeaves, GMMultiItemBlockLeaves.class, blockLeaves.getUnlocalizedName());
		GameRegistry.registerBlock(blockPlank, GMMultiItemBlockPlank.class, blockPlank.getUnlocalizedName());
		GameRegistry.registerBlock(blockSapling, GMMultiItemBlockSapling.class, blockSapling.getUnlocalizedName());
		GameRegistry.registerBlock(blockWood, GMMultiItemBlockWood.class, blockWood.getUnlocalizedName());
		GameRegistry.registerBlock(cropCottonBlock, cropCottonBlock.getUnlocalizedName());
		
		OreDictionary.registerOre("cropCotton", new ItemStack(cropCottonBlock, 1, 0));
		OreDictionary.registerOre("blockBasalt", new ItemStack(blockRock, 1, 0));
		OreDictionary.registerOre("blockMarble", new ItemStack(blockRock, 1, 1));
		OreDictionary.registerOre("gemRuby", new ItemStack(multiGem, 1, 0));
		OreDictionary.registerOre("gemSapphire", new ItemStack(multiGem, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(blockWood, 1, Short.MAX_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(blockPlank, 1, Short.MAX_VALUE));
		OreDictionary.registerOre("oreRuby", new ItemStack(oreGemBlock, 1, 0));
		OreDictionary.registerOre("oreSapphire", new ItemStack(oreGemBlock, 1, 1));
		OreDictionary.registerOre("seedCotton", new ItemStack(itemSeed, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(blockLeaves, 1, Short.MAX_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(blockSapling, 1, Short.MAX_VALUE));
	}
}