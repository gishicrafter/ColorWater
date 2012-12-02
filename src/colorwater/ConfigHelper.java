package colorwater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHelper {

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Item
	{
		String name() default "";
		int defaultID();
	}
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Block
	{
		String name() default "";
		int defaultID();
	}
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Value
	{
		String category() default Configuration.CATEGORY_GENERAL;
		String name() default "";
		String defaultValue();
	}
	
	private File configFile;
	
	public ConfigHelper(File configFile)
	{
		this.configFile = configFile;
	}
	
	public void loadTo(Class klass)
	{
		try{
			Configuration config = new Configuration(configFile);
			config.load();
			Property prop;
			Field[] fields = klass.getFields();
			for(Field field : fields){
				Item annotationItem = field.getAnnotation(Item.class);
				Block annotationBlock = field.getAnnotation(Block.class);
				Value annotationValue = field.getAnnotation(Value.class);
				String key;
				if(annotationItem != null){
					key = annotationItem.name();
					if(key.equals("")) key = field.getName() + ".id";
					prop = config.getItem(key, annotationItem.defaultID());
					field.setInt(null, prop.getInt());
				}else if(annotationBlock != null){
					key = annotationBlock.name();
					if(key.equals("")) key = field.getName() + ".id";
					prop = config.getBlock(key, annotationBlock.defaultID());
					field.setInt(null, prop.getInt());
				}else if(annotationValue != null){
					key = annotationValue.name();
					if(key.equals("")) key = field.getName();
					
					if(field.getType() == int.class){
						prop = config.get(annotationValue.category(), key, Integer.parseInt(annotationValue.defaultValue()));
						field.setInt(null, prop.getInt());
					}
					else if(field.getType() == boolean.class){
						prop = config.get(annotationValue.category(), key, Boolean.parseBoolean(annotationValue.defaultValue()));
						field.setBoolean(null, prop.getBoolean(false));
					}
					else{
						prop = config.get(annotationValue.category(), key, annotationValue.defaultValue());
						field.set(null, prop.value);
					}
				}
			}
			config.save();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
