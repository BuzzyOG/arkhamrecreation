package me.ifswitch;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utils {
	
	public static void SoundAt(String worldName, Location loc, Sound soundType, float volume, float pitch) {
		Bukkit.getWorld(worldName).playSound(loc, soundType, volume, pitch);
	}
	
	public static void broadcastM(String playerName) {
		Bukkit.broadcastMessage("§0§l[§e§lA§f.§b§lN§0§l]" + " " + "§aThanks, §l" + playerName + "§a, for supporting us. \n             §a§nbuy.arkham.network");
		for(Player p : Bukkit.getOnlinePlayers()) {
			SoundAt("world", p.getLocation(), Sound.LEVEL_UP, 100f, 100f);
		}
	}
	
	public static void broadcastMsg(String annString) {
		Bukkit.broadcastMessage("§0§l[§e§lA§f.§b§lN§0§l]" + " §e§l " + annString);
	}

}
