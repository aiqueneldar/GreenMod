package se.luppii.greenmod.item.food;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.gui.GMCreativeTab;
import se.luppii.greenmod.item.GMMultiItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class GMItemFood extends GMMultiItem {
		
	/** Number of ticks to run while 'EnumAction'ing until result. */
	public final int itemUseDuration;

	/** The amount this food item heals the player. */
	protected final int[] healAmount;
	protected final float[] saturationModifier;

	/** Whether wolves like this food (true for raw and cooked porkchop). */
	protected final boolean isWolfsFavoriteMeat;

	/**
	 * If this field is true, the food can be consumed even if the player don't need to eat.
	 */
	private boolean alwaysEdible;

	/**
	 * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
	private int[] potionId;

	/** set by setPotionEffect */
	private int[] potionDuration;

	/** set by setPotionEffect */
	private int[] potionAmplifier;

	/** probably of the set potion effect occurring */
	private float[] potionEffectProbability;
	
	/** Action to perform while using the item */
	protected final EnumAction[] itemUseAction;

	public GMItemFood(int par1, int[] par2, float[] par3, boolean par4Boolean, EnumAction[] par5EnumAction) {
		
		super(par1);
		this.itemUseDuration = 32;
		this.healAmount = par2;
		this.saturationModifier = par3;
		this.isWolfsFavoriteMeat = par4Boolean;
		this.itemUseAction = par5EnumAction;
		this.potionId = new int[par2.length];
		this.potionDuration = new int[par2.length];
		this.potionAmplifier = new int[par2.length];
		this.potionEffectProbability = new float[par2.length];
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		
		--par1ItemStack.stackSize;
		par3EntityPlayer.getFoodStats().addStats(this.getHealAmount(par1ItemStack.getItemDamage()),
				this.getSaturationModifier(par1ItemStack.getItemDamage()));
		par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
		return par1ItemStack;
	}
	
	private void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		
		int meta = par1ItemStack.getItemDamage();
		if (!par2World.isRemote && this.potionId[meta] > 0 && par2World.rand.nextFloat() < this.potionEffectProbability[meta]) {
			
			par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionId[meta], this.potionDuration[meta] * 20, this.potionAmplifier[meta]));
		}
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		
		return 32;
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		
		int meta = par1ItemStack.getItemDamage();
		return this.itemUseAction[meta];
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		
		if (par3EntityPlayer.canEat(this.alwaysEdible)) {
			
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		
		return par1ItemStack;
	}
	
	public int getHealAmount(int meta) {
		return this.healAmount[meta];
	}
	
	public float getSaturationModifier(int meta) {
		return this.saturationModifier[meta];
	}
	
	public boolean isWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}
	
	public GMItemFood setPotionEffect(int par1, int par2, int par3, int par4, float par5) {
		this.potionId[par1] = par2;
		this.potionDuration[par1] = par3;
		this.potionAmplifier[par1] = par4;
		this.potionEffectProbability[par1] = par5;
		return this;
	}
	
	public GMItemFood setAlwaysEdible() {
		this.alwaysEdible = true;
		return this;
	}

}
