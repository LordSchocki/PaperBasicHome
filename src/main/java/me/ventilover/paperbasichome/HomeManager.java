package me.ventilover.paperbasichome;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;


public class HomeManager { //singelton class for management

    private static HomeManager instance;

    public static HashMap<UUID,PlayerHomes> playerIdHashMap = new HashMap<>();

    private static final HashMap<Player, PlayerHomes> playerHomesHashMap = new HashMap<>();

    private final HashMap<Player,Integer> playerTasks = new HashMap<>();

    private HomeManager(){
        //private constructor to interrupt init
    }

    public void attachPlayerHomesHashmap(Player player, PlayerHomes playerHomes){
        playerHomesHashMap.put(player,playerHomes); //class to add something into the session hashmap
    }

    public void detachPlayerHomesHashMap(Player player){
        playerHomesHashMap.remove(player); //remove the player from the session (used on leave listener)
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

    public HashMap<UUID, PlayerHomes> getPlayerIdHashMap() {
        return playerIdHashMap;
    }

    public PlayerHomes getPlayerHomesClass(Player player){ //method to get the home location of a player
        // (useful for teleportation)
        return playerHomesHashMap.get(player);

    }

    public PlayerHomes getPlayerHomesClassFromId(UUID uuid){
        return playerIdHashMap.get(uuid);
    }

    public Home getPlayerHome(Player player,String homeName) throws Exception { //get the player home
        // can cause exception
        // when no such home name exists
        PlayerHomes playerHomesClass= getPlayerHomesClass(player);
        return playerHomesClass.getHomeByName(homeName);
    }

    public void addPlayerHome(Player player,Location location,String homeName){ //method to add a new home
        playerHomesHashMap.get(player).createPlayerHome(location,homeName);
        player.sendMessage("Created a new home!");
    }

    public boolean hasPlayerHomes(UUID uuid){
        return playerIdHashMap.containsKey(uuid);
    }

    public void createPlayerHomesClass(Player player){ //method to create a playerhomes class for a new player
        //creates a new playerhomes instance
        playerIdHashMap.put(player.getUniqueId(),new PlayerHomes());//safe it into the hashmap
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
