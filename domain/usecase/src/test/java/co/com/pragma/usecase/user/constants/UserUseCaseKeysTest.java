package co.com.pragma.usecase.user.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserUseCaseKeysTest {

    @Test
    public void testConstructor() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<UserUseCaseKeys> constructor = UserUseCaseKeys.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}
