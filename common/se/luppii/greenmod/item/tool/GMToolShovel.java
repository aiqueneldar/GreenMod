package se.luppii.greenmod.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;

public class GMToolShovel extends GMTool {

    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

	public GMToolShovel(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, 1.0F, par2EnumToolMaterial, blocksEffectiveAgainst);
	}
	
	@Override
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }

}
