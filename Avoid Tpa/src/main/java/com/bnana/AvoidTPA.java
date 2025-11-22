package com.bnana;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.bnana.commands.*;
import com.bnana.listeners.InventoryClickListener;

public class AvoidTPA extends JavaPlugin {

    private static AvoidTPA instance;
    private TPAStorage storage;
    private GUIManager guiManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        storage = new TPAStorage();
        guiManager = new GUIManager(this);

        getCommand("tpa").setExecutor(new TPACommand(storage, this));
        getCommand("tpahere").setExecutor(new TPAHereCommand(storage, this));
        getCommand("tpaccept").setExecutor(new TPAcceptCommand(storage));
        getCommand("tpdeny").setExecutor(new TPADenyCommand(storage));
        getCommand("tpaguitoggle").setExecutor(new TPAGUIToggleCommand(storage));

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(storage, guiManager), this);

        getLogger().info("AvoidTPA enabled.");
    }

    public static AvoidTPA getInstance() {
        return instance;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }
}
