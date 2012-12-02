package colorwater;

import java.io.File;

public class ModConfiguration
{
	@ConfigHelper.Item(name="bucketGeneric.id", defaultID=17650)
	public static int idBucketGeneric;
	
	@ConfigHelper.Item(name="liquid.id", defaultID=17651)
	public static int idLiquid;
	
	public static void loadConfiguration(File file)
	{
		ConfigHelper helper = new ConfigHelper(file);
		helper.loadTo(ModConfiguration.class);
	}
}