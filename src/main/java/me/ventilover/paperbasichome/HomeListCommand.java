package me.ventilover.paperbasichome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeListCommand extends Command {

    public HomeListCommand(){
        super("homes");
        this.description = "A command to list all homes a player has";
    }


    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            if (!player.hasPermission("paperbasichome.homes")){
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("You do not have valid Permissions"));
            }

            if (HomeManager.getInstance().playerHasHome(player)){
                player.sendMessage(createHomeMessage(HomeManager.getInstance().getPlayerHomesClass(player))); //if the player has homes, send them the list
            }
            else {
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("You don't have any homes!")); //message if the player does not have any homes
            }
        }
        else {
            commandSender.sendMessage("Only players can use this command!");
        }




        return true;
    }

    public String createHomeMessage(PlayerHomes playerHomes){
        StringBuilder builder = new StringBuilder();
        builder.append("You homes are: ");

        // we use a string builder to make the return string


        for (Home home : playerHomes.getHomeArrayList()){
            builder.append(home.getHomeName()).append(", "); //get each home
        }

        if (!builder.isEmpty()){
            builder.setLength(builder.length()-2); //remove extra spaces
        }

        return builder.toString();
    }
}
