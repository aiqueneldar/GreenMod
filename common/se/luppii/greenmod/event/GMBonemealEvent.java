package se.luppii.greenmod.event;

import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.block.GMBlockSapling;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class GMBonemealEvent {

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent e) {
		
		if (!e.world.isRemote && e.ID == GreenMod.blockSapling.blockID) {
			
			((GMBlockSapling)GreenMod.blockSapling).growTree(e.world, e.X, e.Y, e.Z, e.world.rand);
			e.setResult(Result.ALLOW);
		}
	}
	
}
