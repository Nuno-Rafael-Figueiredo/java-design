package design.patterns.command;

import design.NamingStrategy;
import design.patterns.command.exceptions.CommandException;
import design.patterns.command.exceptions.NullArgumentException;
import design.patterns.command.states.Created;
import design.patterns.command.states.Error;
import design.patterns.command.states.Finished;
import design.patterns.command.states.Started;
import design.patterns.state.Context;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public abstract class Command<T> extends Context implements ICommand<T> {

    private static final long serialVersionUID = -591400853365568418L;

    protected final String[] mandatory;
    protected final String[] optional;
    protected final transient Map<String, Object> arguments = new LinkedHashMap<>();
    public static final Created created = new Created();
    public static final Started started = new Started();
    public static final Error error = new Error();
    public static final Finished finished = new Finished();

    protected Command(final String... mandatory) {
        this(mandatory, new String[]{});
    }

    protected Command(final String[] mandatory, final String... optional) {
        this(NamingStrategy.asIs, mandatory, optional);
    }

    public Command(NamingStrategy namingStrategy, String[] mandatory, String... optional) {
        super(namingStrategy);
        this.mandatory = mandatory;
        this.optional = optional;
        setState(created);
    }

    public Command(NamingStrategy namingStrategy, String... mandatory) {
        this(namingStrategy, mandatory, new String[]{});
    }

    protected void addArgument(final String key, final Object value) {
        arguments.put(key, value);
    }

    @Override
    public final void execute() throws CommandException {
        setState(started);
        populateAttributes();

        for (final String arg : mandatory)
            if (!arguments.containsKey(arg) || arguments.get(arg) == null)
                throw new NullArgumentException(this, arg);

        validate(new IValidator() {

            @Override
            public void onSuccess() {
                protectedExecute();
                setState(finished);
            }

            @Override
            public void onException(final CommandException exception) {
                severe(exception);
                setState(error);
            }
        });
    }

    @Override
    public void invoke() {
        Invoker.getInstance().invoke(this);
    }

    protected void populateAttributes() {
    }

    protected abstract void protectedExecute();

    @Override
    public void setParameterMap(final Map<String, String[]> map) {
        final List<String> list = asList(mandatory);

        list.addAll(asList(optional));

        warning("{0}.setParameterMap - mandatory: {1}", getClass()
                .getSimpleName(), list.size());

        for (final String key : list)
            if (map.containsKey(key) && map.get(key).length == 1) {
                addArgument(key, map.get(key)[0]);
                warning("Argumento `{0}` = `{1}`", key, map.get(key)[0]);
            }
    }

    @Override
    public void validate(final IValidator validator) {
        validator.onSuccess();
    }
}
