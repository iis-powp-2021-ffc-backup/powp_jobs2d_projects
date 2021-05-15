package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.MacroDriver;

public class MacroFeature implements Feature {

	private static MacroDriver macroDriver;
	
	@Override
	public void setup() {
		macroDriver = new MacroDriver();
	}
	
	public static MacroDriver getDriver() {
		return macroDriver;
	}
	
}
