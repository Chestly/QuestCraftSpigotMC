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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.IOException;
import java.util.HashMap;

public class EventListeners implements Listener {

    ServerContactUtil serverContactUtil;
    ConfigUtil configUtil;
    StringUtil stringUtil = new StringUtil();
    ConfigReader configReader;
    GuestTeamHandler guestTeamHandler;

    public EventListeners() {
        serverContactUtil = ServerContactUtil.getInstance();
        configUtil = ConfigUtil.getInstance();
        configReader = new ConfigReader();
        guestTeamHandler = GuestTeamHandler.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            if (!configUtil.playerHasPerm(player)) {
                HashMap playerList = stringUtil.stringToMap(configReader.getProp("playerList"));
                Application application = serverContactUtil.getApp(player.getDisplayName()).getApplication();
                if (application.getStatus() >= 4) {
                    try {
                        this.guestTeamHandler.removeFromTeam(player);
                        playerList.put(player.getDisplayName(), player.getUniqueId().toString());
                        configReader.storeProp("playerList", stringUtil.mapToString(playerList));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Your Application has been Accepted. &2STATUS: " + application.getStatus() + "/4"));
                    } catch (NotFound ex) {}

                } else if (application.getStatus() < 4) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft]&r Your Application is in review, Please Contact Administration for more info. &2STATUS: " + application.getStatus() + "/4"));
                }
            }
            if (player.isOp()) {
                configUtil.addAdmin(player);
            }
            MCReturnableLinks mcReturnableLinks = (MCReturnableLinks) serverContactUtil.getVerification(player.getDisplayName());
            if (mcReturnableLinks.getApplication().startsWith("http://localhost:4567") || mcReturnableLinks.getApplication().startsWith("http://questcraft.net")) {
                BaseComponent text = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &rMinecraft Verification from QuestCraft application: '&9" + mcReturnableLinks.getAppUser() + "&r' If this is you click &6&lHere"));
                text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, mcReturnableLinks.getApplication()));
                player.spigot().sendMessage(text);
            }
            if (mcReturnableLinks.getAccount().startsWith("http://localhost:4567") || mcReturnableLinks.getAccount().startsWith("http://questcraft.net")) {
                BaseComponent text = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&4[Quest&6Craft] &rMinecraft Verification from QuestCraft account: '&9" + mcReturnableLinks.getAccountUser() + "&r' If this is you click &6&lHere"));
                text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, mcReturnableLinks.getAccount()));
                player.spigot().sendMessage(text);
            }
        } catch (ContactError ex) {
        } catch (IOException ex) {
        } catch (ErrorClass ex) {
        }

    }
}
