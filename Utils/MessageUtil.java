package me.araidkub.customspawnpoint.Utils;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageUtil {

    private static File messageFile;
    private CustomSpawnPoint customSpawnPoint;

    public MessageUtil(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    private static FileConfiguration message;

    public static void registerMessageFiles() {
        messageFile = new File("plugins//CustomSpawnPoint//message.yml");
        message = YamlConfiguration.loadConfiguration(messageFile);
        message.set("message", "&c&lYOU DIED");
        saveMessage();
    }

    public File getMessageFile() {
        return messageFile;
    }

     public static FileConfiguration getMessage() {
        return message;
    }

    public static void saveMessage() {

        try {
            message.save(messageFile);
        } catch (Exception e) {

        }
    }

    public void sendMessage(String player, String message) {
        Bukkit.getPlayer(player).sendMessage(message);
    }
}
