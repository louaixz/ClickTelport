package me.daz;

import me.daz.tasks.ClearOldRequest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public final class clicktp extends logtask {
    public void onEnable() {
        int getTimeOutValue = getConfig().getInt("request-timeout-seconds");
        loadConfig();
        TimeoutValue(getTimeOutValue);
        logENABLE();
        new ClearOldRequest(this).runTaskTimer(this,0,20);
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

}





