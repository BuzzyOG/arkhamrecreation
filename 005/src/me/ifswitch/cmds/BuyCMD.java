package me.ifswitch.cmds;

import me.ifswitch.Game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String commandLabel, String[] args) {
		Player p = (Player)s;
		p.openInventory(Game.shopmenu);
		return false;
	}

}
