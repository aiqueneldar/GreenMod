package se.luppii.greenmod.handler;

import java.util.logging.Level;

import se.luppii.greenmod.item.tool.GMToolAxe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class GMFoodCraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {

		int axes = 0;
		int slot = 0;
		ItemStack itemInSlot = null;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			
			if (craftMatrix.getStackInSlot(i) != null) {
				
				ItemStack j = craftMatrix.getStackInSlot(i);
				
				// If item is an axe.
				if (j.getItem() != null && (j.getItem() instanceof ItemAxe || j.getItem() instanceof GMToolAxe)) {
					
					// Item is an axe, count.
					axes++;
					slot = i;
					
					// Make a copy of the item, with 1 more damage than before.
					itemInSlot = new ItemStack(j.getItem().itemID, 2, (j.getItemDamage() + 1));
					if (itemInSlot.getItemDamage() >= itemInSlot.getMaxDamage()) {
						itemInSlot.stackSize--;
					}
				}
			}
		}
		
		if (axes == 1 && itemInSlot != null) {
			
			// Place new item in crafting grid.
			craftMatrix.setInventorySlotContents(slot, itemInSlot);	
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		
	}
}