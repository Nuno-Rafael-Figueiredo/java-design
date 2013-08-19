package design.patterns.command;

import design.patterns.command.exceptions.CommandException;

import java.io.Serializable;
import java.util.Map;

public interface ICommand<TResult> extends Serializable {

    void execute() throws CommandException;

    void invoke();

    void validate(IValidator validator);

    void setParameterMap(Map<String, String[]> parameterMap);
}
