package inf22.bitly.command.parser;

import java.util.List;

public interface CommandValidator 
{
	public boolean isValid(List<String> inputs);
}
