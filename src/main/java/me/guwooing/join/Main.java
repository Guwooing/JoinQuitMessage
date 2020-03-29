package me.guwooing.join;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(Utils.chat(getConfig().getString("logon").replace("<player>", p.getName())));

        //announces if the player is new/joined before
        if (!p.hasPlayedBefore()) {
            Bukkit.broadcastMessage(
                    Utils.chat(getConfig().getString("firstJoin_message").replace("<player>", p.getName())));
        } else {
            return;
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(Utils.chat(getConfig().getString("logout").replace("<player>", p.getName())));
    }
}
