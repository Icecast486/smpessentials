package me.sark.coollookin.coollookin.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatEvents implements Listener {
    /* Edits the format of the text to make it look nice */
    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        String playerDisplayName = event.getPlayer().getDisplayName(); /* gets the name of the player that sent the chat */
        String newFormat; /* new format of the text */

        newFormat = ChatColor.translateAlternateColorCodes
                ('&', "&7[&b" + playerDisplayName + "&7] &8»&7 " + event.getMessage().toString());

        event.setFormat(newFormat);
    }



    /* Changes the way the join message looks */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a+&7] " + event.getPlayer().getName()));
    }



    /* Changes the way the leave message looks */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&7[&c-&7] " + event.getPlayer().getName()));
    }
}
