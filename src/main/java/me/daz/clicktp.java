package me.daz;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public final class clicktp extends logtask {

    public void onEnable() {
        loadConfig();
        logENABLE();
    }
    public void onDisable() {
        logDISABLE();
    }

    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {

        Player playerA = (Player) sender ;
        if (command.getLabel().equalsIgnoreCase("tpa")) {
            askTPA(getServer().getPlayer(args[0]),playerA,args);
            return true;
        }
        if (command.getLabel().equalsIgnoreCase("tpahere")) {
            askTPAhere(getServer().getPlayer(args[0]),playerA,args);
            return true;
        }

        if (commandlabel.equalsIgnoreCase("tpyes")){
            tpACCEPT(playerA);
            return true;
        }
        if (commandlabel.equalsIgnoreCase("tpno")){
            tpDENY(playerA);
            return true;
        }

        if (commandlabel.equalsIgnoreCase("getpos")) {
            getPOS(playerA,args);
        }
        return false;
    }
    private void loadConfig(){
        getConfig().addDefault("request-timeout-seconds",10);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}





