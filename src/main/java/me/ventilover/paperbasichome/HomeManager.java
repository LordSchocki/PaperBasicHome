package me.ventilover.paperbasichome;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HomeManager { //singelton class for management

    private static HomeManager instance;

    private static final HashMap<Player, Location>playerLocationHashMap = new HashMap<>();

    private HomeManager(){
        //private constructor to interrupt init
    }


    public static HomeManager getInstance() { //to get the homemanager
        if(instance==null){
            instance = new HomeManager();
        }
        return instance;
    }

    public boolean playerHasHome(Player player){ //method to check if a player already owns a home
        return playerLocationHashMap.containsKey(player);
    }

    public Location getPlayerHomeLocation(Player player){ //method to get the home location of a player (useful for teleportation)
        return playerLocationHashMap.get(player);

    }

    public void addPlayerHome(Player player,Location location){ //method to add a new home to the hashmap
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL){ //only homes in the normal world for now
            playerLocationHashMap.put(player,location);
        }
        else {
            player.sendMessage("You can only set homes in the normal world");
        }

    }

    public void removePlayerHome(Player player){ //method to remove a home
        playerLocationHashMap.remove(player);
    }


}
