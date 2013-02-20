package inf22.bitly.command.parser;

import inf22.bitly.command.AbstractCommand;

import java.util.List;

public interface AbstractCommandParser 
{
	public AbstractCommand parseCommand(List<String> inputs);
}
