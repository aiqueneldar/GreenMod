package se.luppii.greenmod;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.EnumHelper;
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
import se.luppii.greenmod.block.GMBlockDecorativeGem;
import se.luppii.greenmod.block.GMBlockOreGem;
import se.luppii.greenmod.block.GMBlockRock;
import se.luppii.greenmod.block.GMMultiItemBlockDecorativeGem;
import se.luppii.greenmod.block.GMMultiItemBlockOreGem;
import se.luppii.greenmod.block.GMMultiItemBlockRock;
import se.luppii.greenmod.item.GMItemGem;
import se.luppii.greenmod.item.GMItemSawBlade;
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
import se.luppii.greenmod.world.GMWorldGenOreGem;
import se.luppii.greenmod.world.GMWorldGenRock;

@Mod(modid=GMReferences.MOD_ID, name=GMReferences.MOD_NAME, version=GMReferences.MOD_VERSION)
@NetworkMod(clientSideRequired=true)
public class GreenMod {
	
	@Instance(value = "GreenMod")
	public static GreenMod instance;
	public static final String modString = GMReferences.MOD_STRING;
	
	// Block
	public static Block oreGemBlock;
	public static Block decorativeGemBlock;
	public static Block blockRock;
	
	// General
	public static boolean generateBasalt;
	public static boolean generateMarble;
	public static boolean generateOre;
	public static int[] oreGemRarity;
	public static int[] diamondPruningSawAoe;
	public static int[] gemPruningSawAoe;
	public static int[] ironPruningSawAoe;
	
	// Item
	public static Item itemBlockRock;
	public static Item multiGem;
	public static Item multiSawBlade;
	
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
	public final EnumToolMaterial RUBY = new EnumHelper().addToolMaterial("RUBY", 3, 250, 8.0F, 3.0F, 10);
	
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
		decorativeGemBlock = new GMBlockDecorativeGem(conf.blockDecorativeGemID);
		oreGemBlock = new GMBlockOreGem(conf.blockOreGemID);
		blockRock = new GMBlockRock(conf.blockRockID);
		
		// World gen
		generateBasalt = conf.generateBasalt;
		generateMarble = conf.generateMarble;
		generateOre = conf.generateOre;
		oreGemRarity = conf.OreGemRarity;
		
		// Tool AoE
		diamondPruningSawAoe = conf.diamondPruningSawAoe;
		gemPruningSawAoe = conf.gemPruningSawAoe;
		ironPruningSawAoe = conf.ironPruningSawAoe;

		// Item
		multiGem = new GMItemGem(conf.itemGemID)
			.setUnlocalizedName(modString + "gem");
		multiSawBlade = new GMItemSawBlade(conf.itemSawBladeID)
			.setUnlocalizedName(modString + "sawblade");
		
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
			
			GMLogger.log(Level.INFO, "Gem vanilla tools is enabled.");
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
		
		oreDictRegistration();
		addRecipes();
		worldGen(generateOre, generateBasalt, generateMarble);
		
		proxy.registerRenderers();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		// Stuff
		
	}
	
	private static void addRecipes() {
		// Object contain material used for the craft.
		Object[] materials = { Item.diamond, Item.ingotIron, "gemRuby", "gemSapphire" };
		Object[] rocks = { "blockBasalt", "blockMarble" };
		Item[][] tools = { { rubyAxe, rubyHoe, rubyPickaxe, rubyShovel, rubySword }, { sapphireAxe, sapphireHoe, sapphirePickaxe, sapphireShovel, sapphireSword } };
		Item[] saws = { diamondPruningSaw, ironPruningSaw, rubyPruningSaw, sapphirePruningSaw };
		
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
		
	}
	
	private static void oreDictRegistration() {
		GameRegistry.registerBlock(decorativeGemBlock, GMMultiItemBlockDecorativeGem.class, decorativeGemBlock.getUnlocalizedName());
		GameRegistry.registerBlock(oreGemBlock, GMMultiItemBlockOreGem.class, oreGemBlock.getUnlocalizedName());
		GameRegistry.registerBlock(blockRock, GMMultiItemBlockRock.class, blockRock.getUnlocalizedName());
		
		OreDictionary.registerOre("blockBasalt", new ItemStack(blockRock, 1, 0));
		OreDictionary.registerOre("blockMarble", new ItemStack(blockRock, 1, 1));
		OreDictionary.registerOre("gemRuby", new ItemStack(multiGem, 1, 0));
		OreDictionary.registerOre("gemSapphire", new ItemStack(multiGem, 1, 1));
		OreDictionary.registerOre("oreRuby", new ItemStack(oreGemBlock, 1, 0));
		OreDictionary.registerOre("oreSapphire", new ItemStack(oreGemBlock, 1, 1));
	}
	
	private static void worldGen(boolean par1Boolean, boolean par2Boolean, boolean par3Boolean) {
		if (par1Boolean) {
			GameRegistry.registerWorldGenerator(new GMWorldGenOreGem());
			GMLogger.log(Level.INFO, "World gem ore generation is enabled.");
		}
		if (par2Boolean || par3Boolean) {
			GameRegistry.registerWorldGenerator(new GMWorldGenRock(blockRock, par2Boolean, par3Boolean));
			GMLogger.log(Level.INFO, "World rock generation is enabled.");
		}
		if (!par1Boolean && !par2Boolean && par3Boolean) {
			GMLogger.log(Level.INFO, "World generation is disabled.");
		}
	}
}
