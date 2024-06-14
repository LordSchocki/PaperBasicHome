package me.ventilover.paperbasichome;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

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


    public Map<String, Object> serialize() { //method to serialize the home class
        Map<String, Object> map = new HashMap<>();
        map.put("world", homeLocation.getWorld().getName());
        map.put("x", homeLocation.getX());
        map.put("y", homeLocation.getY());
        map.put("z", homeLocation.getZ());
        map.put("yaw", homeLocation.getYaw());
        map.put("pitch", homeLocation.getPitch());
        map.put("name", homeName);
        return map;
    }

    public static Home deserialize(Map<String, Object> map) { //method to get a home by deserialization
        Location location = new Location(
                Bukkit.getWorld((String) map.get("world")),
                (double) map.get("x"),
                (double) map.get("y"),
                (double) map.get("z"),
                ((Double) map.get("yaw")).floatValue(),
                ((Double) map.get("pitch")).floatValue()
        );
        return new Home(location, (String) map.get("name"));
    }
}
