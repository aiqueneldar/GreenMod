package se.luppii.greenmod.item.tool;

import java.util.List;

import se.luppii.greenmod.gui.GMCreativeTab;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class GMTool extends ItemTool {
	
	private Block[] blocksEffectiveAgainst; // Blocks good against.
	public float efficiencyOnProperMaterial = 4.0F; // How fast it is.
	public float damageVsEntity; // Damage vs mobs.
	protected EnumToolMaterial toolMaterial; // The material it's made of.
	private int _metaMax = 0;
	
	protected GMTool(int par1, float par2, EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {
		super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
		this.toolMaterial = par3EnumToolMaterial;
		this.blocksEffectiveAgainst = par4ArrayOfBlock;
		this.maxStackSize = 1;
		this.setMaxDamage(par3EnumToolMaterial.getMaxUses()); // Max uses with tool.
		this.efficiencyOnProperMaterial = par3EnumToolMaterial.getEfficiencyOnProperMaterial();
		this.damageVsEntity = par2 + par3EnumToolMaterial.getDamageVsEntity();
		this.setCreativeTab(GMCreativeTab.tab);
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		super.setUnlocalizedName(par1Str);
		GameRegistry.registerItem(this, getUnlocalizedName());
		return this;
		
	}
	
	// How well it breaks a block.
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) {
			if (this.blocksEffectiveAgainst[i] == par2Block) {
				return this.efficiencyOnProperMaterial;
			}
		}
		return 1.0F;
	}
	
	// What happens when you hit an entity.
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		// Tool isn't effective against entities, decrease durability with 2.
		par1ItemStack.damageItem(2, par3EntityLivingBase);
		return true;
	}
	
	// What happens when you break a block.
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase) {
		if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			// 1 durability loss when breaking a block above 0.0D hardness, else none.
			par1ItemStack.damageItem(1, par7EntityLivingBase);
		}
		return true;
	}
	
	// How much damage the tool does vs entities.
	public float getDamageVsEntity(Entity par1Entity) {
		return this.damageVsEntity;
	}
	
	protected void setMetaMax(int par1) {
		_metaMax = par1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("greenmod:" + getUnlocalizedName());
	}
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i <= _metaMax; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true; // Makes the tool have a different render, only interesting on client side.
	}
	
	// How well it enchants.
	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}
	
	// Allows for repair in an anvil.
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	// How fast it breaks a block (Compability for metaitems).
	public float getStrVsBlock(ItemStack stack, Block block, int meta) {
		if (ForgeHooks.isToolEffective(stack, block, meta)) {
			return efficiencyOnProperMaterial;
		}
		return getStrVsBlock(stack, block);
	}
	
}
