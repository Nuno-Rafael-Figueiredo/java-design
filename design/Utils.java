package design;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Nuno
 * Date: 04-08-2013
 * Time: 18:14
 */
public class Utils {
    public static <E> String join(List<E> data, String separator) {
        Iterator<E> iterator = data.iterator();
        StringBuilder stringBuilder = new StringBuilder();

        if (iterator.hasNext()) {
            stringBuilder.append(iterator.next());

            while (iterator.hasNext())
                stringBuilder.append(separator).append(iterator.next());
        }

        return stringBuilder.toString();
    }

    public static <E> String join(E[] data, String separator) {
        return join(Arrays.asList(data), separator);
    }

    public static final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy/MM/dd");

    private static final String CAMEL_CASE_SPLITTER_REGEX = "(?<!(^|[A-Z0-9]))(?=[A-Z0-9])|(?<!^)(?=[A-Z][a-z])";

    private static final Properties properties = new Properties();

    private static final Logger logger = Logger.getLogger(Utils.class
            .getSimpleName());

    static {
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (final FileNotFoundException ignored) {
        } catch (final IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public static String camelize(final String code, final boolean lowered) {
        final String[] splits = code.split("_");

        for (int i = lowered ? 1 : 0; i < splits.length; i++)
            splits[i] = capitalize(splits[i]);

        return join(splits, "");
    }

    public static String capitalize(final String string) {
        return string.toUpperCase().charAt(0)
                + string.toLowerCase().substring(1);
    }

    public static String humanize(final String camelCased) {
        return join(camelCased.split(CAMEL_CASE_SPLITTER_REGEX), " ");
    }

    public static String codify(final Object o) {
        if (o instanceof String)
            return join(((String)o).split(CAMEL_CASE_SPLITTER_REGEX), "_")
                .toLowerCase();

        if (o instanceof Class<?>)
            return codify(((Class)o).getSimpleName());

        return codify(o.getClass());
    }

    private static String get(final String key) {
        return properties.getProperty(humanToCode(key));
    }

    public static String get(final String key, final String defaultValue) {
        final String value = get(key);

        if (value != null)
            return value;

        set(key, defaultValue);

        return defaultValue;
    }

    public static boolean getBoolean(final String key, final Boolean value) {
        return Boolean.parseBoolean(get(key, value.toString()));
    }

    public static int getInt(final String key, final Integer value) {
        return Integer.parseInt(get(key, value.toString()));
    }

    private static String humanToCode(final String key) {
        return key.toUpperCase().replace(' ', '_');
    }

    public static <T> String join(final Collection<T> values, final String glue) {
        return join(values.toArray(), glue);
    }

    public static void set(final String key, final Object value) {
        properties.setProperty(humanToCode(key), value.toString());
        try {
            properties.store(new FileOutputStream("config.properties"), null);
        } catch (final IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    private Utils() {
    }

    public static String humanize(Object o) {
        if (o instanceof String)
            return humanize((String) o);

        if (!(o instanceof Class))
            return humanize(o.getClass());

        return humanize(((Class) o).getSimpleName());
    }
}