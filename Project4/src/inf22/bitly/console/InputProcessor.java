package inf22.bitly.console;

import inf22.bitly.command.AbstractCommand;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputProcessor 
{
	private InputStream inputStream;
	private Scanner scanner;
	private List<String> inputs;
	
	InputProcessor(InputStream inputStream)
	{
		this.inputStream = inputStream;
		scanner = new Scanner(inputStream);
		inputs = new ArrayList<String>();
	}
	
	public AbstractCommand getCommand()
	{
		
		throw new RuntimeException("Not yet implemented"); 		
	}
	
}
