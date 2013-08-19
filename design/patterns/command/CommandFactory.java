package design.patterns.command;

import design.patterns.factory.Factory;

import java.text.MessageFormat;

public class CommandFactory extends Factory<ICommand<?>> {
    private final String packageName;

    public CommandFactory() {
        this("");
    }

    public CommandFactory(final String packageName) {
        this.packageName = packageName;
    }

    @Override
    public ICommand<?> create(final String commandName) {
        return super.create(MessageFormat.format("{0}.{1}", packageName,
                commandName));
    }
}
