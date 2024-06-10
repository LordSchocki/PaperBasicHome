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
        HomeManager homeManager = HomeManager.getInstance();
        if (commandSender instanceof Player player){ //cast the sender to a player
            if (homeManager.playerHasHome(player)){
                player.sendMessage("You already have an home!"); //check if the player already has a home
            }
            else {
                homeManager.addPlayerHome(player,player.getLocation());// if it's their first home, let them set the home
            }
        }
        else {
            commandSender.sendPlainMessage("Only players can use this command!"); //only player can set homes
        }
        return false;
    }
}
