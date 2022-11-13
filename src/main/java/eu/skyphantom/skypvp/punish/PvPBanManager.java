package eu.skyphantom.skypvp.punish;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PvPBanManager {

	private static final Config config = new Config("plugins/SkyPvP/punishments/", "pvp-bans.yml");

	private final UUID uuid;

	public PvPBanManager(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isBanned() {
		return (System.currentTimeMillis() < config.getConfig().getLong(uuid + ".endDate"));
	}

	public void ban(String reason, long endDate) {
		config.getConfig().set(uuid + ".reason", reason);
		if (endDate == -1) {
			config.getConfig().set(uuid + ".endDate", -1);
		} else {
			config.getConfig().set(uuid + ".endDate", (System.currentTimeMillis()+endDate));
		}
		config.saveConfig();
		if (Bukkit.getPlayer(uuid) != null) {
			Player player = Bukkit.getPlayer(uuid);
			player.sendMessage("§r");
			player.sendMessage(SkyPvP.PUNISH + "§7Du wurdest aus dem PvP ausgeschlossen§8.");
			player.sendMessage(SkyPvP.PUNISH + "§7Grund§8: §c" + reason);
			player.sendMessage(SkyPvP.PUNISH + "§7Verbleibend§8: §c" + (endDate == -1L ? "Permanent" : TimeUtil.timeToString(endDate-1000L, true)));
			player.sendMessage("§r");
		}
	}

	public long getEndDate() {
		if (isBanned()) {
			return (config.getConfig().getLong(uuid + ".endDate")-System.currentTimeMillis());
		}
		return 0;
	}

	public String getReason() {
		return config.getConfig().getString(uuid + ".reason");
	}

	public void unban() {
		config.getConfig().set(uuid + ".reason", null);
		config.getConfig().set(uuid + ".endDate", null);
		config.saveConfig();
	}

}
