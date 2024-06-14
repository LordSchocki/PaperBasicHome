package me.ventilover.paperbasichome;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.UUID;

public class DataSafeManager { //singleton class

    private static DataSafeManager instance;

    private DataSafeManager(){
        //private constructor
    }

    public static DataSafeManager getInstance() { //to get the safe mananger
        if(instance==null){
            instance = new DataSafeManager();
        }
        return instance;
    }

    private File getDataFolder(){
        JavaPluginProvider prover = new JavaPluginProvider();
        return prover.getPlugin().getDataFolder();
    }


    public void saveHomesToFile(){
        File dataFolder = getDataFolder();
        if(!dataFolder.exists()){
            boolean result = dataFolder.mkdirs();
            if (!result){
                JavaPluginProvider provider = new JavaPluginProvider();
                provider.getPlugin().getLogger().info("Did not achieve to create a Folder for data"); //error message if mkdirs dont work
            }
        }

        File file = new File(dataFolder,"playerHomes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (UUID uuid : HomeManager.getInstance().getPlayerIdHashMap().keySet()){
            PlayerHomes homes = HomeManager.getInstance().getPlayerIdHashMap().get(uuid);
            //safe the data now
            config.set(uuid.toString(),homes);
        }

        try{
            config.save(file); //safe  the config
        }catch (Exception ex){
            JavaPluginProvider provider = new JavaPluginProvider();
            provider.getPlugin().getLogger().info(ex.getCause()+ex.getMessage());
        }
    }


    public void loadIdHashMap(){
        File dataFolder = getDataFolder(); //get the data folder
        File file = new File(dataFolder, "playerHomes.yml"); //create the file object
        // if it doesnt exist stop
        if (!file.exists()){
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (String uuidString : config.getKeys(false)){
            UUID uuid = UUID.fromString(uuidString);
            PlayerHomes homes = (PlayerHomes) config.get(uuidString);
            //load the data into the hashmap
            HomeManager.getInstance().getPlayerIdHashMap().put(uuid, homes);
        }
    }
}
