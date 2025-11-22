package com.bnana.commands;

import com.bnana.TPAStorage;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPADenyCommand implements CommandExecutor {

    private final TPAStorage storage;

    public TPADenyCommand(TPAStorage storage) {
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

        p.sendMessage("§5Avoid §9Tpa §7> §cDenied tpa request.");
        req.sendMessage("§5Avoid §9Tpa §7> §cYour request was denied!");

        storage.remove(p);

        return true;
    }
}
