package com.jackalantern29.gamemodehandler.inventory;

import org.bukkit.Material;

public interface WorldInventoryAction {

    boolean onDrop(Action action);
    boolean onPickup(Action action);
    boolean onPlace(Action action);
    public static class Action {
        private final Material material;

        public Action(Material material) {
            this.material = material;
        }

        public Material getMaterial() {
            return material;
        }
    }
}
