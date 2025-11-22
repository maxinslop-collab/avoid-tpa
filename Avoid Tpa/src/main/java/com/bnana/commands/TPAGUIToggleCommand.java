package com.bnana.commands;

import com.bnana.TPAStorage;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPAGUIToggleCommand implements CommandExecutor {

    private final TPAStorage storage;

    public TPAGUIToggleCommand(TPAStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        storage.toggleGUI(p);

        boolean enabled = storage.isGuiEnabled(p);

        p.sendMessage("§5Avoid §9Tpa §7> §fGUI toggled: " + (enabled ? "§aON" : "§cOFF"));

        return true;
    }
}
