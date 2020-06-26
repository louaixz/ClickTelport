package me.daz;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Logger;

public class logtask extends MessageData  {
    private final RequestManager requestManager = new RequestManager();
    public static logtask instance;
    public int timeoutValue;
    private final HashMaps hashMaps = new HashMaps();
    private final Map<Player, Timer> TimerMap = new HashMap<>();
    HashMap<Player,Player>tpa = hashMaps.gettpaMap();
    HashMap<Player,Player>tpahere = hashMaps.gettpaHereMap();
    ArrayList<Player>sent = hashMaps.sentArray();


    private final Logger logger = this.getLogger();



    public void logENABLE(){
        PluginDescriptionFile plugininfo = this.getDescription();
        instance=this;

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

    void loadConfig()  {
        int getTimeOutValue = getConfig().getInt("request-timeout-seconds");
        getConfig().addDefault("request-timeout-seconds",10);
        getConfig().options().copyDefaults(true);
        TimeoutValue(getTimeOutValue);
        saveConfig();
    }
    public static synchronized void addLogEntry(String entry) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&bClicktp&7] " + entry));
    }

    static void sendMessage(Player playerA, String message){
        playerA.sendMessage(message);
    }


   /* public static void sendMessage(CommandSender player, ChatColor color, MessageSpecifier specifier) {
        sendMessage(player, color, specifier.messageID, specifier.messageParams);
    }*/
   /*public void sendMessage(CommandSender player, ChatColor color, Messages messageID) {
       sendMessage(player, color, messageID, 0L);
   }*/

    public void sendMessage(CommandSender player, ChatColor color, Messages messageID) {
        String message = getMessage(messageID);
        sendMessage(player, color, message);
    }

    public static void sendMessage(CommandSender player, ChatColor color, String message) {
        if (message != null && message.length() != 0) {
            if (player == null) {
                addLogEntry(color + message);
            } else {
                player.sendMessage(color + message);
            }

        }
    }


    public static void sendMessage(CommandSender player, ChatColor color, String message, long delayInTicks) {
        SendPlayerMessageTask task = new SendPlayerMessageTask(player, color, message);
        if (delayInTicks > 0L) {
            instance.getServer().getScheduler().runTaskLater(instance, task, delayInTicks);
        } else {
            task.run();
        }

    }


    public void TimeoutValue(int timeoutValue){
        this.timeoutValue=timeoutValue;
    }
    public void clearOldRequests() {
        requestManager.clearOldRequests(timeoutValue);
    }
    public static class HashMaps{
            private final HashMap<Player,Player> tpa = new HashMap<>();
            private final HashMap<Player, Player> tpahere = new HashMap<>();
            private final ArrayList<Player> sent = new ArrayList<>();
            public HashMap<Player, Player> gettpaMap(){return this.tpa;}
            public HashMap<Player, Player> gettpaHereMap(){return this.tpahere;}
            public ArrayList<Player> sentArray(){return this.sent;}

    }
    public boolean playerHasPermission(Permissible player) {
        return player == null || player.hasPermission("Clicktp.tpa") || player.hasPermission("Clicktp.tpahere") ;
    }
    private void DelayClearTP(Player targetPlayer) {
        Timer timer = new Timer();
        TimerMap.put(targetPlayer, timer);
        TpRequestClearer clearer = new TpRequestClearer();
        clearer.setPlayer(targetPlayer);
        clearer.setMap(hashMaps);
        timer.schedule(clearer, timeoutValue*1000);
    }
}

