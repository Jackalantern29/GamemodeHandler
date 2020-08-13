package com.jackalantern29.gamemodehandler.worldsettings;

import com.jackalantern29.gamemodehandler.GamemodeHandler;
import com.jackalantern29.gamemodehandler.inventory.WorldInventoryAction;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryType;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WorldSettings {
    private static final HashMap<World, WorldSettings> MAP = new HashMap<>();

    private final EnumMap<InventoryType, WorldInventorySettings> inventorySettingsMap = new EnumMap<>(InventoryType.class);
    private World world;
    private List<Material> blackList = new ArrayList<>();

    protected WorldSettings(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void saveAsFile() {

    }

    public void saveAsFile(File file) {

    }

    public WorldInventorySettings getInventorySettings(InventoryType type) {
        WorldInventorySettings settings = inventorySettingsMap.get(type);
        if(settings == null) {
            settings = new WorldInventorySettings(world, type);
            inventorySettingsMap.put(type, settings);
        }
        return settings;
    }

    public static WorldSettings getWorldSettings(World world) {
        File file = new File(GamemodeHandler.getInstance().getDataFolder(), "worlds" + File.separator + world.getName().toLowerCase() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(world == null)
            return null;
        if(MAP.get(world) != null)
            return MAP.get(world);
        WorldSettings settings = new WorldSettings(world);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(config.isConfigurationSection("creative.inventory")) {
            ConfigurationSection invSection = config.getConfigurationSection("creative.inventory");
            for(String key : invSection.getKeys(false)) {
                ConfigurationSection section = invSection.getConfigurationSection(key);
                InventoryType type = InventoryType.valueOf(key.toUpperCase());
                for(String matKey : section.getKeys(false)) {
                    Material material = Material.valueOf(matKey.toUpperCase());
                    ConfigurationSection matSection = section.getConfigurationSection(matKey);
                    settings.getInventorySettings(type).setAction(material, new WorldInventoryAction() {
                        @Override
                        public boolean onDrop(Action action) {
                            return matSection.getBoolean("drop.cancel", false);
                        }

                        @Override
                        public boolean onPickup(Action action) {
                            return matSection.getBoolean("pickup.cancel", false);
                        }

                        @Override
                        public boolean onPlace(Action action) {
                            return matSection.getBoolean("place.cancel", false);
                        }
                    });
                }
            }
        }
        MAP.put(world, settings);
        return settings;
    }
}
