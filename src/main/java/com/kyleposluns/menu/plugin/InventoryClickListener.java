package com.kyleposluns.menu.plugin;

import com.kyleposluns.menu.inventorymenu.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * Created by Kyle on 10/7/16.
 */
public class InventoryClickListener implements Listener {

    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof InventoryMenu) {
            InventoryMenu menu = (InventoryMenu) inventory.getHolder();
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
                    exitMenuIfClickOutSide(menu, player);
                } else {
                    int index = event.getRawSlot();
                    if (index < inventory.getSize()) {
                        menu.selectItem(index);
                    } else {
                        exitMenuIfClickOutSide(menu, player);
                    }
                }
            }
            event.setCancelled(true);
        }
    }

    private void exitMenuIfClickOutSide(InventoryMenu menu, Player player) {
        if (menu.exitOnClickOutside()) {
            menu.close(player);
        }
    }
}