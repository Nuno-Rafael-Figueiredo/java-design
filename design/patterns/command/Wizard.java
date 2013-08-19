package design.patterns.command;

import design.patterns.command.exceptions.CommandException;
import design.patterns.command.states.Finished;
import design.patterns.state.StateChanged;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Wizard<TResult> extends Command<TResult> implements Observer {

    /**
     *
     */
    private static final long serialVersionUID = 4719271959020020388L;

    private final List<Command<?>> steps = new ArrayList<>();
    private design.patterns.command.states.Error error;
    private Finished finished;

    @Override
    protected void protectedExecute() {
        invokeStep();
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (arg instanceof StateChanged) {
            final StateChanged stateChanged = (StateChanged) arg;

            if (stateChanged.newState == error)
                setState(error);
            else if (stateChanged.newState == finished)
                invokeStep();
        }
    }

    private void invokeStep() {
        if (steps.size() == 0) {
            setState(finished);
            return;
        }

        final Command<?> step = steps.remove(0);

        step.addObserver(this);

        step.invoke();
    }
}
