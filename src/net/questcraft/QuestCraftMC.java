package net.questcraft;

import mcantigrief.java.ConfigReader;
import mcantigrief.java.GuestTeamHandler;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.questcraft.config.ConfigUtil;
import net.questcraft.servercontact.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class QuestCraftMC extends JavaPlugin {

    ServerContactUtil serverContactUtil;
    ConfigUtil configUtil;
    GuestTeamHandler guestTeamHandler;
    StringUtil stringUtil;
    ConfigReader griefConfigReader;
    @Override
    public void onEnable() {
        System.out.println("System Started");
        serverContactUtil = ServerContactUtil.getInstance();
        configUtil = ConfigUtil.getInstance();
        getServer().getPluginManager().registerEvents(new EventListeners(), this);
        guestTeamHandler = GuestTeamHandler.getInstance();
        stringUtil = new StringUtil();
        griefConfigReader = new ConfigReader();
    }

    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
        Player player = (Player) sender;

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
                    HashMap playerList = stringUtil.stringToMap(griefConfigReader.getProp("playerList"));

                    if (application.getStatus() >= 4) {
                        try {
                            this.guestTeamHandler.removeFromTeam(player);
                        } catch (NotFound ex) {
                        }
                        playerList.put(player.getDisplayName(), player.getUniqueId().toString());
                        griefConfigReader.storeProp("playerList", stringUtil.mapToString(playerList));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Your Application has been Accepted. &2STATUS: " + application.getStatus() + "/4"));
                    } else if (application.getStatus() < 4) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Your Application is in review, Please Contact Administration for more info. &2STATUS: " + application.getStatus() + "/4"));
                    }
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
                try {
                    MCReturnableLinks mcReturnableLinks = (MCReturnableLinks) serverContactUtil.getVerification(player.getDisplayName());
                    if (mcReturnableLinks.getApplication().startsWith("http://localhost:4567") || mcReturnableLinks.getApplication().startsWith("http://questcraft.net")) {
                        BaseComponent text = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &rMinecraft Verification from QuestCraft application: '&9" + mcReturnableLinks.getAppUser() + "&r' If this is you click &6&lHere"));
                        text.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, mcReturnableLinks.getApplication()));
                        player.spigot().sendMessage(text);
                    } else if (mcReturnableLinks.getAccount().startsWith("http://localhost:4567") || mcReturnableLinks.getAccount().startsWith("http://questcraft.net")) {
                        BaseComponent text = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &rMinecraft Verification from QuestCraft account: '&9" + mcReturnableLinks.getAccountUser() + "&r' If this is you click &6&lHere"));
                        text.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, mcReturnableLinks.getAccount()));
                        player.spigot().sendMessage(text);
                    }
                } catch (IOException ex) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Sorry!&r Weve had a internal Error, please contact Staff, Use &o&6/staff&r to do find online administration"));
                } catch (ErrorClass ex) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Sorry!&r Weve had a internal Error, please contact Staff, Use &o&6/staff&r to do find online administration"));
                } catch (ContactError contactError) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r &l&2Sorry!&r Weve had a internal Error, please contact Staff, Use &o&6/staff&r to do find online administration"));
                }
                break;
        }
        return true;
    }
}
