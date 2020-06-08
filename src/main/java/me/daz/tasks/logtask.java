package me.daz.tasks;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class logtask extends JavaPlugin {
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

}

