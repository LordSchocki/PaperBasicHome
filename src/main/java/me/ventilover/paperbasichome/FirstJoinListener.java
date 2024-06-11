package me.ventilover.paperbasichome;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class FirstJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        // if its a players first time joining, we are creating a player home class for the hashmap
        //for testing purpose its every time they join because theres not yet a config to safe them
        /*
        if (!event.getPlayer().hasPlayedBefore()){
            HomeManager.getInstance().createPlayerHomesClass(event.getPlayer());
        }
        */
         HomeManager.getInstance().createPlayerHomesClass(event.getPlayer());
    }
}
