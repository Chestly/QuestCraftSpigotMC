package net.questcraft;

import net.questcraft.config.ConfigUtil;
import net.questcraft.servercontact.Application;
import net.questcraft.servercontact.ContactError;
import net.questcraft.servercontact.ServerContactUtil;
import net.questcraft.servercontact.ErrorClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;

public class QuestCraftMC extends JavaPlugin {

    ServerContactUtil serverContactUtil;
    ConfigUtil configUtil;

    @Override
    public void onEnable() {
        System.out.println("System Started");
        serverContactUtil = ServerContactUtil.getInstance();
        configUtil = ConfigUtil.getInstance();
        getServer().getPluginManager().registerEvents(new EventListeners(), this);
    }

    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        switch (command.getName().toLowerCase()) {
            case ("staff"):
                HashMap<String, String> adminList = configUtil.getAdmins();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2===+++--- &4Quest&6Craft &rStaff: &2---+++==="));
                for (String plr : adminList.keySet()) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(plr))) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2◉ &r" + plr));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4◉ &r" + plr));
                    }
                }
                break;
            case ("addadmin"):
                if (Bukkit.getPlayer(args[0]) != null) {
                    configUtil.addAdmin(Bukkit.getPlayer(args[0]));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Added &3" + args[0] + "&r as a server Admin"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &r&4&lSorry!&r That player isnt Online"));
                }
                break;
            case ("apply"):
                try {
                    Application application = serverContactUtil.getApp(sender.getName()).getApplication();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Your Application is Currently Being reviewed. &2STATUS: " + application.getStatus() + "/4"));
                } catch (ContactError ex) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Hey! &rTo apply for Perms in QuestCraft please go to &3&nquestcraft.net/apply.html"));
                } catch (IOException ex) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Sorry!&r Weve had a internal Error, please contact Staff, Use &o&6/staff&r to do find online administration"));
                } catch (ErrorClass ex) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Hey! &rTo apply for Perms in QuestCraft please go to &3&nquestcraft.net/apply.html"));
                }
                break;
            case ("website"):
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[QUEST&6CRAFT]&r &lHey,&r To get to our website please go to &3&nhttp://questcraft.net/&r Thanks!"));
                break;
            case ("deladmin"):
                if (Bukkit.getPlayer(args[0]) != null) {
                    configUtil.delAdmin(Bukkit.getPlayer(args[0]));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Removed &3" + args[0] + "&r as a server Admin"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &r&4&lSorry!&r That player isnt Online"));
                }
                break;
            case ("linkweb"):

                break;
        }
        return true;
    }
}
