package se.luppii.greenmod.item.food;

import net.minecraft.item.EnumAction;

public class GMItemFoodMisc extends GMItemFood {
	
	private final static String[] name = { "orangeslice", "cocowater", "squid.raw", "squid.cooked" };
	private final static int[] heal = { 1, 4, 2, 2 };
	private final static float[] saturation = { 0.2F, 0.4F, 0.3F, 0.2F };
	private final static EnumAction[] action = { EnumAction.eat, EnumAction.drink, EnumAction.eat, EnumAction.eat }; 
	
	public GMItemFoodMisc(int par1, boolean par2) {
		super(par1, heal, saturation, par2, action);
		this.setNames(name);
	}
}