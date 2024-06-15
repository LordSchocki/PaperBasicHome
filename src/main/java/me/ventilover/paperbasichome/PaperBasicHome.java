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

        //get the commandMap
        CommandMap commandMap = Bukkit.getCommandMap();

        //create instances of the Commands
        Command setHome = new SetHomeCommand();
        Command homeTeleport = new HomeTeleportCommand();
        Command homeListCmd = new HomeListCommand();
        Command delHomeCmd = new RemoveHomeCommand();

        //register the commands
        commandMap.register("sethome",setHome);
        commandMap.register("home",homeTeleport);
        commandMap.register("homes",homeListCmd);
        commandMap.register("delhome",delHomeCmd);

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

        //log stop of the plugin
        getLogger().info("PaperBasicHome has stopped!");
    }
}
