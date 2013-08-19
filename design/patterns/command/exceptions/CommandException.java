package design.patterns.command.exceptions;

import design.patterns.command.ICommand;

public class CommandException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3677404558939293447L;

    protected final ICommand<?> command;

    /**
     *
     */
    protected CommandException(final ICommand<?> command) {
        this.command = command;
    }

    /**
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CommandException(final ICommand<?> command, final String message) {
        super(message);
        this.command = command;
    }

    /**
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    protected CommandException(final ICommand<?> command, final String message,
                               final Throwable cause) {
        super(message, cause);
        this.command = command;
    }

    /**
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public CommandException(final ICommand<?> command, final Throwable cause) {
        super(cause);
        this.command = command;
    }

    public ICommand<?> getCommand() {
        return command;
    }
}
