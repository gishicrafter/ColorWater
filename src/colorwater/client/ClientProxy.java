package colorwater.client;

import net.minecraftforge.client.MinecraftForgeClient;
import colorwater.Color;
import colorwater.CommonProxy;
import colorwater.client.render.TextureWaterLikeFX;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		
		net.minecraft.src.RenderEngine renderEngine = FMLClientHandler.instance().getClient().renderEngine;
		int i;
		for(i = 0; i < 16; ++ i)
		{
			renderEngine.registerTextureFX(new TextureWaterLikeFX(32+i, ITEMS_PNG, Color.values()[i].colorcode, 0xE0E0FF).setMaxColorDistance(48));
			renderEngine.registerTextureFX(new TextureWaterLikeFX(48+i, ITEMS_PNG, Color.values()[i].colorcode, 0xFFFFC0));
			renderEngine.registerTextureFX(new TextureWaterLikeFX(16+i, BLOCKS_PNG, Color.values()[i].colorcode, 0xE0FFE0).setMaxColorDistance(48));
		}
	}

}
