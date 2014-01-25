package se.luppii.greenmod.block;

public class GMMultiItemBlockOreGem extends GMMultiItemBlock {
	
	private String[] names = new String[] { "ruby", "sapphire" };

	public GMMultiItemBlockOreGem(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNames(names);
	}

}
