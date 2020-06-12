package me.daz;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Logger;

public class logtask extends JavaPlugin {
    private RequestManager requestManager = new RequestManager();
    public int timeoutValue;
    private HashMaps hashMaps = new HashMaps();
    HashMap<Player,Player>tpa = hashMaps.gettpaMap();
    HashMap<Player,Player>tpahere = hashMaps.gettpaHereMap();
    ArrayList<Player>sent = hashMaps.sentArray();
    private  Map<Player, Timer> TimerMap = new HashMap<>();
    Logger logger = this.getLogger();



    public void logENABLE(){
            PluginDescriptionFile plugininfo = this.getDescription();

            this.logger.info(plugininfo.getName() + " Version:" + plugininfo.getVersion() + " has been enabled ");
            this.logger.info("The simple click telport plugin!");
        }
        public void logDISABLE(){
            PluginDescriptionFile fileinfo = this.getDescription();
            this.logger.info(fileinfo.getName() + " Version:" + fileinfo.getVersion() + "has been disabled");
        }


        protected void askTPA(Player playerB, Player playerA, String[] args) {

            if (!sent.contains(playerA)) {

                if (args.length != 1) {
                playerA.spigot().sendMessage(new ComponentBuilder("請正確輸入玩家名稱").color(ChatColor.RED).create());
                return;
                }
                if (playerB == null) {
                playerA.spigot().sendMessage(new ComponentBuilder("此玩家不在線上").color(ChatColor.RED).create());
                return;
                }
                if (playerA.equals(playerB)) {
                playerA.spigot().sendMessage(new ComponentBuilder("請不要傳自己").color(ChatColor.RED).create());
                return;
                }

                if (Bukkit.getPlayerExact(args[0]) != null) {
                    if (!sent.contains(playerA) && playerB != null) {
                        sendMessage(playerA,ChatColor.WHITE+"請求已送出");
                        playerB.spigot().sendMessage((new ComponentBuilder(playerA.getDisplayName() + "想要傳送到你的位置")).color(ChatColor.GOLD).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("點擊下方接受或拒絕")).color(ChatColor.GOLD)
                                    .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("接受"))
                            .event(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpyes")).color(ChatColor.BLUE).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("接受")).color(ChatColor.BLUE)
                                    .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("拒絕"))
                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpno")).color(ChatColor.RED).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("拒絕")).color(ChatColor.BLUE)
                                    .create())).create());
                        //requestManager.addRequest(playerB,playerA);
                        DelayClearTP(playerB);
                        tpa.put(playerB, playerA);
                        tpahere.put(playerB, playerA);
                        sent.add(playerA);

                    }
                }
            } else if (sent.contains(playerA)) {
            playerA.sendMessage("§c請求已存在");
        }
    }
    protected void askTPAhere(Player playerB, Player playerA, String[] args) {
        if (!sent.contains(playerA)) {
            if(args.length!=1){
                playerA.spigot().sendMessage(new ComponentBuilder("請正確輸入玩家名稱").color(ChatColor.RED).create());
                return ;
            }
            if (playerB == null){
                playerA.spigot().sendMessage(new ComponentBuilder("此玩家不在線上").color(ChatColor.RED).create());
                return ;
            }
            if (playerA.equals(playerB)){
                playerA.spigot().sendMessage(new ComponentBuilder("請不要傳自己").color(ChatColor.RED).create());
                return ;
            }
            if (Bukkit.getPlayerExact(args[0]) != null){
                if (!sent.contains(playerA)&&playerB != null) {
                    sendMessage(playerA,ChatColor.WHITE+"請求已送出");
                    playerB.spigot().sendMessage((new ComponentBuilder(playerA.getDisplayName()+"想要傳送到你到他的位置")).color(ChatColor.GOLD).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("點擊下方接受或拒絕")).color(ChatColor.GOLD)
                                    .create())).create());
                    playerB.spigot().sendMessage((new ComponentBuilder("接受"))
                            .event(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpyes")).color(ChatColor.BLUE).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("接受")).color(ChatColor.BLUE)
                                    .create())).create());
                    playerB.spigot().sendMessage((new ComponentBuilder("拒絕"))
                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpno")).color(ChatColor.RED).bold(true)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("拒絕")).color(ChatColor.BLUE)
                                    .create())).create());
                    DelayClearTP(playerB);
                    tpa.put(playerB, playerA);
                    tpahere.put(playerB,playerA);
                    sent.add(playerA);
                }
            }
        }else if (sent.contains(playerA)){
            playerA.sendMessage("§c請求已存在");
        }
    }
    protected void tpACCEPT(Player playerA){
        if (tpa.get(playerA)==null) {
            playerA.sendMessage("無任何傳送請求");
        }else if (tpahere.get(playerA)!=null){
            tpa.get(playerA).teleport(playerA);
            tpa.get(playerA).sendMessage("對方已接受請求,開始傳送...");
            playerA.sendMessage("已接受請求");
            tpa.put(playerA,null);
            tpahere.put(playerA,null);
            sent.clear();

        }
        else {
            playerA.teleport(tpa.get(playerA));
            tpa.get(playerA).sendMessage("已接受請求,開始傳送...");
            playerA.sendMessage("已接受請求");
            tpa.put(playerA,null);
            tpahere.put(playerA,null);
            sent.clear();
        }


    }
    protected void tpDENY(Player playerA){
        if(tpa.get(playerA)==null) {
            playerA.sendMessage("無任何傳送請求");
        } else  {
            tpa.get(playerA).sendMessage("對方已拒絕...");
            playerA.sendMessage("已拒絕請求");
            sent.remove(tpa.get(playerA));
            tpa.put(playerA,null);
            tpahere.put(playerA,null);
        }
    }
    protected void getPOS(Player playerA,String args[]) {
        if (Bukkit.getPlayerExact(args[0]) != null) {
            Player getplayerpos = playerA.getServer().getPlayer(args[0]);
            if (getplayerpos != null) {
                Location getplayerposLocation = getplayerpos.getLocation();
                getplayerpos.sendMessage("your position is" + getplayerposLocation.getX() + "," + getplayerposLocation.getY() + "," + getplayerposLocation.getZ());
                playerA.sendMessage("the position is " + getplayerposLocation.toString());
            }
        }
    }

    void loadConfig(){
        getConfig().addDefault("request-timeout-seconds",10);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    static void sendMessage(Player playerA, String message){
        playerA.sendMessage(message);
    }
    public void TimeoutValue(int timeoutValue){
        this.timeoutValue=timeoutValue;
    }
    public void clearOldRequests() {
        requestManager.clearOldRequests(timeoutValue);
    }
    public static class HashMaps{
            private HashMap<Player,Player> tpa = new HashMap<>();
            private HashMap<Player, Player> tpahere = new HashMap<>();
            private ArrayList<Player> sent = new ArrayList<>();
            public HashMap<Player, Player> gettpaMap(){return this.tpa;}
            public HashMap<Player, Player> gettpaHereMap(){return this.tpahere;}
            public ArrayList<Player> sentArray(){return this.sent;}

    }

    private void DelayClearTP(Player targetPlayer) {
        Timer timer = new Timer();
        TimerMap.put(targetPlayer, timer);
        TpRequestClearer clearer = new TpRequestClearer();
        clearer.setPlayer(targetPlayer);
        clearer.setMap(hashMaps);
        timer.schedule(clearer, 5000);
    }


}

