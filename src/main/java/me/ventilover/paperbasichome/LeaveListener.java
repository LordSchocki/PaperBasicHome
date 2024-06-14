package me.ventilover.paperbasichome;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class LeaveListener implements Listener {

    //two hashmaps one for uuid one for current players add here listener method if leaving to remove the session hashmap entrance also make logic onLoad to load the config and save etc

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        HomeManager manager = HomeManager.getInstance();
        manager.detachPlayerHomesHashMap(event.getPlayer());// remove the session instance
    }
}
