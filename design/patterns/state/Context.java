package design.patterns.state;

import design.Base;
import design.NamingStrategy;

/**
 * User: Nuno
 * Date: 05-08-2013
 * Time: 4:04
 */
public class Context extends Base implements IContext {
    protected IState state;

    public Context() {
    }

    public Context(NamingStrategy namingStrategy) {
        super(namingStrategy);
    }

    public void setState(final IState state) {
        this.state = state;
        setChanged();
        notifyObservers(state);
    }
}
