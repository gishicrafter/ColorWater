package colorwater;

import java.io.File;

public class ModConfiguration
{
	@ConfigHelper.Item(name="bucketGeneric.id", defaultID=17650)
	public static int idBucketGeneric;
	
	@ConfigHelper.Item(name="liquid.id", defaultID=17651)
	public static int idLiquid;
	
	@ConfigHelper.Item(name="bucketGel.id", defaultID=17652)
	public static int idBucketGel;
	
	@ConfigHelper.Item(name="itemGel.id", defaultID=17653)
	public static int idItemGel;
	
	@ConfigHelper.Block(name="gel.id", defaultID=3950)
	public static int idBlockGel;
	
	public static void loadConfiguration(File file)
	{
		ConfigHelper helper = new ConfigHelper(file);
		helper.loadTo(ModConfiguration.class);
	}
}