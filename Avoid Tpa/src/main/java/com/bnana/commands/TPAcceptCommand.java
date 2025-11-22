package com.bnana.commands;

import com.bnana.TPAStorage;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPAcceptCommand implements CommandExecutor {

    private final TPAStorage storage;

    public TPAcceptCommand(TPAStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        Player req = storage.getRequester(p);

        if (req == null) {
            p.sendMessage("§5Avoid §9Tpa §7> §cYou have no pending requests.");
            return true;
        }

        p.teleport(req.getLocation());
        p.sendMessage("§5Avoid §9Tpa §7> §aAccepted request.");
        req.sendMessage("§5Avoid §9Tpa §7> §aYour request was accepted!");

        storage.remove(p);

        return true;
    }
}
