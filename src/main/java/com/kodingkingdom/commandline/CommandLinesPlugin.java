package com.kodingkingdom.commandline;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandLinesPlugin extends JavaPlugin {
	CommandLines x=new CommandLines(this);
	@Override
    public void onEnable(){x.Live();} 
    @Override
    public void onDisable(){x.Die();}
        
	
	static CommandLinesPlugin singleton;
	public CommandLinesPlugin(){singleton=this;}
	public static CommandLinesPlugin getPlugin(){return singleton;}
	public static void debug(String msg){
			singleton.getLogger().log(Level.INFO
					, msg);}}