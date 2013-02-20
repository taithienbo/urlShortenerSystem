package inf22.bitly.command.parser;

import inf22.bitly.command.AbstractCommand;
import inf22.bitly.command.BitmarkCommand;
import inf22.bitly.command.ExpandCommand;
import inf22.bitly.command.HourCommand;
import inf22.bitly.command.LoginCommand;
import inf22.bitly.command.LogoutCommand;
import inf22.bitly.command.UnknownCommand;
import inf22.bitly.command.UnwatchCommand;
import inf22.bitly.command.WatchCommand;
import inf22.bitly.command.WeekCommand;

import java.util.List;




public class ConsoleCommandParser implements AbstractCommandParser
{
	private BitmarkCommandValidator bitmarkCommandValidator;
	private ExpandCommandValidator expandCommandValidator;
	private HourCommandValidator hourCommandValidator;
	private WeekCommandValidator weekCommandValidator;
	private LoginCommandValidator loginCommandValidator;
	private LogoutCommandValidator logoutCommandValidator;
	private UnwatchCommandValidator unwatchCommandValidator;
	private WatchCommandValidator watchCommandValidator;
	
	public ConsoleCommandParser()
	{
		bitmarkCommandValidator = new BitmarkCommandValidator();
		expandCommandValidator = new ExpandCommandValidator();
		hourCommandValidator = new HourCommandValidator();
		weekCommandValidator = new WeekCommandValidator();
		loginCommandValidator = new LoginCommandValidator();
		logoutCommandValidator = new LogoutCommandValidator();
		unwatchCommandValidator = new UnwatchCommandValidator();
		watchCommandValidator = new WatchCommandValidator();
	}

	public AbstractCommand parseCommand(List<String> inputs)
	{
		String keyword = inputs.get(0);
		
		switch (keyword.toUpperCase())
		{
		case "LOGIN":
			if (loginCommandValidator.isValid(inputs))
				return new LoginCommand();
		case "LOGOUT":
			if (logoutCommandValidator.isValid(inputs))
				return new LogoutCommand();
		case "BITMARK":
			if (bitmarkCommandValidator.isValid(inputs))
				return new BitmarkCommand();
		case "EXPAND":
			if (expandCommandValidator.isValid(inputs))
				return new ExpandCommand();
		case "WATCH":
			if (watchCommandValidator.isValid(inputs))
				return new WatchCommand();
		case "UNWATCH":
			if (unwatchCommandValidator.isValid(inputs))
				return new UnwatchCommand();
		case "HOUR":
			if (hourCommandValidator.isValid(inputs))
				return new HourCommand();
		case "WEEK":
			if (weekCommandValidator.isValid(inputs))
				return new WeekCommand();
			default:
				return new UnknownCommand();
		}
	}

}
