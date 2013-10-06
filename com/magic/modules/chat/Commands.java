package com.magic.modules.chat;

import java.util.ArrayList;
import com.magic.main.Objects;

public final class Commands {
	
	public static void sendCommands(String s) {
		
		if(s.startsWith(".show")) {
			addChat3(Objects.fManager.getFriends());
		}else
		
		if(s.startsWith(".add")) {
			try {
			String[] split = s.split(" ");
			
			if(!Objects.fManager.getFriends().contains(split[1])) {
				Objects.fManager.getFriends().add(split[1]);
				addChat("Player \247a" + split[1] + "\247f added.");
			}else {
				addChat("Player \247c" + split[1] + "\247f has already been added.");
			}
			}catch (Exception e) {
				addChat("Syntax Error.");
			}
		}else
		
		if(s.startsWith(".remove")) {
			try {
			String[] split = s.split(" ");
			
			if(Objects.fManager.getFriends().contains(split[1])) {
				Objects.fManager.getFriends().remove(split[1]);
				addChat("Player \247c" + split[1] + "\247f removed.");
			}else {
				addChat("Player \247c" + split[1] + "\247f was not found.");
			}
			}catch (Exception e) {
				addChat("Syntax Error.");
			}
		}else
			
		if(s.startsWith(".note")) {
			try {
			String[] split = s.split(" ");
				
			if(!Objects.fManager.getNotes().contains(split[1])) {
				Objects.fManager.getNotes().add(split[1]);
				addChat("Player \247c" + split[1] + "\247f noted.");
			}else {
				addChat("Player \247c" + split[1] + "\247f was not found.");
			}
			}catch (Exception e) {
				addChat("Syntax Error.");
			}
		}else
			
		if(s.startsWith(".unnote")) {
			try {
			String[] split = s.split(" ");
			
			if(Objects.fManager.getNotes().contains(split[1])) {
				Objects.fManager.getNotes().remove(split[1]);
				addChat("Player \247c" + split[1] + "\247f removed.");
			}else {
				addChat("Player \247c" + split[1] + "\247f was not found.");
			}
			}catch (Exception e) {
				
			}
		}else
		
		if(s.startsWith(".clear")) {
			Objects.fManager.getFriends().clear();
			
			addChat("Friends list cleared.");
		}else
			
		if(s.equals(".help")) {
			addChat("Yes Hello.");
			addChat("\247a.add\247f <friend> : add a friend");
			addChat("\247a.remove\247f <friend> : remove a friend");
			addChat("\247a.show\247f : show friends list");
			addChat("\247a.clear\247f : clear friends list");
			addChat("\247a.note\247f <player> : note a player");
			addChat("\247a.unnote\247f <player> : remove a note from a player");
		}else
			
		addChat("Error. Command not valid.");
	}
	
	private static void addChat3(ArrayList array) {
		Objects.mc.thePlayer.addChatMessage("[\2479Magic\247f] " + array);
	}
	
	private static void addChat(String s) {
		Objects.mc.thePlayer.addChatMessage("[\2479Magic\247f] " + s);
	}

}
