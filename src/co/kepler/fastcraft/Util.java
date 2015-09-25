package co.kepler.fastcraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Util {

	public static void loadYaml(YamlConfiguration config, File file)
			throws FileNotFoundException {
		loadYaml(config, new FileInputStream(file));
	}

	public static void loadYaml(YamlConfiguration config, InputStream stream) {
		try {
			config.load(new InputStreamReader(stream, "UTF-8"));
		} catch (Exception e) {
			FastCraft.error(e);
		}
	}

	public static void saveYaml(YamlConfiguration config, File f)
			throws IOException {
		FileOutputStream stream = new FileOutputStream(f);
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(stream, "UTF-8");
			writer.write(config.saveToString());
			writer.close();
		} catch (UnsupportedEncodingException e) {
			FastCraft.error(e);
		}
	}
}
