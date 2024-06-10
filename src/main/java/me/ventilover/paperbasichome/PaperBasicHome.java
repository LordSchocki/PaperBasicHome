package me.ventilover.paperbasichome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperBasicHome extends JavaPlugin {

    @Override
    public void onEnable() {

        //register the commands
        CommandMap commandMap = Bukkit.getCommandMap();

        Command setHome = new SetHomeCommand();
        Command homeTeleport = new HomeTeleportCommand();

        commandMap.register("sethome",setHome);
        commandMap.register("home",homeTeleport);


        getLogger().info("PaperBasicHome has started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaperBasicHome has stopped!");
    }
}
