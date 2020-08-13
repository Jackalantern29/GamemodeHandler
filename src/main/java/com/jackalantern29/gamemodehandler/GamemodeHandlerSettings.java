package com.jackalantern29.gamemodehandler;

import com.jackalantern29.gamemodehandler.worldsettings.WorldSettings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GamemodeHandlerSettings {
    private static GamemodeHandlerSettings settings;
    private final GamemodeHandler plugin;
    private List<Material> blackList = new ArrayList<>();

    protected GamemodeHandlerSettings(GamemodeHandler plugin) {
        this.plugin = plugin;
        File file = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        {
            File worldsFolder = new File(plugin.getDataFolder(), "worlds");
            if(!worldsFolder.exists())
                worldsFolder.mkdir();
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        for(World world : Bukkit.getWorlds()) {
            WorldSettings worldSettings = WorldSettings.getWorldSettings(world);
        }
        this.settings = this;
    }

    public static GamemodeHandlerSettings getSettings() {
        return settings;
    }
}
