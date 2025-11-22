package com.bnana;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TPAStorage {

    private final HashMap<Player, Player> requests = new HashMap<>();
    private final HashMap<Player, Boolean> guiToggled = new HashMap<>();

    public void sendRequest(Player from, Player to) {
        requests.put(to, from);
    }

    public Player getRequester(Player to) {
        return requests.get(to);
    }

    public void remove(Player p) {
        requests.remove(p);
    }

    public boolean isGuiEnabled(Player p) {
        return guiToggled.getOrDefault(p, true);
    }

    public void toggleGUI(Player p) {
        guiToggled.put(p, !isGuiEnabled(p));
    }
}
