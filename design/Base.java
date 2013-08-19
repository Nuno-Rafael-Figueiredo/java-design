package design;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Base extends Observable {

    protected final String name;

    protected Base() {
        this(NamingStrategy.asIs);
    }

    protected Base(NamingStrategy nameCreationType) {
        switch (nameCreationType) {
            case uppercase:
                name = getClass().getSimpleName().toUpperCase();
                break;
            case humanize:
                name = design.Utils.humanize(this);
                break;
            default:
                name = getClass().getSimpleName();
        }
    }

    private static void finest(final Logger logger, final String pattern,
                               final Object[] arguments) {
        logger.log(Level.FINEST, pattern, arguments);
    }

    public static void info(final Logger logger, final String pattern,
                            final Object... arguments) {
        logger.log(Level.INFO, pattern, arguments);
    }

    public static void severe(final Logger logger, final Throwable thrown) {
        severe(logger, thrown, null);
    }

    public static void severe(final Logger logger, final Throwable thrown,
                              final String pattern, final Object... arguments) {
        logger.log(
                Level.SEVERE,
                pattern == null ? null : MessageFormat.format(pattern,
                        arguments), thrown);
    }

    public static void warning(final Logger logger, final String message,
                               final Object... parameters) {
        logger.log(Level.WARNING, message, parameters);
    }

    private transient final Logger logger = Logger.getLogger(getClass()
            .getSimpleName());

    protected void finest(final String pattern, final Object... arguments) {
        finest(logger, pattern, arguments);
    }

    protected void info(final String pattern, final Object... arguments) {
        info(logger, pattern, arguments);
    }

    protected void severe(final Throwable thrown) {
        severe(logger, thrown, null);
    }

    protected void warning(final String message, final Object... parameters) {
        warning(logger, message, parameters);
    }

    public String toString() {
        return name;
    }
}
