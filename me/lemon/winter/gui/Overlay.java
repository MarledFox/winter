package me.lemon.winter.gui;

import com.sun.awt.AWTUtilities;
import com.sun.jna.Native;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import me.lemon.winter.Winter;
import me.lemon.winter.feature.Feature;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Overlay {
	private Window overlayWindow;
	public Overlay() {
		overlayWindow = new Window(null);
		overlayWindow.add(new JComponent() {
			protected void paintComponent(Graphics graphics) {
				drawOverlay(graphics);
			}
			public Dimension getPreferredSize() {
				return new Dimension(5000, 5000);
			}
		});

		overlayWindow.setSize(1, 1);
		overlayWindow.setLocationRelativeTo(null);
		overlayWindow.setVisible(true);
		overlayWindow.setAlwaysOnTop(true);

		AWTUtilities.setWindowOpaque(overlayWindow, false);
		setTransparent(overlayWindow);

		WinDef.HWND minecraftHwnd = User32.INSTANCE.FindWindow(null, "Minecraft");

		new Thread(() -> {
			while(true) {
				repaint();
				try {
					WinUser.WINDOWINFO info = new WinUser.WINDOWINFO();
					User32.INSTANCE.GetWindowInfo(minecraftHwnd, info);
					if ((info.dwStyle & 0x20000000) == 0x20000000) {
						overlayWindow.setLocation(-20000, -20000);
					} else {
						Rectangle rect = WindowUtils.getWindowLocationAndSize(minecraftHwnd);
						overlayWindow.setLocation(rect.x, rect.y);
						overlayWindow.setSize(rect.width, rect.height);
					}
				} catch (Exception e) {
					System.exit(0);
				}
				try {
					Thread.sleep(16);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void drawOverlay(Graphics graphics) {
		if(!Winter.getInstance().isInCraft())
			return;

		Graphics2D graphics2D = (Graphics2D)graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.setFont(new Font("Verdana", Font.BOLD, 35));
		drawShadowString(graphics, "Winter", 12, 68, Color.WHITE);
		graphics.setFont(new Font("Verdana", Font.BOLD, 17));
		drawShadowString(graphics, "Bedrock 1.16.100", 147, 54, Color.LIGHT_GRAY);
		List<Feature> enabledList = Winter.getInstance().getFeatureManager().values().stream().filter(Feature::isEnabled).collect(Collectors.toList());
		Collections.sort(enabledList, (f1, f2) -> {
			String f1Name = f1.getName() + (f1.getSuffix().isEmpty() ? "" : "" +  f1.getSuffix());
			String f2Name = f2.getName() + (f2.getSuffix().isEmpty() ? "" : "" +  f2.getSuffix());
			int f1Width = graphics.getFontMetrics().stringWidth(f1Name);
			int f2Width = graphics.getFontMetrics().stringWidth(f2Name);
			return -Integer.compare(f1Width, f2Width);
		});

		int y = 88;
		for(Feature feature : enabledList) {
			if(!feature.isEnabled())
				continue;

			if(!feature.getSuffix().isEmpty()) {
				drawShadowString(graphics, feature.getName() + " " + feature.getSuffix(), 12, y, Color.LIGHT_GRAY);
			}

			drawShadowString(graphics, feature.getName(), 12, y, feature.getColor());
			y += 20;
		}
	}

	public void drawShadowString(Graphics graphics, String string, int x, int y, Color color) {
		graphics.setColor(color.darker().darker().darker());
		graphics.drawString(string, x + 2, y + 2);
		graphics.setColor(color);
		graphics.drawString(string, x, y);
	}

	public void repaint() {
		overlayWindow.getComponents()[0].repaint();
	}

	private WinDef.HWND getHWnd(Component w) {
		WinDef.HWND hwnd = new WinDef.HWND();
		hwnd.setPointer(Native.getComponentPointer(w));
		return hwnd;
	}

	private void setTransparent(Component w) {
		WinDef.HWND hwnd = getHWnd(w);
		int wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
		wl = wl | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;
		User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl);
	}

	private Rectangle getRect(WinDef.HWND hwnd) throws Exception {
		WinDef.RECT rect = new WinDef.RECT();
		boolean result = User32.INSTANCE.GetWindowRect(hwnd, rect);
		if (!result) {
			throw new Exception();
		}
		return rect.toRectangle();
	}
}
