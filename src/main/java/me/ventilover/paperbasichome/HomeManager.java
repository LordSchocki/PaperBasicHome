package me.ventilover.paperbasichome;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class HomeManager { //singelton class for management

    private static HomeManager instance;

    private static final HashMap<Player, PlayerHomes> playerHomesHashMapHashMap = new HashMap<>();

    private final HashMap<Player,Integer> playerTasks = new HashMap<>();

    private HomeManager(){
        //private constructor to interrupt init
    }


    public static HomeManager getInstance() { //to get the homemanager
        if(instance==null){
            instance = new HomeManager();
        }
        return instance;
    }

    public boolean playerHasHome(Player player){
        return getPlayerHomesClass(player).checkIfPlayerHasHomes();
    }

    public PlayerHomes getPlayerHomesClass(Player player){ //method to get the home location of a player
        // (useful for teleportation)
        return playerHomesHashMapHashMap.get(player);

    }

    public Home getPlayerHome(Player player,String homeName) throws Exception { //get the player home can cause exception when no such homename exist
        PlayerHomes playerHomesClass= getPlayerHomesClass(player);
        return playerHomesClass.getHomeByName(homeName);
    }

    public void addPlayerHome(Player player,Location location,String homeName){ //method to add a new home
        playerHomesHashMapHashMap.get(player).createPlayerHome(location,homeName);
        player.sendMessage("Created a new home!");
    }

    public void createPlayerHomesClass(Player player){ //method to create a playerhomesclass for a new player
        playerHomesHashMapHashMap.put(player,new PlayerHomes());
    }

    public HashMap<Player, Integer> getPlayerTasks() {
        return playerTasks;
    }

    public Component makeErrorMessage(String message){ //method to make error messages

        return Component.text(message) //use new component to make text red
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD);
    }

    public Component makeConfirmMessage(String message){ //method to make error messages

        return Component.text(message) //use new component to make text red
                .color(NamedTextColor.DARK_GREEN)
                .decorate(TextDecoration.ITALIC)
                .decorate(TextDecoration.BOLD);
    }
}
