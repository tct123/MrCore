package me.mrletsplay.mrcore.command;

import java.util.ArrayList;
import java.util.List;

import me.mrletsplay.mrcore.command.option.CommandOption;
import me.mrletsplay.mrcore.command.option.impl.DefaultCommandOption;
import me.mrletsplay.mrcore.command.properties.CommandProperties;

public abstract class AbstractCommand<T extends CommandProperties> implements Command {
	
	private Command parent;
	private String name;
	private List<String> aliases;
	private List<CommandOption<?>> options;
	private String description;
	private T properties;
	private List<Command> subCommands;
	
	public AbstractCommand(String name, T initialProperties) {
		this.name = name;
		this.aliases = new ArrayList<>();
		this.options = new ArrayList<>();
		this.subCommands = new ArrayList<>();
		this.properties = initialProperties;
	}
	
	public AbstractCommand(String name) {
		this(name, null);
	}
	
	public void setParent(Command parent) {
		this.parent = parent;
	}
	
	public Command getParent() {
		return parent;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void addAlias(String alias) {
		aliases.add(alias);
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	public void addOption(CommandOption<?> option) {
		options.add(option);
	}

	@Override
	public List<CommandOption<?>> getOptions() {
		return options;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void setProperties(T properties) {
		this.properties = properties;
	}
	
	@Override
	public T getProperties() {
		return properties;
	}

	public void addSubCommand(Command subCommand) {
		subCommand.setParent(this);
		subCommands.add(subCommand);
	}
	
	@Override
	public List<Command> getSubCommands() {
		return subCommands;
	}
	
	public static CommandOption<?> createCommandOption(String shortName, String longName) {
		return DefaultCommandOption.createCommandOption(shortName, longName);
	}
	
	public static <T> CommandOption<T> createCommandOption(String shortName, String longName, CommandValueType<T> type) {
		return DefaultCommandOption.createCommandOption(shortName, longName, type);
	}

}
