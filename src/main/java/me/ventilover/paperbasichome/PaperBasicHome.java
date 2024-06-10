package me.ventilover.paperbasichome;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperBasicHome extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("PaperBasicHome has started!");


    }

    @Override
    public void onDisable() {
        getLogger().info("PaperBasicHome has stopped!");
    }
}
