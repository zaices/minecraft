package com.magic.utilities;

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
	}
}
