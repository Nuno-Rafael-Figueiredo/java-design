package design.patterns.command;

import design.patterns.command.exceptions.CommandException;

public interface IValidator {

    void onSuccess();

    void onException(CommandException exception);
}
