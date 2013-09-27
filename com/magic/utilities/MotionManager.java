package com.magic.utilities;

import com.magic.main.Objects;
import com.magic.modules.Module;

public class MotionManager {
	
	public synchronized void runPreMotions() {
		for(Module mod : Objects.mManager.getModules()) {
			if(mod.isEnabled()) {
				mod.onPreMotionUpdates();
			}
		}
	}
	
	public synchronized void runPostMotions() {
		for(Module mod : Objects.mManager.getModules()) {
			if(mod.isEnabled()) {
				mod.onPostMotionUpdates();
			}
		}
	}

}
