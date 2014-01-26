package se.luppii.greenmod.event;

import java.util.Random;

import se.luppii.greenmod.GreenMod;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class GMSheepDropEvent {

	private Random random = new Random();
	
	@ForgeSubscribe
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntitySheep) {
			int dropChance = 1 + random.nextInt(2);
			ItemStack itemDrop = event.entity.isBurning() ? new ItemStack(GreenMod.itemFood) : new ItemStack(GreenMod.itemMisc, 1, 1);
			event.entityLiving.entityDropItem(itemDrop, dropChance);
			
		}
	}
	
}