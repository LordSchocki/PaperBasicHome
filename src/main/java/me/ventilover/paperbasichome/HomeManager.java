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

    public static HashMap<UUID,PlayerHomes> playerIdHashMap = new HashMap<>(); //first hashmap for the actual data
    // it holds the uuid and player home instance

    private static final HashMap<Player, PlayerHomes> playerHomesHashMap = new HashMap<>(); //this hashmap is updated on every join or leave
    // and holds the playerhomes instance and a player instance

    private final HashMap<Player,Integer> playerTasks = new HashMap<>(); //Hashmap for every player scheduler

    private HomeManager(){
        //private constructor to interrupt init
    }

    public void attachPlayerHomesHashmap(Player player, PlayerHomes playerHomes){
        playerHomesHashMap.put(player,playerHomes); //method to put a new player into the sessionHashMap
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
        return getPlayerHomesClass(player).checkIfPlayerHasHomes(); //check if the player has homes via the playerhomes instance
    }

    public HashMap<UUID, PlayerHomes> getPlayerIdHashMap() {
        return playerIdHashMap; //get the IdHashMap
    }

    public PlayerHomes getPlayerHomesClass(Player player){ //method to get PlayerHomes Instance from the session hashmap
        // (useful for teleportation)
        return playerHomesHashMap.get(player);

    }

    public PlayerHomes getPlayerHomesClassFromId(UUID uuid){ //method to get the playerhomes instance from an uuid
        return playerIdHashMap.get(uuid);
    }

    public Home getPlayerHome(Player player,String homeName) throws Exception { //get the player home
        // can cause exception
        // when no such home name exists
        PlayerHomes playerHomesClass= getPlayerHomesClass(player);
        return playerHomesClass.getHomeByName(homeName);
    }

    public void addPlayerHome(Player player,Location location,String homeName){ //method to add a new home
        playerHomesHashMap.get(player).createPlayerHome(location,homeName);//make the method via the playerHomesClass
        player.sendMessage(HomeManager.instance.makeConfirmMessage("Successfully created a new Home!"));
    }

    public boolean hasPlayerHomesInstance(UUID uuid){
        return playerIdHashMap.containsKey(uuid); //check if player already has an entry in the data hashmap
    }

    public void createPlayerHomesClass(Player player){ //method to create a playerhomes class for a new player
        //creates a new playerhomes instance
        playerIdHashMap.put(player.getUniqueId(),new PlayerHomes());//safe it into the hashmap
    }

    public HashMap<Player, Integer> getPlayerTasks() { //method to get the scheduler of the player that is teleporting
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
