package inf22.bitly.parser.factory;

import inf22.bitly.command.parser.AbstractCommandParser;

public interface AbstractCommandParserFactory 
{
	public AbstractCommandParser getParser();
}
