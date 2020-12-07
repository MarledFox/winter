package me.lemon.winter.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;

public class GuiWindow extends JFrame {
    private Map<String, JButton> toggleButtonsMap = new HashMap();
    private List<KeybindButton> keyButtonsList = new ArrayList();

    public GuiWindow() {
        this.setTitle("Winter");
        this.setDefaultCloseOperation(3);
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel toggles = new JPanel();
        Iterator var3 = Winter.getInstance().getFeatureManager().values().iterator();

        Feature feature;
        while(var3.hasNext()) {
            feature = (Feature)var3.next();
            toggles.add(new JLabel(feature.getName()));
            JButton toggleButton = new JButton("Off");
            Feature finalFeature = feature;
            toggleButton.addActionListener((e) -> {
                finalFeature.setEnabled(!finalFeature.isEnabled());
                toggleButton.setText(finalFeature.isEnabled() ? "On" : "Off");
            });
            this.toggleButtonsMap.put(feature.getName(), toggleButton);
            toggles.add(toggleButton);
            KeybindButton keybindButton = new KeybindButton(feature);
            this.keyButtonsList.add(keybindButton);
            toggles.add(keybindButton);
        }

        toggles.setLayout(new GridLayout(Winter.getInstance().getFeatureManager().values().size(), 3));
        tabbedPane.add("Features", toggles);
        var3 = Winter.getInstance().getFeatureManager().values().iterator();

        while(true) {
            do {
                if (!var3.hasNext()) {
                    this.setContentPane(tabbedPane);
                    this.setSize(500, 250);
                    this.setVisible(true);
                    return;
                }

                feature = (Feature)var3.next();
            } while(feature.getComponentMap().values().isEmpty());

            JPanel featurePanel = new JPanel();
            Iterator var9 = feature.getComponentMap().keySet().iterator();

            while(var9.hasNext()) {
                String key = (String)var9.next();
                featurePanel.add(new JLabel(key));
                featurePanel.add((Component)feature.getComponentMap().get(key));
            }

            featurePanel.setLayout(new GridLayout(feature.getComponentMap().values().size(), 2));
            tabbedPane.add(feature.getName(), featurePanel);
        }
    }

    public JButton getToggleButton(Feature feature) {
        return (JButton)this.toggleButtonsMap.get(feature.getName());
    }

    public List<KeybindButton> getKeyButtonsList() {
        return this.keyButtonsList;
    }
}
