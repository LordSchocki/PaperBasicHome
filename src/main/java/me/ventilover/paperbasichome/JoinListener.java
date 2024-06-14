package me.ventilover.paperbasichome;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        HomeManager homeManager = HomeManager.getInstance(); //homemanager variable

         if (!homeManager.hasPlayerHomes(event.getPlayer().getUniqueId())){ //if the player does not yet have one uuid in the hashmap
             homeManager.createPlayerHomesClass(event.getPlayer()); //create the uuid in the saving hashmap
             homeManager.attachPlayerHomesHashmap(event.getPlayer(), homeManager.getPlayerHomesClassFromId(event.getPlayer().getUniqueId()) ); //then update the local hashmap
         }
         else {
             homeManager.attachPlayerHomesHashmap(event.getPlayer(), homeManager.getPlayerHomesClassFromId(event.getPlayer().getUniqueId()));//same here without creating
         }
    }
}
