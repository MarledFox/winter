package me.lemon.winter.util;

import java.util.HashMap;
import java.util.Map;

public class KeyHelper {

	private static String wow = "THIS IS SO STUPID!!!!!! THERE HAS GOT TO BE A BETTER WAY TO DO THIS!";

	private static final Map<Integer, String> KEY_MAP = new HashMap<>();

	public static String getKeyName(int keycode) {
		if(keycode == -1) {
			return "NONE";
		}
		return KEY_MAP.get(keycode);
	}

	static {
		KEY_MAP.put(0xC1,"Abnt C1");KEY_MAP.put(0xC2,"Abnt C2");KEY_MAP.put(0x6B,"Numpad +");KEY_MAP.put(0xF6,"Attn");KEY_MAP.put(0x08,"Backspace");
		KEY_MAP.put(0x03,"Break");KEY_MAP.put(0x0C,"Clear");KEY_MAP.put(0xF7,"Cr Sel");KEY_MAP.put(0x6E,"Numpad .");KEY_MAP.put(0x6F,"Numpad /");
		KEY_MAP.put(0xF9,"Er Eof");KEY_MAP.put(0x1B,"Esc");KEY_MAP.put(0x2B,"Execute");KEY_MAP.put(0xF8,"Ex Sel");KEY_MAP.put(0xE6,"IcoClr");
		KEY_MAP.put(0xE3,"IcoHlp");KEY_MAP.put(0x30,"0");KEY_MAP.put(0x31,"1");KEY_MAP.put(0x32,"2");KEY_MAP.put(0x33,"3");KEY_MAP.put(0x34,"4");
		KEY_MAP.put(0x35,"5");KEY_MAP.put(0x36,"6");KEY_MAP.put(0x37,"7");KEY_MAP.put(0x38,"8");KEY_MAP.put(0x39,"9");KEY_MAP.put(0x41,"A");
		KEY_MAP.put(0x42,"B");KEY_MAP.put(0x43,"C");KEY_MAP.put(0x44,"D");KEY_MAP.put(0x45,"E");KEY_MAP.put(0x46,"F");KEY_MAP.put(0x47,"G");
		KEY_MAP.put(0x48,"H");KEY_MAP.put(0x49,"I");KEY_MAP.put(0x4A,"J");KEY_MAP.put(0x4B,"K");KEY_MAP.put(0x4C,"L");KEY_MAP.put(0x4D,"M");
		KEY_MAP.put(0x4E,"N");KEY_MAP.put(0x4F,"O");KEY_MAP.put(0x50,"P");KEY_MAP.put(0x51,"Q");KEY_MAP.put(0x52,"R");KEY_MAP.put(0x53,"S");
		KEY_MAP.put(0x54,"T");KEY_MAP.put(0x55,"U");KEY_MAP.put(0x56,"V");KEY_MAP.put(0x57,"W");KEY_MAP.put(0x58,"X");KEY_MAP.put(0x59,"Y");
		KEY_MAP.put(0x5A,"Z");KEY_MAP.put(0x6A,"Numpad *");KEY_MAP.put(0xFC,"NoName");KEY_MAP.put(0x60,"Numpad 0");KEY_MAP.put(0x61,"Numpad 1");
		KEY_MAP.put(0x62,"Numpad 2");KEY_MAP.put(0x63,"Numpad 3");KEY_MAP.put(0x64,"Numpad 4");KEY_MAP.put(0x65,"Numpad 5");KEY_MAP.put(0x66,"Numpad 6");
		KEY_MAP.put(0x67,"Numpad 7");KEY_MAP.put(0x68,"Numpad 8");KEY_MAP.put(0x69,"Numpad 9");KEY_MAP.put(0xBA,":");KEY_MAP.put(0xE2,">");KEY_MAP.put(0xBF,"?");
		KEY_MAP.put(0xC0,"~");KEY_MAP.put(0xDB,"[");KEY_MAP.put(0xDC,"\\");KEY_MAP.put(0xDD,"]");KEY_MAP.put(0xDE,"\"");KEY_MAP.put(0xDF,"!");
		KEY_MAP.put(0xF0,"Oem Attn");KEY_MAP.put(0xF3,"Auto");KEY_MAP.put(0xE1,"Ax");KEY_MAP.put(0xF5,"Back Tab");KEY_MAP.put(0xFE,"OemClr");
		KEY_MAP.put(0xBC,"<");KEY_MAP.put(0xF2,"Copy");KEY_MAP.put(0xEF,"Cu Sel");KEY_MAP.put(0xF4,"Enlw");KEY_MAP.put(0xF1,"Finish");KEY_MAP.put(0x95,"Loya");
		KEY_MAP.put(0x93,"Mashu");KEY_MAP.put(0x96,"Roya");KEY_MAP.put(0x94,"Touroku");KEY_MAP.put(0xEA,"Jump");KEY_MAP.put(0xBD,"-");KEY_MAP.put(0xEB,"OemPa1");
		KEY_MAP.put(0xEC,"OemPa2");KEY_MAP.put(0xED,"OemPa3");KEY_MAP.put(0xBE,".");KEY_MAP.put(0xBB,"+");KEY_MAP.put(0xE9,"Reset");KEY_MAP.put(0xEE,"WsCtrl");
		KEY_MAP.put(0xFD,"Pa1");KEY_MAP.put(0xE7,"Packet");KEY_MAP.put(0xFA,"Play");KEY_MAP.put(0xE5,"Process");KEY_MAP.put(0x0D,"Enter");KEY_MAP.put(0x29,"Select");
		KEY_MAP.put(0x6C,"Separator");KEY_MAP.put(0x20,"Space");KEY_MAP.put(0x6D,"Num -");KEY_MAP.put(0x09,"Tab");KEY_MAP.put(0xFB,"Zoom");KEY_MAP.put(0xFF,"no VK mapping");
		KEY_MAP.put(0x1E,"Accept");KEY_MAP.put(0x5D,"Context Menu");KEY_MAP.put(0xA6,"Browser Back");KEY_MAP.put(0xAB,"Browser Favorites");KEY_MAP.put(0xA7,"Browser Forward");
		KEY_MAP.put(0xAC,"Browser Home");KEY_MAP.put(0xA8,"Browser Refresh");KEY_MAP.put(0xAA,"Browser Search");KEY_MAP.put(0xA9,"Browser Stop");KEY_MAP.put(0x14,"Caps Lock");
		KEY_MAP.put(0x1C,"Convert");KEY_MAP.put(0x2E,"Delete");KEY_MAP.put(0x28,"Arrow Down");KEY_MAP.put(0x23,"End");KEY_MAP.put(0x70,"F1");KEY_MAP.put(0x79,"F10");
		KEY_MAP.put(0x7A,"F11");KEY_MAP.put(0x7B,"F12");KEY_MAP.put(0x7C,"F13");KEY_MAP.put(0x7D,"F14");KEY_MAP.put(0x7E,"F15");KEY_MAP.put(0x7F,"F16");KEY_MAP.put(0x80,"F17");
		KEY_MAP.put(0x81,"F18");KEY_MAP.put(0x82,"F19");KEY_MAP.put(0x71,"F2");KEY_MAP.put(0x83,"F20");KEY_MAP.put(0x84,"F21");KEY_MAP.put(0x85,"F22");KEY_MAP.put(0x86,"F23");
		KEY_MAP.put(0x87,"F24");KEY_MAP.put(0x72,"F3");KEY_MAP.put(0x73,"F4");KEY_MAP.put(0x74,"F5");KEY_MAP.put(0x75,"F6");KEY_MAP.put(0x76,"F7");KEY_MAP.put(0x77,"F8");
		KEY_MAP.put(0x78,"F9");KEY_MAP.put(0x18,"Final");KEY_MAP.put(0x2F,"Help");KEY_MAP.put(0x24,"Home");KEY_MAP.put(0xE4,"Ico00 *");KEY_MAP.put(0x2D,"Insert");
		KEY_MAP.put(0x17,"Junja");KEY_MAP.put(0x15,"Kana");KEY_MAP.put(0x19,"Kanji");KEY_MAP.put(0xB6,"App1");KEY_MAP.put(0xB7,"App2");KEY_MAP.put(0xB4,"Mail");
		KEY_MAP.put(0xB5,"Media");KEY_MAP.put(0x01,"Left Button **");KEY_MAP.put(0xA2,"Left Ctrl");KEY_MAP.put(0x25,"Arrow Left");KEY_MAP.put(0xA4,"Left Alt");
		KEY_MAP.put(0xA0,"Left Shift");KEY_MAP.put(0x5B,"Left Win");KEY_MAP.put(0x04,"Middle Button **");KEY_MAP.put(0xB0,"Next Track");KEY_MAP.put(0xB3,"Play / Pause");
		KEY_MAP.put(0xB1,"Previous Track");KEY_MAP.put(0xB2,"Stop");KEY_MAP.put(0x1F,"Mode Change");KEY_MAP.put(0x22,"Page Down");KEY_MAP.put(0x1D,"Non Convert");
		KEY_MAP.put(0x90,"Num Lock");KEY_MAP.put(0x92,"Jisho");KEY_MAP.put(0x13,"Pause");KEY_MAP.put(0x2A,"Print");KEY_MAP.put(0x21,"Page Up");KEY_MAP.put(0x02,"Right Button **");
		KEY_MAP.put(0xA3,"Right Ctrl");KEY_MAP.put(0x27,"Arrow Right");KEY_MAP.put(0xA5,"Right Alt");KEY_MAP.put(0xA1,"Right Shift");KEY_MAP.put(0x5C,"Right Win");
		KEY_MAP.put(0x91,"Scrol Lock");KEY_MAP.put(0x5F,"Sleep");KEY_MAP.put(0x2C,"Print Screen");KEY_MAP.put(0x26,"Arrow Up");KEY_MAP.put(0xAE,"Volume Down");
		KEY_MAP.put(0xAD,"Volume Mute");KEY_MAP.put(0xAF,"Volume Up");KEY_MAP.put(0x05,"X Button 1 **");KEY_MAP.put(0x06,"X Button 2 **");
	}
}
