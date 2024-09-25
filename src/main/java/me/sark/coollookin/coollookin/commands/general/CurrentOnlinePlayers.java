package me.sark.coollookin.coollookin.commands.general;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrentOnlinePlayers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&', "&7 --- [&a Current Players Online &7] ---"));

            for (Player p : Bukkit.getOnlinePlayers())
            {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes
                            ('&', "     &e" + p.getName()));
            }

            return true;
        }

        if (commandSender.isOp())
        {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&', "&7 --- [&a Current Players Online &7] ---"));

            for (Player p : Bukkit.getOnlinePlayers())
            {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes
                        ('&', "     &e" + p.getName()));
            }

            return true;
        }

        return false;
    }
}
