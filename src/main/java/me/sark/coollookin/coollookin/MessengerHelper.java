package me.sark.coollookin.coollookin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessengerHelper
{
    public static boolean sendMessageToPlayer(Player target, String message)
    {
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }


    public static boolean sendLogMessage(Player target, String message)
    {
        if (!target.isOp())
        {
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&8Log&7] &8»&7 " + message));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&8Log&7] &8»&7 Why are you seeing this lol?" ));
        }
        else
        {
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&8Log&7] &8»&7 " + message));
        }
        return true;
    }

}
