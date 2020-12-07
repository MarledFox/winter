package me.lemon.winter;

import java.util.Iterator;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.feature.FeatureFileHandler;
import me.lemon.winter.feature.FeatureManager;
import me.lemon.winter.feature.impl.AirJump;
import me.lemon.winter.feature.impl.Flight;
import me.lemon.winter.feature.impl.HighJump;
import me.lemon.winter.feature.impl.KillAura;
import me.lemon.winter.feature.impl.Phase;
import me.lemon.winter.feature.impl.Speed;
import me.lemon.winter.feature.impl.Spider;
import me.lemon.winter.feature.impl.Sprint;
import me.lemon.winter.feature.impl.Step;
import me.lemon.winter.feature.impl.Velocity;
import me.lemon.winter.gui.GuiWindow;
import me.lemon.winter.gui.KeybindButton;
import me.lemon.winter.gui.Overlay;
import me.lemon.winter.minecraft.Player;
import me.lemon.winter.util.Utils;

public class Winter {
    private static Winter instance;
    private FeatureManager featureManager = new FeatureManager();
    private GuiWindow window;
    private Overlay overlay;
    private FeatureFileHandler fileHandler;
    private boolean inCraft;

    public static Winter getInstance() {
        return instance;
    }

    public static void setInstance(Winter _instance) {
        instance = _instance;
    }

    public Winter() {
        this.featureManager.put(new KillAura());
        this.featureManager.put(new Speed());
        this.featureManager.put(new Velocity());
        this.featureManager.put(new Sprint());
        this.featureManager.put(new Flight());
        this.featureManager.put(new HighJump());
        this.featureManager.put(new AirJump());
        this.featureManager.put(new Step());
        this.featureManager.put(new Spider());
        this.featureManager.put(new Phase());
        GlobalKeyboardHook hook = new GlobalKeyboardHook(false);
        hook.addKeyListener(new GlobalKeyListener() {
            public void keyPressed(GlobalKeyEvent globalKeyEvent) {
                Iterator var2 = Winter.this.window.getKeyButtonsList().iterator();

                while(var2.hasNext()) {
                    KeybindButton button = (KeybindButton)var2.next();
                    button.keyPressed(globalKeyEvent.getVirtualKeyCode());
                }

                if (Winter.getInstance().isInCraft()) {
                    var2 = Winter.getInstance().getFeatureManager().values().iterator();

                    while(var2.hasNext()) {
                        Feature feature = (Feature)var2.next();
                        if (globalKeyEvent.getVirtualKeyCode() == feature.getKeybind()) {
                            feature.setEnabled(!feature.isEnabled());
                            Winter.this.window.getToggleButton(feature).setText(feature.isEnabled() ? "On" : "Off");
                        }
                    }

                }
            }

            public void keyReleased(GlobalKeyEvent globalKeyEvent) {
            }
        });
    }

    public void startLoop() {
        this.fileHandler = new FeatureFileHandler();
        this.fileHandler.read();
        this.window = new GuiWindow();
        this.overlay = new Overlay();
        long almostToPlayer = Globals.getMinecraftModule().readLong(56125664L);
        long[] ptrs = new long[]{16L, 296L, 0L};
        long[] var4 = ptrs;
        int var5 = ptrs.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            long ptr = var4[var6];
            almostToPlayer = Globals.getMinecraft().readLong(almostToPlayer + ptr);
        }

        long lastWindowCheck = System.currentTimeMillis();
        long lastTick = System.currentTimeMillis();

        while(true) {
            while(true) {
                try {
                    if (System.currentTimeMillis() - lastWindowCheck > 1000L) {
                        this.inCraft = Utils.getActiveWindowTitle().contains("Minecraft");
                        lastWindowCheck = System.currentTimeMillis();
                    }

                    if (this.inCraft && Globals.getMoveInputHandler() != null) {
                        long basicallyPlayer = Globals.getMinecraft().readLong(almostToPlayer + 184L);
                        Player local = new Player(Globals.getMinecraft().readLong(basicallyPlayer + 816L));
                        Iterator var11;
                        Feature feature;
                        if (System.currentTimeMillis() - lastTick >= 49L) {
                            var11 = this.featureManager.values().iterator();

                            label57:
                            while(true) {
                                do {
                                    if (!var11.hasNext()) {
                                        lastTick = System.currentTimeMillis();
                                        break label57;
                                    }

                                    feature = (Feature)var11.next();
                                } while(!feature.isEnabled() && !(feature instanceof Step) && !(feature instanceof Phase));

                                feature.onTick(local);
                            }
                        }

                        var11 = this.featureManager.values().iterator();

                        while(var11.hasNext()) {
                            feature = (Feature)var11.next();
                            if (feature.isEnabled()) {
                                feature.onLoop(local);
                            }
                        }

                        try {
                            Thread.sleep(5L);
                        } catch (Exception var13) {
                        }
                    }
                } catch (Exception var14) {
                    var14.printStackTrace();
                }
            }
        }
    }

    public FeatureManager getFeatureManager() {
        return this.featureManager;
    }

    public boolean isInCraft() {
        return this.inCraft;
    }

    public FeatureFileHandler getFileHandler() {
        return this.fileHandler;
    }
}
