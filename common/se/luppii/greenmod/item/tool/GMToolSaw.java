package se.luppii.greenmod.item.tool;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import se.luppii.greenmod.GreenMod;
import se.luppii.greenmod.lib.GMConfig;

public class GMToolSaw extends GMTool {
	
	public static final Block[] blocksEffectiveAgainst = new Block[] { Block.leaves, Block.cloth };
	private int[] aoeEffect;
	
	public GMToolSaw(int par1, EnumToolMaterial par2EnumToolMaterial, int[] par3) {
		super(par1, 3.0F, par2EnumToolMaterial, blocksEffectiveAgainst);
		this.aoeEffect = par3;
	}
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return par2Block != null & (par2Block.blockMaterial == Material.leaves || par2Block.blockMaterial == Material.cloth) ?
				this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	}
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase) {
		if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			if (par2World.getBlockMaterial(par4, par5, par6) == Material.leaves) {
				for (int y = this.aoeEffect[1]; y >= -1; y--) {
					for (int x = this.aoeEffect[0]; x >= -1; x--) {
						for (int z = this.aoeEffect[2]; z >= -1; z--) {
							if (par2World.getBlockMaterial(par4 + x, par5 + y, par6 + z) == Material.leaves) {
								par1ItemStack.damageItem(1, par7EntityLivingBase);
								par2World.destroyBlock(par4 + x, par5 + y, par6 + z, true);
							}
						}
					}
				}
			}
			else {
				par1ItemStack.damageItem(2, par7EntityLivingBase);
			}
		}
		return true;
	}
	
}
