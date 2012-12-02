package colorwater.client.render;

import net.minecraft.src.RenderEngine;
import cpw.mods.fml.client.FMLTextureFX;
import net.minecraftforge.client.ForgeHooksClient;

public class TextureWaterLikeFX extends FMLTextureFX
{
	protected float bf1[];
	protected float bf2[];
	protected float bf3[];
	protected float bf4[];
	
	private String texture;
	
	private int redBase;
	private int redShift;
	private int greenBase;
	private int greenShift;
	private int blueBase;
	private int blueShift;
	private int alphaBase;
	private int alphaShift;

	public TextureWaterLikeFX(int textureIndex, String texture, int redBase, int redSpecular,
		int greenBase, int greenSpecular, int blueBase, int blueSpecular, int alphaBase, int alphaSpecular)
	{
		super(textureIndex);
		bf1 = new float[tileSizeSquare];
		bf2 = new float[tileSizeSquare];
		bf3 = new float[tileSizeSquare];
		bf4 = new float[tileSizeSquare];
		this.texture = texture;
		this.redBase = redBase;
		this.redShift = redSpecular - redBase;
		this.greenBase = greenBase;
		this.greenShift = greenSpecular - greenBase;
		this.blueBase = blueBase;
		this.blueShift = blueSpecular - blueBase;
		this.alphaBase = alphaBase;
		this.alphaShift = alphaSpecular - alphaBase;
	}

	public TextureWaterLikeFX(int textureIndex, String tex, int r1, int r2, int g1, int g2, int b1, int b2)
	{
		this(textureIndex, tex, r1, r2, g1, g2, b1, b2, 146, 196-146);
	}
	
	public TextureWaterLikeFX(int textureIndex, String texture, int colorBase, int colorSpecular)
	{
		this(textureIndex, texture,
				(colorBase >> 16) & 0xFF, (colorSpecular >> 16) & 0xFF,
				(colorBase >> 8) & 0xFF, (colorSpecular >> 8) & 0xFF,
				colorBase & 0xFF, colorSpecular & 0xFF);
	}

	@Override
	public void bindImage(RenderEngine renderengine)
	{
		ForgeHooksClient.bindTexture(texture,0);
	}
	
	@Override
	public void onTick()
	{
		for (int i = 0; i < tileSizeBase; i++)
		{
			for (int k = 0; k < tileSizeBase; k++)
			{
				float f = 0.0F;
				for (int j1 = i - 1; j1 <= i + 1; j1++)
				{
					int k1 = j1 & tileSizeMask;
					int i2 = k & tileSizeMask;
					f += bf1[k1 + i2 * tileSizeBase];
				}

				bf2[i + k * tileSizeBase] = f / 3.3F + bf3[i + k * tileSizeBase] * 0.8F;
			}
		}

		for (int j = 0; j < tileSizeBase; j++)
		{
			for (int l = 0; l < tileSizeBase; l++)
			{
				bf3[j + l * tileSizeBase] += bf4[j + l * tileSizeBase] * 0.05F;
				if (bf3[j + l * tileSizeBase] < 0.0F)
				{
					bf3[j + l * tileSizeBase] = 0.0F;
				}
				bf4[j + l * tileSizeBase] -= 0.1F;
				if (Math.random() < 0.05D)
				{
					bf4[j + l * tileSizeBase] = 0.5F;
				}
			}
		}

		float af[] = bf2;
		bf2 = bf1;
		bf1 = af;
		for (int i1 = 0; i1 < tileSizeSquare; i1++)
		{
			float f1 = bf1[i1];
			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}
			if (f1 < 0.0F)
			{
				f1 = 0.0F;
			}
			float f2 = f1 * f1;
			int l1 = (int)(redBase + f2 * redShift);
			int j2 = (int)(greenBase + f2 * greenShift);
			int k2 = (int)(blueBase + f2 * blueShift);
			int l2 = (int)(alphaBase + f2 * alphaShift);
			if (anaglyphEnabled)
			{
				int i3 = (l1 * 30 + j2 * 59 + k2 * 11) / 100;
				int j3 = (l1 * 30 + j2 * 70) / 100;
				int k3 = (l1 * 30 + k2 * 70) / 100;
				l1 = i3;
				j2 = j3;
				k2 = k3;
			}
			imageData[i1 * 4 + 0] = (byte)l1;
			imageData[i1 * 4 + 1] = (byte)j2;
			imageData[i1 * 4 + 2] = (byte)k2;
			imageData[i1 * 4 + 3] = (byte)l2;
		}
	}
}
