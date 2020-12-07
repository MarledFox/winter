package me.lemon.winter;

import com.github.jonatino.process.Module;
import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;
import me.lemon.winter.minecraft.MoveInputHandler;

public class Globals {
    private static Process minecraftProcess;
    private static Module minecraftModule;
    private static MoveInputHandler moveInputHandler;

    public Globals() {
    }

    public static Process getMinecraft() {
        if (minecraftProcess == null) {
            Process foundProcess = null;

            while(foundProcess == null) {
                try {
                    foundProcess = Processes.byName("Minecraft.Windows.exe");
                } catch (Exception var4) {
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception var3) {
                    }
                }
            }

            minecraftProcess = foundProcess;
        }

        return minecraftProcess;
    }

    public static Module getMinecraftModule() {
        if (minecraftModule == null) {
            minecraftModule = getMinecraft().findModule("Minecraft.Windows.exe");
        }

        return minecraftModule;
    }

    public static MoveInputHandler getMoveInputHandler() {
        if (moveInputHandler == null) {
            try {
                long clientInstance = getMinecraftModule().readLong(57672904L);
                long[] clientInstancePtrs = new long[]{3168L, 24L, 24L, 8L};
                long[] var3 = clientInstancePtrs;
                int var4 = clientInstancePtrs.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    long ptr = var3[var5];
                    clientInstance = getMinecraft().readLong(clientInstance + ptr);
                    System.out.println(Long.toHexString(ptr));
                }

                long moveInputHandlerAddress = getMinecraft().readLong(clientInstance + 120L);
                moveInputHandler = new MoveInputHandler(moveInputHandlerAddress);
            } catch (Exception var8) {
            }
        }

        return moveInputHandler;
    }
}
