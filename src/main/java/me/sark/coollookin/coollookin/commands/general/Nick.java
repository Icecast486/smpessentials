package me.sark.coollookin.coollookin.commands.general;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player;

        if (!(commandSender instanceof Player)) {
            System.out.println("[Nick] The console cannot run this command!");
            return true;
        }

        player = ((Player) commandSender).getPlayer();

        if (args.length > 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&', "&7[&cNick&7] &8»&7 Usage is /nick &e<nick>&7 or /nick"));
            return true;
        }

        if (args.length == 0) {
            player.setDisplayName(player.getName());

            player.sendMessage(ChatColor.translateAlternateColorCodes
                    ('&', "&7[&aNick&7] &8»&7 Changed your name back to default."));

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getUniqueId() != player.getUniqueId())
                    p.sendMessage(ChatColor.translateAlternateColorCodes
                            ('&', "&7[&aNick&7] &8»&e " + player.getName() + "&7 changed their name back to default."));
            }

            return true;
        }

        player.setDisplayName(args[0]);

        player.sendMessage(ChatColor.translateAlternateColorCodes
                ('&', "&7[&aNick&7] &8»&7 Changed your nick-name to &e" + args[0]));

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() != player.getUniqueId())
                p.sendMessage(ChatColor.translateAlternateColorCodes
                        ('&', "&7[&aNick&7] &8»&e " + player.getName() + "&7 changed their nick-name to &e" + args[0]));
        }

        return true;
    }
}
