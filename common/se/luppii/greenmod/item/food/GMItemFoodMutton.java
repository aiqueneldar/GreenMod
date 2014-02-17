package se.luppii.greenmod.item.food;

import net.minecraft.item.EnumAction;

public class GMItemFoodMutton extends GMItemFood {
	
	private final static String[] name = { "cooked", "raw" };
	private final static int[] heal = { 6, 2 };
	private final static float[] saturation = { 0.6F, 0.3F };
	private final static EnumAction[] action = { EnumAction.eat, EnumAction.eat }; 
	
	public GMItemFoodMutton(int par1, boolean par2) {
		super(par1, heal, saturation, par2, action);
		this.setNames(name);
	}
}
