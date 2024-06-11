package me.ventilover.paperbasichome;

import org.bukkit.Location;

public class Home { //class for the actual homes
    private final Location homeLocation;
    private final String homeName;

    public Home (Location location, String homeName){
        this.homeLocation = location;
        this.homeName = homeName;
    }

    public String getHomeName() {
        return homeName;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }
}
