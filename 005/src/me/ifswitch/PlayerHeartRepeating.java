package me.ifswitch;

import me.ifswitch.ref.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHeartRepeating implements Runnable {

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			ParticleEffect.HEART.display(0, 0, 0, 10, 10, p.getEyeLocation().add(0, 1, 0), 150);
		}
	}

}
