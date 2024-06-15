package me.ventilover.paperbasichome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHomeCommand extends Command {

    public SetHomeCommand(){
        super("sethome");
        this.setDescription("Set home command");
    }
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {

        //check for a home name
        if (!checkIfValidCommand(commandSender,strings)){
            return true; //don't run the command further
        }

        // then create the home
        String homeName = strings[0]; //the home name from the argument

        HomeManager homeManager = HomeManager.getInstance();
        if (commandSender instanceof Player player){ //cast the sender to a player
            if (!player.hasPermission("paperbasichome.sethome")){
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("You do not have valid Permissions"));
                return true;
            }

            if (homeManager.getPlayerHomesClass(player).checkIfHomeAlreadyExist(homeName)){
                //home name already given
                player.sendMessage(homeManager.makeErrorMessage("That home name already exists!"));
                return true;

            }
            if (homeManager.getPlayerHomesClass(player).checkHomeCapacityLessThanThree()){//check if the player already has 3 homes

                homeManager.addPlayerHome(player,player.getLocation(),homeName);// if they don't have 3 homes,
                // they can set one
            }
            else {
                player.sendMessage(homeManager.makeErrorMessage("You already have 3 homes!"));
            }
            return true;
        }



        return true; //return that the command has run
    }

    public boolean checkIfValidCommand(CommandSender commandSender, String[] strings){
        if (commandSender instanceof  Player player){
            if (strings.length != 1){
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("Please enter one valid home name!"));
                return false;
            }
            else {
                return true;
            }
        }

        else {
            commandSender.sendMessage("Only players can use this");
            return false;
        }
    }
}
