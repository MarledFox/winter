package me.lemon.winter.feature;

import me.lemon.winter.Winter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class FeatureFileHandler {
	public void write() {
		File directory = new File(".\\features\\");
		if(!directory.exists()) {
			directory.mkdirs();
		}

		try {
			PrintWriter printer = new PrintWriter(new File(".\\features\\keybinds.txt"));
			for(Feature feature : Winter.getInstance().getFeatureManager().values()) {
				printer.println(feature.getName() + ":" + feature.getKeybind());
			}
			printer.close();
		} catch(Exception e) {}
	}

	public void read() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(".\\features\\keybinds.txt")));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split(":");
				Winter.getInstance().getFeatureManager().get(split[0]).setKeybind(Integer.parseInt(split[1]));
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
