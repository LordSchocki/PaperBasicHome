package me.ventilover.paperbasichome;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PlayerHomes {
    private final ArrayList<Home> homeArrayList = new ArrayList<>(); //3 homes to hold

    private boolean isTeleporting = false; //boolean to check if the player is currently teleporting



    public void createPlayerHome(Location location, String homeName){
        //class for creating the home
        if (checkHomeCapacityLessThanThree()){

            homeArrayList.add(new Home(location,homeName)); // add the home into the arraylist
        }
    }

    public boolean checkHomeCapacityLessThanThree(){ //returns true if the user has less then 3 homes
        return homeArrayList.size() < 3;
    }

    public boolean checkIfPlayerHasHomes(){ //returns true if the player has homes
        return !homeArrayList.isEmpty();
    }

    public ArrayList<String> getHomeNameArrayList(){// method to get the homes into an home name array list
        //useful for the auto complete

        ArrayList<String>resultArray = new ArrayList<>(); //declare a new arraylist

        for (Home home : homeArrayList){
            resultArray.add(home.getHomeName()); //fill it with home names
        }
        return resultArray;//return it
    }


    public Home getHomeByName(String homeNameToFind) throws Exception {
        Home homeToFind = null;

        for (Home home : homeArrayList){
            if (home.getHomeName().equalsIgnoreCase(homeNameToFind)){ //search for the home case-insensitive in the array
                homeToFind = home; //if found, break the loop and assign the home variable
                break;
            }
        }

        //if the homeToFind is still null we didn't find a home
        if (homeToFind == null){
            throw new Exception("No home has been found");
        }

        return homeToFind;
    }

    public ArrayList<Home> getHomeArrayList() {
        return homeArrayList;
    }

    public void setTeleportingTrue(){
        this.isTeleporting = true;
    }

    public void setTeleportingFalse(){
        this.isTeleporting = false;
    }

    public boolean getTeleportingState(){
        return isTeleporting;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> homesList = new ArrayList<>();
        for (Home home : homeArrayList) {
            homesList.add(home.serialize());
        }
        map.put("homes", homesList);
        return map;
    }

    public static PlayerHomes deserialize(Map<String, Object> map) { //complicated deserializing of the playerhomes class
        PlayerHomes playerHomes = new PlayerHomes();
        @SuppressWarnings("unchecked") //a bit unsafe but cant really check
        ArrayList<Map<String, Object>> homesList = (ArrayList<Map<String, Object>>) map.get("homes"); //
        if (homesList != null) {
            for (Map<String, Object> homeMap : homesList) {
                playerHomes.homeArrayList.add(Home.deserialize(homeMap));
            }
        }
        return playerHomes;
    }
}
