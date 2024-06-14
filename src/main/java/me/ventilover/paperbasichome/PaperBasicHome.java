package me.ventilover.paperbasichome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperBasicHome extends JavaPlugin {

    @Override
    public void onEnable() {

        //set the plugin provider
        JavaPluginProvider provider = new JavaPluginProvider();
        provider.setPlugin(this);


        //register the commands
        CommandMap commandMap = Bukkit.getCommandMap();

        Command setHome = new SetHomeCommand();
        Command homeTeleport = new HomeTeleportCommand();
        Command homeListCmd = new HomeListCommand();

        commandMap.register("sethome",setHome);
        commandMap.register("home",homeTeleport);
        commandMap.register("homes",homeListCmd);
        // register the event listener
        Bukkit.getPluginManager().registerEvents(new JoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new TeleportMoveListener(),this);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(),this);

        //Load the hashmaps
        DataSafeManager.getInstance().loadIdHashMap();

        //Log the start of the plugin
        getLogger().info("PaperBasicHome has started!");
    }

    @Override
    public void onDisable() {
        //safe the hashmap
        DataSafeManager.getInstance().saveHomesToFile();
        getLogger().info("PaperBasicHome has stopped!");
    }
}
