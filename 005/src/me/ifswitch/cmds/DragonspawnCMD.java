package me.ifswitch.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class DragonspawnCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) s;
		EnderDragon ed = (EnderDragon) Bukkit.getWorld("world").spawnEntity(p.getLocation(), EntityType.ENDER_DRAGON);
		ed.setPassenger(p);
		ed.setCustomNameVisible(true);
		ed.setCustomName("ALLI IS NEGRO");
		
		return false;
	}

}
