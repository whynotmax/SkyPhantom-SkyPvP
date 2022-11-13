package eu.skyphantom.skypvp.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final File file;
    private final FileConfiguration fileConfig;

    public Config(String path, String fileName) {
        if (!fileName.contains(".yml")) {
            fileName = fileName + ".yml";
        }

        this.file = new File(path, fileName);
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
        if (!this.file.exists()) {
            this.fileConfig.options().header("Config - " + fileName.replace(".yml", ""));
            this.fileConfig.options().copyDefaults(true);

            try {
                this.fileConfig.save(this.file);
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }
    }

    public FileConfiguration getConfig() {
        return this.fileConfig;
    }

    public void saveConfig() {
        try {
            this.fileConfig.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

}

