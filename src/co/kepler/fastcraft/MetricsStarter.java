package co.kepler.fastcraft;

import java.io.IOException;

import org.bukkit.plugin.Plugin;

import co.kepler.fastcraft.config.PluginConfig;
import co.kepler.fastcraft.libraries.Metrics;
import co.kepler.fastcraft.libraries.Metrics.Graph;
import co.kepler.fastcraft.libraries.Metrics.Plotter;

/**
 * Starts up Plugin Metrics, and registers/plots graphs.
 * 
 * @author Kepler_
 */
public class MetricsStarter {
	public static void start(Plugin plugin) {
		Metrics metrics;
		try {
			metrics = new Metrics(plugin);
		} catch (IOException e) {
			FastCraft.info("Error submitting plugin metrics!");
			return;
		}
		PluginConfig config = FastCraft.configs().config;
		
		Graph autoUpdate = metrics.createGraph("Auto Update");
		if (config.autoUpdate_enabled()) {
			autoUpdate.addPlotter(new MyPlotter("Enabled", 1));
		} else {
			autoUpdate.addPlotter(new MyPlotter("Disabled", 1));
		}
		
		if (config.autoUpdate_enabled()) {
			Graph devBuilds = metrics.createGraph("Auto Update - Dev Builds");
			if (config.autoUpdate_devBuilds()) {
				devBuilds.addPlotter(new MyPlotter("Enabled", 1));
			} else {
				devBuilds.addPlotter(new MyPlotter("Disabled", 1));
			}
			
			Graph consoleMessages = metrics.createGraph("Auto Update - Show Console Messages");
			if (config.autoUpdate_showConsoleMessages()) {
				consoleMessages.addPlotter(new MyPlotter("Enabled", 1));
			} else {
				consoleMessages.addPlotter(new MyPlotter("Disabled", 1));
			}
			
			Graph autoDownload = metrics.createGraph("Auto Update - Auto Download");
			if (config.autoUpdate_autoDownload()) {
				autoDownload.addPlotter(new MyPlotter("Enabled", 1));
			} else {
				autoDownload.addPlotter(new MyPlotter("Disabled", 1));
			}
			
			Graph checkInterval = metrics.createGraph("Auto Update - Check Interval");
			int interval = config.autoUpdate_checkInterval();
			checkInterval.addPlotter(new MyPlotter(interval + " Minutes", 1));
		}
		
		Graph defaultEnabled = metrics.createGraph("FastCraft Default Enabled");
		if (config.fastCraftDefaultEnabled()) {
			defaultEnabled.addPlotter(new MyPlotter("Enabled", 1));
		} else {
			defaultEnabled.addPlotter(new MyPlotter("Disabled", 1));
		}

		Graph language = metrics.createGraph("Language");
		language.addPlotter(new MyPlotter(config.language(), 1));

		metrics.start();
	}
}

class MyPlotter extends Plotter {
	private final int value;

	public MyPlotter(final String name, int value) {
		super(name);
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}