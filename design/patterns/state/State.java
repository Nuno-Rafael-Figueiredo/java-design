package design.patterns.state;

import design.Base;
import design.NamingStrategy;

public class State<E extends Context> extends Base implements IState<E> {

    public State() {
        super(NamingStrategy.humanize);
    }
}
