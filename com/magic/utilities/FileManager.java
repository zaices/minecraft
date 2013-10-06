package com.magic.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

import com.magic.main.Objects;

public class FileManager {
	
	private static BufferedReader bw;
	public static boolean isUpToDate;
	
	public static void createMain() {
		try {
			File directory = new File("Magic");
			  
			if(!directory.exists()) {
				System.out.println("creating directory: `Magic`");
			    boolean sucess = directory.mkdir();
			     if(sucess) {    
			    	 System.out.println("Directory created.");  
			     }
			}
		}
		catch(Exception exception) {
			System.out.print(exception.toString());
		}
	}
	
	public static void saveFriends() {
		try {
			File file = new File("Magic/Friends.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int fag = 0; fag < Objects.fManager.getFriends().size(); fag++) {
				bw.write((new StringBuilder()).append((String)Objects.fManager.getFriends().get(fag)).append("\r\n").toString());
			}
			bw.close();
		}catch (Exception e) {
			System.out.print(e.toString());
		}
	}
	
	public static void loadFriends() {
		try {
			int gay[] = new int[512];
			File file = new File("Magic/Friends.txt");
			if(file.exists()) {
				bw = new BufferedReader(new FileReader(file));
				String string;
				for(int gayer = 0; (string = bw.readLine()) !=null; gayer++) {
					Objects.fManager.getFriends().add(string);
				}
			}
		}
		catch(Exception exception) {
			System.out.print(exception.toString());
		}
	}
	
	public static void checkUpdate() {
      try {
         URL e = new URL("https://dl.dropboxusercontent.com/u/87961137/Update.txt");
         Scanner s = new Scanner(e.openStream());
         String line = s.nextLine();
         if(line != null && line.contains(Objects.magic.getRelease())) {
        	 isUpToDate = true;
         }
         s.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
