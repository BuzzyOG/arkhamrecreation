package me.ifswitch;

import me.mgone.bossbarapi.BossbarAPI;


public class BossBarRepeating implements Runnable {
	
	@Override
	public void run() { 
		try {
			BossbarAPI.setMessage("§a§l40% §nOFF§6§l 'Christmas' Sale §b§nbuy.arkham.network");
			Thread.sleep(300);
			BossbarAPI.setMessage("§e§l40% §nOFF§6§l 'Christmas' Sale §b§nbuy.arkham.network");
			Thread.sleep(300);
			BossbarAPI.setMessage("§2§l40% §nOFF§6§l 'Christmas' Sale §b§nbuy.arkham.network");
			Thread.sleep(300);
			BossbarAPI.setMessage("§d§l40% §nOFF§6§l 'Christmas' Sale §b§nbuy.arkham.network");
			Thread.sleep(300);
			BossbarAPI.setMessage("§b§l40% §nOFF§6§l 'Christmas' Sale §b§nbuy.arkham.network");
			Thread.sleep(300);
			run();
		}catch(Exception e){}
	}

}
