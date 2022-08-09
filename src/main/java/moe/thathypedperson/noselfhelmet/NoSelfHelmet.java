package moe.thathypedperson.noselfhelmet;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

@Mod(modid = NoSelfHelmet.MODID, version = NoSelfHelmet.VERSION)
public class NoSelfHelmet
{
	public static final String MODID = "noselfhelmet";
	public static final String VERSION = "1.0";
	
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Get config file
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig(true);
	}
	
	public static boolean helmetDisabled = true;
	
	public static void syncConfig(boolean preInit)
	{
		try
		{
			config.load();
			
			Property helmetDisabledProperty = config.get(
					Configuration.CATEGORY_GENERAL,
					"helmetDisabled",
					"true",
					"Whether to show your own helmet.");
			if(preInit) helmetDisabled = helmetDisabledProperty.getBoolean();
			else helmetDisabledProperty.set(helmetDisabled);
		}
		catch(Exception e) {
			FMLLog.log(Level.ERROR, e, MODID + " Has a problem loading the config file.");
		}
		finally
		{
			if (config.hasChanged()) config.save();
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// Register Command
		ClientCommandHandler.instance.registerCommand(new ToggleCommand());
	}
}
