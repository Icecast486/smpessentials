package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class HomeDataManager
{
    private CoolLookin plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public HomeDataManager(CoolLookin plugin)
    {
        System.out.println("----------------- Attempting to Construct Data ------------------");
        this.plugin = plugin;

        /* Save/initialize the config */
        saveDefaultConfig();
    }

    /* Create config if it doesn't exist, also checks for yml issues (missing quotes, spacing errors etc) */
    public void reloadConfig()
    {
        /* Create the file is it doesn't exist */
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "homes.yml");

        /* Load the figgy */
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("homes.yml");

        if (defaultStream != null)
        {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    /* Returns the config */
    public FileConfiguration getConfig()
    {
        /* If config doesn't exist, create it */
        if (this.dataConfig == null)
            reloadConfig();

        return this.dataConfig;
    }

    /* Use this when you add stuff to the config file */
    public void saveConfig()
    {
        System.out.println("----------------- TRYING TO SAVE CFG ----------------------");
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        }
        catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }

    }

    public void saveDefaultConfig()
    {
        System.out.println("----------------- TRYING TO SAVE DEFAULT CFG ----------------------");
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "homes.yml");

        if (!this.configFile.exists())
            this.plugin.saveResource("homes.yml", false);
    }
}
