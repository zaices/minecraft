package com.magic.utilities;

import com.magic.modules.Module;
import com.magic.modules.combat.*;
import com.magic.modules.player.*;
import com.magic.modules.world.*;

public class ModManager {
	
	private final Module[] modules = new Module[] {
		new Brightness(),
		new Step(),
		new KillAura(),
		new Xray(),
		new Sprint(),
		new Tracers(),
		new Criticals(),
		new Sneak(),
		new Fly(),
		new Freecam(),
		new DepthMask(),
		new NameTags()
	};
	
	public Module[] getModules() {
		return modules;
	}
}
