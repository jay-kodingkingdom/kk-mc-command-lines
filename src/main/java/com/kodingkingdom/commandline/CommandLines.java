package com.kodingkingdom.commandline;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import com.kodingkingdom.commandline.kinds.NormalCommandLine;
import com.kodingkingdom.commandline.kinds.SilentCommandLine;

//TODO: make on hand book automatically usable
public class CommandLines implements Listener, CommandExecutor{
	public final static long pollInterval=8;

	CommandLinesPlugin plugin;	
	public CommandLines(CommandLinesPlugin Plugin){plugin=Plugin;}
	
	//TODO: handle cases of old book already on hand, aka onheld event not fired
	public void Live(){
		SilentCommandLine.go();
		
		plugin .getCommand ("eval").setExecutor(this);
	}
	public void Die(){}
	
	public void registerEvents(Listener listener){
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);}

	public int scheduleAsyncTask(Runnable task){
		return plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, task);}
	public int scheduleAsyncTask(Runnable task, long delay){
		return plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, task, delay);}
	public int scheduleTask(Runnable task, long delay){
		return plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, task, delay);}
	public void cancelTask(int taskId){
		plugin.getServer().getScheduler().cancelTask(taskId);}

	@Override
	public boolean onCommand(CommandSender x, Command y, String z, String[] args) {
		if (args .length < 2)
			return false;
		else {
			try {
				NormalCommandLine.eval(
					Bukkit .getPlayer(args [0]),
					Arrays.asList(String .join(" ", Arrays.asList(args).subList(1, args.length))));
			}
			catch (Exception e) {}
			return true;
		}
	}
}
