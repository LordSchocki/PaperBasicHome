package me.ventilover.paperbasichome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeTeleportCommand extends Command {


    public HomeTeleportCommand(){
        super("home");
        this.setDescription("Command to teleport home");
    }

    public void teleportPlayerToHome(Player player,Home home){
        player.teleport(home.getHomeLocation()); //method to teleport the player to a location
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {

        HomeManager homeManager = HomeManager.getInstance(); //get the home manager

        //check for a home name
        if (!checkIfValidCommand(commandSender,strings)){
            return true; //don't run the command further
        }

        Player player = (Player) commandSender; //cast the commandSender to a player

        if (!homeManager.playerHasHome(player)){
            //player doesn't have anyhomes so none to tp to
            player.sendMessage("You dont have any home to teleport to!");
            return true;
        }

        // then get the home name
        String homeName = strings[0]; //the home name from the argument



        try{
            teleportPlayerToHome(player,homeManager.getPlayerHome(player,homeName));
        }catch (Exception ex){
            player.sendMessage("No such home name!");
            return true;
        }


        return true;
    }

    public boolean checkIfValidCommand(CommandSender commandSender, String[] strings){
        if (commandSender instanceof  Player player){
            if (strings.length != 1){
                player.sendMessage("Please enter one valid home name!");
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
