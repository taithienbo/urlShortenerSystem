package inf22.bitly.command;

import inf22.bitly.result.AbstractResult;
import inf22.bitly.state.State;

public interface AbstractCommand 
{
	
	public AbstractResult execute(State state);

}
