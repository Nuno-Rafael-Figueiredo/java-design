package design.patterns.state;

import design.Base;
import design.patterns.command.Command;
import design.patterns.command.CommandState;

public class StateChanged extends Base {

    public final Command<?> command;
    public final CommandState oldState;
    public final CommandState newState;

    public StateChanged(final Command<?> command, final CommandState oldState,
                        final CommandState newState) {
        this.command = command;
        this.oldState = oldState;
        this.newState = newState;
    }

}
