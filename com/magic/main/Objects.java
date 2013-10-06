package com.magic.main;

import java.awt.Font;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.Minecraft;

import com.magic.Magic;
import com.magic.modules.combat.Criticals;
import com.magic.modules.combat.KillAura;
import com.magic.modules.player.Freecam;
import com.magic.utilities.ArraylistManager;
import com.magic.utilities.CustomFont;
import com.magic.utilities.FriendsManager;
import com.magic.utilities.KeybindManager;
import com.magic.utilities.ModManager;
import com.magic.utilities.MotionManager;

public final class Objects {
	
	public static CustomFont customFont = new CustomFont(Objects.mc, new Font("Arial", 1, 17), 17);
	
	public static Minecraft mc = Minecraft.getMinecraft();
	public static FontRenderer fr = mc.fontRenderer;
	
	public static Magic magic = new Magic();
	
	public static KillAura killaura = new KillAura();
	public static Criticals crits = new Criticals();
	public static Freecam freecam = new Freecam();
	
	public static ModManager mManager = new ModManager();
	public static KeybindManager kManager = new KeybindManager();
	public static ArraylistManager aManager = new ArraylistManager();
	public static MotionManager moManager = new MotionManager();
	public static FriendsManager fManager = new FriendsManager();
	
}
