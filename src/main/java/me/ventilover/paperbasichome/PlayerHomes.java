package me.ventilover.paperbasichome;

import org.bukkit.Location;

import java.util.ArrayList;


public class PlayerHomes {
    private final ArrayList<Home> homeArrayList = new ArrayList<>(); //3 homes to hold


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


    public Home getHomeByName(String homeNameToFind) throws Exception {
        Home homeToFind = null;

        for (Home home : homeArrayList){
            if (home.getHomeName().equalsIgnoreCase(homeNameToFind)){ //search for the home case-insensitive in the array
                homeToFind = home; //if found, break the loop and assign the home variable
                break;
            }
        }

        //if the homeToFind is still null we didn't find a home
        if (homeNameToFind == null){
            throw new Exception("No home has been found");
        }

        return homeToFind;
    }

    public ArrayList<Home> getHomeArrayList() {
        return homeArrayList;
    }
}
