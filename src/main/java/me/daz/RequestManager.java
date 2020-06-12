package me.daz;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



class RequestManager  {
    private logtask.HashMaps HashMap =new logtask.HashMaps();


    private final ConcurrentHashMap<Request, Date> pendingRequests;

    RequestManager() {
        pendingRequests = new ConcurrentHashMap<>();
    }

    void clearOldRequests(int timeoutValue) {

        Date now = new Date();

        pendingRequests.forEach((request, date) -> {
            if (((now.getTime() - date.getTime()) / 1000) > timeoutValue) {
                clicktp.sendMessage(request.getRequester(), ChatColor.GOLD + "Your teleport request to " + ChatColor.RESET + request.getTarget().getDisplayName() + ChatColor.GOLD + " timed out.");
                pendingRequests.remove(request);
            }
        });
    }

    void addRequest(Player playerB,Player playerA){
        addRequest(new Request(playerB,playerA));
    }

    private void addRequest(Request newrequest) {
        pendingRequests.forEach((request, date) -> {
            if (request.isSameRequest(newrequest)) {
                pendingRequests.remove(request);
            }
        });
        pendingRequests.put(newrequest, new Date());
    }

    void removeRequest(Player target, Player requester) {
        removeRequest(new Request(target, requester));
    }

    private void removeRequest(Request toDeleteRequest) {
        pendingRequests.forEach((request, date) -> {
            if (request.isSameRequest(toDeleteRequest)) {
                pendingRequests.remove(request);
            }
        });
    }


}


