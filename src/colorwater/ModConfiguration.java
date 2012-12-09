package colorwater;

import java.io.File;
import gishicrafter.util.ConfigHelper;

public class ModConfiguration
{
	@ConfigHelper.Item(name="bucketGeneric.id")
	public static int idBucketGeneric=17650;
	
	@ConfigHelper.Item(name="liquid.id")
	public static int idLiquid=17651;
	
	@ConfigHelper.Item(name="bucketGel.id")
	public static int idBucketGel=17652;
	
	@ConfigHelper.Item(name="itemGel.id")
	public static int idItemGel=17653;
	
	@ConfigHelper.Block(name="gel.id")
	public static int idBlockGel=3950;
	
	public static void loadConfiguration(File file)
	{
		ConfigHelper helper = new ConfigHelper(file);
		helper.loadTo(ModConfiguration.class);
	}
}