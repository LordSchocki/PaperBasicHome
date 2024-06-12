package me.ventilover.paperbasichome;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class TeleportMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (!HomeManager.getInstance().getPlayerHomesClass(event.getPlayer()).getTeleportingState()){ //check if player is even teleporting
            return;
        }

        //the player is teleporting if the code here is reached
        if (hasPlayerReallyMoved(event)){
            cancelTaskForPlayer(event.getPlayer()); //cancel the scheduler
        }

    }

    public void cancelTaskForPlayer(Player player){

        //get the taskId

        Integer taskId = HomeManager.getInstance().getPlayerTasks().get(player); //first get the scheduler task



        if (taskId != null) {
            Bukkit.getScheduler().cancelTask(taskId); //then cancel it

            HomeManager.getInstance().getPlayerTasks().remove(player); //also remove the task from the hashmap
            HomeManager.getInstance().getPlayerHomesClass(player).setTeleportingFalse();
            //send the player a cancellation message
            player.sendMessage(" You moved while teleporting!");
        }

    }


    public boolean hasPlayerReallyMoved(PlayerMoveEvent event){
        Location from = event.getFrom();
        Location to = event.getTo();

        //Check if location is diffrent
        return from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ(); //false if player has just looked
    }
}
