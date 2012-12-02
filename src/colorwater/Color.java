package colorwater;

public enum Color {
	White(0xFFFFFF),
	Orange(0xDD854B),
	Magenta(0xB855C1),
	LightBlue(0x8EA6D6),
	Yellow(0xCFC231),
	Lime(0x4FC246),
	Pink(0xDEA6B6),
	Gray(0x4A4A4A),
	LightGray(0xB4B8B8),
	Cyan(0x36809E),
	Purple(0x9152C6),
	Blue(0x3640A4),
	Brown(0x5C3A24),
	Green(0x3D5220),
	Red(0xAE3E38),
	Black(0x242020);
	
	public final int colorcode;
	
	public static final Color[][] mixing = new Color[][]
	{
		new Color[]{},
		new Color[]{Yellow, Red},
		new Color[]{Pink, Purple},
		new Color[]{White, Blue},
		new Color[]{},
		new Color[]{White, Green},
		new Color[]{White, Red},
		new Color[]{White, Black},
		new Color[]{White, Gray},
		new Color[]{Blue, Green},
		new Color[]{Blue, Red},
		new Color[]{},
		new Color[]{},
		new Color[]{},
		new Color[]{},
		new Color[]{}
	};
	
	Color(int colorcode)
	{
		this.colorcode = colorcode;
	}
	
	String colorName()
	{
		return this.name().toLowerCase();
	}
}
