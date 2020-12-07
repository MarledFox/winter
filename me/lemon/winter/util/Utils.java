package me.lemon.winter.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.MoveInputHandler;
import me.lemon.winter.minecraft.Vec2;

import java.awt.*;

public class Utils {
	public static String getActiveWindowTitle() {
		WinDef.HWND fgWindow = User32.INSTANCE.GetForegroundWindow();
		int titleLength = User32.INSTANCE.GetWindowTextLength(fgWindow) + 1;
		char[] title = new char[titleLength];
		User32.INSTANCE.GetWindowText(fgWindow, title, titleLength);
		return Native.toString(title);
	}

	public static Vec2 getMoveVec(float speed, float angle) {
		MoveInputHandler movementInput = Globals.getMoveInputHandler();
		float mX = 0.0f, mZ = 0.0f,
				forward = movementInput.getMoveForward().read(),
				strafe = movementInput.getMoveStrafe().read();
		if (forward != 0.0f || strafe != 0.0f) {
			if (forward != 0.0f) {
				angle += ((strafe > 0.0f ? 1.0f : strafe < 0.0f ? -1.0f : 0.0f) * (forward > 0.0f ? -45 : 45));
				forward = forward > 0.0f ? 1.0f : forward < 0.0f ? -1.0f : 0.0f;
				strafe = 0.0f;
			}
			angle = (angle + 90.0f) * ((float)Math.PI / 180.0f);
			mX = forward * speed * (float)Math.cos(angle) + strafe * speed * (float)Math.sin(angle);
			mZ = forward * speed * (float)Math.sin(angle) - strafe * speed * (float)Math.cos(angle);
		}
		return new Vec2(mX, mZ);
	}
}
