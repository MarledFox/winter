package me.lemon.winter.feature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import me.lemon.winter.Winter;

public class FeatureFileHandler {
    public FeatureFileHandler() {
    }

    public void write() {
        File directory = new File(".\\features\\");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            PrintWriter printer = new PrintWriter(new File(".\\features\\keybinds.txt"));
            Iterator var3 = Winter.getInstance().getFeatureManager().values().iterator();

            while(var3.hasNext()) {
                Feature feature = (Feature)var3.next();
                printer.println(feature.getName() + ":" + feature.getKeybind());
            }

            printer.close();
        } catch (Exception var5) {
        }

    }

    public void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(".\\features\\keybinds.txt")));

            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(":");
                System.out.println(split[0]);
                Winter.getInstance().getFeatureManager().get(split[0]).setKeybind(Integer.parseInt(split[1]));
            }

            reader.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
