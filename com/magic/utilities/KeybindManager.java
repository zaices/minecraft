package com.magic.utilities;

import org.lwjgl.input.Keyboard;

import com.magic.gui.click.Click;
import com.magic.main.Objects;
import com.magic.modules.Module;

public final class KeybindManager {
	
	public void fireKeybind(int key) {		
		for(Module mod : Objects.mManager.getModules()) {
			if(key == mod.getKey()) {
				mod.toggle();
				
				System.out.println(mod.getName() + " was toggled - " + mod.isEnabled());
			}
		}
		
		switch(key) {
		case Keyboard.KEY_RSHIFT :
			Objects.mc.displayGuiScreen(new Click());
			break;
		}
	}
}
