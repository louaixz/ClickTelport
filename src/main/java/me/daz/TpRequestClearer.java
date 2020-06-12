package me.daz;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;
public class TpRequestClearer extends TimerTask {

    private Player player;
    private logtask.HashMaps hashMaps;

    public void run() {

        Map<Player, Player> TpaMap = hashMaps.gettpaMap();
        Map<Player, Player> TpaHereMap = hashMaps.gettpaHereMap();
        ArrayList<Player> sent = hashMaps.sentArray();
        if  (TpaMap.containsKey(player)) {
            Player targetPlayer = TpaMap.get(player);
            player.sendMessage("來自"+targetPlayer.getName()+"的傳送請求已過期");
            targetPlayer.sendMessage("你的傳送請求已過期");
            TpaMap.remove(player);
            sent.clear();
            return;
        }
        if  (TpaHereMap.containsKey(player)) {
            Player targetPlayer = TpaHereMap.get(player);
            player.sendMessage("來自"+targetPlayer.getName()+"的傳送請求已過期");
            targetPlayer.sendMessage("你的傳送請求已過期");
            TpaMap.remove(player);
            TpaHereMap.remove(player);
            sent.clear();
        }
    }

    public void setMap(logtask.HashMaps hashMaps) {
        this.hashMaps = hashMaps;
    }

    public void setPlayer(Player p) {
        player = p;
    }
}