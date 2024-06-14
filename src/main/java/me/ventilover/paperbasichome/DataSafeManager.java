package me.ventilover.paperbasichome;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class DataSafeManager { //singleton class

    // this is a class for handling the two hashmap systems

    private static DataSafeManager instance; //instance object


    private DataSafeManager(){
        //private constructor
    }

    public static DataSafeManager getInstance() { //to get the safe manager
        if(instance==null){
            instance = new DataSafeManager();
        }
        return instance;
    }

    private File getDataFolder(){
        JavaPluginProvider provider = new JavaPluginProvider();
        return provider.getPlugin().getDataFolder();//Get the dataFolder of the plugin
    }


    public void saveHomesToFile(){ //to save homes into the yaml
        File dataFolder = getDataFolder(); //get the directory
        if(!dataFolder.exists()){ //if it doesn't exist yet create it
            boolean result = dataFolder.mkdirs();
            if (!result){
                JavaPluginProvider provider = new JavaPluginProvider();
                provider.getPlugin().getLogger().info("Did not achieve to create a Folder for data"); //error message if mkdirs dont work
            }
        }

        File file = new File(dataFolder,"playerHomes.yml"); //make a file object for the actual yaml
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);// load the config for the file and put it into a variable

        for (UUID uuid : HomeManager.getInstance().getPlayerIdHashMap().keySet()){ //looping through the hashmap
            PlayerHomes homes = HomeManager.getInstance().getPlayerIdHashMap().get(uuid); //for each uuid get the playerHomes instance
            //safe the data now
            config.set(uuid.toString(),homes.serialize()); //safe the playerHoms instance into the config
        }

        try{
            config.save(file); //safe the config
        }catch (Exception ex){
            JavaPluginProvider provider = new JavaPluginProvider();
            provider.getPlugin().getLogger().info(ex.getCause()+ex.getMessage());
        }
    }


    public void loadIdHashMap(){
        File dataFolder = getDataFolder(); //get the data folder
        File file = new File(dataFolder, "playerHomes.yml"); //create the file object
        // if it doesn't exist stop
        if (!file.exists()){
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file); //load the config again

        for (String uuidString : config.getKeys(false)){
            try {
                UUID uuid = UUID.fromString(uuidString);
                PlayerHomes homes = PlayerHomes.deserialize(Objects.requireNonNull(config.getConfigurationSection(uuidString)).getValues(false)); // use deserialization
                HomeManager.getInstance().getPlayerIdHashMap().put(uuid, homes);
            } catch (IllegalArgumentException ex) {
                JavaPluginProvider provider = new JavaPluginProvider();
                provider.getPlugin().getLogger().info("Error loading home for UUID " + uuidString + ": " + ex.getMessage());
            }
        }
    }
}
