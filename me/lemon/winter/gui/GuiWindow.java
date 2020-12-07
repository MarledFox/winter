package me.lemon.winter.gui;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiWindow extends JFrame {
	private Map<String, JButton> toggleButtonsMap = new HashMap<>();
	private List<KeybindButton> keyButtonsList = new ArrayList<>();

	public GuiWindow() {
		this.setTitle("Winter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel toggles = new JPanel();
		{
			for(Feature feature : Winter.getInstance().getFeatureManager().values()) {
				toggles.add(new JLabel(feature.getName()));
				JButton toggleButton = new JButton("Off");
				toggleButton.addActionListener((e) -> {
					feature.setEnabled(!feature.isEnabled());
					toggleButton.setText(feature.isEnabled() ? "On" : "Off");
				});
				toggleButtonsMap.put(feature.getName(), toggleButton);
				toggles.add(toggleButton);

				KeybindButton keybindButton = new KeybindButton(feature);
				keyButtonsList.add(keybindButton);
				toggles.add(keybindButton);
			}
		}
		toggles.setLayout(new GridLayout(Winter.getInstance().getFeatureManager().values().size(), 3));
		tabbedPane.add("Features", toggles);

		for(Feature feature : Winter.getInstance().getFeatureManager().values()) {
			if(!feature.getComponentMap().values().isEmpty()) {
				JPanel featurePanel = new JPanel();
				{
					for(String key : feature.getComponentMap().keySet()) {
						featurePanel.add(new JLabel(key));
						featurePanel.add(feature.getComponentMap().get(key));
					}
				}
				featurePanel.setLayout(new GridLayout(feature.getComponentMap().values().size(), 2));
				tabbedPane.add(feature.getName(), featurePanel);
			}
		}

		this.setContentPane(tabbedPane);
		this.setSize(500, 250);
		this.setVisible(true);
	}

	public JButton getToggleButton(Feature feature) {
		return toggleButtonsMap.get(feature.getName());
	}

	public List<KeybindButton> getKeyButtonsList() {
		return keyButtonsList;
	}
}
