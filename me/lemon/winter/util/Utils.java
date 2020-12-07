package me.lemon.winter.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import me.lemon.winter.Globals;
import me.lemon.winter.minecraft.MoveInputHandler;
import me.lemon.winter.minecraft.Vec2;

public class Utils {
    public Utils() {
    }

    public static String getActiveWindowTitle() {
        HWND fgWindow = User32.INSTANCE.GetForegroundWindow();
        int titleLength = User32.INSTANCE.GetWindowTextLength(fgWindow) + 1;
        char[] title = new char[titleLength];
        User32.INSTANCE.GetWindowText(fgWindow, title, titleLength);
        return Native.toString(title);
    }

    public static Vec2 getMoveVec(float speed, float angle) {
        MoveInputHandler movementInput = Globals.getMoveInputHandler();
        float mX = 0.0F;
        float mZ = 0.0F;
        float forward = movementInput.getMoveForward().read();
        float strafe = movementInput.getMoveStrafe().read();
        if (forward != 0.0F || strafe != 0.0F) {
            if (forward != 0.0F) {
                angle += (strafe > 0.0F ? 1.0F : (strafe < 0.0F ? -1.0F : 0.0F)) * (float)(forward > 0.0F ? -45 : 45);
                forward = forward > 0.0F ? 1.0F : (forward < 0.0F ? -1.0F : 0.0F);
                strafe = 0.0F;
            }

            angle = (angle + 90.0F) * 0.017453292F;
            mX = forward * speed * (float)Math.cos((double)angle) + strafe * speed * (float)Math.sin((double)angle);
            mZ = forward * speed * (float)Math.sin((double)angle) - strafe * speed * (float)Math.cos((double)angle);
        }

        return new Vec2(mX, mZ);
    }
}
