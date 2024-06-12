package me.ventilover.paperbasichome;

import org.bukkit.plugin.java.JavaPlugin;

public class JavaPluginProvider { //class to get the java plugin instance

    private static JavaPlugin plugin;


    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        JavaPluginProvider.plugin = plugin;
    }
}
