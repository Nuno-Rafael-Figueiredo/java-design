package design.patterns.factory;

public interface IFactory<T> {
    T create(final String expression);
}
