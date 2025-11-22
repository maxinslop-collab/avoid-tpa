package com.bnana.listeners;

import com.bnana.AvoidTPA;
import com.bnana.GUIManager;
import com.bnana.TPAStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final TPAStorage storage;
    private final GUIManager gui;

    public InventoryClickListener(TPAStorage storage, GUIManager gui) {
        this.storage = storage;
        this.gui = gui;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§5§lAvoid §9§lTpa")) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            Player req = storage.getRequester(p);

            if (req == null) {
                p.closeInventory();
                return;
            }

            switch (e.getSlot()) {
                case 14: // Accept
                    p.teleport(req.getLocation());
                    p.sendMessage("§5Avoid §9Tpa §7> §aAccepted request.");
                    req.sendMessage("§5Avoid §9Tpa §7> §aYour request was accepted!");
                    storage.remove(p);
                    p.closeInventory();
                    break;

                case 12: // Deny
                    p.sendMessage("§5Avoid §9Tpa §7> §cYou denied the request.");
                    req.sendMessage("§5Avoid §9Tpa §7> §cYour request was denied!");
                    storage.remove(p);
                    p.closeInventory();
                    break;
            }
        }
    }
}
