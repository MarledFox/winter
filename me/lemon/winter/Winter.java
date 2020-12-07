package me.lemon.winter;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import me.lemon.winter.feature.Feature;
import me.lemon.winter.feature.FeatureFileHandler;
import me.lemon.winter.feature.FeatureManager;
import me.lemon.winter.feature.impl.*;
import me.lemon.winter.gui.GuiWindow;
import me.lemon.winter.gui.KeybindButton;
import me.lemon.winter.minecraft.*;
import me.lemon.winter.gui.Overlay;
import me.lemon.winter.util.Utils;

public class Winter {
	private static Winter instance;
	public static Winter getInstance() {
		return instance;
	}

	public static void setInstance(Winter _instance) {
		instance = _instance;
	}

	private FeatureManager featureManager;
	private GuiWindow window;
	private Overlay overlay;
	private FeatureFileHandler fileHandler;
	private boolean inCraft;

	public Winter() {
		featureManager = new FeatureManager();
		featureManager.put(new KillAura());
		featureManager.put(new Speed());
		featureManager.put(new Velocity());
		featureManager.put(new Sprint());
		featureManager.put(new Flight());
		featureManager.put(new HighJump());
		featureManager.put(new AirJump());
		featureManager.put(new Step());
		featureManager.put(new Spider());
		featureManager.put(new Phase());

		GlobalKeyboardHook hook = new GlobalKeyboardHook(false);
		hook.addKeyListener(new GlobalKeyListener() {
			@Override
			public void keyPressed(GlobalKeyEvent globalKeyEvent) {
				for(KeybindButton button : window.getKeyButtonsList()) {
					button.keyPressed(globalKeyEvent.getVirtualKeyCode());
				}

				if(!Winter.getInstance().isInCraft()) {
					return;
				}

				for(Feature feature : Winter.getInstance().getFeatureManager().values()) {
					if(globalKeyEvent.getVirtualKeyCode() != feature.getKeybind())
						continue;

					feature.setEnabled(!feature.isEnabled());
					window.getToggleButton(feature).setText(feature.isEnabled() ? "On" : "Off");
				}
			}

			@Override public void keyReleased(GlobalKeyEvent globalKeyEvent) { }
		});
	}

	public void startLoop() {
		fileHandler = new FeatureFileHandler();
		fileHandler.read();

		this.window = new GuiWindow();
		this.overlay = new Overlay();

		long almostToPlayer = Globals.getMinecraftModule().readLong(0x35868E0L);
		long ptrs[] = { 0x10, 0x128, 0x0 };
		for(long ptr : ptrs) {
			almostToPlayer = Globals.getMinecraft().readLong(almostToPlayer + ptr);
		}

		long lastWindowCheck = System.currentTimeMillis();
		long lastTick = System.currentTimeMillis();
		while(true) {
			try {
				if(System.currentTimeMillis() - lastWindowCheck > 1000) {
					inCraft = Utils.getActiveWindowTitle().contains("Minecraft");
					lastWindowCheck = System.currentTimeMillis();
				}
				if(!inCraft || Globals.getMoveInputHandler() == null) {
					continue;
				}

				long basicallyPlayer = Globals.getMinecraft().readLong(almostToPlayer + 0xB8);
				//System.out.println(Long.toHexString(Globals.getMinecraft().readLong(basicallyPlayer + 0x330)));
				//return;
				Player local = new Player(Globals.getMinecraft().readLong(basicallyPlayer + 0x330));

				if(System.currentTimeMillis() - lastTick >= 49) {
					for(Feature feature : featureManager.values()) {
						if(!feature.isEnabled() && !(feature instanceof Step) && !(feature instanceof Phase))
							continue;

						feature.onTick(local);
					}
					lastTick = System.currentTimeMillis();
				}
				for(Feature feature : featureManager.values()) {
					if(!feature.isEnabled())
						continue;

					feature.onLoop(local);
				}
				try {
					Thread.sleep(5);
				} catch (Exception e) {}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public FeatureManager getFeatureManager() {
		return featureManager;
	}

	public boolean isInCraft() {
		return inCraft;
	}

	public FeatureFileHandler getFileHandler() {
		return fileHandler;
	}
}
