package me.mrletsplay.mrcore.command;

public interface CommandOption<T> {
	
	public String getShortName();
	
	public String getLongName();
	
	public boolean needsValue();

	public CommandValueType<T> getType();
	
}
