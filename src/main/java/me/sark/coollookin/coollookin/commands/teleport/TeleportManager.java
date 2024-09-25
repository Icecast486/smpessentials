package me.sark.coollookin.coollookin.commands.teleport;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TeleportManager
{
    /* target -> requester */
    private static HashMap<Player, Player> requests = new HashMap<Player, Player>();

    public static boolean addRequest(Player target, Player requester)
    {
        if (!requests.containsKey(target))
        {
            requests.put(target, requester);
            return true;
        }

        return false; /* player already has request */
    }



    public static boolean removeRequest(Player target)
    {
        if (!requests.containsKey(target))
        {
            return false; /* target does not have a request */
        }

        requests.remove(target);
        return true;
    }



    public static boolean hasRequest(Player target)
    {
        return requests.containsKey(target);
    }



    public static void removeRequestByRequester(Player requester)
    {
        Player existingTarget = null;

        for (HashMap.Entry<Player, Player> entry : requests.entrySet())
        {
            if (entry.getValue().equals(requester))
            {
                existingTarget = entry.getKey(); // Find the target UUID
                break;
            }
        }

        if (existingTarget != null) {
            requests.remove(existingTarget);
        }
    }



    public static boolean isRequesting(Player requester)
    {
        return requests.containsValue(requester);
    }



    public static Player getRequester(Player target)
    {
        return requests.get(target);
    }
}
