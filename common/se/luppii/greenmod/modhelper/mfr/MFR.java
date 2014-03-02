package se.luppii.greenmod.modhelper.mfr;

import java.util.logging.Level;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.HarvestType;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;
import se.luppii.greenmod.lib.GMReferences;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "GreenMod|MFR", name = "GreenMod Compatible: MFR", version = GMReferences.MOD_VERSION, dependencies = "required-after:GreenMod;after:MineFactoryReloaded")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class MFR {

	@EventHandler
	public void load(FMLInitializationEvent e) {
		
		if (!Loader.isModLoaded("MineFactoryReloaded")) {
			
			GMLogger.log(Level.INFO, "MineFactoryReloaded missing. GreenMod Compatible: MFR is not loading.");
			return;
		}
		try {
			
			FactoryRegistry.registerPlantable(new PlantableCrop(GreenMod.itemSeed.itemID, GreenMod.cropCottonBlock.blockID));
			FactoryRegistry.registerPlantable(new Plantable(GreenMod.blockSapling.blockID, GreenMod.blockSapling.blockID));
			FactoryRegistry.registerHarvestable(new HarvestableCrop(GreenMod.cropCottonBlock.blockID, 7));
			FactoryRegistry.registerHarvestable(new Harvestable(GreenMod.blockWood.blockID, HarvestType.Tree));
			FactoryRegistry.registerHarvestable(new Harvestable(GreenMod.blockLeaves.blockID, HarvestType.TreeLeaf));
			FactoryRegistry.registerFertilizable(new FertilizableCrop(GreenMod.cropCottonBlock.blockID, 7));
			FactoryRegistry.registerFertilizable(new FertilizablePlant(GreenMod.blockSapling.blockID));
		}
		catch (Exception err) {
			
			err.printStackTrace();
		}
	}
	
}
