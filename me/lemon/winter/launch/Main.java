package me.lemon.winter.launch;

import me.lemon.winter.Winter;

public class Main {
	public static void main(String... args) {
		Winter.setInstance(new Winter());
		Winter.getInstance().startLoop();
	}

}
