package se.luppii.greenmod.block;

public class GMMultiItemBlockRock extends GMMultiItemBlock {
	
	private String[] names = new String[] { "basalt", "marble", "basaltcobblestone", "marblecobblestone", "basaltbrick", "marblebrick" };

	public GMMultiItemBlockRock(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNames(names);
	}

}
