package inf22.bitly.parser.factory;

import inf22.bitly.command.parser.AbstractCommandParser;
import inf22.bitly.command.parser.ConsoleCommandParser;

import java.util.List;

public class ConsoleCommandParserFactory implements AbstractCommandParserFactory
{
	public List<String> inputs;
	
	public ConsoleCommandParserFactory(List<String> inputs)
	{
		this.inputs = inputs;
	}
	
	@Override
	public AbstractCommandParser getParser() 
	{
		// TODO Auto-generated method stub
		return new ConsoleCommandParser();
	}

}
