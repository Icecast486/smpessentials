package me.sark.coollookin.coollookin.commands.teleport;

import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportRequest implements CommandExecutor {

    /* This map holds all the requests, <Requester, Subject> */


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        /* The executor of the command; the target the requester wants to teleport to */
        Player requester, target;

        /* Is the command executor the console? */
        if (!(commandSender instanceof Player))
        {
            System.out.println("[Teleport] The console cannot run this command!");
            return true;
        }

        /* Setting requester to the player that sent the command */
        requester = ((Player) commandSender).getPlayer();

        /* Checking whether args are valid */
        if (args.length != 1)
        {
            MessengerHelper.sendMessageToPlayer(requester, "&7[&cTeleport&7] &8»&7 Usage is /tpr &e<player>");
            return true;
        }

        /* Checking whether the target exists */
        if (Bukkit.getPlayer(args[0]) == null)
        {
            MessengerHelper.sendMessageToPlayer(requester,"&7[&cTeleport&7] &8»&7 That player does not exist!");
            return true;
        }

        /* Setting target to the argument passed in by the requester */
        target = Bukkit.getPlayer(args[0]);

        /* Checking if the player wants to teleport to themselves */
        if (target.getUniqueId() == requester.getUniqueId())
        {
            MessengerHelper.sendMessageToPlayer(requester, "&7[&cTeleport&7] &8»&7 You cannot teleport to yourself!");
            return true;
        }

        /* Checking if the target has a pending request */
        if (TeleportManager.hasRequest(target))
        {
            MessengerHelper.sendMessageToPlayer(requester,"&7[&cTeleport&7] &8»&7 That player already has a pending request!");
            return true;
        }

        /* If the requester has a request pending remove the old one */
        if (TeleportManager.isRequesting(requester))
        {
            TeleportManager.removeRequestByRequester(requester);

            MessengerHelper.sendMessageToPlayer(requester,"&7[&aTeleport&7] &8»&7 Removed your previous request!");
        }

        /* Sending the messages */
        MessengerHelper.sendMessageToPlayer(requester, "&7[&aTeleport&7] &8»&7 Request sent to &e" + target.getPlayer().getDisplayName() + "!");
        MessengerHelper.sendMessageToPlayer(target, "&7[&aTeleport&7] &8»&7 A teleport request has been sent from &a" + requester.getDisplayName() + "&7 type &e/tpa&7 to accept the request.");

        /* Playing a sound to the requester and target */
        requester.playSound(requester, Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 2.0F);
        target.playSound(target, Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 2.0F);

        /* Puts the request in the hashmap (store the request) */
        TeleportManager.addRequest(target, requester);

        return true;
    }
}


