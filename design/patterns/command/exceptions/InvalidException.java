package design.patterns.command.exceptions;

import design.patterns.command.ICommand;

public class InvalidException extends CommandException {

    /**
     *
     */
    private static final long serialVersionUID = 2891423735774916606L;

    public InvalidException(final ICommand<?> command) {
        super(command, command.getClass().getSimpleName() + " is invalid");
    }
}
