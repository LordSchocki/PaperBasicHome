package me.ventilover.paperbasichome;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeTeleportCommand extends Command {


    public HomeTeleportCommand(){
        super("home");
        this.setDescription("Command to teleport home");
    }

    public void teleportPlayerToHome(Player player, Location location){
        player.teleport(location); //method to teleport the player to a location
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        HomeManager homeManager = HomeManager.getInstance();
        if (commandSender instanceof Player player){ //first checking if a player is using the command
            if (homeManager.playerHasHome(player)){
                teleportPlayerToHome(player,homeManager.getPlayerHomeLocation(player)); //if it is then teleport the player to the home location
                player.sendMessage("Teleported home!");
            }
            else {
                player.sendMessage("You need to have a home to teleport there!"); //else they have to create a home first
            }
        }
        else {
            commandSender.sendPlainMessage("You need to be a player in order to use this command"); //if it isn't a player, e.g., a command block cant use the command
        }


        return false;
    }
}
