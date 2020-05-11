package net.questcraft.config;


import net.questcraft.StringUtil;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;

public class ConfigUtil {
    private static ConfigUtil instance;

    ConfigReader configReader;
    StringUtil stringUtil = new StringUtil();

    public ConfigUtil() {
        configReader = ConfigReader.getInstance();
    }

    public HashMap<String, String> getAdmins() {
        try {
            return stringUtil.stringToMap(configReader.getProp("admins"));
        } catch (IOException e) {
            return new HashMap<>();
        }
    }
    public void addPlayer(Player player) {
        mcantigrief.java.ConfigReader griefConfigR = mcantigrief.java.ConfigReader.getInstance();
        HashMap playerList;
        try {
            playerList = stringUtil.mapToLowerCase(stringUtil.stringToMap(griefConfigR.getProp("playerList")));
        } catch (IOException ex) {
            playerList = new HashMap<String, String>();
        }
    }
    public Boolean playerHasPerm(Player player) {
        mcantigrief.java.ConfigReader griefConfigR = mcantigrief.java.ConfigReader.getInstance();
        HashMap playerList;
        try {
            playerList = stringUtil.mapToLowerCase(stringUtil.stringToMap(griefConfigR.getProp("playerList")));
        } catch (IOException ex) {
            playerList = new HashMap<String, String>();
        }
        return (playerList.containsValue(player.getUniqueId()) || playerList.containsKey(player.getDisplayName()));
    }
    public void addAdmin(Player player) {
        HashMap<String, String> admins = new HashMap<>();
        try {
             admins = stringUtil.stringToMap(configReader.getProp("admins"));
            admins.put(player.getDisplayName().toLowerCase(), player.getUniqueId().toString());
        } catch (IOException ex) {
            admins.put(player.getDisplayName().toLowerCase(), player.getUniqueId().toString());
        } finally {
            try {
                configReader.storeProp("admins", stringUtil.mapToString(admins));
            } catch (IOException ex) {

            }
        }


    }

    public void delAdmin(Player player) {
        HashMap<String, String> admins = new HashMap<>();
        try {
            admins = stringUtil.stringToMap(configReader.getProp("admins"));
            admins.remove(player.getDisplayName().toLowerCase());
        } catch (IOException ex) {
        } finally {
            try {
                configReader.storeProp("admins", stringUtil.mapToString(admins));
            } catch (IOException ex) {}
        }


    }

    public static synchronized ConfigUtil getInstance() {
        if (instance == null) {
            instance = new ConfigUtil();
        }
        return instance;
    }
}
