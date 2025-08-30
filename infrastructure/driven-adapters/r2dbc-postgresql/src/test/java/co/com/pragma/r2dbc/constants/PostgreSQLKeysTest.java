package co.com.pragma.r2dbc.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostgreSQLKeysTest {

    @Test
    public void testConstructor() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<PostgreSQLKeys> constructor = PostgreSQLKeys.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}
