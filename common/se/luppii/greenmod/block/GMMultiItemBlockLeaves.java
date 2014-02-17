package se.luppii.greenmod.block;

public class GMMultiItemBlockLeaves extends GMMultiItemBlock {

	private String[] names = new String[] { "orange", "coconut" };
	
	public GMMultiItemBlockLeaves(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNames(names);
	}

}