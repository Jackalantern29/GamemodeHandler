package com.jackalantern29.gamemodehandler;

import com.jackalantern29.gamemodehandler.listeners.InventoryListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GamemodeHandler extends JavaPlugin {
    private static GamemodeHandler instance;
    @Override
    public void onEnable() {
        instance = this;
        new GamemodeHandlerSettings(this);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static GamemodeHandler getInstance() {
        return instance;
    }
    public static GamemodeHandlerSettings getSettings() {
        return GamemodeHandlerSettings.getSettings();
    }
}
