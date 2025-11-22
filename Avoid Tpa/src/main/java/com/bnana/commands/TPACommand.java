package com.bnana.commands;

import com.bnana.AvoidTPA;
import com.bnana.TPAStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {

    private final TPAStorage storage;
    private final AvoidTPA plugin;

    public TPACommand(TPAStorage storage, AvoidTPA plugin) {
        this.storage = storage;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if (args.length != 1) {
            p.sendMessage("§5Avoid §9Tpa §7> §fUsage: /tpa <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage("§5Avoid §9Tpa §7> §cPlayer not found.");
            return true;
        }

        storage.sendRequest(p, target);

        p.sendMessage("§5Avoid §9Tpa §7> §dSent request to §b" + target.getName());
        target.sendMessage("§5Avoid §9Tpa §7> §b" + p.getName() + " §dhas requested to teleport to you!");

        if (storage.isGuiEnabled(target)) {
            target.openInventory(plugin.getGuiManager().createTPAGUI(p));
        }

        return true;
    }
}
