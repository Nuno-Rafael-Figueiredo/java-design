package design.patterns.factory;

public class FactoryException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 507172311343160082L;

    public final ReflectiveOperationException exception;

    public FactoryException(final ReflectiveOperationException e) {
        exception = e;
    }

}
