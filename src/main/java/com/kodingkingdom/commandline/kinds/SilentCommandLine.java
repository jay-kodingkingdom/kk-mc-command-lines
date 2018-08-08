package com.kodingkingdom.commandline.kinds;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.kodingkingdom.commandline.CommandLinesPlugin;

public class SilentCommandLine {
	
	public static void go () {
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(CommandLinesPlugin.getPlugin(), PacketType.Play.Server.CHAT) {
			public void onPacketSending(PacketEvent e) {
				if (boycott.contains(e.getPlayer()))
					e.setCancelled(true);
			}
		});
	}
	
	public static HashSet<Player> boycott = new HashSet<Player> ();
	
	public static void eval (Player p, List<String> transaction) {
		boolean is_op = p.isOp();
		try {
			p.setOp(true);
			boycott.add(p);
			for (String s : transaction) {
				Bukkit.getServer().dispatchCommand(p, s);
			}
		}
		finally {
			boycott.remove(p);
			if (! is_op)
				p.setOp(false);									
		}
	}

}
