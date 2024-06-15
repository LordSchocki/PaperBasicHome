package me.ventilover.paperbasichome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveHomeCommand extends Command {

    public RemoveHomeCommand(){
        super("delhome");
        this.description = "Command to remove a home";
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {

        //check for a home name
        if (!checkIfValidCommand(commandSender,strings)){
            return true; //don't run the command further
        }
        Player player = (Player) commandSender;

        if (!player.hasPermission("paperbasichome.delhome")){
            player.sendMessage(HomeManager.getInstance().makeErrorMessage("You do not have valid Permissions")); //check the permissions
            return true;
        }
        String homeName = strings[0]; //the home name from the argument
        try {
            HomeManager.getInstance().removeHome(player,homeName);
        } catch (Exception ex) {
            player.sendMessage(HomeManager.getInstance().makeErrorMessage("No such home to delete!"));
            return true;
        }

        return true;
    }

    public boolean checkIfValidCommand(CommandSender commandSender, String[] strings){
        if (commandSender instanceof  Player player){
            if (strings.length != 1){
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("Please enter a valid home name")); //check if the command is valid
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
