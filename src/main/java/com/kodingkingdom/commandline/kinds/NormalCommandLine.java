package com.kodingkingdom.commandline.kinds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NormalCommandLine {
	
	public static void eval (Player p, List<String> transaction) {
		for (String s : transaction) {
			Bukkit.getServer().dispatchCommand(p, s);
		}
	}

}
