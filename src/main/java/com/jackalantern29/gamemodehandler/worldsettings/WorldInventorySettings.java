package com.jackalantern29.gamemodehandler.worldsettings;

import com.jackalantern29.gamemodehandler.inventory.WorldInventoryAction;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryType;

import java.util.EnumMap;

public class WorldInventorySettings extends WorldSettings {
    private InventoryType type;
    private final EnumMap<Material, WorldInventoryAction> actions = new EnumMap<>(Material.class);

    protected WorldInventorySettings(World world, InventoryType type) {
        super(world);
    }

    public void setAction(Material material, WorldInventoryAction action) {
        actions.put(material, action);
    }

    public void removeAction(Material material) {
        actions.remove(material);
    }

    public boolean containsAction(Material material) {
        return actions.containsKey(material);
    }

    public WorldInventoryAction getAction(Material material) {
        return actions.get(material);
    }

}
