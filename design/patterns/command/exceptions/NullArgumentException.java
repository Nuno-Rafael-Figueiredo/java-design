package design.patterns.command.exceptions;

import design.patterns.command.ICommand;

import java.text.MessageFormat;

public class NullArgumentException extends CommandException {

    /**
     *
     */
    private static final long serialVersionUID = 3213155484897272308L;

    public NullArgumentException(final ICommand<?> command, final String arg) {
        super(command, MessageFormat.format(
                "O comando ''{0}'' exige o argumento ''{1}''", command, arg));
    }

}
