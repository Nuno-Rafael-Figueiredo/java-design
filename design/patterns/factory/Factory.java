package design.patterns.factory;

import design.Base;

public abstract class Factory<T> extends Base implements IFactory<T> {
    @SuppressWarnings("unchecked")
    @Override
    public T create(final String expression) {
        try {
            return (T) Class.forName(expression).newInstance();
        } catch (final ClassNotFoundException | InstantiationException
                | IllegalAccessException e) {
            severe(new FactoryException(e));
        }

        return null;
    }
}
