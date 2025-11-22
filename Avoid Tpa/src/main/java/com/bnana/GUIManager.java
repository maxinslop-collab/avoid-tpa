package com.bnana;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIManager {

    private final AvoidTPA plugin;

    public GUIManager(AvoidTPA plugin) {
        this.plugin = plugin;
    }

    public Inventory createTPAGUI(Player requester) {
        FileConfiguration cfg = plugin.getConfig();

        Inventory inv = Bukkit.createInventory(null, 27, "§5§lAvoid §9§lTpa");

        ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        // Fill everything with placeholder
        for (int i = 0; i < 27; i++) inv.setItem(i, empty);

        // Player head of requester
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(requester);
        meta.setDisplayName("§dRequest from: §b" + requester.getName());
        head.setItemMeta(meta);

        inv.setItem(13, head);

        // Accept button
        ItemStack accept = new ItemStack(Material.LIME_CONCRETE);
        accept.getItemMeta().setDisplayName("§a§lACCEPT");
        inv.setItem(14, accept);

        // Deny button
        ItemStack deny = new ItemStack(Material.RED_CONCRETE);
        deny.getItemMeta().setDisplayName("§c§lDENY");
        inv.setItem(12, deny);

        // World icon
        String world = requester.getWorld().getName();
        String matName = cfg.getString("world-icons." + world, "GRASS_BLOCK");
        Material mat = Material.getMaterial(matName.toUpperCase());

        if (mat == null) mat = Material.GRASS_BLOCK;

        inv.setItem(11, new ItemStack(mat));

        return inv;
    }
}
