package se.luppii.greenmod.event;

import java.util.Random;
import java.util.logging.Level;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMLogger;

public class GMSquidDropEvent {
	
	private Random random = new Random();
	
	@ForgeSubscribe
	public void onEntityDrop(LivingDropsEvent event) {
		
		if (event.entityLiving instanceof EntitySquid) {
			
			int dropAmount = 1 + random.nextInt(3);
			ItemStack itemDrop = event.entity.isBurning() ? new ItemStack(GreenMod.itemFoodMisc, dropAmount * 3, 3) : new ItemStack(GreenMod.itemFoodMisc, dropAmount, 2);

			event.entityLiving.entityDropItem(itemDrop, 0.0F);
		}
	}
}
