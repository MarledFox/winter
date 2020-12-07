package me.lemon.winter;

import com.github.jonatino.process.Module;
import com.github.jonatino.process.Process;
import com.github.jonatino.process.Processes;
import me.lemon.winter.minecraft.MoveInputHandler;

public class Globals {
	private static Process minecraftProcess;
	private static Module minecraftModule;
	private static MoveInputHandler moveInputHandler;

	public static Process getMinecraft() {
		if(minecraftProcess == null) {
			Process foundProcess = null;
			while(foundProcess == null) {
				try {
					foundProcess = Processes.byName("Minecraft.Windows.exe");
				} catch(Exception e) { try { Thread.sleep(1000); } catch(Exception ex) {} }
			}
			minecraftProcess = foundProcess;
		}
		return minecraftProcess;
	}

	public static Module getMinecraftModule() {
		if(minecraftModule == null) {
			minecraftModule = getMinecraft().findModule("Minecraft.Windows.exe");
		}
		return minecraftModule;
	}

	public static MoveInputHandler getMoveInputHandler() {
		if(moveInputHandler == null) {
			try {
				long clientInstance = Globals.getMinecraftModule().readLong(0x37004C8L);
				long clientInstancePtrs[] = { 0xC60, 0x18, 0x18, 0x8 };
				for(long ptr : clientInstancePtrs) {
					clientInstance = Globals.getMinecraft().readLong(clientInstance + ptr);
					System.out.println(Long.toHexString(ptr));
				}
				long moveInputHandlerAddress = Globals.getMinecraft().readLong(clientInstance + 0x78);
				moveInputHandler = new MoveInputHandler(moveInputHandlerAddress);
			} catch (Exception e) { }
		}
		return moveInputHandler;
	}
}
