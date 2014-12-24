package me.ifswitch;

import java.util.Random;

import org.bukkit.Bukkit;

public class AnnouncerRepeating implements Runnable {

	@Override 
	public void run() {
		Random rand = new Random();
		int randomint = rand.nextInt(6 - 0 + 1) + 0;
		
		switch(randomint) {
		case 0:
			Bukkit.broadcastMessage("§e§l>> Type §b§l/server <name> §eto get to a specific server!");
			break;
		case 1:
			Bukkit.broadcastMessage("§e§l>> §eWalk into §nThe Tesseract §eto join a server");
			break;
		case 2:
			Bukkit.broadcastMessage("§e§l>> §eType §b§l/server §eto view all servers!");
			break;
		case 3:
			Bukkit.broadcastMessage("§e§l>> §eJoin the server forums at §b§larkham.network/community");
			break;
		case 4:
			Bukkit.broadcastMessage("§e§l>> §ePurchase §b§lranks §eat §b§lbuy.arkham.network");
			break;
		case 5:
			Bukkit.broadcastMessage("§e§l>> §eTry out your §b§lFlash Speed §eand §b§lWeb Shot §e superpowers!");
			break;
		case 6:
			Bukkit.broadcastMessage("§e§l>> §eBe sure to right click §b§lall §ethe items in your hotbar!");
			break;
			default:
				System.out.println("AutoAnnouncer is encounatered an error!!" + " " + randomint);
				break;
		}
	}

}
