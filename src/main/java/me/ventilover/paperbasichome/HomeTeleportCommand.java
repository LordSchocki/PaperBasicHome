package me.ventilover.paperbasichome;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeTeleportCommand extends Command {

    JavaPluginProvider provider;

    public HomeTeleportCommand(){
        super("home");
        this.setDescription("Command to teleport home");
        provider = new JavaPluginProvider();
    }

    public void teleportPlayerToHome(Player player,Home home){
        if (HomeManager.getInstance().getPlayerHomesClass(player).getTeleportingState()){
            player.sendMessage(HomeManager.getInstance().makeErrorMessage("You are already teleporting!"));
        }
        else {
            HomeManager.getInstance().getPlayerHomesClass(player).setTeleportingTrue(); //set the boolean variable true when the player starts teleporting

            //scheduler for teleporting
            //saves the task id for later
            int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(provider.getPlugin(), () -> {
                player.teleport(home.getHomeLocation()); //teleporting the player now 5 seconds later
                // if the scheduler doesn't get canceled
                HomeManager.getInstance().getPlayerHomesClass(player).setTeleportingFalse();//stop teleporting
                player.sendMessage(HomeManager.getInstance().makeConfirmMessage("Teleported home!"));
            }, 5L * 5L);
            // now put the task it into the hashmap
            HomeManager.getInstance().getPlayerTasks().put(player,taskId);
        }
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
            //player doesn't have any homes so none to tp to
            player.sendMessage(homeManager.makeErrorMessage("You don't have any home to teleport to!"));
            return true;
        }

        // then get the home name
        String homeName = strings[0]; //the home name from the argument



        try{
            Home home = homeManager.getPlayerHome(player,homeName); //try block to catch if the home is null
            teleportPlayerToHome(player,home);

        }catch (Exception ex){
            player.sendMessage(homeManager.makeErrorMessage("No such home name!"));
            return true;
        }


        return true;
    }

    public boolean checkIfValidCommand(CommandSender commandSender, String[] strings){
        if (commandSender instanceof  Player player){
            if (!player.hasPermission("paperbasichome.home")){
                player.sendMessage(HomeManager.getInstance().makeErrorMessage("You do not have valid Permissions"));
            }
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
