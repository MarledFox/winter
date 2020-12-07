package me.lemon.winter.gui;

import com.sun.awt.AWTUtilities;
import com.sun.jna.Native;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser.WINDOWINFO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;

public class Overlay {
    private Window overlayWindow = new Window((Frame)null);

    public Overlay() {
        this.overlayWindow.add(new JComponent() {
            protected void paintComponent(Graphics graphics) {
                Overlay.this.drawOverlay(graphics);
            }

            public Dimension getPreferredSize() {
                return new Dimension(5000, 5000);
            }
        });
        this.overlayWindow.setSize(1, 1);
        this.overlayWindow.setLocationRelativeTo((Component)null);
        this.overlayWindow.setVisible(true);
        this.overlayWindow.setAlwaysOnTop(true);
        AWTUtilities.setWindowOpaque(this.overlayWindow, false);
        this.setTransparent(this.overlayWindow);
        HWND minecraftHwnd = User32.INSTANCE.FindWindow((String)null, "Minecraft");
        (new Thread(() -> {
            while(true) {
                this.repaint();

                try {
                    WINDOWINFO info = new WINDOWINFO();
                    User32.INSTANCE.GetWindowInfo(minecraftHwnd, info);
                    if ((info.dwStyle & 536870912) == 536870912) {
                        this.overlayWindow.setLocation(-20000, -20000);
                    } else {
                        Rectangle rect = WindowUtils.getWindowLocationAndSize(minecraftHwnd);
                        this.overlayWindow.setLocation(rect.x, rect.y);
                        this.overlayWindow.setSize(rect.width, rect.height);
                    }
                } catch (Exception var5) {
                    System.exit(0);
                }

                try {
                    Thread.sleep(16L);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }
        })).start();
    }

    public void drawOverlay(Graphics graphics) {
        if (Winter.getInstance().isInCraft()) {
            Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.setFont(new Font("Verdana", 1, 35));
            this.drawShadowString(graphics, "Winter", 12, 68, Color.WHITE);
            graphics.setFont(new Font("Verdana", 1, 17));
            this.drawShadowString(graphics, "Bedrock 1.16.100", 147, 54, Color.LIGHT_GRAY);
            List<Feature> enabledList = (List)Winter.getInstance().getFeatureManager().values().stream().filter(Feature::isEnabled).collect(Collectors.toList());
            Collections.sort(enabledList, (f1, f2) -> {
                String f1Name = f1.getName() + (f1.getSuffix().isEmpty() ? "" : "" + f1.getSuffix());
                String f2Name = f2.getName() + (f2.getSuffix().isEmpty() ? "" : "" + f2.getSuffix());
                int f1Width = graphics.getFontMetrics().stringWidth(f1Name);
                int f2Width = graphics.getFontMetrics().stringWidth(f2Name);
                return -Integer.compare(f1Width, f2Width);
            });
            int y = 88;
            Iterator var5 = enabledList.iterator();

            while(var5.hasNext()) {
                Feature feature = (Feature)var5.next();
                if (feature.isEnabled()) {
                    if (!feature.getSuffix().isEmpty()) {
                        this.drawShadowString(graphics, feature.getName() + " " + feature.getSuffix(), 12, y, Color.LIGHT_GRAY);
                    }

                    this.drawShadowString(graphics, feature.getName(), 12, y, feature.getColor());
                    y += 20;
                }
            }

        }
    }

    public void drawShadowString(Graphics graphics, String string, int x, int y, Color color) {
        graphics.setColor(color.darker().darker().darker());
        graphics.drawString(string, x + 2, y + 2);
        graphics.setColor(color);
        graphics.drawString(string, x, y);
    }

    public void repaint() {
        this.overlayWindow.getComponents()[0].repaint();
    }

    private HWND getHWnd(Component w) {
        HWND hwnd = new HWND();
        hwnd.setPointer(Native.getComponentPointer(w));
        return hwnd;
    }

    private void setTransparent(Component w) {
        HWND hwnd = this.getHWnd(w);
        int wl = User32.INSTANCE.GetWindowLong(hwnd, -20);
        wl = wl | 524288 | 32;
        User32.INSTANCE.SetWindowLong(hwnd, -20, wl);
    }

    private Rectangle getRect(HWND hwnd) throws Exception {
        RECT rect = new RECT();
        boolean result = User32.INSTANCE.GetWindowRect(hwnd, rect);
        if (!result) {
            throw new Exception();
        } else {
            return rect.toRectangle();
        }
    }
}
