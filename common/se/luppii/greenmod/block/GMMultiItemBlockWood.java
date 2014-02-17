package se.luppii.greenmod.block;

public class GMMultiItemBlockWood extends GMMultiItemBlock {

	private String[] names = new String[] { "orange", "coconut" };
	
	public GMMultiItemBlockWood(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNames(names);
	}

}
