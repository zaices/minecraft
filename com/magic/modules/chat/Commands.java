package com.magic.modules.chat;

import java.util.ArrayList;

import com.magic.main.Objects;

public final class Commands {
	
	public static void sendCommands(String s) {
		if(s.startsWith(".show")) {
			addChat3(Objects.fManager.getFriends());
		}
	}
	
	private static void addChat3(ArrayList array)
	{
		Objects.mc.thePlayer.addChatMessage("[\2479Magic\247f] "+array);
	}

}
