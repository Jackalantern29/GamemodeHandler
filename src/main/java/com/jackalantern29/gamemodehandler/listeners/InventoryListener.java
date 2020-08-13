package com.jackalantern29.gamemodehandler.listeners;

import com.jackalantern29.gamemodehandler.inventory.WorldInventoryAction;
import com.jackalantern29.gamemodehandler.worldsettings.WorldInventorySettings;
import com.jackalantern29.gamemodehandler.worldsettings.WorldSettings;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

    @EventHandler
    public void inventoryCreative(InventoryCreativeEvent event) {
        HumanEntity player = event.getWhoClicked();
        World world = player.getWorld();
        Material material = event.getCursor().getType();
        WorldInventorySettings settings = WorldSettings.getWorldSettings(world).getInventorySettings(InventoryType.CREATIVE);
        WorldInventoryAction invAction = settings.getAction(material);
        InventoryAction action = event.getAction();
        if(invAction == null)
            return;
        if(action.name().contains("DROP")) {
            event.setCancelled(invAction.onDrop(new WorldInventoryAction.Action(material)));
        } else if(action.name().contains("PICKUP")) {
            event.setCancelled(invAction.onPickup(new WorldInventoryAction.Action(material)));
        } else if(action.name().contains("PLACE")) {
            event.setCancelled(invAction.onPlace(new WorldInventoryAction.Action(material)));
        }

    }
}
